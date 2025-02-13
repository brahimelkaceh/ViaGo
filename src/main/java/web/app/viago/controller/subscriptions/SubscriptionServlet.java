package web.app.viago.controller.subscriptions;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import web.app.viago.model.Company;
import web.app.viago.model.Shuttle;
import web.app.viago.model.Subscription;
import web.app.viago.model.User;
import web.app.viago.services.CompanyService;
import web.app.viago.services.ShuttleService;
import web.app.viago.services.SubscriptionService;
import web.app.viago.services.UserService;

import java.io.IOException;
import java.util.*;

@WebServlet(name = "subscriptionServlet", urlPatterns = "/subscriptions")
public class SubscriptionServlet extends HttpServlet {

    private final ShuttleService shuttleService = new ShuttleService();
    private final SubscriptionService subscriptionService = new SubscriptionService();
    private final UserService userService = new UserService();
    private final CompanyService companyService = new CompanyService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        User loggedInUser = (User) session.getAttribute("user");

        if (loggedInUser == null) {
            response.sendRedirect("vues/login.jsp");
            return;
        }

        String action = Optional.ofNullable(request.getParameter("action")).orElse("list");

        switch (action) {
            case "canceled" -> handleSubscriptionCancellation(request, response, loggedInUser);
            case "subscribed" -> handleSubscription(request, response);
            case "search" -> handleSearch(request, response, loggedInUser);
            default -> response.sendRedirect("/subscriptions?action=list");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User loggedInUser = (User) session.getAttribute("user");

        if (loggedInUser == null) {
            response.sendRedirect("vues/login.jsp");
            return;
        }
        loadShuttleData(request, response, loggedInUser);
    }

    private void handleSubscriptionCancellation(HttpServletRequest request, HttpServletResponse response, User loggedInUser) throws IOException {
        try {
            int shuttleId = Integer.parseInt(request.getParameter("id"));
            int subscriptionId = Integer.parseInt(request.getParameter("subscriptionId"));

            Subscription existSubscription = subscriptionService.getSubscriptionByUserIdAndShuttleId(loggedInUser.getId(), shuttleId);

            if (existSubscription != null) {
                Subscription subscriptionUpdate = new Subscription(subscriptionId, "subscribed");
                subscriptionService.updateSubscription(subscriptionUpdate);
            } else {
                Subscription newSubscription = new Subscription(shuttleId, loggedInUser.getId(), "subscribed", new Date());
                subscriptionService.createSubscription(newSubscription);
            }
            response.sendRedirect("/subscriptions?action=list");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleSubscription(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int shuttleId = Integer.parseInt(request.getParameter("id"));
            int subscriptionId = Integer.parseInt(request.getParameter("subscriptionId"));

            shuttleService.updateShuttle(new Shuttle(shuttleId));
            subscriptionService.updateSubscription(new Subscription(subscriptionId, "canceled"));

            response.sendRedirect("/subscriptions?action=list");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleSearch(HttpServletRequest request, HttpServletResponse response, User loggedInUser)
            throws ServletException, IOException {
        try {
            String departureCity = request.getParameter("departureCity");
            String arrivalCity = request.getParameter("arrivalCity");

            // Validate input parameters
            if (departureCity == null || departureCity.trim().isEmpty() ||
                    arrivalCity == null || arrivalCity.trim().isEmpty()) {
                request.setAttribute("error", "Both departure and arrival cities are required.");
                request.getRequestDispatcher("/subscriptions/list.jsp").forward(request, response);
                return; // Stop execution if input is invalid
            }

            // Fetch shuttles and subscriptions
            List<Shuttle> shuttles = shuttleService.search(departureCity.trim(), arrivalCity.trim());
            List<Subscription> subscriptions = subscriptionService.getSubscriptionsByUserId(loggedInUser.getId());

            // Enrich shuttle data with subscription info
            enrichShuttleData(shuttles, subscriptions);

            // Set attributes for the frontend
            request.setAttribute("shuttles", shuttles);
            request.setAttribute("result", "Search Results: " + departureCity + ", " + arrivalCity);

            // Forward to results page
            request.getRequestDispatcher("/subscriptions/list.jsp").forward(request, response);
        } catch (Exception e) {
            // Log the exception (Best Practice)
            e.printStackTrace(); // Consider using a logger instead of printing stack trace
            request.setAttribute("error", "Unable to fetch shuttles. Please try again later.");
            request.getRequestDispatcher("/vues/dashboard.jsp").forward(request, response);
        }
    }

    private void loadShuttleData(HttpServletRequest request, HttpServletResponse response, User loggedInUser) throws ServletException, IOException {
        try {
            List<Shuttle> shuttles = shuttleService.getAllShuttles();
            List<Subscription> subscriptions = subscriptionService.getSubscriptionsByUserId(loggedInUser.getId());

            enrichShuttleData(shuttles, subscriptions);
            request.setAttribute("shuttles", shuttles);
            request.getRequestDispatcher("/subscriptions/list.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Unable to fetch shuttles. Please try again later. " + e.getMessage());
            request.getRequestDispatcher("/vues/dashboard.jsp").forward(request, response);
        }
    }

    private void enrichShuttleData(List<Shuttle> shuttles, List<Subscription> subscriptions) {
        Map<Integer, Subscription> subscriptionMap = new HashMap<>();
        for (Subscription subscription : subscriptions) {
            subscriptionMap.put(subscription.getShuttleId(), subscription);
        }

        for (Shuttle shuttle : shuttles) {
            Subscription subscription = subscriptionMap.get(shuttle.getId());
            Company owner = companyService.getCompanyById(shuttle.getUserId());

            if (subscription != null) {
                shuttle.setStatus(subscription.getStatus());
                shuttle.setIsSubscribed(true);
                shuttle.setSubscriptionId(subscription.getId());
            } else {
                shuttle.setStatus("canceled");
                shuttle.setIsSubscribed(false);
            }
            shuttle.setShuttleOwner(owner != null ? owner.getName() : "Unknown");
        }
    }
}

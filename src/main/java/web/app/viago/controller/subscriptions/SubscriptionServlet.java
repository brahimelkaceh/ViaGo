package web.app.viago.controller.subscriptions;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import web.app.viago.model.Shuttle;
import web.app.viago.model.Subscription;
import web.app.viago.model.User;
import web.app.viago.services.ShuttleService;
import web.app.viago.services.SubscriptionService;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "subscriptionServlet", urlPatterns = "/subscriptions")
public class SubscriptionServlet extends HttpServlet {
    private ShuttleService shuttleService;
    private SubscriptionService subscriptionService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.subscriptionService = new SubscriptionService();
        this.shuttleService = new ShuttleService(); // Initialize the service

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        User loggedInUser = (User) session.getAttribute("user");

        if (loggedInUser == null) {
            System.out.println("Error: No logged-in user found.");
            response.sendRedirect("/login.jsp");
            return;
        }

        String action = request.getParameter("action");
        int shuttleId = Integer.parseInt(request.getParameter("id"));
        int userId = loggedInUser.getId();
        if ("subscribe".equals(action)) {
            try {
                String status = request.getParameter("status");
                Subscription subscription = new Subscription();
                subscription.setShuttleId(shuttleId);
                subscription.setUserId(userId);
                subscription.setStatus(status);
                subscription.setCreatedAt(new java.util.Date());
                subscriptionService.createSubscription(subscription);

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

            response.sendRedirect("/subscriptions/list.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        HttpSession session = request.getSession();
        User loggedInUser = (User) session.getAttribute("user");

        if (loggedInUser == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        try {
            // Fetch all shuttles and the user's subscriptions
            List<Shuttle> shuttles = shuttleService.getAllShuttles();
            List<Subscription> subscriptions = subscriptionService.getSubscriptionsByUserId(loggedInUser.getId());

            // Create a map to store subscriptions by shuttleId for fast lookup
            Map<Integer, String> subscriptionStatusMap = new HashMap<>();
            for (Subscription subscription : subscriptions) {
                subscriptionStatusMap.put(subscription.getShuttleId(), subscription.getStatus());
            }

            // Iterate over shuttles and set the status for each
            for (Shuttle shuttle : shuttles) {
                // Check if the shuttle has a subscription
                String status = subscriptionStatusMap.get(shuttle.getId());

                // If the user is subscribed to this shuttle, mark it
                if (status != null) {
                    shuttle.setStatus(status); // Assuming Shuttle has a setStatus method
                    shuttle.setIsSubscribed(true); // Mark this shuttle as subscribed
                } else {
                    shuttle.setStatus("Subscribe"); // Default status
                    shuttle.setIsSubscribed(false); // Mark this shuttle as not subscribed
                }
            }

            // Pass the updated shuttles to the JSP
            request.setAttribute("shuttles", shuttles);
            request.getRequestDispatcher("/subscriptions/list.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Unable to fetch shuttles. Please try again later.");
            request.getRequestDispatcher("/vues/dashboard.jsp").forward(request, response);
        }

    }
}

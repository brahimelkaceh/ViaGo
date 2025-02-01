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
import web.app.viago.services.UserService;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "subscriptionServlet", urlPatterns = "/subscriptions")
public class SubscriptionServlet extends HttpServlet {

    private final ShuttleService shuttleService;
    private final SubscriptionService subscriptionService;
    private final UserService userService;

    // ✅ Constructor Injection (Best Practice)
    public SubscriptionServlet() {
        this.shuttleService = new ShuttleService();
        this.subscriptionService = new SubscriptionService();
        this.userService = new UserService();  // Initialize properly
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        User loggedInUser = (User) session.getAttribute("user");

        if (loggedInUser == null) {
            System.out.println("Error: No logged-in user found.");
            response.sendRedirect("vues/login.jsp");
            return;
        }

        String action = request.getParameter("action");
        int shuttleId = Integer.parseInt(request.getParameter("id"));
        int userId = loggedInUser.getId();
        String status = request.getParameter("status");
        if ("canceled".equals(action)) {
            Subscription existSubscription = subscriptionService.getSubscriptionByUserIdAndShuttleId(userId, shuttleId);

            try {
                if (existSubscription != null) {
                    int subscriptionId = Integer.parseInt(request.getParameter("subscriptionId"));
                    Subscription subscriptionUpdate = new Subscription(subscriptionId, "subscribed");
                    subscriptionService.updateSubscription(subscriptionUpdate);
                } else {
                    System.out.println("Creating a new subscription...");
                    Subscription subscription = new Subscription();
                    subscription.setShuttleId(shuttleId);
                    subscription.setUserId(userId);
                    subscription.setStatus("subscribed");
                    subscription.setCreatedAt(new java.util.Date());
                    subscriptionService.createSubscription(subscription); // Create new subscription
                }

                response.sendRedirect("/subscriptions?action=list"); // Redirect to the user list
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else if ("subscribed".equals(action)) {
            int subscriptionId = Integer.parseInt(request.getParameter("subscriptionId"));
            Shuttle shuttleUpdate = new Shuttle(shuttleId);
            shuttleService.updateShuttle(shuttleUpdate);
            Subscription subscriptionUpdate = new Subscription(subscriptionId, "canceled");
            subscriptionService.updateSubscription(subscriptionUpdate);
            System.out.println(", id: " + shuttleId + ", status: " + status + ", id: " + subscriptionId);
            response.sendRedirect("/subscriptions?action=list");  // Redirect to the user list
        } else {
            response.sendRedirect("/subscriptions?action=list");  // Redirect to the user list
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

        try {
            // Fetch shuttles and subscriptions
            List<Shuttle> shuttles = shuttleService.getAllShuttles();
            List<Subscription> subscriptions = subscriptionService.getSubscriptionsByUserId(loggedInUser.getId());

            // Store subscription details for each shuttle
            Map<Integer, Map<String, Object>> subscriptionStatusMap = new HashMap<>();
            for (Subscription subscription : subscriptions) {
                Map<String, Object> details = new HashMap<>();
                details.put("subscriptionId", subscription.getId());
                details.put("status", subscription.getStatus());
                subscriptionStatusMap.put(subscription.getShuttleId(), details);
            }

            // Iterate over shuttles and get owner details
            for (Shuttle shuttle : shuttles) {
                Map<String, Object> subscriptionDetails = subscriptionStatusMap.get(shuttle.getId());

                // ✅ Ensure userService is initialized before calling it
                User owner = userService.getUserById(shuttle.getUserId());

                if (subscriptionDetails != null) {
                    String status = (String) subscriptionDetails.get("status");
                    int subscriptionId = (int) subscriptionDetails.get("subscriptionId");
                    shuttle.setStatus(status);
                    shuttle.setIsSubscribed(true);
                    shuttle.setSubscriptionId(subscriptionId);
                    shuttle.setShuttleOwner(String.valueOf(owner.getName()));  // 🔥 Add owner details to shuttle

                } else {
                    shuttle.setStatus("canceled");
                    shuttle.setIsSubscribed(false);
                }
            }
            System.out.println("shuttles : " + shuttles);
            request.setAttribute("shuttles", shuttles);
            request.getRequestDispatcher("/subscriptions/list.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Unable to fetch shuttles. Please try again later. " + e.getMessage());
            request.getRequestDispatcher("/vues/dashboard.jsp").forward(request, response);
        }
    }
}

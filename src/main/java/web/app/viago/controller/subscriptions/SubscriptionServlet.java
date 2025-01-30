package web.app.viago.controller.subscriptions;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import web.app.viago.model.Shuttle;
import web.app.viago.model.User;
import web.app.viago.services.ShuttleService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "subscriptionServlet", urlPatterns = "/subscriptions")
public class SubscriptionServlet extends HttpServlet {
    private ShuttleService shuttleService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.shuttleService = new ShuttleService(); // Initialize the service

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User loggedInUser = (User) session.getAttribute("user");

        String action = request.getParameter("action");
        int shuttleId = Integer.parseInt(request.getParameter("id"));
        int userId = loggedInUser.getId();

        if ("subscribe".equals(action)) {
            // Handle subscription logic here
            System.out.println("User subscribed to shuttle with ID: " + shuttleId + " and USERID: " + userId);

            // Redirect to confirmation or reload page
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
            List<Shuttle> shuttles = shuttleService.getAllShuttles();
            request.setAttribute("shuttles", shuttles);
            request.getRequestDispatcher("/subscriptions/list.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Unable to fetch shuttles. Please try again later.");
            request.getRequestDispatcher("/vues/dashboard.jsp").forward(request, response);
        }
    }
}

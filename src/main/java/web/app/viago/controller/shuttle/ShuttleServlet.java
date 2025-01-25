package web.app.viago.controller.shuttle;

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
import java.sql.Date;
import java.util.List;

@WebServlet(name = "ShuttleServlet", urlPatterns = {"/dashboard"})
public class ShuttleServlet extends HttpServlet {
    private ShuttleService shuttleService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.shuttleService = new ShuttleService(); // Initialize the service
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getServletPath());
        System.out.println("Get Method");

        HttpSession session = request.getSession();
        User loggedInUser = (User) session.getAttribute("user");

        if (loggedInUser == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            // Fetch all shuttles
            List<Shuttle> shuttles = shuttleService.getAllShuttles();
            // Pass the shuttles to the JSP page
            request.setAttribute("shuttles", shuttles);
            request.getRequestDispatcher("/vues/dashboard.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Unable to fetch shuttles. Please try again later.");
            request.getRequestDispatcher("/vues/dashboard.jsp").forward(request, response);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User loggedInUser = (User) session.getAttribute("user");

        if (loggedInUser == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Retrieve shuttle data from the form
        String departureCity = request.getParameter("departureCity");
        String arrivalCity = request.getParameter("arrivalCity");
        Date startDate = Date.valueOf(request.getParameter("startDate"));
        Date endDate = Date.valueOf(request.getParameter("endDate"));
        String departureTime = request.getParameter("departureTime");
        String arrivalTime = request.getParameter("arrivalTime");
        String busDescription = request.getParameter("busDescription");
        int maxSubscribers = Integer.parseInt(request.getParameter("maxSubscribers"));

        // Create a Shuttle object
        Shuttle shuttle = new Shuttle();
        shuttle.setDepartureCity(departureCity);
        shuttle.setArrivalCity(arrivalCity);
        shuttle.setStartDate(startDate);
        shuttle.setEndDate(endDate);
        shuttle.setDepartureTime(departureTime);
        shuttle.setArrivalTime(arrivalTime);
        shuttle.setBusDescription(busDescription);
        shuttle.setMaxSubscribers(maxSubscribers);
        shuttle.setCreatedAt(new java.util.Date());

        // Call the DAO to persist the shuttle
        shuttleService.createShuttle(shuttle, loggedInUser.getId());

        // Redirect to the shuttle list page after adding the shuttle
        response.sendRedirect("dashboard.jsp");
    }
}


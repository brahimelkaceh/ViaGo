package web.app.viago.controller.shuttle;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import web.app.viago.model.Company;
import web.app.viago.model.Shuttle;
import web.app.viago.model.User;
import web.app.viago.services.ShuttleService;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "ShuttleServlet", urlPatterns = "/shuttles")
public class ShuttleServlet extends HttpServlet {

    private ShuttleService shuttleService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.shuttleService = new ShuttleService(); // Initialize the service
    }


    // Handles GET requests
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();

        Company loggedInCompany = (Company) session.getAttribute("company");

        if (loggedInCompany == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        if (action != null) {
            switch (action) {

                case "view":
                    int shuttleId = Integer.parseInt(request.getParameter("id"));
                    Shuttle shuttle = shuttleService.getShuttleById(shuttleId);
                    if (shuttle != null) {
                        request.setAttribute("shuttle", shuttle);
                        request.getRequestDispatcher("/shuttles/view.jsp").forward(request, response);
                    } else {
                        response.sendRedirect("/shuttles?action=list");
                    }
                    break;

                case "update":
                    int updateShuttleId = Integer.parseInt(request.getParameter("id"));
                    Shuttle updateShuttle = shuttleService.getShuttleById(updateShuttleId);  // Fetch the user to update
                    if (updateShuttle != null) {
                        System.out.println("selected Shuttle : " + updateShuttle);
                        request.setAttribute("updateShuttle", updateShuttle);  // Set user as request attribute
                        request.getRequestDispatcher("/shuttles/form.jsp").forward(request, response);  // Forward to form.jsp for update
                    } else {
                        response.sendRedirect("/shuttles?action=list");  // Redirect to list if user not found
                    }
                    break;

                case "create":
                    request.getRequestDispatcher("/shuttles/form.jsp").forward(request, response);  // Forward to empty form for new user
                    break;

                default:
                    List<Shuttle> defaultUsers = shuttleService.getAllShuttles();
                    request.setAttribute("shuttles", defaultUsers);
                    request.getRequestDispatcher("/shuttles/list.jsp").forward(request, response);
                    break;
            }
        } else {
            List<Shuttle> shuttles = shuttleService.getAllShuttles();
            request.setAttribute("shuttles", shuttles);
            request.getRequestDispatcher("/shuttles/list.jsp").forward(request, response);
        }
    }

    // Handles POST requests (e.g., Create or Update)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        Company loggedInCompany = (Company) session.getAttribute("company");
        if (loggedInCompany == null) {
            response.sendRedirect("login.jsp");
        }
        if (action != null) {
            switch (action) {
                case "create":
                    try {
                        String departureCity = request.getParameter("departureCity");
                        String arrivalCity = request.getParameter("arrivalCity");
                        Date startDate = Date.valueOf(request.getParameter("startDate"));
                        Date endDate = Date.valueOf(request.getParameter("endDate"));
                        String departureTime = request.getParameter("departureTime");
                        String arrivalTime = request.getParameter("arrivalTime");
                        String busDescription = request.getParameter("busDescription");
                        int maxSubscribers = Integer.parseInt(request.getParameter("maxSubscribers"));
                        //            // Create a Shuttle object
                        Shuttle shuttle = new Shuttle();
                        shuttle.setDepartureCity(departureCity);
                        assert loggedInCompany != null;
                        shuttle.setShuttleOwner(loggedInCompany.getName());
                        shuttle.setArrivalCity(arrivalCity);
                        shuttle.setStartDate(startDate);
                        shuttle.setEndDate(endDate);
                        shuttle.setDepartureTime(departureTime);
                        shuttle.setArrivalTime(arrivalTime);
                        shuttle.setBusDescription(busDescription);
                        shuttle.setMaxSubscribers(maxSubscribers);
                        shuttle.setNumSubscribers(0);
                        shuttle.setCreatedAt(new java.util.Date());

                        // Call the DAO to persist the shuttle
                        shuttleService.createShuttle(shuttle, loggedInCompany.getId());

                        // Redirect to the shuttle list page after adding the shuttle
                        response.sendRedirect("/shuttles?action=list");
                    } catch (Exception e) {
                        request.setAttribute("error", "Error creating user: " + e.getMessage());
                        request.getRequestDispatcher("/shuttles/error.jsp").forward(request, response);

                    }
                    break;
                case "update":
                    try {
                        int shuttleId = Integer.parseInt(request.getParameter("id"));
                        String departureCity = request.getParameter("departureCity");
                        String arrivalCity = request.getParameter("arrivalCity");
                        Date startDate = Date.valueOf(request.getParameter("startDate"));
                        Date endDate = Date.valueOf(request.getParameter("endDate"));
                        String departureTime = request.getParameter("departureTime");
                        String arrivalTime = request.getParameter("arrivalTime");
                        String busDescription = request.getParameter("busDescription");
                        int maxSubscribers = Integer.parseInt(request.getParameter("maxSubscribers"));
                        //            // Create a Shuttle object
                        Shuttle shuttle = new Shuttle();
                        shuttle.setId(shuttleId);
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
                        assert loggedInCompany != null;
                        shuttleService.updateShuttle(shuttle);

                        // Redirect to the shuttle list page after adding the shuttle
                        response.sendRedirect("/shuttles?action=list");
                    } catch (Exception e) {
                        request.setAttribute("error", "Error creating user: " + e.getMessage());
                        request.getRequestDispatcher("/shuttles/error.jsp").forward(request, response);

                    }
                    break;
                case "delete":
                    try {
                        int deleteId = Integer.parseInt(request.getParameter("id"));
                        shuttleService.deleteShuttle(deleteId);  // Delete the user
                        response.sendRedirect("/shuttles?action=list");  // Redirect to the user list
                    } catch (Exception e) {
                        request.setAttribute("error", "Error deleting user: " + e.getMessage());
                        request.getRequestDispatcher("/shuttles/error.jsp").forward(request, response);
                    }
                    break;

                default:
                    response.sendRedirect("/shuttles?action=list");  // Default action is to redirect to the user list
                    break;

            }
        }
    }
}


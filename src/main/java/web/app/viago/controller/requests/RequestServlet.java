package web.app.viago.controller.requests;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import web.app.viago.model.Company;
import web.app.viago.model.Request;
import web.app.viago.model.Shuttle;
import web.app.viago.model.User;
import web.app.viago.services.CompanyService;
import web.app.viago.services.RequestService;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet(name = "RequestServlet", urlPatterns = "/requests")
public class RequestServlet extends HttpServlet {

    private CompanyService companyService;
    private RequestService requestService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.companyService = new CompanyService();
        this.requestService = new RequestService();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null && session.getAttribute("company") == null) {
            response.sendRedirect("vues/login.jsp");
            return;
        }

        User loggedInUser = (User) session.getAttribute("user");
        Company company = (Company) session.getAttribute("company");


        if (action != null) {
            switch (action) {
                case "update":
                    int requestId = Integer.parseInt(request.getParameter("id"));
                    Request updateRequest = requestService.getRequestById(requestId);  // Fetch the user to update
                    if (updateRequest != null) {
                        List<Company> companies = companyService.getAllCompanies();
                        if (companies == null || companies.isEmpty()) {
                            companies = List.of();  // Avoid NullPointerException
                        }

                        request.setAttribute("companies", companies);
                        request.setAttribute("updateRequest", updateRequest);  // Set user as request attribute
                        request.getRequestDispatcher("/requests/form.jsp").forward(request, response);  // Forward to form.jsp for update
                    } else {
                        response.sendRedirect("/requests?action=list");  // Redirect to list if user not found
                    }
                    break;

                case "create":  // Fetch company data
                    List<Company> companies = companyService.getAllCompanies();
                    if (companies == null || companies.isEmpty()) {
                        companies = List.of();  // Avoid NullPointerException
                    }

                    request.setAttribute("companies", companies);
                    request.getRequestDispatcher("/requests/form.jsp").forward(request, response);  // Forward to empty form for new user
                    break;
                default:
                    if (company != null) {
                        int companyId = company.getId();
                        List<Request> requests = requestService.getRequestsByCompany(companyId);
                        request.setAttribute("requests", requests);
                        request.getRequestDispatcher("/requests/company-requests.jsp").forward(request, response);

                    } else {
                        int userId = loggedInUser.getId();
                        List<Request> requests = requestService.getAllRequests(userId);
                        request.setAttribute("requests", requests);
                        request.getRequestDispatcher("/requests/list.jsp").forward(request, response);
                    }
                    break;
            }
        } else {
            int userId = loggedInUser.getId();
            List<Request> requests = requestService.getAllRequests(userId);
            request.setAttribute("requests", requests);
            request.getRequestDispatcher("/requests/list.jsp").forward(request, response);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();

        Company company = (Company) session.getAttribute("company");
        User userId = (User) session.getAttribute("user");

        if (session == null || userId == null && company == null) {
            response.sendRedirect("vues/login.jsp");
            return;
        }


        if (action != null) {
            String departureCity = request.getParameter("departure_city");
            String arrivalCity = request.getParameter("arrival_city");
            List<Request> existRequest = requestService.getExistRequests(departureCity, arrivalCity);
            if (existRequest.size() >= 1) {
                request.getSession().setAttribute("error", "The request already exists");
                response.sendRedirect("/requests?action=create");
                return;
            }
            switch (action) {
                case "statusChanged":
                    try {
                        int id = Integer.parseInt(request.getParameter("id"));
                        String status = request.getParameter("status");

                        Request updatedRequestStatus = new Request();
                        updatedRequestStatus.setId(id);
                        updatedRequestStatus.setStatus(status);
                        // Call the DAO to persist the shuttle
                        assert userId != null;
                        requestService.updateRequestStatus(updatedRequestStatus);

                        response.sendRedirect("/requests?action=list");
                    } catch (Exception e) {
                        System.out.println("There is an error in updating request status" + e.getMessage());
                        response.sendRedirect("/requests?action=list");

                    }

                    break;
                case "create":
                    try {
                        Date startDate = Date.valueOf(request.getParameter("departure_start_date"));
                        Date endDate = Date.valueOf(request.getParameter("arrival_end_date"));
                        int maxSubscribers = Integer.parseInt(request.getParameter("subscribers_count"));
                        int companyId = Integer.parseInt(request.getParameter("companyId"));
                        Request newRequest = new Request();
                        newRequest.setDeparture_city(departureCity);
                        newRequest.setArrival_city(arrivalCity);
                        newRequest.setDeparture_start_date(startDate);
                        newRequest.setArrival_end_date(endDate);
                        newRequest.setSubscribers_count(maxSubscribers);
                        newRequest.setCompany_id(companyId);
                        assert userId != null;
                        newRequest.setUser_id(userId.getId());
                        newRequest.setCreatedAt(new java.util.Date());
                        newRequest.setStatus("pending");

                        requestService.createRequest(newRequest);
                        response.sendRedirect("/requests?action=list");


                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Create Action from requests servlet (POST): " + action);
                    break;
                case "delete":
                    try {
                        int requestId = Integer.parseInt(request.getParameter("id"));
                        requestService.deleteRequest(requestId);  // Delete the user
                        response.sendRedirect("/requests?action=list");  // Redirect to the user list
                    } catch (Exception e) {
                        request.setAttribute("error", "Error deleting user: " + e.getMessage());
                        request.getRequestDispatcher("/requests/error.jsp").forward(request, response);
                    }
                    break;
                case "update":
                    try {
                        int RequestId = Integer.parseInt(request.getParameter("id"));
                        Date startDate = Date.valueOf(request.getParameter("departure_start_date"));
                        Date endDate = Date.valueOf(request.getParameter("arrival_end_date"));
                        int maxSubscribers = Integer.parseInt(request.getParameter("subscribers_count"));
                        int companyId = Integer.parseInt(request.getParameter("companyId"));
                        //            // Create a Shuttle object
                        Request updatedRequest = new Request();
                        updatedRequest.setId(RequestId);
                        updatedRequest.setDeparture_city(departureCity);
                        updatedRequest.setArrival_city(arrivalCity);
                        updatedRequest.setDeparture_start_date(startDate);
                        updatedRequest.setArrival_end_date(endDate);
                        updatedRequest.setSubscribers_count(maxSubscribers);
                        updatedRequest.setCompany_id(companyId);
                        updatedRequest.setCreatedAt(new java.util.Date());

                        // Call the DAO to persist the shuttle
                        assert userId != null;
                        requestService.updateRequest(updatedRequest);

                        // Redirect to the shuttle list page after adding the shuttle
                        response.sendRedirect("/requests?action=list");
                    } catch (Exception e) {
                        request.setAttribute("error", "Error creating user: " + e.getMessage());
                        request.getRequestDispatcher("/requests?action=create").forward(request, response);

                    }
                    break;
                default:
                    response.sendRedirect("/requests?action=list");  // Default action is to redirect to the user list
                    break;
            }
        }

    }

}
    
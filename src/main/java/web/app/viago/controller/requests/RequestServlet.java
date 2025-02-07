package web.app.viago.controller.requests;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import web.app.viago.model.Company;
import web.app.viago.model.User;
import web.app.viago.services.CompanyService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "RequestServlet", urlPatterns = "/requests")
public class RequestServlet extends HttpServlet {

    private CompanyService companyService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.companyService = new CompanyService();  // Ensure this works properly
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("Action from requests servlet (POST): " + action);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("🚀 doGet() method is called in RequestServlet!");

        String action = request.getParameter("action");
        System.out.println("🔍 Action from request: " + action);

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            System.out.println("⚠️ No active session, redirecting to login page.");
            response.sendRedirect("vues/login.jsp");
            return;
        }

        User loggedInUser = (User) session.getAttribute("user");
        System.out.println("👤 Logged-in user: " + loggedInUser);

        // Fetch company data
        List<Company> companies = companyService.getAllCompanies();
        if (companies == null || companies.isEmpty()) {
            System.out.println("⚠️ No companies found.");
            companies = List.of();  // Avoid NullPointerException
        }

        System.out.println("🏢 Companies retrieved: " + companies);
        request.setAttribute("companies", companies);

        request.getRequestDispatcher("/requests/form.jsp").forward(request, response);
        System.out.println("✅ Forwarded to form.jsp");
    }
    
}
    
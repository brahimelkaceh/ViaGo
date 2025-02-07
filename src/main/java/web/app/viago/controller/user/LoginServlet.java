package web.app.viago.controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import web.app.viago.dao.users.UserDAO;
import web.app.viago.dao.users.UserDaoImpl;
import web.app.viago.model.Company;
import web.app.viago.model.User;
import web.app.viago.services.CompanyService;
import web.app.viago.services.UserService;


import java.io.IOException;
import java.util.Objects;

@WebServlet("/vues/LoginServlet")
public class LoginServlet extends HttpServlet {

    private UserDAO userDao;
    private CompanyService companyService;

    @Override
    public void init() {
        userDao = new UserDaoImpl();
        this.companyService = new CompanyService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Check if the email exists in users table
        User user = userDao.findByEmail(email);

        // Check if the email exists in company table
        Company company = companyService.getCompanyByEmail(email);

        if (user != null && isValidPassword(password, user.getPassword())) {
            // Create session for user
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            // Redirect regular users
            response.sendRedirect("/subscriptions");
            return;
        }

        if (company != null && isValidPassword(password, company.getPassword())) {
            // Create session for company
            HttpSession session = request.getSession();
            session.setAttribute("company", company);
            System.out.println("Company found: " + company);

            // Redirect company users
            response.sendRedirect("/dashboard");
            return;

        }

        // Invalid credentials, show error message
        request.setAttribute("error", "Invalid email or password.");
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    // Method to validate password (upgrade this with hashing if needed)
    private boolean isValidPassword(String inputPassword, String storedPassword) {
        return Objects.equals(inputPassword, storedPassword);
    }
}

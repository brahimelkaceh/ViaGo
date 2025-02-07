package web.app.viago.controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import web.app.viago.dao.users.UserDAO;
import web.app.viago.dao.users.UserDaoImpl;
import web.app.viago.model.Company;
import web.app.viago.model.User;
import web.app.viago.services.CompanyService;
import web.app.viago.services.UserService;

import java.io.IOException;

@WebServlet("/vues/RegisterServlet")  // Ensure correct servlet mapping
public class RegisterServlet extends HttpServlet {

    private UserDAO userDao;
    private final CompanyService companyService;
    private final UserService userService;

    public RegisterServlet() {
        this.companyService = new CompanyService(); // Fixed capitalization issue
        this.userService = new UserService();
    }

    @Override
    public void init() {
        userDao = new UserDaoImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String role = request.getParameter("role");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        // Validate passwords match
        if (!password.equals(confirmPassword)) {
            request.setAttribute("errorMessage", "Passwords do not match.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // Check if email is already in use
        if (userDao.findByEmail(email) != null) {
            request.setAttribute("errorMessage", "Email already exists.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // Handle role-based registration
        if ("user".equalsIgnoreCase(role)) {
            registerUser(name, email, password);
        } else if ("company".equalsIgnoreCase(role)) {
            registerCompany(name, email, password);
        } else {
            request.setAttribute("errorMessage", "Invalid role selected.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // Redirect to login after successful registration
        response.sendRedirect("login.jsp");
    }

    private void registerUser(String name, String email, String password) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setRole("user");
        user.setPassword(password); // Hash password before saving in production
        user.setPhoneNumber("************");
        boolean isCreated = userService.createUser(user);
        if (isCreated) {
            System.out.println("user created successfully.");
        } else {
            System.out.println("Failed to create user.");
        }
    }

    private void registerCompany(String name, String email, String password) {
        Company company = new Company();
        company.setName(name);
        company.setEmail(email);
        company.setPassword(password); // Hash password before saving in production
        company.setPhoneNumber("************");
        boolean isCreated = companyService.createCompany(company);

        if (isCreated) {
            System.out.println("Company created successfully.");
        } else {
            System.out.println("Failed to create company.");
        }
    }
}

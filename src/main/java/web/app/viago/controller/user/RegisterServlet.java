package web.app.viago.controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import web.app.viago.dao.users.UserDAO;
import web.app.viago.dao.users.UserDaoImpl;
import web.app.viago.model.User;


import java.io.IOException;

@WebServlet("/vues/RegisterServlet")  // Correct the mapping here if needed
public class RegisterServlet extends HttpServlet {

    private UserDAO userDao;

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

        // Check if passwords match
        if (!password.equals(confirmPassword)) {
            request.setAttribute("errorMessage", "Passwords do not match.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // Check if email already exists
        User existingUser = userDao.findByEmail(email);
        if (existingUser != null) {
            request.setAttribute("errorMessage", "Email already exists.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // Create new user
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setRole(role);
        user.setPassword(password); // Store hashed password in production
        user.setPhoneNumber("************");
        userDao.create(user);


        // Redirect to login page after successful registration
        response.sendRedirect("login.jsp");
    }
}

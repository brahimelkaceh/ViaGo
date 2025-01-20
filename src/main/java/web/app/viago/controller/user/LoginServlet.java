package web.app.viago.controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import web.app.viago.dao.users.UserDAO;
import web.app.viago.dao.users.UserDaoImpl;
import web.app.viago.model.User;


import java.io.IOException;

@WebServlet("/vues/LoginServlet")
public class LoginServlet extends HttpServlet {

    private UserDAO userDao;

    @Override
    public void init() {
        userDao = new UserDaoImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Validate user credentials
        User user = userDao.findByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            // Create session
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            System.out.println("user logged in" + user);

            // Check user role and redirect accordingly
            if ("company".equals(user.getRole())) {
                // Redirect to the dashboard for company users
                response.sendRedirect("dashboard/index.jsp");
            } else {
                // Redirect to subscriptions page for other roles
                response.sendRedirect("subscriptions.jsp");
            }
        } else {
            // Invalid credentials, show error message
            request.setAttribute("errorMessage", "Invalid email or password.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}

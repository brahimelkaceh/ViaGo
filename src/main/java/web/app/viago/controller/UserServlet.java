package web.app.viago.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import web.app.viago.model.User;
import web.app.viago.services.UserService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "userServlet", urlPatterns = "/users")
public class UserServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.userService = new UserService(); // Initialize the service
    }

    // Handles GET requests
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        action = "list";

        System.out.println("action: " + action);


        if (action != null) {
            switch (action) {
                case "list":
                    List<User> users = userService.getAllUsers();
                    System.out.println("user list: " + users);
                    request.setAttribute("users", users);
                    request.getRequestDispatcher("/users/list.jsp").forward(request, response);
                    break;

                case "view":
                    int userId = Integer.parseInt(request.getParameter("id"));
                    User user = userService.getUserById(userId);
                    if (user != null) {
                        request.setAttribute("user", user);
                        request.getRequestDispatcher("/users/view.jsp").forward(request, response);
                    } else {
                        response.sendRedirect("/users?action=list");
                    }
                    break;

                default:
                    List<User> defaultUsers = userService.getAllUsers();
                    request.setAttribute("users", defaultUsers);
                    request.getRequestDispatcher("/users/list.jsp").forward(request, response);
                    break;
            }
        } else {
            List<User> defaultUsers = userService.getAllUsers();
            request.setAttribute("users", defaultUsers);
            request.getRequestDispatcher("/users/list.jsp").forward(request, response);
        }
    }

    // Handles POST requests (e.g., Create or Update)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "create":
                    try {
                        String name = request.getParameter("name");
                        String email = request.getParameter("email");
                        String password = request.getParameter("password"); // Ensure this is hashed before saving
                        String role = request.getParameter("role");
                        String phoneNumber = request.getParameter("phone_number");

                        User newUser = new User(name, email, password, role, phoneNumber);
                        userService.createUser(newUser);
                        response.sendRedirect("/users?action=list");
                    } catch (Exception e) {
                        request.setAttribute("error", "Error creating user: " + e.getMessage());
                        request.getRequestDispatcher("/users/error.jsp").forward(request, response);
                    }
                    break;

                case "update":
                    try {
                        int userId = Integer.parseInt(request.getParameter("id"));
                        String updatedName = request.getParameter("name");
                        String updatedEmail = request.getParameter("email");
                        String updatedPassword = request.getParameter("password"); // Hash before saving
                        String updatedRole = request.getParameter("role");
                        String updatedPhoneNumber = request.getParameter("phone_number");

                        User updatedUser = new User(userId, updatedName, updatedEmail, updatedPassword, updatedRole, updatedPhoneNumber);
                        userService.updateUser(updatedUser);
                        response.sendRedirect("/users?action=view&id=" + userId);
                    } catch (Exception e) {
                        request.setAttribute("error", "Error updating user: " + e.getMessage());
                        request.getRequestDispatcher("/users/error.jsp").forward(request, response);
                    }
                    break;

                case "delete":
                    try {
                        int deleteId = Integer.parseInt(request.getParameter("id"));
                        userService.deleteUser(deleteId);
                        response.sendRedirect("/users?action=list");
                    } catch (Exception e) {
                        request.setAttribute("error", "Error deleting user: " + e.getMessage());
                        request.getRequestDispatcher("/users/error.jsp").forward(request, response);
                    }
                    break;

                default:
                    response.sendRedirect("/users?action=list");
                    break;
            }
        }
    }
}

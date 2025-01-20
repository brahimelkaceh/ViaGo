package web.app.viago.controller.user;

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

        if (action != null) {
            switch (action) {
                case "list":
                    List<User> users = userService.getAllUsers();
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

                case "update":
                    int updateUserId = Integer.parseInt(request.getParameter("id"));
                    User updateUser = userService.getUserById(updateUserId);  // Fetch the user to update
                    if (updateUser != null) {
                        System.out.println("selected user : " + updateUser);
                        request.setAttribute("user", updateUser);  // Set user as request attribute
                        request.getRequestDispatcher("/users/form.jsp").forward(request, response);  // Forward to form.jsp for update
                    } else {
                        response.sendRedirect("/users?action=list");  // Redirect to list if user not found
                    }
                    break;

                case "create":
                    request.getRequestDispatcher("/users/form.jsp").forward(request, response);  // Forward to empty form for new user
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
                        userService.createUser(newUser);  // Create the new user
                        response.sendRedirect("/users?action=list");  // Redirect to user list
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
                        userService.updateUser(updatedUser);  // Update the user details
                        response.sendRedirect("/users?action=view&id=" + userId);  // Redirect to the updated user view page
                    } catch (Exception e) {
                        request.setAttribute("error", "Error updating user: " + e.getMessage());
                        request.getRequestDispatcher("/users/error.jsp").forward(request, response);
                    }
                    break;

                case "delete":
                    try {
                        int deleteId = Integer.parseInt(request.getParameter("id"));
                        userService.deleteUser(deleteId);  // Delete the user
                        response.sendRedirect("/users?action=list");  // Redirect to the user list
                    } catch (Exception e) {
                        request.setAttribute("error", "Error deleting user: " + e.getMessage());
                        request.getRequestDispatcher("/users/error.jsp").forward(request, response);
                    }
                    break;

                default:
                    response.sendRedirect("/users?action=list");  // Default action is to redirect to the user list
                    break;
            }
        }
    }
}

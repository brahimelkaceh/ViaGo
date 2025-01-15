package web.app.viago.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import web.app.viago.dao.UserDAO;
import web.app.viago.model.User;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
@WebServlet(name = "userServlet", urlPatterns = "/users")
public class UserServlet extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = userDAO.getAllUsers();

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<h1>Users</h1>");
        for (User user : users) {
            out.println("<p> username : " + user.getUsername() + " (" + user.getEmail() + ")</p>");
        }
    }
}


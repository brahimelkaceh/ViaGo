package web.app.viago;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "newServlet", urlPatterns = { "/new-servlet", "/consulter/*", "/transfert"} )
public class NewServlet extends HttpServlet {

    private String message;

    @Override
    public void init() {
        message = "Hello World! from new-servlet";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Hello World!1111");

        response.setContentType("text/html");

        // Add the message as a request attribute
        request.setAttribute("message", message);

        try {
            // Forward the request to the JSP page
            request.getRequestDispatcher("new-servlet.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
            response.getWriter().println("An error occurred while forwarding the request.");
        }
    }

    @Override
    public void destroy() {
        // Clean up resources if needed
    }
}

package web.app.viago;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
public class Shuttles extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Get Method Called" + req.getServletPath());

            req.getRequestDispatcher("/vues/manage-shuttles.jsp").forward(req, resp);
        System.out.println("Get Method Executed");
    }
}

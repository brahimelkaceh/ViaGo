package web.app.viago.controller.dashboard;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import web.app.viago.model.Company;
import web.app.viago.model.Shuttle;
import web.app.viago.model.User;
import web.app.viago.services.UserService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "dashboardServlet", urlPatterns = "/dashboard")
public class DashboardServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Company company = (Company) session.getAttribute("company");

        if (company == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            request.getRequestDispatcher("/vues/dashboard.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Unable to fetch shuttles. Please try again later.");
            request.getRequestDispatcher("/vues/dashboard.jsp").forward(request, response);
        }
    }
}

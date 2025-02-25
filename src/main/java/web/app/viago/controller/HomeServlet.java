package web.app.viago.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import web.app.viago.model.Request;
import web.app.viago.services.RequestService;

import java.io.IOException;

import java.util.List;

@WebServlet(name = "homeServlet", urlPatterns = "/index")
public class HomeServlet extends HttpServlet {
    private RequestService requestService;


    public void init() throws ServletException {
        super.init();
        this.requestService = new RequestService();

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Request> requests = requestService.getRequestsByStatus();
        request.setAttribute("requests", requests);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }


}
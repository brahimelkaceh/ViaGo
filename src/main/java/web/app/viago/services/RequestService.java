package web.app.viago.services;

import web.app.viago.dao.requests.RequestsDAO;
import web.app.viago.dao.requests.RequestsDaoImpl;
import web.app.viago.model.Request;
import web.app.viago.model.Shuttle;

import java.util.List;


public class RequestService {
    private final RequestsDAO requestsDAO;

    public RequestService() {
        this.requestsDAO = new RequestsDaoImpl();
    }

    public List<Request> getRequestsByCompany(int companyId) {

        try {
            List<Request> requests = requestsDAO.getRequestsByCompany(companyId);
            if (requests != null) {
                return requests;
            } else {
                System.out.println("No requests found.");
            }
        } catch (Exception e) {
            System.out.println("Error retrieving all requests: " + e.getMessage());
        }
        return null;
    }

    public List<Request> getAllRequests() {
        try {
            List<Request> requests = requestsDAO.getAllRequests();
            if (requests != null) {
                return requests;
            } else {
                System.out.println("No requests found.");
            }
        } catch (Exception e) {
            System.out.println("Error retrieving all requests: " + e.getMessage());
        }
        return null;
    }

    public List<Request> getRequestsByStatus() {
        try {
            List<Request> requests = requestsDAO.getRequestsByStatus();
            if (requests != null) {
                return requests;
            } else {
                System.out.println("No requests found.");
            }
        } catch (Exception e) {
            System.out.println("Error retrieving all requests: " + e.getMessage());
        }
        return null;
    }

    public void createRequest(Request request) {
        try {
            requestsDAO.create(request);
        } catch (Exception e) {
            System.out.println("Error creating request: " + e.getMessage());
        }
    }

    public Request getRequestById(int id) {
        try {
            return requestsDAO.findById(id);
        } catch (Exception e) {
            System.out.println("Error retrieving request by ID: " + e.getMessage());
        }
        return null;
    }


    public void updateRequest(Request request) {
        try {
            requestsDAO.update(request);
        } catch (Exception e) {
            System.out.println("Error updating shuttle: " + e.getMessage());
        }
    }

    public void updateRequestStatus(Request request) {
        try {
            requestsDAO.UpdateStatus(request);
        } catch (Exception e) {
            System.out.println("Error updating shuttle: " + e.getMessage());
        }
    }

    public void deleteRequest(int id) {
        try {
            requestsDAO.delete(id);
        } catch (Exception e) {
            System.out.println("Error deleting request: " + e.getMessage());
        }
    }


}

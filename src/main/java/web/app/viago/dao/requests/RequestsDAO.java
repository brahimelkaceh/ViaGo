package web.app.viago.dao.requests;

import web.app.viago.model.Request;

import java.util.List;


public interface RequestsDAO {
    void create(Request request);

    //    getAllRequests
    List<Request> getAllRequests(int userId);

    Request findById(int id);

    void update(Request request);

    void UpdateStatus(Request request);

    void delete(int id);

    List<Request> getRequestsByCompany(int companyId);

    List<Request> getRequestsByStatus();


    List<Request> CheckExistRequest(String departureCity, String arrivalCity);
}

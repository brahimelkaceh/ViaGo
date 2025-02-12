package web.app.viago.dao.requests;

import web.app.viago.model.Request;
import web.app.viago.model.Shuttle;

import java.util.List;


public interface RequestsDAO {
    void create(Request request);

    //    getAllRequests
    List<Request> getAllRequests();

    Request findById(int id);

    void update(Request request);

    void UpdateStatus(Request request);

    void delete(int id);

    List<Request> getRequestsByCompany(int companyId);

    List<Request> getRequestsByStatus();


}

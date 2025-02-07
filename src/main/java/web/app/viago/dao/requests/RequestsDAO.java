package web.app.viago.dao.requests;

import web.app.viago.model.Request;

import java.util.List;


public interface RequestsDAO {
    void create(Request request);

    //    getAllRequests
    List<Request> getAllRequests();
}

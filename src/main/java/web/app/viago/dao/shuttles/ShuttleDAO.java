package web.app.viago.dao.shuttles;

import web.app.viago.model.Shuttle;

import java.util.List;

public interface ShuttleDAO {

    // Create a new shuttle
    void create(Shuttle shuttle, int loggedInUserId);

    // Get a shuttle by ID
    Shuttle findById(int id);

    // Get all shuttles
    List<Shuttle> getAllShuttles();

    List<Shuttle> getAllShuttlesByCompanyId(int companyId);

    // Update an existing shuttle
    void update(Shuttle shuttle);

    // Delete a shuttle by ID
    void delete(int id);

    List<Shuttle> findByCity(String city);
}

package web.app.viago.dao.shuttles;

import web.app.viago.model.Shuttle;

import java.util.List;
import java.util.Optional;

public interface ShuttleDAO {

    // Create a new shuttle
    void create(Shuttle shuttle, int loggedInUserId);

    // Get a shuttle by ID
    Optional<Shuttle> findById(int id);

    // Get all shuttles
    List<Shuttle> getAllShuttles();

    // Get shuttles by user ID
    List<Shuttle> findByUserId(int userId);

    // Update an existing shuttle
    void update(Shuttle shuttle);

    // Delete a shuttle by ID
    void delete(int id);

    List<Shuttle> findByCity(String city);
}

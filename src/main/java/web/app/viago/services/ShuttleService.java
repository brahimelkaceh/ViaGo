package web.app.viago.services;

import web.app.viago.dao.shuttles.ShuttleDAO;
import web.app.viago.dao.shuttles.ShuttleDaoImpl;
import web.app.viago.model.Shuttle;

import java.util.List;
import java.util.Optional;

public class ShuttleService {

    private final ShuttleDAO shuttleDao;

    public ShuttleService() {
        this.shuttleDao = new ShuttleDaoImpl(); // Use the appropriate DAO implementation
    }

    // Create a new shuttle
    public void createShuttle(Shuttle shuttle, int loggedInUserId) {
        try {
            shuttleDao.create(shuttle, loggedInUserId);
        } catch (Exception e) {
            System.out.println("Error creating shuttle: " + e.getMessage());
        }
    }

    // Get a shuttle by ID
    public Optional<Shuttle> getShuttleById(int id) {
        try {
            return shuttleDao.findById(id);
        } catch (Exception e) {
            System.out.println("Error retrieving shuttle by ID: " + e.getMessage());
        }
        return Optional.empty();
    }

    // Update an existing shuttle
    public void updateShuttle(Shuttle shuttle) {
        try {
            shuttleDao.update(shuttle);
        } catch (Exception e) {
            System.out.println("Error updating shuttle: " + e.getMessage());
        }
    }

    // Delete a shuttle by ID
    public void deleteShuttle(int id) {
        try {
            shuttleDao.delete(id);
        } catch (Exception e) {
            System.out.println("Error deleting shuttle: " + e.getMessage());
        }
    }

    // Get a list of all shuttles
    public List<Shuttle> getAllShuttles() {
        try {
            List<Shuttle> shuttles = shuttleDao.getAllShuttles();
            if (shuttles != null) {
                return shuttles;
            } else {
                System.out.println("No shuttles found.");
            }
        } catch (Exception e) {
            System.out.println("Error retrieving all shuttles: " + e.getMessage());
        }
        return null;
    }

    // Get shuttles by city or other criteria if needed
    public List<Shuttle> getShuttlesByCity(String city) {
        try {
            return shuttleDao.findByCity(city);
        } catch (Exception e) {
            System.out.println("Error retrieving shuttles by city: " + e.getMessage());
        }
        return null;
    }
}

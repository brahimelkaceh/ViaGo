package web.app.viago.dao.shuttles;

import web.app.viago.dao.DbConnection;
import web.app.viago.model.Shuttle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ShuttleDaoImpl implements ShuttleDAO {
    private Connection connection;

    public ShuttleDaoImpl() {
        try {
            this.connection = DbConnection.getInstance().getConnection();
            if (this.connection == null) {
                throw new SQLException("Connection is null");
            }
        } catch (SQLException e) {
            System.err.println("Error initializing database connection: " + e.getMessage());
        }
    }

    @Override
    public void create(Shuttle shuttle, int loggedInUserId) {
        PreparedStatement statement = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            String query = "INSERT INTO shuttles (user_id, departure_city, arrival_city, start_date, end_date, " +
                    "departure_time, arrival_time, bus_description, max_subscribers, created_at) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(query);
            statement.setInt(1, loggedInUserId); // Set the user_id to the logged-in user's ID
            statement.setString(2, shuttle.getDepartureCity());
            statement.setString(3, shuttle.getArrivalCity());
            statement.setDate(4, new java.sql.Date(shuttle.getStartDate().getTime()));
            statement.setDate(5, new java.sql.Date(shuttle.getEndDate().getTime()));
            statement.setString(6, shuttle.getDepartureTime());
            statement.setString(7, shuttle.getArrivalTime());
            statement.setString(8, shuttle.getBusDescription());
            statement.setInt(9, shuttle.getMaxSubscribers());
            statement.setTimestamp(10, new java.sql.Timestamp(shuttle.getCreatedAt().getTime()));
            statement.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Error while creating shuttle", e);
        }
    }

    @Override
    public Optional<Shuttle> findById(int id) {
        return Optional.empty();
    }

    @Override
    public List<Shuttle> getAllShuttles() {
        return List.of();
    }

    @Override
    public List<Shuttle> findByUserId(int userId) {
        return List.of();
    }

    @Override
    public void update(Shuttle shuttle) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Shuttle> findByCity(String city) {
        return List.of();
    }
}

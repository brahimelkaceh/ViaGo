package web.app.viago.dao.shuttles;

import web.app.viago.dao.DbConnection;
import web.app.viago.model.Shuttle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
            String query = "INSERT INTO shuttleservices (user_id, departure_city, arrival_city, start_date, end_date, " +
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
            System.out.println("Error creating shuttle: " + e.getMessage());
            throw new RuntimeException("Error while creating shuttle", e);
        }
    }

    @Override
    public Optional<Shuttle> findById(int id) {
        return Optional.empty();
    }

    @Override
    public List<Shuttle> getAllShuttles() {
        List<Shuttle> shuttles = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // SQL query to fetch all shuttle services
            String query = "SELECT * FROM shuttleservices";

            // Get the database connection
            Connection connection = DbConnection.getInstance().getConnection();

            // Prepare and execute the SQL statement
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            // Process the result set
            while (resultSet.next()) {
                Shuttle shuttle = new Shuttle();

                // Map each column in the database to the Shuttle object
                shuttle.setId(resultSet.getInt("id"));
                shuttle.setUserId(resultSet.getInt("user_id"));
                shuttle.setDepartureCity(resultSet.getString("departure_city"));
                shuttle.setArrivalCity(resultSet.getString("arrival_city"));
                shuttle.setStartDate(resultSet.getDate("start_date"));
                shuttle.setEndDate(resultSet.getDate("end_date"));
                shuttle.setDepartureTime(resultSet.getString("departure_time"));
                shuttle.setArrivalTime(resultSet.getString("arrival_time"));
                shuttle.setBusDescription(resultSet.getString("bus_description"));
                shuttle.setMaxSubscribers(resultSet.getInt("max_subscribers"));
                shuttle.setCreatedAt(resultSet.getTimestamp("created_at"));

                // Add the shuttle to the list
                shuttles.add(shuttle);
            }
        } catch (SQLException e) {
            // Handle SQL exceptions
            System.out.println("Error getting all shuttles: " + e.getMessage());
            throw new RuntimeException("Error while fetching shuttles: " + e.getMessage());
        } finally {
            // Close resources to avoid memory leaks
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                System.out.println("Error while closing statement: " + e.getMessage());
            }
        }

        return shuttles;
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

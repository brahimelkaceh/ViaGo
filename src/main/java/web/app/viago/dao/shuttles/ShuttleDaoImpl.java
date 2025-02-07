package web.app.viago.dao.shuttles;

import web.app.viago.dao.DbConnection;
import web.app.viago.model.Shuttle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


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
            String query = "INSERT INTO shuttleservices (company_id, company_name, departure_city, arrival_city, start_date, end_date, " +
                    "departure_time, arrival_time, bus_description, max_subscribers, num_subscribers, created_at) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            statement = connection.prepareStatement(query);
            statement.setInt(1, loggedInUserId); // Set the user_id to the logged-in user's ID
            statement.setString(2, shuttle.getShuttleOwner());
            statement.setString(3, shuttle.getDepartureCity());
            statement.setString(4, shuttle.getArrivalCity());
            statement.setDate(5, new java.sql.Date(shuttle.getStartDate().getTime()));
            statement.setDate(6, new java.sql.Date(shuttle.getEndDate().getTime()));
            statement.setString(7, shuttle.getDepartureTime());
            statement.setString(8, shuttle.getArrivalTime());
            statement.setString(9, shuttle.getBusDescription());
            statement.setInt(10, shuttle.getMaxSubscribers());
            statement.setInt(11, shuttle.getNumSubscribers());
            statement.setTimestamp(12, new java.sql.Timestamp(shuttle.getCreatedAt().getTime()));
            statement.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error creating shuttle: " + e.getMessage());
            throw new RuntimeException("Error while creating shuttle", e);
        }
    }

    @Override
    public Shuttle findById(int id) {
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            String query = "SELECT * FROM shuttleservices WHERE id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            if (rs.next()) {
                return new Shuttle(
                        rs.getInt("id"),
                        rs.getInt("company_id"),
                        rs.getString("departure_city"),
                        rs.getString("arrival_city"),
                        rs.getDate("start_date"),
                        rs.getDate("end_date"),
                        rs.getString("departure_time"),
                        rs.getString("arrival_time"),
                        rs.getString("bus_description"),
                        rs.getInt("max_subscribers"),
                        rs.getInt("num_subscribers"),
                        rs.getString("company_name"),
                        rs.getTimestamp("created_at")
                );
            } else {
                return null;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // Clean up resources in the finally block
            try {
                if (rs != null) rs.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
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
                shuttle.setUserId(resultSet.getInt("company_id"));
                shuttle.setDepartureCity(resultSet.getString("departure_city"));
                shuttle.setArrivalCity(resultSet.getString("arrival_city"));
                shuttle.setStartDate(resultSet.getDate("start_date"));
                shuttle.setEndDate(resultSet.getDate("end_date"));
                shuttle.setDepartureTime(resultSet.getString("departure_time"));
                shuttle.setArrivalTime(resultSet.getString("arrival_time"));
                shuttle.setBusDescription(resultSet.getString("bus_description"));
                shuttle.setMaxSubscribers(resultSet.getInt("max_subscribers"));
                shuttle.setNumSubscribers(resultSet.getInt("num_subscribers"));
                shuttle.setShuttleOwner(resultSet.getString("company_name"));
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
    public void update(Shuttle shuttle) {
        PreparedStatement statement = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            String query = "UPDATE shuttleservices SET  departure_city = ?, arrival_city = ?, start_date = ?, end_date = ?, departure_time = ?, arrival_time = ?, bus_description = ?, max_subscribers = ?, created_at = ? WHERE id = ?;";
            statement = connection.prepareStatement(query);

            statement.setString(1, shuttle.getDepartureCity());
            statement.setString(2, shuttle.getArrivalCity());
            statement.setDate(3, new java.sql.Date(shuttle.getStartDate().getTime()));
            statement.setDate(4, new java.sql.Date(shuttle.getEndDate().getTime()));
            statement.setString(5, shuttle.getDepartureTime());
            statement.setString(6, shuttle.getArrivalTime());
            statement.setString(7, shuttle.getBusDescription());
            statement.setInt(8, shuttle.getMaxSubscribers());
            statement.setTimestamp(9, new java.sql.Timestamp(shuttle.getCreatedAt().getTime()));
            statement.setInt(10, shuttle.getId()); // Use primary key for identifying the record


            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Successfully updated shuttle: " + shuttle);
            } else {
                System.out.println("Failed to update shuttle: " + shuttle);
            }
        } catch (Exception e) {
            System.out.println("Error updating shuttle: " + e.getMessage());
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Error while closing statement: " + e.getMessage());
            }
        }
    }

    @Override
    public void delete(int id) {
        PreparedStatement statement = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            String query = "DELETE FROM shuttleservices WHERE id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Shuttle with id " + id + " deleted successfully.");
            } else {
                System.out.println("No Shuttle found with id " + id + " to delete.");
            }
        } catch (SQLException e) {
            System.err.println("Error deleting Shuttle: " + e.getMessage());
        } finally {
            // Clean up resources in the finally block
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
    }

    @Override
    public List<Shuttle> findByCity(String city) {
        return List.of();
    }
}

package web.app.viago.dao.requests;

import web.app.viago.dao.DbConnection;
import web.app.viago.model.Request;
import web.app.viago.model.Subscription;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RequestsDaoImpl implements RequestsDAO {

    private Connection connection;

    public RequestsDaoImpl() {
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
    public void create(Request request) {
        PreparedStatement statement = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            String query = "INSERT INTO requests (user_id, company_id, departure_city, arrival_city, requested_start_date, requested_end_date, subscribers_count, status, created_at , start_time , end_time)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ? , ? , ?)";

            statement = connection.prepareStatement(query);
            statement.setInt(1, request.getUser_id()); // Set the user_id to the logged-in user's ID
            statement.setInt(2, request.getCompany_id());
            statement.setString(3, request.getDeparture_city());
            statement.setString(4, request.getArrival_city());
            statement.setDate(5, new java.sql.Date(request.getDeparture_start_date().getTime()));
            statement.setDate(6, new java.sql.Date(request.getArrival_end_date().getTime()));
            statement.setInt(7, request.getSubscribers_count());
            statement.setString(8, request.getStatus());
            statement.setTimestamp(9, new java.sql.Timestamp(request.getCreatedAt().getTime()));
            statement.setString(10, request.getDeparture_time());
            statement.setString(11, request.getArrival_time());
            statement.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error creating request: " + e.getMessage());
            throw new RuntimeException("Error while creating request", e);
        }

    }

    @Override
    public List<Request> getAllRequests(int userId) {
        List<Request> requests = new ArrayList<Request>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            connection = DbConnection.getInstance().getConnection();
            String query = "SELECT * FROM requests WHERE user_id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, userId);

            resultSet = statement.executeQuery();


            // Process the result set
            while (resultSet.next()) {
                Request request = new Request();
                // Map each column in the database to the Shuttle object
                request.setId(resultSet.getInt("id"));
                request.setCompany_id(resultSet.getInt("company_id"));
                request.setDeparture_city(resultSet.getString("departure_city"));
                request.setArrival_city(resultSet.getString("arrival_city"));
                request.setDeparture_start_date(resultSet.getDate("requested_start_date"));
                request.setDeparture_time(resultSet.getString("start_time"));
                request.setArrival_time(resultSet.getString("end_time"));
                request.setArrival_end_date(resultSet.getDate("requested_end_date"));
                request.setSubscribers_count(resultSet.getInt("subscribers_count"));
                request.setStatus(resultSet.getString("status"));
                request.setCreatedAt(resultSet.getTimestamp("created_at"));

                // Add the shuttle to the list
                requests.add(request);
            }
        } catch (SQLException e) {
            // Handle SQL exceptions
            System.out.println("Error getting all requests: " + e.getMessage());
            throw new RuntimeException("Error while fetching requests: " + e.getMessage());
        } finally {
            // Close resources to avoid memory leaks
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                System.out.println("Error while closing statement: " + e.getMessage());
            }
        }

        return requests;
    }

    @Override
    public Request findById(int id) {
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            String query = "SELECT * FROM requests WHERE id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            if (rs.next()) {
                return new Request(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("company_id"),
                        rs.getString("departure_city"),
                        rs.getString("arrival_city"),
                        rs.getDate("requested_start_date"),
                        rs.getDate("requested_end_date"),
                        rs.getString("start_time"),
                        rs.getString("end_time"),
                        rs.getInt("subscribers_count")
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
    public void update(Request request) {
        PreparedStatement statement = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            String query = "UPDATE requests SET  departure_city = ?, arrival_city = ?, requested_start_date = ?, requested_end_date = ?, start_time = ? , end_time = ? , subscribers_count = ?, created_at = ?, company_id = ? WHERE id = ?;";
            statement = connection.prepareStatement(query);

            statement.setString(1, request.getDeparture_city());
            statement.setString(2, request.getArrival_city());
            statement.setDate(3, new java.sql.Date(request.getDeparture_start_date().getTime()));
            statement.setDate(4, new java.sql.Date(request.getArrival_end_date().getTime()));
            statement.setString(5, request.getDeparture_time());
            statement.setString(6, request.getArrival_time());
            statement.setInt(7, request.getSubscribers_count());
            statement.setTimestamp(8, new java.sql.Timestamp(request.getCreatedAt().getTime()));
            statement.setInt(9, request.getCompany_id());
            statement.setInt(10, request.getId()); // Use primary key for identifying the record


            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Successfully updated request: " + request);
            } else {
                System.out.println("Failed to update request: " + request);
            }
        } catch (Exception e) {
            System.out.println("Error updating request: " + e.getMessage());
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
    public void UpdateStatus(Request request) {
        PreparedStatement statement = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            String query = "UPDATE requests SET  status = ? WHERE id = ?;";
            statement = connection.prepareStatement(query);
            statement = connection.prepareStatement(query);

            statement.setString(1, request.getStatus());
            statement.setInt(2, request.getId());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Successfully updated request status: " + request);
            } else {
                System.out.println("Failed to update request status: " + request);
            }
        } catch (Exception e) {
            System.out.println("Error updating request status: " + e.getMessage());
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
            String query = "DELETE FROM requests WHERE id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("request with id " + id + " deleted successfully.");
            } else {
                System.out.println("No request found with id " + id + " to delete.");
            }
        } catch (SQLException e) {
            System.err.println("Error deleting request: " + e.getMessage());
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
    public List<Request> getRequestsByCompany(int companyId) {
        if (companyId <= 0) {
            throw new IllegalArgumentException("Invalid companyId: " + companyId);
        }

        List<Request> requests = new ArrayList<>();
        String query = "SELECT * FROM requests WHERE company_id = ?";

        try (
                // Get the database connection
                Connection connection = DbConnection.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setInt(1, companyId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Request request = new Request();
                    request.setId(resultSet.getInt("id"));
                    request.setCompany_id(resultSet.getInt("company_id"));
                    request.setUser_id(resultSet.getInt("user_id"));
                    request.setDeparture_city(resultSet.getString("departure_city"));
                    request.setArrival_city(resultSet.getString("arrival_city"));
                    request.setDeparture_start_date(resultSet.getDate("requested_start_date"));
                    request.setArrival_end_date(resultSet.getDate("requested_end_date"));
                    request.setDeparture_time(resultSet.getString("start_time"));
                    request.setArrival_time(resultSet.getString("end_time"));
                    request.setSubscribers_count(resultSet.getInt("subscribers_count"));
                    request.setStatus(resultSet.getString("status"));
                    request.setCreatedAt(resultSet.getTimestamp("created_at"));
                    requests.add(request);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while fetching requests", e);
        }

        return requests;
    }

    @Override
    public List<Request> getRequestsByStatus() {
        List<Request> requests = new ArrayList<>();
        String query = "SELECT * FROM requests WHERE status = ?";

        try (
                // Get the database connection
                Connection connection = DbConnection.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setString(1, "ok");
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Request request = new Request();
                    request.setId(resultSet.getInt("id"));
                    request.setDeparture_city(resultSet.getString("departure_city"));
                    request.setArrival_city(resultSet.getString("arrival_city"));
                    request.setDeparture_start_date(resultSet.getDate("requested_start_date"));
                    request.setArrival_end_date(resultSet.getDate("requested_end_date"));
                    request.setDeparture_time(resultSet.getString("start_time"));
                    request.setArrival_time(resultSet.getString("end_time"));
                    request.setSubscribers_count(resultSet.getInt("subscribers_count"));
                    requests.add(request);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while fetching requests", e);
        }

        return requests;
    }

    @Override
    public List<Request> CheckExistRequest(String departureCity, String arrivalCity) {
        List<Request> requests = new ArrayList<>();

        String query = "SELECT id FROM requests WHERE departure_city = ? AND arrival_city = ? AND status = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, departureCity);
            statement.setString(2, arrivalCity);
            statement.setString(3, "ok");

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Request request = new Request();
                    request.setId(resultSet.getInt("id"));
                    requests.add(request);

                }
            }

        } catch (SQLException e) {
            System.err.println("Error fetching subscription by ID: " + e.getMessage());
        }
        return requests;
    }


}

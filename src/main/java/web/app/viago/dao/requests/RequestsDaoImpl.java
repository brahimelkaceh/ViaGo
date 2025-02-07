package web.app.viago.dao.requests;

import web.app.viago.dao.DbConnection;
import web.app.viago.model.Request;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
            String query = "INSERT INTO requests (user_id, company_id, departure_city, arrival_city, departure_start_date, arrival_end_date, subscribers_count, status, created_at)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
            statement.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error creating request: " + e.getMessage());
            throw new RuntimeException("Error while creating request", e);
        }

    }

    @Override
    public List<Request> getAllRequests() {
        return List.of();
    }
}

package web.app.viago.dao.subscribtions;

import web.app.viago.dao.DbConnection;
import web.app.viago.model.Subscription;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionDAOImpl implements SubscriptionDAO {
    private  Connection connection;

    public SubscriptionDAOImpl() {
        try {
            this.connection = DbConnection.getInstance().getConnection();
            if (this.connection == null) {
                throw new SQLException("Connection is null");
            }
        } catch (SQLException e) {
            System.err.println("Error initializing database connection: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createSubscribe(Subscription subscribe) {
        System.out.println("Executing createSubscribe() in DAO");

        String query = "INSERT INTO subscriptions (user_id, shuttle_id, status, created_at) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, subscribe.getUserId());
            statement.setInt(2, subscribe.getShuttleId());
            statement.setString(3, subscribe.getStatus());
            statement.setTimestamp(4, new Timestamp(subscribe.getCreatedAt().getTime()));

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Subscription created in DB successfully.");
            } else {
                System.out.println("Failed to insert subscription.");
            }

        } catch (SQLException e) {
            System.err.println("SQL Error while creating subscription: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Subscription> getAllSubscriptions() {
        List<Subscription> subscriptions = new ArrayList<>();


        String query = "SELECT * FROM subscriptions";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Subscription subscription = new Subscription(
                        resultSet.getInt("id"),
                        resultSet.getInt("user_id"),
                        resultSet.getInt("shuttle_id"),
                        resultSet.getString("status"),
                        resultSet.getTimestamp("created_at")
                );
                subscriptions.add(subscription);
            }

        } catch (SQLException e) {
            System.err.println("Error fetching subscriptions: " + e.getMessage());
        }
        return subscriptions;
    }

    @Override
    public List<Subscription> getSubscriptionsByUserId(int userId) {
        List<Subscription> subscriptions = new ArrayList<>();
        String query = "SELECT * FROM subscriptions WHERE user_id = ?"; // SQL query

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            // Set the userId parameter for the query
            statement.setInt(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
                // Loop through the result set and map it to Subscription objects
                while (resultSet.next()) {
                    Subscription subscription = new Subscription();
                    subscription.setId(resultSet.getInt("id"));
                    subscription.setUserId(resultSet.getInt("user_id"));
                    subscription.setShuttleId(resultSet.getInt("shuttle_id"));
                    subscription.setStatus(resultSet.getString("status"));
                    subscription.setCreatedAt(resultSet.getTimestamp("created_at"));
                    subscriptions.add(subscription); // Add the subscription to the list
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching subscriptions for userId " + userId + ": " + e.getMessage());
        }

        return subscriptions; // Return the list of subscriptions
    }


    @Override
    public Subscription getSubscriptionById(int id) {
        String query = "SELECT * FROM subscriptions WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Subscription(
                            resultSet.getInt("id"),
                            resultSet.getInt("user_id"),
                            resultSet.getInt("shuttle_id"),
                            resultSet.getString("status"),
                            resultSet.getTimestamp("created_at")
                    );
                }
            }

        } catch (SQLException e) {
            System.err.println("Error fetching subscription by ID: " + e.getMessage());
        }
        return null;
    }

    @Override
    public String updateSubscriptionStatus(int id, String status) {
        String query = "UPDATE subscriptions SET status = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, status);
            statement.setInt(2, id);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                return "Subscription status updated successfully.";
            } else {
                return "Subscription not found.";
            }

        } catch (SQLException e) {
            System.err.println("Error updating subscription status: " + e.getMessage());
            return "Error updating subscription.";
        }
    }
}

package web.app.viago.dao.users;

import web.app.viago.dao.DbConnection;
import web.app.viago.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDAO {
    private Connection connection;

    public UserDaoImpl() {
        try {
            this.connection = DbConnection.getInstance().getConnection();
            if (this.connection == null) {
                throw new SQLException("Failed to establish database connection.");
            }
        } catch (SQLException e) {
            System.err.println("Error initializing database connection: " + e.getMessage());
        }
    }

    @Override
    public void create(User user) {
        PreparedStatement statement = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            String query = "INSERT INTO users (name, email, password, role, phone_number) VALUES (?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getRole());
            statement.setString(5, user.getPhoneNumber());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error creating user: " + e.getMessage());
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
    }


    @Override
    public User findById(int id) {
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            String query = "SELECT * FROM users WHERE id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            rs = statement.executeQuery();
            if (rs.next()) {
                // If user is found, create and return the User object
                return new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getString("phone_number")
                );
            } else {
                // If no user is found, return null
                return null;
            }
        } catch (SQLException e) {
            System.err.println("Error finding user: " + e.getMessage());
            return null; // You can also return an empty User or throw a custom exception
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
    public List<User> findByRole(String role) {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users WHERE role = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, role);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                users.add(new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getString("phone_number"),
                        rs.getDate("created_at")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error finding users: " + e.getMessage());
        }
        return users; // Now returns the list of users with the specified role
    }

    @Override
    public void update(User user) {
        PreparedStatement statement = null;

        try {
            connection = DbConnection.getInstance().getConnection();
            String query = "UPDATE users SET name = ?, email = ?, password = ?, role = ?, phone_number = ? WHERE id = ?";
            statement = connection.prepareStatement(query);

            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getRole());
            statement.setString(5, user.getPhoneNumber());
            statement.setInt(6, user.getId());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User with id " + user.getId() + " updated successfully.");
            } else {
                System.out.println("No user found with id " + user.getId() + " to delete.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating user: " + e.getMessage());
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
    public void delete(int id) {
        PreparedStatement statement = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            String query = "DELETE FROM users WHERE id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User with id " + id + " deleted successfully.");
            } else {
                System.out.println("No user found with id " + id + " to delete.");
            }
        } catch (SQLException e) {
            System.err.println("Error deleting user: " + e.getMessage());
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
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users"; // Adjust the query according to your table structure

        try (Connection connection = DbConnection.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                // Assuming your User table has columns: id, name, email, password, role, phone_number
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                String phoneNumber = resultSet.getString("phone_number");

                User user = new User(id, name, email, password, role, phoneNumber);
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Error getting all users: " + e.getMessage());
            // Handle SQL exception
        }
        return users;
    }
}


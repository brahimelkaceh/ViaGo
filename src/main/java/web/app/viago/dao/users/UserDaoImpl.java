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
        String query = "INSERT INTO users (name, email, password, role, phone_number) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());  // Remember, password is hashed
            statement.setString(4, user.getRole());
            statement.setString(5, user.getPhoneNumber());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error creating user: " + e.getMessage());
        }
    }

    @Override
    public User findById(int id) {
        String query = "SELECT * FROM users WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getString("phone_number"),
                        rs.getDate("created_at")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error finding user: " + e.getMessage());
        }
        return null;
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
        String query = "UPDATE users SET name = ?, email = ?, password = ?, role = ?, phone_number = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getRole());
            statement.setString(5, user.getPhoneNumber());
            statement.setInt(6, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating user: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting user: " + e.getMessage());
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


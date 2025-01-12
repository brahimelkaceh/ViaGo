package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static volatile DbConnection instance; // Volatile for thread-safety
    private Connection connection;

    // Database credentials
    private static final String URL = "jdbc:mysql://localhost:3306/viagodb";
    private static final String USERNAME = "root"; // Replace with your DB username
    private static final String PASSWORD = "elkaceh@Brahim10"; // Replace with your DB password

    // Private constructor for Singleton pattern
    private DbConnection() {
        initializeConnection();
    }

    // Initializes the database connection
    private void initializeConnection() {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connected to the database: " + connection);
        } catch (ClassNotFoundException e) {
            System.err.println("Database driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database: " + e.getMessage());
        }
    }

    // Thread-safe Singleton implementation
    public static DbConnection getInstance() {
        if (instance == null) {
            synchronized (DbConnection.class) {
                if (instance == null) {
                    instance = new DbConnection();
                }
            }
        }
        return instance;
    }

    // Getter for the connection
    public Connection getConnection() {
        try {
            // Reinitialize connection if it's closed or null
            if (connection == null || connection.isClosed()) {
                System.err.println("Database connection is closed or null. Reinitializing...");
                initializeConnection();
            }
        } catch (SQLException e) {
            System.err.println("Error while checking database connection: " + e.getMessage());
        }
        return connection;
    }
}

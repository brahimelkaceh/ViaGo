package web.app.viago.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static DbConnection instance;
    private Connection connection;

    private static final String URL = "jdbc:mysql://localhost:3306/viagodb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "elkaceh@Brahim10";

    private DbConnection() {
        initializeConnection();
    }

    private void initializeConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connected to the database: " + connection);
        } catch (Exception e) {
            System.err.println("Error initializing connection: " + e.getMessage());
        }
    }

    public static synchronized DbConnection getInstance() {
        if (instance == null) {
            instance = new DbConnection();
        }
        return instance;
    }

    public synchronized Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                System.err.println("Connection is null or closed. Reinitializing...");
                initializeConnection();
            }
        } catch (SQLException e) {
            System.err.println("Error checking connection status: " + e.getMessage());
        }
        return connection;
    }
}


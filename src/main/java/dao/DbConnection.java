package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static Connection connection = null;
    private static DbConnection instance;

    public static DbConnection getInstance() {
        if (instance == null) {
            instance = new DbConnection();
            System.out.println("Connecting to database...");
        }
        return instance;
    }

    private DbConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found: " + e.getMessage());
            e.printStackTrace();
        }

        String url = "jdbc:mysql://localhost:3306/viagodb";
        String username = "root"; // Replace with your DB username
        String password = "password"; // Replace with your DB password

        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to database");
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}

package web.app.viago.dao.company;

import web.app.viago.dao.DbConnection;
import web.app.viago.model.Company;
import web.app.viago.model.Shuttle;
import web.app.viago.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyDaoImpl implements CompanyDAO {

    private Connection connection;

    public CompanyDaoImpl() {
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
    public boolean create(Company company) {
        String checkQuery = "SELECT COUNT(*) FROM company WHERE email = ?";
        String query = "INSERT INTO company (name, email, password, phone_number) VALUES (?, ?, ?,?)";
        PreparedStatement checkStatement = null;
        PreparedStatement statement = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            checkStatement = connection.prepareStatement(checkQuery);
            checkStatement.setString(1, company.getEmail());
            ResultSet rs = checkStatement.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("Email already exists: " + company.getEmail());
                return false;
            }

            statement = connection.prepareStatement(query);
            statement.setString(1, company.getName());
            statement.setString(2, company.getEmail());
            statement.setString(3, company.getPassword());
            statement.setString(4, company.getPhoneNumber());
            statement.executeUpdate();
            System.out.println("Company registered successfully.");
            return true;
        } catch (SQLException e) {
            System.err.println("Error creating company: " + e.getMessage());
            return false;
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
    public Company login(String email, String password) {
        String query = "SELECT * FROM company WHERE email = ? AND password = ?";
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, password); // Hash the input password and compare it with the stored hash
            rs = statement.executeQuery();
            if (rs.next()) {
                return new Company(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("phone_number"));
            } else {
                System.out.println("Invalid email or password.");
                return null; // Login failed
            }
        } catch (Exception e) {
            System.err.println("Error during login: " + e.getMessage());
            throw new RuntimeException(e);
        } finally {
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
    public Company findById(int id) {
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            String query = "SELECT * FROM company WHERE id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            rs = statement.executeQuery();
            if (rs.next()) {
                // If user is found, create and return the User object
                return new Company(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
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
    public Company findByEmail(String email) {
        PreparedStatement statement = null;

        try {
            connection = DbConnection.getInstance().getConnection();
            String query = "SELECT * FROM company WHERE email = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new Company(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("phone_number")
                );
            }


        } catch (Exception e) {
            System.out.println("Error during findById: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void update(Company company) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Company> getAllCompanies() {
        List<Company> companies = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            // SQL query to fetch all shuttle services
            String query = "SELECT * FROM company";

            // Get the database connection
            Connection connection = DbConnection.getInstance().getConnection();

            // Prepare and execute the SQL statement
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            // Process the result set
            while (resultSet.next()) {
                Company company = new Company();
                // Map each column in the database to the Shuttle object
                company.setId(resultSet.getInt("id"));
                company.setName(resultSet.getString("name"));

                // Add the company to the list
                companies.add(company);
            }
        } catch (SQLException e) {
            // Handle SQL exceptions
            System.out.println("Error getting all companies: " + e.getMessage());
            throw new RuntimeException("Error while fetching companies: " + e.getMessage());
        } finally {
            // Close resources to avoid memory leaks
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                System.out.println("Error while closing statement: " + e.getMessage());
            }
        }

        return companies;
    }
}

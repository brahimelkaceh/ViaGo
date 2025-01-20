package web.app.viago.model;

import java.sql.Date;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private String role; // USER or COMPANY
    private String phoneNumber;

    // Constructors
    public User() {
    }

    public User(int id, String name, String email, String password, String role, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.phoneNumber = phoneNumber;
    }

    public User(int id, String name, String email, String password, String role, String phoneNumber, Date createdAt) {
    }

    public User(String name, String email, String password, String role, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.phoneNumber = phoneNumber;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return " User {id=" + id + ", name='" + name + "', email='" + email + "', role='" + role + "', phoneNumber='" + phoneNumber + "'}";
    }

}


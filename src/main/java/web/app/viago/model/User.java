package web.app.viago.model;

public class User {
    private int iduser;
    private String username;
    private String email;

    // Getters and Setters
    public int getId() {
        return iduser;
    }

    public void setId(int id) {
        this.iduser = id;
    }

    public String getUsername() {
        return username;
    }

    public void setName(String name) {
        this.username = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

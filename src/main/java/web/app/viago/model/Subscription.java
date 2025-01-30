package web.app.viago.model;

import java.sql.Timestamp;

public class Subscription {
    private int id;
    private int userId;
    private int shuttleId;
    private String status; // Enum as String (PENDING, CONFIRMED, CANCELLED)
    private Timestamp createdAt;

    public Subscription() {
    }

    public Subscription(int id, int userId, int shuttleId, String status, Timestamp createdAt) {
        this.id = id;
        this.userId = userId;
        this.shuttleId = shuttleId;
        this.status = status;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getShuttleId() {
        return shuttleId;
    }

    public void setShuttleId(int shuttleId) {
        this.shuttleId = shuttleId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", userId=" + userId +
                ", shuttleId=" + shuttleId +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}

package web.app.viago.model;

import java.util.Date;

public class Shuttle {
    private int id;
    private int userId;
    private String departureCity;
    private String arrivalCity;
    private Date startDate;
    private Date endDate;
    private String departureTime;
    private String arrivalTime;
    private String busDescription;
    private int maxSubscribers;
    private Date createdAt;

    // Constructors
    public Shuttle() {
    }

    public Shuttle(int id, int userId, String departureCity, String arrivalCity, Date startDate, Date endDate,
                   String departureTime, String arrivalTime, String busDescription, int maxSubscribers, Date createdAt) {
        this.id = id;
        this.userId = userId;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.startDate = startDate;
        this.endDate = endDate;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.busDescription = busDescription;
        this.maxSubscribers = maxSubscribers;
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

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getBusDescription() {
        return busDescription;
    }

    public void setBusDescription(String busDescription) {
        this.busDescription = busDescription;
    }

    public int getMaxSubscribers() {
        return maxSubscribers;
    }

    public void setMaxSubscribers(int maxSubscribers) {
        this.maxSubscribers = maxSubscribers;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Shuttle{" +
                "id=" + id +
                ", userId=" + userId +
                ", departureCity='" + departureCity + '\'' +
                ", arrivalCity='" + arrivalCity + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", departureTime='" + departureTime + '\'' +
                ", arrivalTime='" + arrivalTime + '\'' +
                ", busDescription='" + busDescription + '\'' +
                ", maxSubscribers=" + maxSubscribers +
                ", createdAt=" + createdAt +
                '}';
    }
}

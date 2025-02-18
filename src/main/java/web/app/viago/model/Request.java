package web.app.viago.model;

import java.util.Date;

public class Request {
    private int id;
    private int user_id;
    private int company_id;
    private String departure_city;
    private String arrival_city;
    private Date departure_start_date;
    private Date arrival_end_date;
    private String departure_time;
    private String arrival_time;
    private int subscribers_count;
    private String status;
    private Date createdAt;


    public Request() {
    }

    public Request(int id, int user_id, int company_id, String departure_city, String arrival_city, Date departure_start_date, Date arrival_end_date, int subscribers_count, String status, Date createdAt) {
        this.id = id;
        this.user_id = user_id;
        this.company_id = company_id;
        this.departure_city = departure_city;
        this.arrival_city = arrival_city;
        this.departure_start_date = departure_start_date;
        this.arrival_end_date = arrival_end_date;
        this.subscribers_count = subscribers_count;
        this.status = status;
        this.createdAt = createdAt;
    }


    public Request(int id, int companyId, int user_id, String departureCity, String arrivalCity, java.sql.Date departureStartDate, java.sql.Date arrivalEndDate, String departure_time, String arrival_time, int subscribersCount) {
        this.id = id;
        this.company_id = companyId;
        this.user_id = user_id;
        this.departure_city = departureCity;
        this.arrival_city = arrivalCity;
        this.departure_start_date = departureStartDate;
        this.arrival_end_date = arrivalEndDate;
        this.departure_time = departure_time;
        this.arrival_time = arrival_time;
        this.subscribers_count = subscribersCount;


    }

    public Request(int id, int companyId, String departureCity, String arrivalCity, java.sql.Date requestedStartDate, java.sql.Date requestedEndDate, String status, int subscribersCount) {
        this.id = id;
        this.company_id = companyId;
        this.departure_city = departureCity;
        this.arrival_city = arrivalCity;
        this.departure_start_date = requestedStartDate;
        this.arrival_end_date = requestedEndDate;
        this.subscribers_count = subscribersCount;
        this.status = status;
    }

    public Request(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public String getDeparture_city() {
        return departure_city;
    }

    public void setDeparture_city(String departure_city) {
        this.departure_city = departure_city;
    }

    public String getArrival_city() {
        return arrival_city;
    }

    public void setArrival_city(String arrival_city) {
        this.arrival_city = arrival_city;
    }

    public Date getDeparture_start_date() {
        return departure_start_date;
    }

    public void setDeparture_start_date(Date departure_start_date) {
        this.departure_start_date = departure_start_date;
    }

    public Date getArrival_end_date() {
        return arrival_end_date;
    }

    public void setArrival_end_date(Date arrival_end_date) {
        this.arrival_end_date = arrival_end_date;
    }

    public String getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(String departure_time) {
        this.departure_time = departure_time;
    }

    public String getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(String arrival_time) {
        this.arrival_time = arrival_time;
    }

    public int getSubscribers_count() {
        return subscribers_count;
    }

    public void setSubscribers_count(int subscribers_count) {
        this.subscribers_count = subscribers_count;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", company_id=" + company_id +
                ", departure_city='" + departure_city + '\'' +
                ", arrival_city='" + arrival_city + '\'' +
                ", departure_start_date='" + departure_start_date + '\'' +
                ", arrival_end_date='" + arrival_end_date + '\'' +
                ", subscribers_count=" + subscribers_count +
                ", status='" + status + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }


}

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

<%@ page import="web.app.viago.model.User" %><%--
  Created by IntelliJ IDEA.
  User: ELKACEH BRAHIM
  Date: 20/01/2025
  Time: 19:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Browse Subscriptions</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<!-- Retrieve the User object from the session -->
<%
    User user = (User) session.getAttribute("user");
    if (user != null) {
        out.println("<div><p>Hello, \" + user.getName() + \"!</p> <a href=\"logout\">Logout</a>\n</div>");
    } else {
        out.println("<p>You are not logged in.</p>");
    }
%>
<div class="container py-4">
    <h1 class="text-center mb-4">Browse Subscriptions</h1>

    <!-- Search and Filter Section -->
    <form class="mb-4">
        <div class="row g-3">
            <div class="col-md-4">
                <input type="text" name="departure_city" class="form-control" placeholder="Departure City">
            </div>
            <div class="col-md-4">
                <input type="text" name="arrival_city" class="form-control" placeholder="Arrival City">
            </div>
            <div class="col-md-2">
                <input type="date" name="start_date" class="form-control" placeholder="Start Date">
            </div>
            <div class="col-md-2">
                <button type="submit" class="btn btn-primary w-100">Search</button>
            </div>
        </div>
    </form>

    <!-- Subscription List -->
    <div class="row">
        <!-- Replace with dynamic data -->
        <div class="col-md-4 mb-4">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">City A to City B</h5>
                    <p class="card-text">Departure: 08:00 AM<br>Arrival: 10:00 AM</p>
                    <p>Status: <span class="badge bg-success">Open</span></p>
                    <button class="btn btn-primary">Subscribe</button>
                </div>
            </div>
        </div>
        <!-- Repeat -->
    </div>
</div>
</body>
</html>


<%--
  Created by IntelliJ IDEA.
  User: ELKACEH BRAHIM
  Date: 20/01/2025
  Time: 19:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create Subscription Offer</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container py-4">
    <h1 class="text-center mb-4">Create a Subscription Offer</h1>

    <form>
        <div class="mb-3">
            <label for="departure_city" class="form-label">Departure City</label>
            <input type="text" id="departure_city" name="departure_city" class="form-control">
        </div>
        <div class="mb-3">
            <label for="arrival_city" class="form-label">Arrival City</label>
            <input type="text" id="arrival_city" name="arrival_city" class="form-control">
        </div>
        <div class="mb-3">
            <label for="time" class="form-label">Time</label>
            <input type="time" id="time" name="time" class="form-control">
        </div>
        <div class="mb-3">
            <label for="subscription_period" class="form-label">Subscription Period</label>
            <input type="date" id="subscription_period" name="subscription_period" class="form-control">
        </div>
        <div class="mb-3">
            <label for="max_subscribers" class="form-label">Maximum Number of Subscribers</label>
            <input type="number" id="max_subscribers" name="max_subscribers" class="form-control">
        </div>
        <div class="mb-3">
            <label for="bus_description" class="form-label">Bus Description</label>
            <textarea id="bus_description" name="bus_description" class="form-control" rows="3"
                      placeholder="Amenities, seat count, etc."></textarea>
        </div>
        <button type="submit" class="btn btn-primary">Create Offer</button>
    </form>
</div>
</body>
</html>


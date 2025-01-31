<%@ page import="web.app.viago.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Browse Subscriptions</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<!-- Retrieve the User object from the session -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">ViaGo</a>
    </div>

    <%
        User user = (User) session.getAttribute("user");
        if (user != null) {
    %>
    <div class="ms-auto text-white d-flex flex-row align-items-center">
        <p>Hello, <%= user.getName() %>!</p>
        <a href="logout" class="btn btn-outline-light">Logout</a>
    </div>
    <%
    } else {
    %>
    <div class="ms-auto text-white">
        <p>You are not logged in.</p>
        <a href="login.jsp" class="btn btn-outline-light">Login</a>
    </div>
    <%
        }
    %>
</nav>

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
        <c:forEach var="shuttle" items="${shuttles}">
            <div class="col-md-4 mb-4">
                <div class="card shadow-sm border-0 rounded-3">
                    <div class="card-header bg-primary text-white text-center">
                        <h5 class="card-title m-0">${shuttle.departureCity} ➝ ${shuttle.arrivalCity}</h5>
                    </div>
                    <div class="card-body">
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item"><strong>🗓️ Start Date:</strong> ${shuttle.startDate}</li>
                            <li class="list-group-item"><strong>📅 End Date:</strong> ${shuttle.endDate}</li>
                            <li class="list-group-item"><strong>🕒
                                Departure:</strong> ${fn:substring(shuttle.departureTime, 0, 5)}</li>
                            <li class="list-group-item"><strong>⏳
                                Arrival:</strong> ${fn:substring(shuttle.arrivalTime, 0, 5)}</li>
                            <li class="list-group-item"><strong>🚌 Bus:</strong> ${shuttle.busDescription}</li>
                            <li class="list-group-item"><strong>👥 Max
                                Passengers:</strong> ${shuttle.maxSubscribers}
                            </li>
                        </ul>
                        <div class="d-flex justify-content-between align-items-center mt-3">
                            <span class="badge bg-success px-3 py-2">✅ Open</span>
                            <form action="/subscriptions" method="POST" class="d-inline">
                                <input type="hidden" name="action" value="subscribe">
                                <input type="hidden" name="id" value="${shuttle.id}">
                                <input type="hidden" name="status" value="subscribed">
                                <button type="submit"
                                        class="btn btn-${shuttle.status eq 'subscribed' ? 'outline-primary' : (shuttle.status eq 'canceled' ? 'danger' : 'primary')} btn-sm">
                                        ${shuttle.status}
                                </button>
                            </form>


                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
        <!-- Repeat -->
    </div>
</div>
</body>
</html>


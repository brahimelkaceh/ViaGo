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
<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark px-3">
    <a class="navbar-brand" href="/">ViaGo</a>
    <div class="ms-auto d-flex align-items-center">
        <%
            User user = (User) session.getAttribute("user");
            if (user != null) {
        %>
        <span class="text-white me-3">Hello, <%= user.getName() %>!</span>
        <a href="logout" class="btn btn-outline-light">Logout</a>
        <%
        } else {
        %>
        <span class="text-white me-3">You are not logged in.</span>
        <a href="login.jsp" class="btn btn-outline-light">Login</a>
        <%
            }
        %>
    </div>
</nav>

<!-- Main Content -->
<div class="container py-4">
    <h1 class="text-center mb-4">Browse Subscriptions</h1>
    <a href="/requests/form.jsp?action=create" class="btn btn-success mb-3">Add New Request</a>

    <!-- Subscription List -->
    <div class="row">
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
                            <li class="list-group-item"><strong>👥 Max Passengers:</strong> ${shuttle.maxSubscribers}
                                - ${shuttle.numSubscribers}</li>
                            <li class="list-group-item"><strong>👨‍💻 By:</strong> ${shuttle.shuttleOwner}</li>
                        </ul>
                        <div class="d-flex justify-content-between align-items-center mt-3">
                            <c:set var="remainingSlots" value="${shuttle.maxSubscribers - shuttle.numSubscribers}"/>
                            <span class="badge ${remainingSlots > 0 ? 'bg-success' : 'bg-danger'} px-3 py-2">
                                    ${remainingSlots > 0 ? '✅ Open' : '❌ Full'}
                            </span>
                            <form action="/subscriptions" method="POST" class="d-inline">
                                <input type="hidden" name="action" value="${shuttle.status}">
                                <input type="hidden" name="id" value="${shuttle.id}">
                                <input type="hidden" name="subscriptionId" value="${shuttle.subscriptionId}">
                                <input type="hidden" name="status" value="${shuttle.status}">
                                <button type="submit"
                                        class="btn btn-${shuttle.status eq 'subscribed' ? 'outline-primary' : 'primary'} btn-sm"
                                    ${remainingSlots le 0 && shuttle.status eq 'canceled' ? 'disabled' : ''}>
                                        ${shuttle.status eq 'canceled' ? 'Subscribe' : 'Subscribed'}
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
<%@ page import="web.app.viago.model.Company" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="web.app.viago.model.Company" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${param.action == 'update' ? 'Update Shuttle' : 'Add New Shuttle'}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-dark text-white">
<nav class="navbar navbar-expand-lg navbar-dark bg-secondary">
    <div class="container-fluid">
        <a class="navbar-brand" href="/index">ViaGo</a>

        <%
            Company company = (Company) session.getAttribute("company");
            if (company != null) {
        %>
        <div class="ms-auto text-white d-flex flex-row align-items-center">
            <span class="text-white me-3">Hello, <%= company.getName() %>!</span>
            <a href="logout" class="btn btn-outline-light">Logout</a>
        </div>
        <%
        } else {
        %>
        <a href="login.jsp" class="btn btn-outline-light">Login</a>
        <%
            }
        %>

    </div>

</nav>

<div class="container my-5 d-flex justify-content-center text-dark">
    <div class="card shadow-lg p-4" style="max-width: 500px; width: 100%;">
        <div class="d-flex justify-content-between align-items-center mb-1">
            <a href="/shuttles?action=list" class="btn-sm btn btn-outline-info">⬅️ Back to Requests List</a>
            <h2 class="text-center">${param.action == 'update' ? 'Update Shuttle' : 'Add New Shuttle'}</h2>
        </div>
        <form action="/shuttles" method="post">
            <input type="hidden" name="action" value="${param.action}">

            <c:if test="${param.action == 'update'}">
                <input type="hidden" name="id" value="${param.id}">
            </c:if>
            <div class="mb-1">
                <input type="text" class="form-control" id="departureCity"
                       value="${updateShuttle != null ? updateShuttle.departureCity : ''}"
                       name="departureCity"
                       required>
                <label for="departureCity" class="form-label">Departure City</label>
            </div>
            <div class="mb-1">
                <input type="text" class="form-control" id="arrivalCity" name="arrivalCity"
                       value="${updateShuttle != null ? updateShuttle.arrivalCity : ''}" required>
                <label for="arrivalCity" class="form-label">Arrival City</label>
            </div>
            <div class="mb-1">
                <input type="date" class="form-control" id="startDate" name="startDate"
                       value="${updateShuttle != null ? updateShuttle.startDate : ''}" required>
                <label for="startDate" class="form-label">Start Date</label>
            </div>
            <div class="mb-1">
                <input type="date" class="form-control" id="endDate" name="endDate"
                       value="${updateShuttle != null ? updateShuttle.endDate : ''}" required>
                <label for="endDate" class="form-label">End Date</label>
            </div>
            <div class="mb-1">
                <input type="time" class="form-control" id="departureTime" name="departureTime"
                       value="${updateShuttle != null ? updateShuttle.departureTime : ''}"
                       required>
                <label for="departureTime" class="form-label">Departure Time</label>
            </div>
            <div class="mb-1">
                <input type="time" class="form-control" id="arrivalTime" name="arrivalTime"
                       value="${updateShuttle != null ?  updateShuttle.arrivalTime : ''}" required>
                <label for="arrivalTime" class="form-label">Arrival Time</label>
            </div>
            <div class="mb-1">
                <input class="form-control" id="busDescription" name="busDescription" type="text"
                       value="${updateShuttle != null ?  updateShuttle.busDescription : ''}"
                       required/>
                <label for="busDescription" class="form-label">Bus Description</label>
            </div>
            <div class="mb-1">
                <input type="number" class="form-control" id="maxSubscribers" name="maxSubscribers"
                       value="${updateShuttle != null ?  updateShuttle.maxSubscribers : ''}" required>
                <label for="maxSubscribers" class="form-label">Max Subscribers</label>
            </div>
            <div class="d-flex justify-content-end  align-items-center">
                <button type="submit" class="btn btn-primary mx-1">${param.action == 'update' ? 'Update' : 'Add'}
                    Shuttle
                </button>
                <a href="/shuttles?action=list" class="btn btn-secondary">Cancel</a>
            </div>


        </form>

    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

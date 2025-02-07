<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${param.action == 'update' ? 'Update Shuttle' : 'Add New Shuttle'}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container my-4">
    <h2 class="text-center">${param.action == 'update' ? 'Update Shuttle' : 'Add New Shuttle'}</h2>
    <form action="/shuttles" method="post">
        <input type="hidden" name="action" value="${param.action}">
        <c:if test="${param.action == 'update'}">
            <input type="hidden" name="id" value="${param.id}">
        </c:if>
        <div class="mb-3">
            <label for="departureCity" class="form-label">Departure City</label>
            <input type="text" class="form-control" id="departureCity"
                   value="${updateShuttle != null ? updateShuttle.departureCity : ''}"
                   name="departureCity"
                   required>
        </div>
        <div class="mb-3">
            <label for="arrivalCity" class="form-label">Arrival City</label>
            <input type="text" class="form-control" id="arrivalCity" name="arrivalCity"
                   value="${updateShuttle != null ? updateShuttle.arrivalCity : ''}" required>
        </div>
        <div class="mb-3">
            <label for="startDate" class="form-label">Start Date</label>
            <input type="date" class="form-control" id="startDate" name="startDate"
                   value="${updateShuttle != null ? updateShuttle.startDate : ''}" required>
        </div>
        <div class="mb-3">
            <label for="endDate" class="form-label">End Date</label>
            <input type="date" class="form-control" id="endDate" name="endDate"
                   value="${updateShuttle != null ? updateShuttle.endDate : ''}" required>
        </div>
        <div class="mb-3">
            <label for="departureTime" class="form-label">Departure Time</label>
            <input type="time" class="form-control" id="departureTime" name="departureTime"
                   value="${updateShuttle != null ? updateShuttle.departureTime : ''}"
                   required>
        </div>
        <div class="mb-3">
            <label for="arrivalTime" class="form-label">Arrival Time</label>
            <input type="time" class="form-control" id="arrivalTime" name="arrivalTime"
                   value="${updateShuttle != null ?  updateShuttle.arrivalTime : ''}" required>
        </div>
        <div class="mb-3">
            <label for="busDescription" class="form-label">Bus Description</label>
            <input class="form-control" id="busDescription" name="busDescription" type="text"
                   value="${updateShuttle != null ?  updateShuttle.busDescription : ''}"
                   required/>
        </div>
        <div class="mb-3">
            <label for="maxSubscribers" class="form-label">Max Subscribers</label>
            <input type="number" class="form-control" id="maxSubscribers" name="maxSubscribers"
                   value="${updateShuttle != null ?  updateShuttle.maxSubscribers : ''}" required>
        </div>
        <button type="submit"
                class="btn btn-primary">${param.action == 'update' ? 'Update Shuttle' : 'Add New Shuttle'}</button>
        <a href="/shuttles?action=list" class="btn btn-secondary">Cancel</a>

    </form>

</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

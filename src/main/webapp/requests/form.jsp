<%@ page import="web.app.viago.model.Company" %>
<%@ page import="web.app.viago.model.User" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${param.action == 'update' ? 'Update Request' : 'Add New Request'}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="bg-dark text-white">
<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark bg-secondary">
    <div class="container-fluid">
        <a class="navbar-brand" href="/index">ViaGo</a>

        <%
            User user = (User) session.getAttribute("user");
            Company company = (Company) session.getAttribute("company");
            if (user != null) {
        %>
        <div class="ms-auto text-white d-flex flex-row align-items-center">
            <span class="text-white me-3">Hello, <%= user.getName() %>!</span>
            <a href="logout" class="btn btn-outline-light">Logout</a>
        </div>
        <%
        } else if (company != null) {
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

        <div class="d-flex justify-content-between align-items-center mb-3">
            <a href="/requests?action=list" class="btn-sm btn btn-outline-info">⬅️ Back to Requests List</a>
            <h2 class="text-center">${param.action == 'update' ? 'Update Request' : 'Add New Request'}</h2>
        </div>
        <form action="/requests" method="post">
            <input type="hidden" name="action" value="${param.action}">

            <c:if test="${param.action == 'update'}">
                <input type="hidden" name="id" value="${param.id}">
            </c:if>
            <div class="mb-3">
                <label for="departure_city" class="form-label">Departure City</label>
                <input type="text" class="form-control" id="departure_city"
                       value="${updateRequest != null ? updateRequest.departure_city : ''}"
                       name="departure_city"
                       required>
            </div>
            <div class="mb-3">
                <label for="arrival_city" class="form-label">Arrival City</label>
                <input type="text" class="form-control" id="arrival_city" name="arrival_city"
                       value="${updateRequest != null ? updateRequest.arrival_city : ''}" required>
            </div>
            <div class="mb-3">
                <label for="departure_start_date" class="form-label">Start Date</label>
                <input type="date" class="form-control" id="departure_start_date" name="departure_start_date"
                       value="${updateRequest != null ? updateRequest.departure_start_date : ''}" required>
            </div>
            <div class="mb-3">
                <label for="arrival_end_date" class="form-label">End Date</label>
                <input type="date" class="form-control" id="arrival_end_date" name="arrival_end_date"
                       value="${updateRequest != null ? updateRequest.arrival_end_date : ''}" required>
            </div>
            <div class="mb-3">
                <label for="subscribers_count" class="form-label">Number of Subscribers</label>
                <input type="number" class="form-control" id="subscribers_count" name="subscribers_count"
                       value="${updateRequest != null ?  updateRequest.subscribers_count : ''}" required>
            </div>
            <div class="mb-3">
                <label for="company" class="form-label">Select a Company:</label>
                <select id="company" name="companyId" class="form-select" required>
                    <option value="">-- Select Company --</option>
                    <c:forEach items="${companies}" var="company">
                        <option value="${company.id}" ${company.id == updateRequest.company_id ? 'selected' : ''}>
                                ${company.name}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="d-flex justify-content-end  align-items-center">
                <button type="submit" class="btn btn-primary mx-1">${param.action == 'update' ? 'Update' : 'Add'}
                    Request
                </button>
                <a href="/requests?action=list" class="btn btn-secondary">Cancel</a>
            </div>


        </form>
        <%
            String errorMessage = (String) session.getAttribute("error");
            if (errorMessage != null) {
        %>
        <div class="alert alert-danger mt-2">The request with the chosen city already exists!! ${errorMessage}</div>
        <%
                session.removeAttribute("error"); // Remove the message after displaying it
            }
        %>

        <c:if test="${not empty error}">
            <div class="alert alert-danger mt-2">${error}</div>
        </c:if>
    </div>

</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

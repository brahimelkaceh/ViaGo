<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%@ page import="web.app.viago.model.Company" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User List</title>
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
<div class="container my-4">
    <div class="d-flex justify-content-between align-items-center mb-3">
        <a href="/dashboard" class="btn btn-outline-info">⬅️ Back to Dashboard</a>
        <h2 class="text-center flex-grow-1">Requests List</h2>
    </div>

    <div class="card shadow-lg border-0">
        <div class="card shadow p-3">
            <table class="table table-striped table-hover">
                <thead class="table-dark">
                <tr style="white-space: nowrap; font-size: 0.8rem ; text-align: center">
                    <th>ID</th>
                    <th>User</th>
                    <th>Departure City</th>
                    <th>Arrival City</th>
                    <th>Start Date</th>
                    <th>End Date</th>
                    <th>Departure Time</th>
                    <th>Arrival Time</th>
                    <th>Max Subscribers</th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="request" items="${requests}">
                    <tr style="white-space: nowrap; font-size: 0.8rem ; text-align: center">
                        <td>${request.id}</td>
                        <td>${request.user_id}</td>
                        <td>${request.departure_city}</td>
                        <td>${request.arrival_city}</td>
                        <td>${request.departure_start_date}</td>
                        <td>${request.arrival_end_date}</td>
                        <td> ${fn:substring(request.departure_time, 0, 5)} </td>
                        <td> ${fn:substring(request.arrival_time, 0, 5)} </td>
                        <td>${request.subscribers_count}</td>
                        <td>
                            <form action="/requests" method="post" class="d-flex align-items-center">
                                <input type="hidden" name="action" value="statusChanged">
                                <input type="hidden" name="id" value="${request.id}">
                                <select name="status" class="form-select status-select text-capitalize"
                                        data-request-id="${request.id}">
                                    <option class="bg-warning text-dark" value="pending"
                                        ${request.status == 'pending' ? 'selected' : ''}>
                                        Pending
                                    </option>
                                    <option class="bg-success text-white" value="ok"
                                        ${request.status == 'ok' ? 'selected' : ''}>
                                        Approved
                                    </option>
                                    <option class="bg-danger text-white" value="cancel"
                                        ${request.status == 'cancel' ? 'selected' : ''}>
                                        Canceled
                                    </option>
                                </select>
                                <button type="submit" class="btn btn-dark btn-sm ms-1">
                                    🔃
                                </button>
                            </form>
                        </td>
                        <td>
                            <div class="btn-group">
                                <form action="/requests" method="post">
                                    <input type="hidden" value="add" name="action"/>
                                    <input type="hidden" value="${request.id}" name="requestId"/>
                                    <button type="submit" class="btn btn-success border-light text-light btn-sm"
                                            data-bs-toggle="modal">
                                        ✅ Add it
                                    </button>
                                </form>
                                <button type="button" class="btn btn-light border-danger text-danger btn-sm ms-2"
                                        data-bs-toggle="modal"
                                        data-bs-target="#confirmDeleteModal" onclick="setDeleteId(${request.id})">
                                    ❌ Delete
                                </button>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

</div>

<!-- Modal HTML Structure -->
<div class="modal fade" id="confirmDeleteModal" tabindex="-1" aria-labelledby="confirmDeleteModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="confirmDeleteModalLabel">Confirm Deletion</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body text-center">
                <p class="mb-2 text-danger">Are you sure you want to delete this request?</p>
                <p class="text-danger fw-bold">This action cannot be undone.</p>
            </div>
            <div class="modal-footer justify-content-center">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                <button type="button" id="confirmDeleteBtn" class="btn btn-danger">Delete</button>
            </div>
        </div>
    </div>
</div>

<script>
    // Store the ID of the user to be deleted
    let IdToDelete = null;

    // Set the user ID in the modal when the Delete button is clicked
    function setDeleteId(userId) {
        IdToDelete = userId;
    }

    // When the user clicks the "Confirm Delete" button in the modal
    document.getElementById('confirmDeleteBtn').addEventListener('click', function () {
        if (IdToDelete !== null) {
            // Set the ID of the user to be deleted in the form
            document.querySelector('#deleteForm input[name="id"]').value = IdToDelete;
            // Submit the form to delete the user
            document.getElementById('deleteForm').submit();
        }
    });
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

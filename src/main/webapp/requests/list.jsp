<%@ page import="web.app.viago.model.User" %><%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-dark text-white">

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark bg-secondary">
    <div class="container-fluid">

        <a class="navbar-brand" href="/index">ViaGo</a>
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
    </div>
</nav>
<div class="container my-4">
    <div class="d-flex justify-content-between align-items-center mb-3">
        <a href="/subscriptions" class="btn btn-outline-info">⬅️ Back to Subscriptions</a>
        <h2 class="text-center flex-grow-1">Request List</h2>
        <a href="/requests?action=create" class="btn btn-success">Add New Request</a>
    </div>
    <div class="card shadow p-3">
        <div class="table-responsive" style="max-height: 60vh">
            <table class="table table-striped table-hover align-middle">
                <thead class="table-dark text-center">
                <tr>
                    <th>ID</th>
                    <th>Departure City</th>
                    <th>Arrival City</th>
                    <th>Start Date</th>
                    <th>End Date</th>
                    <th>Max Subscribers</th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <!-- Example shuttle row, dynamically populate this with backend data -->
                <c:forEach var="request" items="${requests}">
                    <tr>
                        <td>${request.id}</td>
                        <td>${request.departure_city}</td>
                        <td>${request.arrival_city}</td>
                        <td>${request.departure_start_date}</td>
                        <td>${request.arrival_end_date}</td>
                        <td>${request.subscribers_count}</td>
                        <td><span
                                class="badge text-dark text-capitalize ${request.status == "cancel" ? "bg-danger" : request.status == "ok" ? "bg-success text-white" : "bg-warning "}">${request.status}</span>
                        </td>
                        <td>
                            <div class="btn-group">

                                <a href="/requests?action=update&id=${request.id}"
                                   class="btn btn-warning btn-sm">Edit</a>
                                <form id="deleteForm" action="/requests" method="POST" style="display:none;">
                                    <input type="hidden" name="action" value="delete">
                                    <input type="hidden" name="id" value="${request.id}">
                                </form>
                                <button type="button"
                                        class="btn btn-danger btn-sm"
                                        data-bs-toggle="modal"
                                        data-bs-target="#confirmDeleteModal" onclick="setDeleteId(${request.id})">
                                    Delete
                                </button>
                            </div>

                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <c:if test="${not empty error}">
                <div class="alert alert-danger">${error}</div>
            </c:if>
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

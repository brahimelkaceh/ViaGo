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
        <h2 class="text-center flex-grow-1">Shuttle List</h2>
        <a href="/shuttles/form.jsp?action=create" class="btn btn-success">Add New Shuttle</a>
    </div>
    <div class="card shadow p-3">
        <div class="table-responsive">
            <table class="table table-striped table-hover align-middle">
                <thead class="table-dark text-center">
                <tr style="white-space: nowrap; font-size: 0.8rem ; text-align: center">
                    <th>ID</th>
                    <th>Departure City</th>
                    <th>Arrival City</th>
                    <th>Start Date</th>
                    <th>End Date</th>
                    <th>Departure Time</th>
                    <th>Arrival Time</th>
                    <th>Bus Description</th>
                    <th>Max Subscribers</th>
                    <th>Number Subscribers</th>
                    <th>Actions</th>

                </tr>
                </thead>
                <tbody>
                <!-- Example shuttle row, dynamically populate this with backend data -->
                <c:forEach var="shuttle" items="${shuttles}">
                    <tr style="white-space: nowrap; font-size: 0.8rem ; text-align: center">
                        <td>${shuttle.id}</td>
                        <td>${shuttle.departureCity}</td>
                        <td>${shuttle.arrivalCity}</td>
                        <td>${shuttle.startDate}</td>
                        <td>${shuttle.endDate}</td>
                        <td>${fn:substring(shuttle.departureTime, 0, 5)}</td>
                        <td>${fn:substring(shuttle.arrivalTime, 0, 5)}</td>
                        <td>${shuttle.busDescription}</td>
                        <td>${shuttle.maxSubscribers}</td>
                        <td>${shuttle.numSubscribers}</td>
                        <td class="d-flex justify-content-between">


                            <a href="/shuttles?action=update&id=${shuttle.id}" class="btn btn-warning btn-sm">Edit</a>

                            <form id="deleteForm" action="/shuttles" method="POST" style="display:none;">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="id" value="${shuttle.id}">
                            </form>
                            <button type="button" class="btn btn-danger btn-sm" data-bs-toggle="modal"
                                    data-bs-target="#confirmDeleteModal" onclick="setDeleteId(${shuttle.id})">
                                Delete
                            </button>
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
                <p class="mb-2 text-danger">Are you sure you want to delete this shuttle?</p>
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
    let userIdToDelete = null;

    // Set the user ID in the modal when the Delete button is clicked
    function setDeleteId(userId) {
        userIdToDelete = userId;
    }

    // When the user clicks the "Confirm Delete" button in the modal
    document.getElementById('confirmDeleteBtn').addEventListener('click', function () {
        if (userIdToDelete !== null) {
            // Set the ID of the user to be deleted in the form
            document.querySelector('#deleteForm input[name="id"]').value = userIdToDelete;
            // Submit the form to delete the user
            document.getElementById('deleteForm').submit();
        }
    });
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

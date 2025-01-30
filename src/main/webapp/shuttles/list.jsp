<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container my-4">
    <h2 class="text-center">Shuttle List</h2>
    <a href="/dashboard" class="btn btn-success">back</a>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Departure City</th>
            <th>Arrival City</th>
            <th>Start Date</th>
            <th>End Date</th>
            <th>Departure Time</th>
            <th>Arrival Time</th>
            <th>Bus Description</th>
            <th>Max Subscribers</th>
            <th>Actions</th>

        </tr>
        </thead>
        <tbody>
        <!-- Example shuttle row, dynamically populate this with backend data -->
        <c:forEach var="shuttle" items="${shuttles}">
            <tr>
                <td>${shuttle.id}</td>
                <td>${shuttle.departureCity}</td>
                <td>${shuttle.arrivalCity}</td>
                <td>${shuttle.startDate}</td>
                <td>${shuttle.endDate}</td>
                <td>${shuttle.departureTime}</td>
                <td>${shuttle.arrivalTime}</td>
                <td>${shuttle.busDescription}</td>
                <td>${shuttle.maxSubscribers}</td>
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
    <a href="/shuttles/form.jsp?action=create" class="btn btn-success">Add New Shuttle</a>
</div>

<!-- Modal HTML Structure -->
<div class="modal fade" id="confirmDeleteModal" tabindex="-1" aria-labelledby="confirmDeleteModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="confirmDeleteModalLabel">Confirm Deletion</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                Are you sure you want to delete this user? This action cannot be undone.
            </div>
            <div class="modal-footer">
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

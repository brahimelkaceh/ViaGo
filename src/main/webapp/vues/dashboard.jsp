<%@ page import="web.app.viago.model.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>ViaGo - Dashboard</title>
</head>
<body class="container bg-light">
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

<c:if test="${not empty error}">
    <div class="alert alert-danger mt-2">${error}</div>
</c:if>

<!-- Main Dashboard -->
<div class="container mt-4">
    <section id="shuttles" class="mt-5">
        <div class="d-flex justify-content-between">
            <a class="btn btn-info mb-3" href="/users?action=list">
                Manage Users
            </a>
            <a class="btn btn-danger mb-3" href="/shuttles?action=list">
                Manage Shuttles
            </a>
            <button class="btn btn-success mb-3" data-bs-toggle="modal" data-bs-target="#addShuttleModal">
                Add Shuttle
            </button>
        </div>
        <h2>Manage Shuttles </h2>

        <!-- Button to trigger the Add Shuttle Form -->
        <button class="btn btn-success mb-3" data-bs-toggle="modal" data-bs-target="#addShuttleModal">
            Add Shuttle
        </button>

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

                        <a href="/dashboard?action=update&id=${shuttle.id}" class="btn btn-warning btn-sm"
                           data-bs-toggle="modal" data-bs-target="#editShuttleModal">Edit</a>

                        <form id="deleteForm" action="/dashboard" method="POST" style="display:none;">
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

        <!-- Add Shuttle Modal -->
        <div class="modal fade" id="addShuttleModal" tabindex="-1" aria-labelledby="addShuttleModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="addShuttleModalLabel">Add Shuttle</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <!-- Add Shuttle Form -->
                        <form action="addShuttle" method="post">

                            <div class="mb-3">
                                <label for="departureCity" class="form-label">Departure City</label>
                                <input type="text" class="form-control" id="departureCity" name="departureCity"
                                       required>
                            </div>
                            <div class="mb-3">
                                <label for="arrivalCity" class="form-label">Arrival City</label>
                                <input type="text" class="form-control" id="arrivalCity" name="arrivalCity" required>
                            </div>
                            <div class="mb-3">
                                <label for="startDate" class="form-label">Start Date</label>
                                <input type="date" class="form-control" id="startDate" name="startDate" required>
                            </div>
                            <div class="mb-3">
                                <label for="endDate" class="form-label">End Date</label>
                                <input type="date" class="form-control" id="endDate" name="endDate" required>
                            </div>
                            <div class="mb-3">
                                <label for="departureTime" class="form-label">Departure Time</label>
                                <input type="time" class="form-control" id="departureTime" name="departureTime"
                                       required>
                            </div>
                            <div class="mb-3">
                                <label for="arrivalTime" class="form-label">Arrival Time</label>
                                <input type="time" class="form-control" id="arrivalTime" name="arrivalTime" required>
                            </div>
                            <div class="mb-3">
                                <label for="busDescription" class="form-label">Bus Description</label>
                                <textarea class="form-control" id="busDescription" name="busDescription"
                                          required></textarea>
                            </div>
                            <div class="mb-3">
                                <label for="maxSubscribers" class="form-label">Max Subscribers</label>
                                <input type="number" class="form-control" id="maxSubscribers" name="maxSubscribers"
                                       required>
                            </div>
                            <button type="submit" class="btn btn-primary">Add Shuttle</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <!-- Edit Shuttle Modal -->
        <div class="modal fade" id="editShuttleModal" tabindex="-1" aria-labelledby="editShuttleModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="editShuttleModalLabel">Edit Shuttle</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <!-- Edit Shuttle Form -->
                        <form action="editShuttle" method="post">
                            <input type="hidden" id="editShuttleId" name="id">

                            <div class="mb-3">
                                <label for="editDepartureCity" class="form-label">Departure City</label>
                                <input type="text" class="form-control" id="editDepartureCity" name="departureCity"
                                       required>
                            </div>
                            <div class="mb-3">
                                <label for="editArrivalCity" class="form-label">Arrival City</label>
                                <input type="text" class="form-control" id="editArrivalCity" name="arrivalCity"
                                       required>
                            </div>
                            <div class="mb-3">
                                <label for="editStartDate" class="form-label">Start Date</label>
                                <input type="date" class="form-control" id="editStartDate" name="startDate" required>
                            </div>
                            <div class="mb-3">
                                <label for="editEndDate" class="form-label">End Date</label>
                                <input type="date" class="form-control" id="editEndDate" name="endDate" required>
                            </div>
                            <div class="mb-3">
                                <label for="editDepartureTime" class="form-label">Departure Time</label>
                                <input type="time" class="form-control" id="editDepartureTime" name="departureTime"
                                       required>
                            </div>
                            <div class="mb-3">
                                <label for="editArrivalTime" class="form-label">Arrival Time</label>
                                <input type="time" class="form-control" id="editArrivalTime" name="arrivalTime"
                                       required>
                            </div>
                            <div class="mb-3">
                                <label for="editBusDescription" class="form-label">Bus Description</label>
                                <textarea class="form-control" id="editBusDescription" name="busDescription"
                                          required></textarea>
                            </div>
                            <div class="mb-3">
                                <label for="editMaxSubscribers" class="form-label">Max Subscribers</label>
                                <input type="number" class="form-control" id="editMaxSubscribers" name="maxSubscribers"
                                       required>
                            </div>
                            <button type="submit" class="btn btn-primary">Save Changes</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <%--Delete Shuttle Modal--%>
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
    </section>
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
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
<%--<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>--%>
<%--<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>--%>
</body>
</html>
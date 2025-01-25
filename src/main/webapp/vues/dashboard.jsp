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
        <h2>Manage Shuttles  </h2>

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
                        <a href="/dashboard?action=update&id=${shuttle.id}" class="btn btn-warning btn-sm">Edit</a>
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
    </section>
    <!-- Section: Requests -->
<%--    <section id="requests">--%>
<%--        <h2>Manage Requests</h2>--%>
<%--        <table class="table table-striped">--%>
<%--            <thead>--%>
<%--            <tr>--%>
<%--                <th>#</th>--%>
<%--                <th>Request ID</th>--%>
<%--                <th>Customer Name</th>--%>
<%--                <th>Status</th>--%>
<%--                <th>Actions</th>--%>
<%--            </tr>--%>
<%--            </thead>--%>
<%--            <tbody>--%>
<%--            <tr>--%>
<%--                <td>1</td>--%>
<%--                <td>REQ123</td>--%>
<%--                <td>John Doe</td>--%>
<%--                <td><span class="badge bg-warning">Pending</span></td>--%>
<%--                <td>--%>
<%--                    <button class="btn btn-success btn-sm">Approve</button>--%>
<%--                    <button class="btn btn-danger btn-sm">Reject</button>--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--            </tbody>--%>
<%--        </table>--%>
<%--    </section>--%>

    <!-- Section: Users -->
<%--    <section id="users" class="mt-5">--%>
<%--        <h2>Manage Users</h2>--%>
<%--        <table class="table table-striped">--%>
<%--            <thead>--%>
<%--            <tr>--%>
<%--                <th>#</th>--%>
<%--                <th>User ID</th>--%>
<%--                <th>Name</th>--%>
<%--                <th>Email</th>--%>
<%--                <th>Status</th>--%>
<%--                <th>Actions</th>--%>
<%--            </tr>--%>
<%--            </thead>--%>
<%--            <tbody>--%>
<%--            <tr>--%>
<%--                <td>1</td>--%>
<%--                <td>USR001</td>--%>
<%--                <td>Jane Smith</td>--%>
<%--                <td>jane@example.com</td>--%>
<%--                <td><span class="badge bg-success">Active</span></td>--%>
<%--                <td>--%>
<%--                    <button class="btn btn-primary btn-sm">Edit</button>--%>
<%--                    <button class="btn btn-danger btn-sm">Deactivate</button>--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--            </tbody>--%>
<%--        </table>--%>
<%--    </section>--%>



    <!-- Section: Subscriptions -->
<%--    <section id="subscriptions" class="mt-5">--%>
<%--        <h2>Manage Subscriptions</h2>--%>
<%--        <table class="table table-striped">--%>
<%--            <thead>--%>
<%--            <tr>--%>
<%--                <th>#</th>--%>
<%--                <th>Subscription ID</th>--%>
<%--                <th>Plan</th>--%>
<%--                <th>Status</th>--%>
<%--                <th>Actions</th>--%>
<%--            </tr>--%>
<%--            </thead>--%>
<%--            <tbody>--%>
<%--            <tr>--%>
<%--                <td>1</td>--%>
<%--                <td>SUB001</td>--%>
<%--                <td>Premium</td>--%>
<%--                <td><span class="badge bg-success">Active</span></td>--%>
<%--                <td>--%>
<%--                    <button class="btn btn-warning btn-sm">Renew</button>--%>
<%--                    <button class="btn btn-danger btn-sm">Cancel</button>--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--            </tbody>--%>
<%--        </table>--%>
<%--    </section>--%>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
<%--<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>--%>
<%--<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>--%>
</body>
</html>
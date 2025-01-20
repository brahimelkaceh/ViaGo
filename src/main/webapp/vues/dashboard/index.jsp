<%@ page import="web.app.viago.model.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    <div class="ms-auto text-white">
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


<div class="container bg-dark  mt-5 p-2 rounded">
    <div class="row  row-cols-3 g-3">
        <div class="col">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Total Routes</h5>
                    <p class="card-text">3</p>
                </div>
            </div>
        </div>
        <div class="col">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Active Shuttles</h5>
                    <p class="card-text">12</p>
                </div>
            </div>
        </div>
        <div class="col">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Today's Bookings</h5>
                    <p class="card-text">129</p>
                </div>
            </div>
        </div>
        <div class="col">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Total Passengers</h5>
                    <p class="card-text">1329</p>
                </div>
            </div>
        </div>
        <div class="col">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Shuttle Services</h5>
                    <p class="card-text">9</p>
                </div>
            </div>
        </div>
        <div class="col">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Subscription Requests</h5>
                    <p class="card-text">2</p>
                </div>
            </div>
        </div>
    </div>
    <div class="container mt-2 bg-white py-2">
        <a class="btn btn-primary" href="/create" role="button">Manage Shuttle Services</a>
        <a class="btn btn-primary" href="/users" role="button">Manage Users</a>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
<%--<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>--%>
<%--<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>--%>
</body>
</html>
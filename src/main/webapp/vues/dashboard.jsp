<%@ page import="web.app.viago.model.Company" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>ViaGo - Dashboard</title>
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

<c:if test="${not empty error}">
    <div class="alert alert-danger mt-2">${error}</div>
</c:if>

<!-- Main Dashboard -->
<div class="container mt-4">
    <div class="row">
        <div class="col-md-4">
            <div class="card text-white bg-info shadow">
                <div class="card-body text-center">
                    <h5 class="card-title">Manage Users</h5>
                    <p class="card-text">View and manage registered users.</p>
                    <a href="/users?action=list" class="btn btn-light">Go to Users</a>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card text-white bg-danger shadow">
                <div class="card-body text-center">
                    <h5 class="card-title">Manage Shuttles</h5>
                    <p class="card-text">Monitor and schedule shuttles.</p>
                    <a href="/shuttles?action=list" class="btn btn-light">Go to Shuttles</a>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card text-dark bg-warning shadow">
                <div class="card-body text-center">
                    <h5 class="card-title">Manage Requests</h5>
                    <p class="card-text">Handle user travel requests.</p>
                    <a href="/requests?action=list" class="btn btn-dark">Go to Requests</a>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
<%--<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>--%>
<%--<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>--%>
</body>
</html>
<%--
  Created by IntelliJ IDEA.
  User: ELKACEH BRAHIM
  Date: 20/01/2025
  Time: 19:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>My Requests</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container py-4">
    <h1 class="text-center mb-4">My Requests</h1>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>Request</th>
            <th>Status</th>
            <th>Interested Users</th>
        </tr>
        </thead>
        <tbody>
        <!-- Replace with dynamic data -->
        <tr>
            <td>City A to City B</td>
            <td><span class="badge bg-warning">Being Considered</span></td>
            <td>3</td>
        </tr>
        <tr>
            <td>City C to City D</td>
            <td><span class="badge bg-success">Accepted</span></td>
            <td>5</td>
        </tr>
        <!-- Repeat -->
        </tbody>
    </table>
</div>
</body>
</html>


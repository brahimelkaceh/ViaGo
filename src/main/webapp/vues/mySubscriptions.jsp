<%--
  Created by IntelliJ IDEA.
  User: ELKACEH BRAHIM
  Date: 20/01/2025
  Time: 19:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>My Subscriptions</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container py-4">
    <h1 class="text-center mb-4">My Subscriptions</h1>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>Subscription</th>
            <th>Status</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <!-- Replace with dynamic data -->
        <tr>
            <td>City A to City B</td>
            <td><span class="badge bg-success">Confirmed</span></td>
            <td>
                <button class="btn btn-danger btn-sm">Cancel</button>
            </td>
        </tr>
        <tr>
            <td>City C to City D</td>
            <td><span class="badge bg-warning">Pending</span></td>
            <td>
                <button class="btn btn-danger btn-sm">Cancel</button>
            </td>
        </tr>
        <!-- Repeat -->
        </tbody>
    </table>
</div>
</body>
</html>


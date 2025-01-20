<%--
  Created by IntelliJ IDEA.
  User: ELKACEH BRAHIM
  Date: 20/01/2025
  Time: 20:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>View User Requests</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container py-4">
    <h1 class="text-center mb-4">View User Requests</h1>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>Request</th>
            <th>Details</th>
            <th>Interested Users</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <!-- Replace with dynamic content -->
        <tr>
            <td>City A to City B</td>
            <td>
                Time: 09:00 AM<br>
                Period: 01/01/2024 - 01/31/2024
            </td>
            <td>10</td>
            <td>
                <button class="btn btn-primary btn-sm">Accept & Create Offer</button>
            </td>
        </tr>
        <tr>
            <td>City C to City D</td>
            <td>
                Time: 02:00 PM<br>
                Period: 01/15/2024 - 01/20/2024
            </td>
            <td>5</td>
            <td>
                <button class="btn btn-primary btn-sm">Accept & Create Offer</button>
            </td>
        </tr>
        <!-- Repeat -->
        </tbody>
    </table>
</div>
</body>
</html>


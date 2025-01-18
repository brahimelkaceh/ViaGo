<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>User Details</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container my-4">
  <h2 class="text-center">User Details</h2>
  <div class="card">
    <div class="card-body">
      <h5 class="card-title">${user.name}</h5>
      <p class="card-text"><strong>Email:</strong> ${user.email}</p>
      <p class="card-text"><strong>Role:</strong> ${user.role}</p>
      <p class="card-text"><strong>Phone:</strong> ${user.phoneNumber}</p>
      <a href="/users?action=list" class="btn btn-secondary">Back to List</a>
    </div>
  </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

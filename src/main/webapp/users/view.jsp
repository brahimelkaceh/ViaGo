<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>View User</title>
</head>
<body>
<h1>User Details</h1>
<table >
  <tr>
    <td>ID</td>
    <td>${user.id}</td>
  </tr>
  <tr>
    <td>Name</td>
    <td>${user.name}</td>
  </tr>
  <tr>
    <td>Email</td>
    <td>${user.email}</td>
  </tr>
  <tr>
    <td>Role</td>
    <td>${user.role}</td>
  </tr>
  <tr>
    <td>Phone</td>
    <td>${user.phoneNumber}</td>
  </tr>
</table >
<a href="/users?action=list">Back to User List</a>
</body>
</html>

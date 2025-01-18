<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${param.action == 'update' ? 'Update User' : 'Add New User'}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container my-4">
    <h2 class="text-center">${param.action == 'update' ? 'Update User' : 'Add New User'}</h2>
    <form action="/users" method="post">
        <input type="hidden" name="action" value="${param.action}">
        <c:if test="${param.action == 'update'}">
            <input type="hidden" name="id" value="${param.id}">
        </c:if>
        <div class="mb-3">
            <label for="name" class="form-label">Name</label>
            <input type="text" class="form-control" id="name" name="name" value="${user != null ? user.name : ''}"
                   required>
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input type="email" class="form-control" id="email" name="email" value="${user != null ? user.email : ''}"
                   required>
        </div>
        <div class="mb-3">
            <% if ("create".equals(request.getParameter("action"))) { %>
            <label for="password" class="form-label">Password</label>
            <input type="password" class="form-control" id="password" name="password"
                   value="${user != null ? user.password : ''}" required>
            <% } else { %>
            <!-- For update, no password field is shown -->
            <input type="hidden" id="password" name="password" value="${user != null ? user.password : ''}">
            <% } %>
        </div>

        <div class="mb-3">
            <label for="role" class="form-label">Role</label>
            <select class="form-control" id="role" name="role" required>
                <option value="user" ${user != null && user.role == 'user' ? 'selected' : ''}>USER</option>
                <option value="company" ${user != null && user.role == 'company' ? 'selected' : ''}>COMPANY</option>

            </select>
        </div>

        <div class="mb-3">
            <label for="phone_number" class="form-label">Phone Number</label>
            <input type="text" class="form-control" id="phone_number" name="phone_number"
                   value="${user != null ? user.phoneNumber : ''}" required>
        </div>
        <button type="submit" class="btn btn-primary">${param.action == 'update' ? 'Update' : 'Add'} User</button>
        <a href="/users?action=list" class="btn btn-secondary">Cancel</a>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

<%@ page import="web.app.viago.model.Company" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${param.action == 'update' ? 'Update User' : 'Add New User'}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
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

<div class="container my-5 d-flex justify-content-center text-dark">
    <div class="card shadow-lg p-4" style="max-width: 500px; width: 100%;">
        <div class="d-flex justify-content-between align-items-center mb-3">
            <a href="/users?action=list" class="btn-sm btn btn-outline-info">⬅️ Back to Requests List</a>
            <h2 class="text-center ">${param.action == 'update' ? 'Update User' : 'Add New User'}</h2>
        </div>
        <form action="/users" method="post">
            <input type="hidden" name="action" value="${param.action}">
            <c:if test="${param.action == 'update'}">
                <input type="hidden" name="id" value="${param.id}">
            </c:if>

            <input type="text" class="form-control" id="name" name="name" value="${user != null ? user.name : ''}"
                   required>
            <label for="name" class="form-label">Name</label>
            <input type="email" class="form-control" id="email" name="email"
                   value="${user != null ? user.email : ''}"
                   required>
            <label for="email" class="form-label">Email</label>
            <% if ("create".equals(request.getParameter("action"))) { %>
            <input type="password" class="form-control" id="password" name="password"
                   value="${user != null ? user.password : ''}" required>
            <label for="password" class="form-label">Password</label>
            <% } else { %>
            <!-- For update, no password field is shown -->
            <input type="hidden" id="password" name="password" value="${user != null ? user.password : ''}">
            <% } %>
            <div>
                <input type="text" class="form-control" id="phone_number" name="phone_number"
                       value="${user != null ? user.phoneNumber : ''}" required>
                <label for="phone_number" class="form-label">Phone Number</label>
            </div>
            <select class="form-control my-3 form-select-sm" id="role" name="role" required>
                <option selected>Role</option>
                <option value="user" ${user != null && user.role == 'user' ? 'selected' : ''}>USER</option>
                <option value="company" ${user != null && user.role == 'company' ? 'selected' : ''}>COMPANY</option>

            </select>


            <div class="d-flex justify-content-end  align-items-center">
                <button type="submit" class="btn btn-primary mx-1">${param.action == 'update' ? 'Update' : 'Add'} User
                </button>
                <a href="/users?action=list" class="btn btn-secondary">Cancel</a>
            </div>

        </form>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

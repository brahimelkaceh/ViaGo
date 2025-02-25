<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="web.app.viago.model.Company" %>
<%@ page import="web.app.viago.model.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Viago - Home</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .hero-section {
            background: linear-gradient(to bottom, rgba(0, 0, 0, 0.7), rgba(0, 0, 0, 0.7)), url('https://images.pexels.com/photos/30154480/pexels-photo-30154480/free-photo-of-dusk-at-the-beach-with-urban-skyline.jpeg?auto=compress&cs=tinysrgb&w=600&lazy=load') center/cover;
            color: white;
            padding: 100px 0;
            text-align: center;
        }

        .feature-icon {
            font-size: 2rem;
            color: #0d6efd;
        }
    </style>
</head>
<body>
<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="/index">ViaGo</a>

        <%
            User user = (User) session.getAttribute("user");
            Company company = (Company) session.getAttribute("company");
            if (user != null) {
        %>
        <div class="ms-auto text-white d-flex flex-row align-items-center">
            <span class="text-white me-3">Hello, <%= user.getName() %>!</span>
            <a href="logout" class="btn btn-outline-light">Logout</a>
        </div>
        <%
        } else if (company != null) {
        %>
        <div class="ms-auto text-white d-flex flex-row align-items-center">
            <a href="/dashboard" class="btn btn-sm btn-outline-info"> Dashboard</a>
            <span class="text-white mx-3">Hello, <%= company.getName() %>!</span>
            <a href="logout" class="btn btn-outline-light">Logout</a>
        </div>
        <%
        } else {
        %>
        <div class="ms-auto text-white d-flex flex-row align-items-center">
            <a href="vues/login.jsp" class="btn btn-outline-light me-3">Login</a>
            <a href="vues/register.jsp" class="btn btn-outline-light">Register</a>
        </div>

        <%
            }
        %>

    </div>
</nav>
<!-- Hero Section -->
<section class="hero-section">
    <div class="container">
        <h1>Welcome to Viago</h1>
        <p>Your one-stop solution for managing shuttle subscriptions and travel requests.</p>
        <a href="subscriptions" class="btn btn-primary btn-lg">Explore Subscriptions</a>

        <%
            if (user == null && company == null) {
        %>
        <a class="btn btn-outline-light btn-lg" href="vues/register.jsp">Register</a>

        <%
            }
        %>

    </div>
</section>

<!-- How It Works Section -->
<section class="py-5" style="height: 100%;">
    <div class="container">
        <h2 class="text-center mb-4">How It Works</h2>
        <div class="row text-center">
            <div class="col-md-6">
                <i class="feature-icon bi bi-person"></i>
                <h5 class="mt-3">For Users</h5>
                <p>Search, subscribe, and manage your shuttle services with ease.</p>
            </div>
            <div class="col-md-6">
                <i class="feature-icon bi bi-building"></i>
                <h5 class="mt-3">For Companies</h5>
                <p>Create offers, manage requests, and monitor subscriptions effortlessly.</p>
            </div>
        </div>
    </div>
</section>


<!-- Footer -->
<footer class="bg-dark text-white py-4">
    <div class="container text-center">
        <p>Contact Us: info@viago.com | 123-456-7890</p>
        <p><a href="terms.jsp" class="text-white text-decoration-none">Terms of Service</a> | <a href="privacy.jsp"
                                                                                                 class="text-white text-decoration-none">Privacy
            Policy</a></p>
    </div>
</footer>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>


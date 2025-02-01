<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    <div class="container">
        <a class="navbar-brand" href="#">Viago</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link active" href="#">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="vues/login.jsp">Login</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="vues/register.jsp">Register</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="vues/subscriptions.jsp">Subscriptions</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<!-- Hero Section -->
<section class="hero-section">
    <div class="container">
        <h1>Welcome to Viago</h1>
        <p>Your one-stop solution for managing shuttle subscriptions and travel requests.</p>
        <a href="subscriptions" class="btn btn-primary btn-lg">Explore Subscriptions</a>
        <a href="register.jsp" class="btn btn-outline-light btn-lg">Register Now</a>
    </div>
</section>

<!-- How It Works Section -->
<section class="py-5">
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

<!-- Featured Subscriptions Section -->
<section class="bg-light py-5">
    <div class="container">
        <h2 class="text-center mb-4">Featured Subscriptions</h2>
        <div class="row">
            <!-- Dynamic content can go here -->
            <div class="col-md-4">
                <div class="card">
                    <img src="https://via.placeholder.com/350x200" class="card-img-top" alt="Subscription">
                    <div class="card-body">
                        <h5 class="card-title">City A to City B</h5>
                        <p class="card-text">10 spots available. Book now!</p>
                        <a href="#" class="btn btn-primary">View Details</a>
                    </div>
                </div>
            </div>
            <!-- Add more cards dynamically -->
        </div>
    </div>
</section>

<!-- Footer -->
<footer class="bg-dark text-white py-4">
    <div class="container text-center">
        <p>Contact Us: info@shuttleease.com | 123-456-7890</p>
        <p><a href="terms.jsp" class="text-white text-decoration-none">Terms of Service</a> | <a href="privacy.jsp"
                                                                                                 class="text-white text-decoration-none">Privacy
            Policy</a></p>
    </div>
</footer>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>


<%@ page import="uz.web.domain.DAO.UserDao" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Courses Page</title>
    <!-- Include Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        body {
            background-color: white; /* Light grey background */
            color: #212529; /* Dark grey text */
            height: 100vh;
            display: flex;
            flex-direction: column;
            margin: 0;
        }
        .container {
            padding: 20px;
        }
        .course-card {
            background-color: #F1F5F9;
            border: 1px solid #343a40;
            border-radius: 10px;
            margin: 20px;
            padding: 20px;
        }
        .course-card img {
            max-width: 100%;
            height: auto;
            border-bottom: 5px solid #343a40; /* Dark grey bottom border for image */
        }
        .course-card .btn-primary {
            background-color: #343a40; /* Dark grey button */
            border-color: #343a40; /* Dark grey button border */
        }
    </style>
</head>
<body>
<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom border-1 p-3 border-dark" style="font-size: 23px">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/user/main-menu">
            <img src="https://raw.githubusercontent.com/reactanvar/42.uz-loyihasi/main/favicon.ico" alt="Logo" width="40" height="40" class="d-inline-block align-text-top">
        </a>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto d-flex align-items-center">
                <!-- User Balance -->
                <li class="nav-item me-3">
                    <span class="navbar-text">
                        <strong>Balans: <%= ((UserDao) session.getAttribute("user")).getBalance() %></strong>
                    </span>
                </li>
                <!-- Dropdown Menu -->
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <i class="bi bi-list"></i>
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/payments">To'lovlar</a></li>
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/profile">Profile</a></li>
                        <li><hr class="dropdown-divider"></li>
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/">Chiqish</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>


<div class="container">
    <c:if test="${not empty userCourses}">
        <h2>Mening Kurslarim</h2>
        <div class="row">
            <c:forEach var="course" items="${userCourses}">
                <div class="col-md-4">
                    <div class="course-card">
                        <img src="${course.imageUrl}" alt="Course Image">
                        <h3>${course.name}</h3>
                        <p>${course.description}</p>
                        <a class="btn btn-dark" href="/course/u-modules/${course.id}">Kursga o'tish</a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:if>
</div>

<div class="container">
    <h2>Barcha Kurslar</h2>
    <div class="row">
        <c:forEach var="course" items="${courses}">
            <div class="col-md-4">
                <div class="course-card">
                    <img src="${course.imageUrl}" alt="Course Image">
                    <h3>${course.name}</h3>
                    <p>${course.description}</p>
                    <p><strong>Price:</strong> ${course.price}</p>
                    <p><strong>Mentor:</strong> ${course.mentor}</p>
                    <a class="btn btn-dark" href="/course/u-modules/${course.id}">Kursga o'tish</a>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>

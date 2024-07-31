<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Course Modules Page</title>
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
        .rating {
            color: gold;
        }
        .rating .bi-star-fill {
            font-size: 1.2rem;
        }
        .comment {
            background-color: #F8F9FA;
            border: 1px solid #DEE2E6;
            border-radius: 5px;
            padding: 10px;
            margin-top: 10px;
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
            <ul class="navbar-nav ms-auto">
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
    <!-- Course Details -->
    <div class="course-card">
        <h2>${course.name}</h2>
        <p>${course.description}</p>
        <p><strong>Price:</strong> ${course.price}</p>
        <p><strong>Mentor:</strong> ${course.mentor}</p>
        <c:if test="${}">
            <a class="btn btn-dark" href="${pageContext.request.contextPath}/buy/${course.id}">Buy Course</a>
        </c:if>
    </div>

    <div class="course-card">
        <h3>Course Rating</h3>
        <div class="rating">
            <i class="bi bi-star-fill"></i>
            <i class="bi bi-star-fill"></i>
            <i class="bi bi-star-fill"></i>
            <i class="bi bi-star-fill"></i>
            <i class="bi bi-star-fill"></i>
        </div>
    </div>

    <!-- Course Modules -->
    <div class="container">
        <h2>Course Modules</h2>
        <div class="row">
            <c:forEach var="module" items="${modules}">
                <div class="col-md-4">
                    <div class="course-card">
                        <h4>${module.name}</h4>
                        <p><strong>Order Number:</strong> ${module.orderNum}</p>
                        <p>${module.description}</p>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    <!-- Course Comments -->
    <div class="container">
        <h2>Course Comments</h2>
        <c:forEach var="comment" items="${comments}">
            <div class="comment">
                <p><strong>${comment.userName}:</strong></p>
                <p>${comment.text}</p>
            </div>
        </c:forEach>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>

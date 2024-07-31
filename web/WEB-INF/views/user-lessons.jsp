<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="uz.web.domain.DAO.UserDao" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lesson Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        body {
            background-color: white;
            color: #212529;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }
        .container {
            padding: 20px;
            flex-grow: 1;
        }
        .card {
            background-color: #F1F5F9;
            border: 1px solid #343a40;
            border-radius: 10px;
            margin-bottom: 20px;
            padding: 20px;
        }
        .lessons-card {
            height: 100%;
            overflow-y: auto;
        }
        .table-wrapper {
            max-height: 600px;
            overflow-y: auto;
        }
        .btn-dark {
            background-color: #343a40;
            border-color: #343a40;
        }
        .error-message {
            position: fixed;
            top: 20px;
            right: 20px;
            background-color: #dc3545;
            color: #fff;
            padding: 15px;
            border-radius: 5px;
            font-size: 0.875rem;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            z-index: 1050; /* Ensure it's above other content */
            opacity: 1;
            transition: opacity 0.5s ease-in-out;
        }
        .error-message.hidden {
            opacity: 0;
        }
    </style>
</head>
<body>
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

<c:if test="${not empty errorMessage}">
    <div class="error-message" id="error-message">
        <c:out value="${errorMessage}"/>
    </div>
</c:if>

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="card lessons-card">
                <h3>Kurs Darslari</h3>
                <div class="table-wrapper">
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Dars Nomi</th>
                            <th scope="col">Video Davomiyligi</th>
                            <th scope="col">Amal</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="lesson" items="${lessons}">
                            <tr>
                                <td>${lesson.orderNum}</td>
                                <td>${lesson.name}</td>
                                <td>${lesson.videoDuration}</td>
                                <td>
                                    <a class="btn btn-dark" href="/lesson/${lesson.id}">Videoni Ko'rish</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script>
    window.addEventListener('DOMContentLoaded', (event) => {
        const errorMessage = document.getElementById('error-message');
        if (errorMessage) {
            setTimeout(() => {
                errorMessage.classList.add('hidden');
            }, 5000);
        }
    });
</script>
</body>
</html>

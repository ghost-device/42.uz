<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="uz.web.domain.DAO.UserDao" %>
<!DOCTYPE html>
<html>
<head>
    <title>Course Modules Page</title>
    <!-- Include Bootstrap CSS -->
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
        .modules-card {
            height: 100%;
            overflow-y: auto;
        }
        .rating .bi-star-fill {
            font-size: 1.2rem;
            color: gold;
        }
        .comment {
            background-color: #F8F9FA;
            border: 1px solid #DEE2E6;
            border-radius: 5px;
            padding: 10px;
            margin-top: 10px;
        }
        .table-wrapper {
            max-height: 600px;
            overflow-y: auto;
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
        .rating-form, .comment-form {
            margin-top: 20px;
        }
        .rating-form input[type="radio"] {
            display: none;
        }
        .rating-form label {
            font-size: 1.5rem;
            color: gold;
            cursor: pointer;
        }
        .rating-form input[type="radio"]:checked ~ label {
            color: #ffbb00;
        }

        .rating-display {
            display: flex;
            justify-content: center; /* Center the stars horizontally */
            align-items: center; /* Center the stars vertically */
            margin-top: 10px; /* Add some spacing above the stars */
        }

        .rating-display i {
            font-size: 2rem; /* Increase the size of the stars */
            color: #e0e0e0;
            margin: 0 2px;
        }

        .rating-display i.filled {
            color: gold;
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

<!-- Error Message -->
<c:if test="${not empty errorMessage}">
    <div class="error-message" id="error-message">
        <c:out value="${errorMessage}"/>
    </div>
</c:if>

<div class="container">
    <div class="row">
        <div class="col-md-4">
            <div class="card">
                <img src="${course.imageUrl}" class="card-img-top img-fluid">
                <h2>${course.name}</h2>
                <p>${course.description}</p>
                <c:if test="${!isBuy}">
                    <p><strong>Narx:</strong> ${course.price}</p>
                </c:if>
                <p><strong>Mentor:</strong> ${course.mentor}</p>
                <c:if test="${!isBuy}">
                    <a class="btn btn-dark w-100" href="${pageContext.request.contextPath}/purchase/${course.id}">Sotib olish</a>
                </c:if>
            </div>

            <div class="card">
                <c:if test="${isBuy}">
                    <form action="${pageContext.request.contextPath}/rating/rate" method="post" class="rating-form">
                        <div class="form-group">
                            <select id="rating" name="rating" class="form-select">
                                <option value="1">1 Yulduz</option>
                                <option value="2">2 Yulduz</option>
                                <option value="3">3 Yulduz</option>
                                <option value="4">4 Yulduz</option>
                                <option value="5">5 Yulduz</option>
                            </select>
                        </div>
                        <input type="hidden" name="courseId" value="${course.id}">
                        <button type="submit" class="btn btn-dark mt-2">Yuborish</button>
                    </form>
                </c:if>

                <div class="rating-display">
                    <c:forEach var="i" begin="1" end="5">
                        <i class="bi bi-star-fill <c:if test="${i <= rating}">filled</c:if>"></i>
                    </c:forEach>
                </div>
            </div>

            <div class="card">
                <h3>Izohlar</h3>
                <c:if test="${empty comments}">
                    <p>Izohlar mavjud emas!</p>
                </c:if>
                <c:forEach var="comment" items="${comments}">
                    <div class="comment">
                        <p><strong>${comment.userName}:</strong></p>
                        <p>${comment.text}</p>
                    </div>
                </c:forEach>

                <c:if test="${isBuy}">
                    <div class="comment-form">
                        <form action="${pageContext.request.contextPath}/comment/add" method="post">
                            <div class="mb-3">
                                <label for="commentText" class="form-label">Izohingizni kiriting:</label>
                                <textarea class="form-control" id="commentText" name="text" rows="3" required></textarea>
                            </div>
                            <input type="hidden" name="courseId" value="${course.id}">
                            <button type="submit" class="btn btn-dark">Yuborish</button>
                        </form>
                    </div>
                </c:if>
            </div>
        </div>
        <div class="col-md-8">
            <div class="card modules-card">
                <h3>Kursning Modullari</h3>
                <div class="table-wrapper">
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Modul Nomi</th>
                            <th scope="col">Modul Haqida</th>
                            <th scope="col">Darslar</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="module" items="${modules}">
                            <tr>
                                <td>${module.orderNum}</td>
                                <td>${module.name}</td>
                                <td>${module.description}</td>
                                <td>
                                    <a class="btn btn-dark" href="/module/u-lessons/${module.id}">Darslar</a>
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

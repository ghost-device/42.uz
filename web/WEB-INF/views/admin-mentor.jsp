<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mentor Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <style>
        .card {
            margin-bottom: 20px;
        }
        .form-container {
            margin-top: 40px;
        }
    </style>
</head>
<body>

<div class="container mt-5">
    <nav class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse vertical-nav">
        <div class="position-sticky">
            <ul class="nav flex-column">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="#">
                        <img src="https://raw.githubusercontent.com/reactanvar/42.uz-loyihasi/main/favicon.ico" alt="Logo" width="50" height="50" class="d-inline-block align-text-top">
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#"><i class="bi bi-cash-stack"></i> Payments</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#"><i class="bi bi-person-circle"></i> Mentors</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#"><i class="bi bi-box-arrow-right"></i> Log Out</a>
                </li>
            </ul>
        </div>
    </nav>

    <div class="form-container">
        <h2>Create New Mentor</h2>

        <form action="${pageContext.request.contextPath}/mentor/create" method="post">
            <div class="mb-3">
                <label for="mentorName" class="form-label">Mentor Name</label>
                <input type="text" class="form-control" id="mentorName" name="name" placeholder="Enter mentor name" required>
            </div>
            <div class="mb-3">
                <label for="mentorBiography" class="form-label">Biography</label>
                <textarea class="form-control" id="mentorBiography" name="biography" rows="3" placeholder="Enter mentor biography" required></textarea>
            </div>
            <button type="submit" class="btn btn-primary">Create Mentor</button>
        </form>
    </div>

    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger" role="alert">
                ${errorMessage}
        </div>
    </c:if>

    <div class="mt-5">
        <h2>Mentors</h2>
        <div class="row">
            <c:forEach var="mentor" items="${mentors}">
                <div class="col-md-4">
                    <div class="card shadow-sm">
                        <div class="card-body">
                            <h5 class="card-title">${mentor.name}</h5>
                            <p class="card-text">${mentor.biography}</p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>

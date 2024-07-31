<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Courses Page</title>
  <!-- Include Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <style>
    body {
      background-color: white; /* Light grey background */
      color: #212529;
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
<nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom border-1 p-3 border-dark" style="font-size: 23px">
  <div class="container-fluid">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/">
      <img src="https://raw.githubusercontent.com/reactanvar/42.uz-loyihasi/main/favicon.ico" alt="Logo" width="40" height="40" class="d-inline-block align-text-top">
    </a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="#">Kurslar</a>
        </li>
      </ul>
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath}/auth/login">Kirish</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath}/verification/verify-email">Ro'yhatdan o'tish</a>
        </li>
      </ul>
    </div>
  </div>
</nav>

<!-- Courses Content -->
<div class="container">
  <div class="row">
    <c:forEach var="course" items="${courses}">
      <div class="col-md-4">
        <div class="course-card">
          <img src="${course.imageUrl}" alt="Course Image">
          <h3>${course.name}</h3>
          <p>${course.description}</p>
          <p><strong>Price:</strong> ${course.price}</p>
          <p><strong>Mentor:</strong> ${course.mentor}</p>
        </div>
      </div>
    </c:forEach>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>

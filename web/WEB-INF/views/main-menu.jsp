<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
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
            display: flex;
            flex: 1;
            flex-direction: row;
            align-items: center;
            justify-content: center;
            padding: 20px;
        }
        .text-content {
            flex: 1;
            text-align: left;
            font-size: 2rem; /* Increase font size for larger text */
            padding: 20px;
        }
        .image-content {
            flex: 1;
            text-align: right;
            padding: 20px;
        }
        .btn-primary {
            background-color: #343a40; /* Dark grey button */
            border-color: #343a40; /* Dark grey button border */
        }
        .image-content img {
            max-width: 100%;
            height: auto; /* Ensure image scales properly */
            border-bottom: 5px solid #343a40; /* Dark grey bottom border for image */
        }
    </style>
</head>
<body>
<!-- Navbar -->
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
                    <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/course">Kurslar</a>
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

<div class="container m-5 rounded" style="background-color: #F1F5F9">
    <div class="text-content">
        <p style="font-size: 60px"><b>42 kunda backend</b></p>
        <p style="font-size: 60px"><b>dasturlashni o'rganing</b></p>
        <p style="font-size: 20px">⚡️ 42 kun o'z ustingizda ishlang va kelajagingiz sari yo'l toping!</p>
        <button class="btn btn-primary mt-4">⚡️Boshlash</button>
    </div>
    <div class="image-content">
        <img src="https://storage.googleapis.com/videos42/hey.svg" class="img-fluid" alt="Placeholder Image">
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>

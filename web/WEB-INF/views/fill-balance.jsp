<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Fill Balance</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <style>
        body {
            background-color: white;
            color: #212529;
            height: 100vh;
            display: flex;
            flex-direction: column;
            margin: 0;
        }
        .container {
            display: flex;
            flex: 1;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            padding: 20px;
        }
        .form-content {
            background-color: #F1F5F9;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 500px;
        }
        .btn-primary {
            background-color: #343a40;
            border-color: #343a40;
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

<div class="container m-5 rounded">
    <div class="form-content">
        <h2 class="mb-4">Balansni to'ldirish</h2>
        <form action="${pageContext.request.contextPath}/fill-balance" method="post" enctype="multipart/form-data">
            <div class="mb-3">
                <label for="amount" class="form-label">Summani kiriting:</label>
                <input type="number" class="form-control" id="amount" name="amount" placeholder="Summani kiriting" required>
            </div>
            <div class="mb-3">
                <label for="check" class="form-label">Tolov cheki:</label>
                <input type="file" class="form-control" id="check" name="check" required>
            </div>
            <button type="submit" class="btn btn-primary mt-4">Tasdiqlash</button>
        </form>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>

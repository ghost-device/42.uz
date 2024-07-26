<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Panelga Kirish</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <style>
        body {
            background-color: #f8f9fa; /* Light grey background */
        }
        .card {
            background-color: #ffffff; /* White card background */
            border-color: #dee2e6; /* Light grey border */
        }
        .btn-primary {
            background-color: #343a40; /* Dark grey button */
            border-color: #343a40; /* Dark grey button border */
        }
        .password-container {
            position: relative;
        }
        .password-toggle {
            position: absolute;
            top: 50%;
            right: 10px;
            transform: translateY(-50%);
            border: none;
            background: none;
            cursor: pointer;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow-sm">
                <div class="card-header text-center">
                    <h3>Admin Panelga Kirish</h3>
                </div>
                <div class="card-body">
                    <c:if test="${not empty errorMessage}">
                        <div class="alert alert-danger" role="alert">
                                ${errorMessage}
                        </div>
                    </c:if>

                    <form action="${pageContext.request.contextPath}/auth/admin-login" method="post">
                        <div class="mb-3">
                            <label for="email" class="form-label">Username</label>
                            <input type="text" class="form-control" id="email" name="email" placeholder="Username kiriting" required>
                        </div>
                        <div class="mb-3 password-container">
                            <label for="password" class="form-label">Parol</label>
                            <input type="password" class="form-control" id="password" name="password" placeholder="Parolingizni kiriting" required>
                            <button type="button" class="password-toggle" onclick="togglePassword()">
                                <span id="toggleIcon">👁️</span>
                            </button>
                        </div>
                        <button type="submit" class="btn btn-primary btn-block">Kirish</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Include Bootstrap JS and dependencies -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script>
    function togglePassword() {
        const passwordInput = document.getElementById('password');
        const toggleIcon = document.getElementById('toggleIcon');
        if (passwordInput.type === 'password') {
            passwordInput.type = 'text';
            toggleIcon.textContent = '🙈'; // Change icon to indicate password is visible
        } else {
            passwordInput.type = 'password';
            toggleIcon.textContent = '👁️'; // Change icon to indicate password is hidden
        }
    }
</script>
</body>
</html>

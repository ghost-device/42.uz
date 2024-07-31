<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Email Verification</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <style>
        body {
            background-color: #f8f9fa; /* Light grey background */
            color: #212529; /* Dark grey text */
        }
        .card {
            background-color: #ffffff; /* White card background */
            border-color: #dee2e6; /* Light grey border */
        }
        .btn-primary {
            background-color: #343a40; /* Dark grey button */
            border-color: #343a40; /* Dark grey button border */
        }
        .btn-secondary {
            background-color: #6c757d; /* Grey button */
            border-color: #6c757d; /* Grey button border */
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
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow-sm">
                <div class="card-header">
                    <h3 class="text-center">Email Verification</h3>
                </div>
                <div class="card-body">
                    <h5 class="card-title text-center mb-4">${email}</h5>

                    <c:if test="${not empty errorMessage}">
                        <div class="alert alert-danger" role="alert">
                                ${errorMessage}
                        </div>
                    </c:if>

                    <form action="${pageContext.request.contextPath}/verification/verify-check" method="post">
                        <input type="hidden" name="email" value="${email}">
                        <div class="mb-3">
                            <label for="code" class="form-label">Enter Verification Code</label>
                            <input id="code" name="code" type="text" class="form-control" placeholder="Code" required>
                        </div>
                        <div class="d-flex justify-content-between">
                            <button type="button" class="btn btn-secondary" id="resendButton" onclick="resendVerificationCode()" disabled>Resend</button>
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </div>
                    </form>
                    <p class="text-center mt-3" id="timer">You can resend the code in <span id="time">60</span> seconds.</p>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script>
    function resendVerificationCode() {
        const email = document.getElementById('email').value;

        fetch('${pageContext.request.contextPath}/verification/verify-email', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ email: email })
        })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    alert("Verification code resent successfully!");
                    startTimer();
                } else {
                    alert("Error resending verification code: " + data.message);
                }
            })
            .catch(error => {
                console.error("Error:", error);
                alert("An error occurred while resending the verification code.");
            });
    }

    function startTimer() {
        let timer = 60;
        const resendButton = document.getElementById('resendButton');
        const timerElement = document.getElementById('time');

        resendButton.disabled = true;

        const interval = setInterval(() => {
            timer--;
            timerElement.textContent = timer;

            if (timer <= 0) {
                clearInterval(interval);
                resendButton.disabled = false;
                timerElement.textContent = '60';
            }
        }, 1000);
    }

    // Start the timer when the page loads
    window.onload = startTimer;
</script>
</body>
</html>

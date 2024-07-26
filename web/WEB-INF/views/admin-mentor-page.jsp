<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mentor Dashboard</title>
    <!-- Include Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <!-- Include Bootstrap Icons CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <!-- Include Chart.js library -->
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        body {
            background-color: white;
            color: black; /* Set text color to black */
            height: 100vh;
            display: flex;
            flex-direction: column;
            margin: 0;
            position: relative; /* Make sure body has a position for absolute positioning of the alert */
        }
        .vertical-nav {
            width: 250px;
        }
        .content {
            flex: 1;
            padding: 20px;
        }
        .nav-link {
            color: black; /* Set navbar link color to black */
        }
        .nav-link:hover {
            color: #343a40; /* Darker color on hover */
        }
        .card {
            margin-bottom: 20px;
        }
        .card img {
            height: 200px;
            object-fit: cover;
        }
        .form-container {
            margin-top: 40px;
        }
        .alert-bottom-right {
            position: absolute;
            bottom: 20px;
            right: 20px;
            z-index: 1050; /* Ensure it's above other content */
            transition: opacity 0.5s ease-out, transform 0.5s ease-out;
        }
        .alert-bottom-right.fade-out {
            opacity: 0;
            transform: translateY(20px);
        }
    </style>
</head>
<body>
<!-- Vertical Navbar -->
<div class="container-fluid">
    <div class="row">
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

        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4 content">
            <div class="row">
                <c:forEach var="course" items="${mentors}">
                    <div class="col-md-4">
                        <div class="card shadow-sm">
                            <img src="${mentor.pictureUrl}" class="card-img-top" alt="${mentor.pictureUrl}">
                            <div class="card-body">
                                <h5 class="card-title">${mentor.name}</h5>
                                <p class="card-text"><i class="bi bi-tag"></i> Biografiya: ${mentor.biography}</p>
                                <p class="card-text"><i class="bi bi-check-circle"></i>
                                    <a href="/mentor/courses?mentorid=${mentor.id}">Kurslar</a>
                                </p>
                            </div>
                            <div class="card-footer">
                                <button class="btn btn-warning">O'chirish</button>
                                <button class="btn btn-primary">O'zgartirish</button>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <!-- Course Creation Form -->
            <div class="form-container">
                <h3>Yangi Mentor qo'shish</h3>

                <!-- Error Message Alert -->
                <c:if test="${not empty errorMessage}">
                    <div id="alertMessage" class="alert alert-danger alert-bottom-right" role="alert">
                            ${errorMessage}
                    </div>
                </c:if>

                <form action="${pageContext.request.contextPath}/mentor/add" method="post" enctype="multipart/form-data">
                    <div class="mb-3">
                        <label for="courseName" class="form-label">Mentor Ismi</label>
                        <input type="text" class="form-control" id="courseName" name="name" placeholder="Mentor ismi" required>
                    </div>
                    <div class="mb-3">
                        <label for="courseDescription" class="form-label">Biografiyasi</label>
                        <textarea class="form-control" id="courseDescription" name="biography" rows="3" placeholder="Mentor biografiyasi" required></textarea>
                    </div>
                    <div class="mb-3">
                        <label for="courseImage" class="form-label">Mentor rasmi</label>
                        <input type="file" class="form-control" id="courseImage" name="picture" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Mentor qo'shish</button>
                </form>
            </div>
        </main>
    </div>
</div>

<!-- Include Bootstrap JS and dependencies -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script>
    // Sample data for the chart
    const labels = ['January', 'February', 'March', 'April', 'May', 'June', 'July'];
    const data = {
        labels: labels,
        datasets: [{
            label: 'Number of Enrollments',
            backgroundColor: 'rgba(54, 162, 235, 0.2)',
            borderColor: 'rgba(54, 162, 235, 1)',
            borderWidth: 1,
            data: [65, 59, 80, 81, 56, 55, 40],
        }]
    };

    const config = {
        type: 'bar',
        data: data,
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    };

    var statisticsChart = new Chart(
        document.getElementById('statisticsChart'),
        config
    );

    window.addEventListener('load', function() {
        const alert = document.getElementById('alertMessage');
        if (alert) {
            setTimeout(() => {
                alert.classList.add('fade-out');
            }, 3000);
        }
    });
</script>
</body>
</html>

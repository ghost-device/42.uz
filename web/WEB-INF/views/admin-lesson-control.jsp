<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Dashboard - Lessons</title>
    <!-- Include Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <!-- Include Bootstrap Icons CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        body {
            background-color: white;
            color: black;
            height: 100vh;
            display: flex;
            flex-direction: column;
            margin: 0;
            position: relative;
        }
        .vertical-nav {
            width: 250px;
        }
        .content {
            flex: 1;
            padding: 20px;
        }
        .nav-link {
            color: black;
        }
        .nav-link:hover {
            color: #343a40;
        }
        .card {
            margin-bottom: 20px;
        }
        .form-container {
            margin-top: 40px;
        }
        .alert-bottom-right {
            position: absolute;
            bottom: 20px;
            right: 20px;
            z-index: 1050;
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
                            Admin Dashboard
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#"><i class="bi bi-cash-stack"></i> Payments</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/mentor"><i class="bi bi-person-circle"></i> Mentors</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/"><i class="bi bi-box-arrow-right"></i> Log Out</a>
                    </li>
                </ul>
            </div>
        </nav>

        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4 content">
            <div class="row">
                <!-- Lessons Table -->
                <h3 class="mb-4">Lessons</h3>
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th scope="col">Nomi</th>
                        <th scope="col">Dars ma'lumotlari</th>
                        <th scope="col">Video uzunligi</th>
                        <th scope="col">Tartib raqami</th>
                        <th scope="col">Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="lesson" items="${lessons}">
                        <tr>
                            <td>${lesson.name}</td>
                            <td>${lesson.description}</td>
                            <td>${lesson.videoDuration}</td>
                            <td>${lesson.orderNum}</td>
                            <td>
                                <button class="btn btn-warning btn-sm" data-bs-toggle="modal" data-bs-target="#deleteModal-${lesson.id}">O'chirish</button>
                                <button class="btn btn-primary btn-sm" data-bs-toggle="modal" data-bs-target="#updateModal-${lesson.id}">Ma'lumotlarni yangilash</button>
                            </td>
                        </tr>

                        <!-- Delete Modal -->
                        <div class="modal fade" id="deleteModal-${lesson.id}" tabindex="-1" aria-labelledby="deleteModalLabel-${lesson.id}" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="deleteModalLabel-${lesson.id}">Darsni o'chirish</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        Haqiqatdan ham ushbu "${lesson.name}" darsni o'chirmoqchimisiz?
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Bekor qilish</button>
                                        <a href="/lesson/delete/${lesson.id}" class="btn btn-danger">O'chirish</a>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Update Modal -->
                        <div class="modal fade" id="updateModal-${lesson.id}" tabindex="-1" aria-labelledby="updateModalLabel-${lesson.id}" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="updateModalLabel-${lesson.id}">Ma'lumotlarni yangilash</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <form action="${pageContext.request.contextPath}/lesson/update" method="post" enctype="multipart/form-data">
                                            <input type="hidden" name="id" value="${lesson.id}">
                                            <div class="mb-3">
                                                <label for="lessonName-${lesson.id}" class="form-label">Dars nomi</label>
                                                <input type="text" class="form-control" id="lessonName-${lesson.id}" name="name" value="${lesson.name}">
                                            </div>
                                            <div class="mb-3">
                                                <label for="lessonDescription-${lesson.id}" class="form-label">Dars ma'lumotlari</label>
                                                <textarea class="form-control" id="lessonDescription-${lesson.id}" name="lessonDescription" placeholder="Modul ma'lumotlarini kiriting" required>${lesson.description}</textarea>
                                            </div>
                                            <div class="mb-3">
                                                <label for="lessonVideoDuration-${lesson.id}" class="form-label">Video uzunligi</label>
                                                <input type="text" class="form-control" id="lessonVideoDuration-${lesson.id}" name="videoDuration" value="${lesson.videoDuration}">
                                            </div>
                                            <div class="mb-3">
                                                <label for="lessonOrderNum-${lesson.id}" class="form-label">Tartib raqami</label>
                                                <input type="number" class="form-control" id="lessonOrderNum-${lesson.id}" name="orderNum" value="${lesson.orderNum}">
                                            </div>
                                            <div class="mb-3">
                                                <label for="lessonVideoFile-${lesson.id}" class="form-label">Video fayl</label>
                                                <input type="file" class="form-control" id="lessonVideoFile-${lesson.id}" name="videoFile">
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Bekor qilish</button>
                                                <button type="submit" class="btn btn-primary">O'zgarishlarni saqlash</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </c:forEach>
                    </tbody>
                </table>

                <!-- Lesson Creation Form -->
                <div class="form-container">
                    <h3>Yangi dars qo'shish</h3>

                    <c:if test="${not empty errorMessage}">
                        <div id="alertMessage" class="alert alert-danger alert-bottom-right" role="alert">
                                ${errorMessage}
                        </div>
                    </c:if>

                    <form action="${pageContext.request.contextPath}/lesson/add" method="post" enctype="multipart/form-data">
                        <div class="mb-3">
                            <label for="lessonName" class="form-label">Dars nomi</label>
                            <input type="text" class="form-control" id="lessonName" name="name" placeholder="Dars nomini kiriting" required>
                        </div>
                        <div class="mb-3">
                            <label for="lessonOrderNum" class="form-label">Tartib raqam</label>
                            <input type="number" class="form-control" id="lessonOrderNum" name="orderNum" placeholder="Tartib raqamni kiriting" required>
                        </div>
                        <div class="mb-3">
                            <label for="lessonVideoDuration" class="form-label">Video uzunligi</label>
                            <input type="number" class="form-control" id="lessonVideoDuration" name="videoDuration" placeholder="Video uzunligini kiriting (sekundlarda)" required>
                        </div>
                        <div class="mb-3">
                            <label for="lessonDescription" class="form-label">Dars ma'lumotlari</label>
                            <textarea class="form-control" id="lessonDescription" name="lessonDescription" rows="3" placeholder="Dars ma'lumotlarini kiriting" required></textarea>
                        </div>
                        <div class="mb-3">
                            <label for="lessonVideoFile" class="form-label">Video fayl yuklash</label>
                            <input type="file" class="form-control" id="lessonVideoFile" name="videoFile" required>
                        </div>
                        <input type="hidden" name="id" value="${moduleId}">
                        <button type="submit" class="btn btn-primary">Darslikni qo'shish</button>
                    </form>
                </div>
            </div>
        </main>
    </div>
</div>

<!-- Include Bootstrap JS and dependencies -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script>
    setTimeout(function() {
        var alertMessage = document.getElementById('alertMessage');
        if (alertMessage) {
            alertMessage.classList.add('fade-out');
            setTimeout(function() {
                alertMessage.remove();
            }, 500);
        }
    }, 3000);
</script>
</body>
</html>

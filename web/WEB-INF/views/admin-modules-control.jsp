<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Dashboard - Modules</title>
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
                <!-- Module Table -->
                <h3 class="mb-4">Modules</h3>
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th scope="col">Name</th>
                        <th scope="col">Description</th>
                        <th scope="col">Order</th>
                        <th scope="col">Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="module" items="${modules}">
                        <tr>
                            <td>${module.name}</td>
                            <td>${module.description}</td>
                            <td>${module.orderNum}</td>
                            <td>
                                <button class="btn btn-warning btn-sm" data-bs-toggle="modal" data-bs-target="#deleteModal-${module.id}">O'chirish</button>
                                <button class="btn btn-primary btn-sm" data-bs-toggle="modal" data-bs-target="#updateModal-${module.id}">O'zgartirish</button>
                                <a href="/module/lessons?moduleId=${module.id}" class="btn btn-secondary btn-sm">Get Lessons</a>
                            </td>
                        </tr>

                        <!-- Delete Modal -->
                        <div class="modal fade" id="deleteModal-${module.id}" tabindex="-1" aria-labelledby="deleteModalLabel-${module.id}" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="deleteModalLabel-${module.id}">Delete Module</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        Are you sure you want to delete the module "${module.name}"?
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                        <button type="button" class="btn btn-danger">Delete</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Update Modal -->
                        <div class="modal fade" id="updateModal-${module.id}" tabindex="-1" aria-labelledby="updateModalLabel-${module.id}" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="updateModalLabel-${module.id}">Update Module</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <form>
                                            <div class="mb-3">
                                                <label for="moduleName-${module.id}" class="form-label">Module Name</label>
                                                <input type="text" class="form-control" id="moduleName-${module.id}" value="${module.name}">
                                            </div>
                                            <div class="mb-3">
                                                <label for="moduleDescription-${module.id}" class="form-label">Description</label>
                                                <textarea class="form-control" id="moduleDescription-${module.id}" rows="3">${module.description}</textarea>
                                            </div>
                                            <div class="mb-3">
                                                <label for="moduleOrderNum-${module.id}" class="form-label">Order Number</label>
                                                <input type="number" class="form-control" id="moduleOrderNum-${module.id}" value="${module.orderNum}">
                                            </div>
                                        </form>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                        <button type="button" class="btn btn-primary">Save Changes</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    </tbody>
                </table>

                <!-- Module Creation Form -->
                <div class="form-container">
                    <h3>Create New Module</h3>

                    <c:if test="${not empty errorMessage}">
                        <div id="alertMessage" class="alert alert-danger alert-bottom-right" role="alert">
                                ${errorMessage}
                        </div>
                    </c:if>

                    <form action="${pageContext.request.contextPath}/module/create" method="post">
                        <div class="mb-3">
                            <label for="moduleName" class="form-label">Module Name</label>
                            <input type="text" class="form-control" id="moduleName" name="name" placeholder="Enter module name" required>
                        </div>
                        <div class="mb-3">
                            <label for="moduleDescription" class="form-label">Description</label>
                            <textarea class="form-control" id="moduleDescription" name="description" rows="3" placeholder="Enter module description" required></textarea>
                        </div>
                        <div class="mb-3">
                            <label for="moduleOrderNum" class="form-label">Order Number</label>
                            <input type="number" class="form-control" id="moduleOrderNum" name="orderNum" placeholder="Enter order number" required>
                        </div>
                        <input type="hidden" name="courseId" value="${sessionScope.get('courseId')}">
                        <button type="submit" class="btn btn-primary">Create Module</button>
                    </form>
                </div>
            </div>
        </main>
    </div>
</div>

<!-- Include Bootstrap JS and dependencies -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="uz.web.domain.DAO.UserDao" %>
<html>
<head>
    <title>Fill Balance</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        .payment-history {
            max-height: 500px;
            overflow-y: auto;
        }
    </style>
</head>
<body>
<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom border-1 p-3 border-dark"
     style="font-size: 23px">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/user/main-menu">
            <img src="https://raw.githubusercontent.com/reactanvar/42.uz-loyihasi/main/favicon.ico" alt="Logo"
                 width="40" height="40" class="d-inline-block align-text-top">
        </a>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto d-flex align-items-center">
                <!-- User Balance -->
                <li class="nav-item me-3">
                    <span class="navbar-text">
                        <strong>Balans: ${balance}</strong>
                    </span>
                </li>
                <!-- Dropdown Menu -->
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                       data-bs-toggle="dropdown" aria-expanded="false">
                        <i class="bi bi-list"></i>
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/payments">To'lovlar</a>
                        </li>
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/profile">Profile</a></li>
                        <li>
                            <hr class="dropdown-divider">
                        </li>
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/">Chiqish</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container mt-5">
    <div class="row">
        <div class="col-4">
            <div class="form-control">
                <h2 class="mb-4">Balansni to'ldirish</h2>
                <form action="${pageContext.request.contextPath}/payment/fill-balance" method="post"
                      enctype="multipart/form-data">
                    <div class="mb-3">
                        <label for="amount" class="form-label">Summani kiriting:</label>
                        <input type="number" class="form-control" id="amount" name="amount"
                               placeholder="Summani kiriting" required>
                        <input type="hidden" name="userId" value="<%= ((UserDao) session.getAttribute("user")).getId() %>">
                    </div>
                    <div class="mb-3">
                        <label for="check" class="form-label">Tolov cheki:</label>
                        <input type="file" class="form-control" id="check" name="checkImg" required>
                    </div>
                    <button type="submit" class="btn btn-dark mt-4">Tasdiqlash</button>
                </form>
            </div>
        </div>
        <div class="col-8">
            <div class="payment-history">
                <h2 class="mb-4">To'lovlar tarixi</h2>
                <table class="table table-sm table-hover ">
                    <thead class="table-light">
                    <tr>
                        <th>#</th>
                        <th>Sana</th>
                        <th>Miqdori</th>
                        <th>Statusi</th>
                        <th>Amallar</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="payment" items="${payments}">
                        <tr>
                            <td>1</td>
                            <td>${payment.paymentDate}</td>
                            <td>${payment.amount} UZS</td>
                            <td>${payment.status}</td>
                            <td>
                                <button type="button" class="btn btn-dark" data-bs-toggle="modal"
                                        data-bs-target="#paymentModal-${payment.paymentCheckId}">Chekni ko'rish
                                </button>
                            </td>
                        </tr>
                        <!-- Repeat similar rows for other payments -->
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<c:forEach items="${payments}" var="payment">
    <div class="modal fade" id="paymentModal-${payment.paymentCheckId}" tabindex="-1"
         aria-labelledby="paymentModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="paymentModalLabel">Tolov cheki</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <img src="${payment.paymentCheckId}" class="img-fluid" alt="Tolov cheki">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-dark" data-bs-dismiss="modal">Yopish</button>
                </div>
            </div>
        </div>
    </div>

</c:forEach>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>

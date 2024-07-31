<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Admin Dashboard - Payments</title>
    <!-- Include Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
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
            /* Ensure it's above other content */
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
                        <a class="nav-link" aria-current="page" href="${pageContext.request.contextPath}/admin/main">
                            Admin Dashboard
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="${pageContext.request.contextPath}/payment"><i
                                class="bi bi-cash-stack"></i> Payments</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/mentor"><i
                                class="bi bi-person-circle"></i> Mentors</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/admin/main"><i class="bi bi-tv"></i> Courses</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/"><i
                                class="bi bi-box-arrow-right"></i> Log Out</a>
                    </li>
                </ul>
            </div>
        </nav>

        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4 content">
            <!-- Pending payments  -->
            <div class="container">
                <c:choose>
                    <c:when test="${not empty pendingPayments}">
                        <div class="row">
                            <h3 class="mb-4">Tasdiqlanishi kutilayotgan to'lovlar</h3>

                            <c:forEach var="payment" items="${pendingPayments}">


                                <div class="col-md-4 mb-3">
                                    <div class="card shadow-sm h-100">
                                        <img src="${payment.pictureUrl}" class="card-img-top"
                                             alt="${payment.pictureUrl}">
                                        <div class="card-body p-2">
                                            <h5 class="card-title">To'lov IDsi: ${payment.id}</h5>
                                            <p class="card-text mb-1"><i class="bi bi-person"></i>
                                                Foydalanuvchi: ${payment.email}
                                            </p>
                                            <p class="card-text mb-1"><i class="bi bi-currency-dollar"></i> To'langan
                                                summa:
                                                $${payment.amount}</p>
                                            <p class="card-text mb-1"><i class="bi bi-calendar"></i> To'langan
                                                sana: ${payment.date}
                                            </p>
                                            <p class="card-text mb-1"><i class="bi bi-hourglass-split"></i> To'lov
                                                statusi:
                                                    ${payment.status}</p>
                                        </div>
                                        <div class="card-footer p-2">
                                            <button class="btn btn-success btn-sm" data-bs-toggle="modal"
                                                    data-bs-target="#approveModal-${payment.id}">Qabul qilish
                                            </button>
                                            <button class="btn btn-danger btn-sm" data-bs-toggle="modal"
                                                    data-bs-target="#rejectModal-${payment.id}">Rad qilish
                                            </button>
                                        </div>
                                    </div>
                                </div>

                                <!-- Approve Modal -->
                                <div class="modal fade" id="approveModal-${payment.id}" tabindex="-1"
                                     aria-labelledby="approveModalLabel-${payment.id}"
                                     aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="approveModalLabel-${payment.id}">To'lovni
                                                    qabul qilish
                                                </h5>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                        aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                Haqiqatdan ham ushbu ${payment.email} foydalanuvchining
                                                $${payment.amount} to'lashini
                                                tasdiqlamoqchimisiz?
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary"
                                                        data-bs-dismiss="modal">Bekor qilish
                                                </button>
                                                <a href="${pageContext.request.contextPath}/payment/approve/${payment.id}"
                                                   class="btn btn-success">Tasdiqlash</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!-- Reject Modal -->
                                <div class="modal fade" id="rejectModal-${payment.id}" tabindex="-1"
                                     aria-labelledby="rejectModalLabel-${payment.id}"
                                     aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="rejectModalLabel-${payment.id}">To'lovni rad
                                                    qilish
                                                </h5>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                        aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                Haqiqatdan ham ushbu ${payment.email} foydalanuvchining
                                                $${payment.amount} to'lashini
                                                rad qilmoqchimisiz?
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary"
                                                        data-bs-dismiss="modal">Bekor qilish
                                                </button>
                                                <a href="${pageContext.request.contextPath}/payment/reject/${payment.id}"
                                                   class="btn btn-danger">To'lovni rad etish</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </c:when>

                    <c:otherwise>
                        <h1>Tasdiqlanishi kutilayotgan to'lovlar mavjud emas!</h1>
                    </c:otherwise>

                </c:choose>
            </div>

            <div class="container mt-5">

                <c:choose>
                    <c:when test="${not empty otherPayments}">
                        <div class="row">
                            <h3 class="mb-4">Boshqa to'lovlar</h3>

                            <c:forEach var="otherPayment" items="${otherPayments}">

                                <div class="col-md-4 mb-3">
                                    <div class="card shadow-sm h-100">
                                        <div class="card-body p-2">
                                            <h5 class="card-title">To'lov IDsi: ${otherPayment.id}</h5>
                                            <p class="card-text mb-1"><i class="bi bi-person"></i>
                                                Foydalanuvchi: ${otherPayment.email}
                                            </p>
                                            <p class="card-text mb-1"><i class="bi bi-currency-dollar"></i> To'langan
                                                summa: $${otherPayment.amount}</p>
                                            <p class="card-text mb-1"><i class="bi bi-calendar">
                                            </i> To'lov sanasi: ${otherPayment.date}
                                            </p>
                                            <p class="card-text mb-1"><i class="bi bi-hourglass-split"></i>
                                                To'lov statusi: ${otherPayment.status}</p>
                                        </div>
                                        <div class="card-footer p-2">
                                            <button class="btn btn-info btn-sm" data-bs-toggle="modal"
                                                    data-bs-target="#viewModal-${otherPayment.id}">
                                                To'lov chekini ko'rish
                                            </button>
                                        </div>
                                    </div>
                                </div>
                                <!-- View Modal -->
                                <div class="modal fade" id="viewModal-${otherPayment.id}" tabindex="-1"
                                     aria-labelledby="viewModalLabel-${otherPayment.id}"
                                     aria-hidden="true">
                                    <div class="modal-dialog modal-sm">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="viewModalLabel-${otherPayment.id}">To'lov
                                                    chek rasmi</h5>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                        aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                <img src="${otherPayment.pictureUrl}" class="img-fluid"
                                                     alt="Receipt Image">
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                                                    Yopish
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>


                            </c:forEach>
                        </div>
                    </c:when>

                    <c:otherwise>
                        <h1>Barcha to'lovlar tasdiqlangan!</h1>
                    </c:otherwise>

                </c:choose>


            </div>
        </main>
    </div>
</div>

<!-- Include Bootstrap JS and dependencies -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>

</html>
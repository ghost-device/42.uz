<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>${lesson.name}</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    .video-container {
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh; /* Ekran balandligini to'ldiradi */
      padding: 20px;
    }
    .video-container video {
      max-width: 80%; /* Videoning maksimal kengligi */
      max-height: 80vh; /* Videoning maksimal balandligi */
    }
  </style>
</head>
<body>
<div class="container mt-5 video-container">
  <video controls class="w-100">
    <source src="${lesson.videoUrl}" type="video/mp4">
    Sizning brauzeringiz video tegi qo'llab-quvvatlamaydi.
  </video>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

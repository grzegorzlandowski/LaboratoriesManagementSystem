<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity5">
<head>
    <title>Strona Główna</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/../css/navbar.css">
    <link rel="stylesheet" href="/../css/body.css">
    <link rel="stylesheet" href="/../css/footer.css">
</head>
<body class="d-flex flex-column min-vh-100">
<%@include file="navbar.jsp" %>
<div id="test" class="container rounded ">
    <center><h1 style="color: white">Aktualności:</h1></center>
<table class="table table-dark table-hover">
    <tr class="table-dark">
        <td class="table-dark">Tytuł</td>
        <td class="table-dark">Data</td>
    </tr>
    <tbody>
    <c:forEach var="InformationDashboard" items="${informationdashboardlist}">
        <c:if test="${InformationDashboard.isactive== true}" >
       <tr class="table-dark" onclick="window.location='/dashboard/${InformationDashboard.id}'">
            <th class="table-dark" data-column="id"> ${InformationDashboard.title}</th>
            <th class="table-dark" data-column="nazwa">${InformationDashboard.date}</th>

        </tr>
    </c:if>
    </c:forEach>
    </tbody>
</table>
</div>

<%@include file="footer.jsp" %>
</body>

</html>

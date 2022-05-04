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
    <link rel="stylesheet" href="/../css/getreport.css">
</head>
<body class="d-flex flex-column min-vh-100">
<%@include file="navbar.jsp" %>
<div id="bodycontainer" class="container rounded ">
    <button onclick="history.back()" id="addmessage" type="button" class="btn btn-success">
         Powrót
    </button>


    <h1 class="text-center" style="color:#FFE8E8;">Zgłoszenie ID: ${reportSystem.id}</h1>
        <div class="bodycontainer" class="container rounded">
        <div style="color:#FFE8E8; font-size: 25px;">Nazwa Sali: ${reportSystem.laboratory.name}</div>
       <div style="color:#FFE8E8; font-size: 25px;">Tytuł: ${reportSystem.title}</div>
    <div style="color:#FFE8E8; font-size: 25px;">Data: ${reportSystem.date}</div>
    <div style="color:#FFE8E8; font-size: 25px;">Status: ${reportSystem.status}</div>
    <div style="color:#FFE8E8; font-size: 25px;">Typ: ${reportSystem.type}</div>
    <div style="color:#FFE8E8; font-size: 25px;">Opis: ${reportSystem.description}</div></div>


    <div class="messagecontainer" class="container rounded">
        <h1 class="text-center" style="color:#FFE8E8;">Wiadomości</h1>

        <button onclick="window.location='/zgloszenie/${reportSystem.id}/nowawiadomosc'" id="addmessage" type="button" class="btn btn-success">
            <i class="bi bi-envelope-plus"></i> Dodaj nową wiadomość
        </button>
<c:forEach var="ReportMessages" items="${reportMessage}">
        <div  class="bodycontainer" class="container rounded">
            <p class="reportmessage">
         ${ReportMessages.message}
            </p>

            <p class="text-end">
                ${ReportMessages.date} ${ReportMessages.user.username}</p>

        </div>
    </c:forEach>


    </div>




</div>

<%@include file="footer.jsp" %>
</body>

</html>

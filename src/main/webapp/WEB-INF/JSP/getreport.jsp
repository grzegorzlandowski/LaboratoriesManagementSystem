<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity5">
<head>
    <title>Zgłoszenie ID: ${reportSystem.id}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/../css/navbar.css">
    <link rel="stylesheet" href="/../css/body.css">
    <link rel="stylesheet" href="/../css/footer.css">
    <link rel="stylesheet" href="/../css/getreport.css">
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-sweetalert/1.0.1/sweetalert.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
    <script type="text/javascript" src="//code.jquery.com/jquery-3.6.0.min.js"></script>

</head>
<body class="d-flex flex-column min-vh-100">
<%@include file="navbar.jsp" %>
<div id="bodycontainer" class="container rounded ">
    <button onclick="history.back()" id="addmessage" type="button" class="btn btn-success">
        <i class="bi bi-back"></i> Powrót
    </button>


    <h1 class="text-center" style="color:#FFE8E8;">Zgłoszenie ID: ${reportSystem.id}</h1>
        <div class="bodycontainer" class="container rounded">
            <div style="color:#FFE8E8; font-size: 24px;"><b style="color:#D5D587;">Nazwa Sali:</b> ${reportSystem.laboratory.name}</div>
            <div style="color:#FFE8E8; font-size: 24px;"><b style="color:#D5D587;">Tytuł:</b> ${reportSystem.title}</div>
            <div style="color:#FFE8E8; font-size: 24px;"><b style="color:#D5D587;">Data:</b> ${reportSystem.date}</div>
            <div style="color:#FFE8E8; font-size: 24px;"><b style="color:#D5D587;">Status:</b> ${reportSystem.status} <c:if test="${reportSystem.supervisor.username==sessionusername}"><button onclick="window.location='/zgloszenie/${reportSystem.id}/zmienstatus'"  type="button" class="btn btn-success">
                <i class="bi bi-pencil-square"></i> Zmień
            </button></c:if></div>
            <div style="color:#FFE8E8; font-size: 24px;"><b style="color:#D5D587;">Typ:</b> <c:if test="${reportSystem.type=='request'}">Zapotrzebowanie</c:if>
                <c:if test="${reportSystem.type=='fault'}">Awaria/Usterka</c:if></div>
            <div style="color:#FFE8E8; font-size: 24px;"><b style="color:#D5D587;">Opiekun:</b> ${reportSystem.supervisor.username}</div>
            <div style="color:#FFE8E8; font-size: 24px;"><b style="color:#D5D587;">Zgłaszający:</b> ${reportSystem.applicant.username}</div>
            <div style="color:#FFE8E8; font-size: 24px;"><b style="color:#D5D587;">Opis:</b> ${reportSystem.description}</div></div>


    <div class="messagecontainer" class="container rounded">
        <h1 class="text-center" style="color:#FFE8E8;">Wiadomości</h1>
<c:if test="${reportSystem.status!='Archiwalne'}">
        <button onclick="window.location='/zgloszenie/${reportSystem.id}/nowawiadomosc'" id="addmessage" type="button" class="btn btn-success">
            <i class="bi bi-envelope-plus"></i> Dodaj nową wiadomość
        </button>
</c:if>
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

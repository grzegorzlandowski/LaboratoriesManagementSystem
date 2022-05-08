<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity5">
<head>
    <title>Moje zgłoszenia - archiwalne</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/../css/navbar.css">
    <link rel="stylesheet" href="/../css/body.css">
    <link rel="stylesheet" href="/../css/footer.css">
    <link rel="stylesheet" href="/../css/table.css">
    <script src="/../js/confirmbox.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-sweetalert/1.0.1/sweetalert.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
    <script type="text/javascript" src="//code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body class="d-flex flex-column min-vh-100">
<%@include file="navbar.jsp" %>
<div id="bodycontainer" class="container rounded">
    <c:if test="${reportSystemList == []}" >
        <div class="alert alert-warning" role="alert">
            Brak zgłoszeń
        </div>
    </c:if>
    <button onclick="window.location='/mojezgloszenia'" id="archivebutton" type="button" class="btn btn-success">
        <i class="bi bi-archive"></i> Aktualne
    </button>
    <c:if test="${reportSystemList != []}" >
        <table class="table table-borderless table-responsive card-1 p-4">
            <thead><tr class="border-bottom">
                <th colspan="5"><center><h1 style="color:#FFE8E8;">Lista moich zgłoszeń - archiwalne</h1></center></th>

            </tr></thead>
            <thead>
            <tr class="border-bottom">
                <th> Tytuł</th>
                <th>Typ zgłoszenia</th>
                <th>Sala</th>
                <th>Status</th>
                <th>Data</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="ReportSystem" items="${reportSystemList}">
                <tr class="border-top">
                    <td style="cursor: pointer;" onclick="window.location='/zgloszenie/${ReportSystem.id}'">
                            ${ReportSystem.title}
                    </td>
                    <td style="cursor: pointer;" onclick="window.location='/zgloszenie/${ReportSystem.id}'">
                        <c:if test="${ReportSystem.type=='fault'}" >Awaria</c:if>
                        <c:if test="${ReportSystem.type=='request'}" >Zapotrzebowanie</c:if>

                    </td>
                    <td style="cursor: pointer;" onclick="window.location='/zgloszenie/${ReportSystem.id}'">
                            ${ReportSystem.laboratory.name}
                    </td>
                    <td style="cursor: pointer;" onclick="window.location='/zgloszenie/${ReportSystem.id}'">
                            ${ReportSystem.status}
                    </td>

                    <td style="cursor: pointer;" onclick="window.location='/zgloszenie/${ReportSystem.id}'">
                            ${ReportSystem.date}
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>


</div>

<%@include file="footer.jsp" %>
</body>

</html>

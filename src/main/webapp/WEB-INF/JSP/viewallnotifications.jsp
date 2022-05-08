<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity5">
<head>
    <title>Powiadomienia</title>
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
    <c:if test="${succes== 'OK'}">
        <script>
            $(document).ready(function (){
                swal({
                    text: "Operacja zakończyła się powodzeniem",
                    timer: 1000,
                });
            });
        </script>
    </c:if>
</head>
<body class="d-flex flex-column min-vh-100">
<%@include file="navbar.jsp" %>
<div id="bodycontainer" class="container rounded">
    <c:if test="${notificationslist == []}" >
        <div class="alert alert-warning" role="alert">
            Brak Powiadomień.
        </div>
    </c:if>
    <button onclick="window.location='/pokazpowiadomienia/archiwalne'" id="archivebutton" type="button" class="btn btn-success">
        <i class="bi bi-archive"></i> Archiwalne


    </button>
    <c:if test="${notificationslist != []}" >
        <table class="table table-borderless table-responsive card-1 p-4">
            <thead><tr class="border-bottom">
                <th colspan="3"><center><h1 style="color:#FFE8E8;">Aktualne Powiadomienia</h1></center></th>

            </tr></thead>
            <thead>
            <tr class="border-bottom">
                <th>Powiadomienie</th>
                <th>Data</th>


            </tr>
            </thead>
            <tbody>
            <c:forEach var="Notification" items="${notificationslist}">
                <tr onclick="window.location='/powiadoemienie/oznaczjakoprzeczytane/${Notification.id}'" title="Kliknij aby zarchiwizować powiadomienie" style="cursor: pointer;" class="border-top">
                    <td  >
                            ${Notification.message}
                    </td>

                    <td style="cursor: pointer;">
                            ${Notification.date}
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

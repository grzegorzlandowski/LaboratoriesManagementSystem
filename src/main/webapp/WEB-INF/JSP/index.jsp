<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html >
<head>
    <title>Strona Główna</title>
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
<c:if test="${informationdashboardlist == []}" >
    <div class="alert alert-warning" role="alert">
        Brak ogłoszeń.
    </div>
</c:if>
<c:if test="${informationdashboardlist != []}" >
    <table class="table table-borderless table-responsive card-1 p-4">
        <thead><tr class="border-bottom"><th colspan="3"><center><h1 style="color:#FFE8E8;">Tablica Ogłoszeń</h1></center></th></tr></thead>
        <tr class="border-bottom">
            <th> Tytuł</th>
            <th>Data</th>
<sec:authorize access="hasRole('ROLE_ADMIN')"> <th>Zarządzanie</th> </sec:authorize>
        </tr>
        <tbody>
<c:forEach var="InformationDashboard" items="${informationdashboardlist}">
        <tr class="border-top">
            <td style="cursor: pointer;" onclick="window.location='/aktualnosci/${InformationDashboard.id}'">${InformationDashboard.title} </td>
            <td style="cursor: pointer;" onclick="window.location='/aktualnosci/${InformationDashboard.id}'">${InformationDashboard.date}  </td>
<sec:authorize access="hasRole('ROLE_ADMIN')">
            <td><button type="button" class="btn btn-primary" onclick="window.location='/edytujaktualnosc/${InformationDashboard.id}'">
                    <i class="bi bi-pencil-square"></i> Edytuj </button>
<c:if test="${InformationDashboard.isactive==true}">
                <button type="button" class="btn btn-warning" onclick="return JSconfirm('Czy na pewno chcesz zarchiwizować ogłoszenie?','/archiwizuj/${InformationDashboard.id}')">
                    <i class="bi bi-archive-fill"></i> Archiwizuj
</c:if>
<c:if test="${InformationDashboard.isactive==false}">
                    <button type="button" class="btn btn-warning" onclick="return JSconfirm('Czy na pewno chcesz przywrócić ogłoszenie?','/przywrocaktualnosc/${InformationDashboard.id}')">
                        <i class="bi bi-archive"></i> Przywróć
                    </button>
</c:if>
            </td>
</sec:authorize>
        </tr>
</c:forEach>
        </tbody>
    </table>
</c:if>
</div>
<%@include file="footer.jsp" %>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity5">
<head>
    <title>Laboratorium ${laboratory.name}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/../css/navbar.css">
    <link rel="stylesheet" href="/../css/body.css">
    <link rel="stylesheet" href="/../css/table.css">
    <link rel="stylesheet" href="/../css/footer.css">
    <link rel="stylesheet" href="/../css/getreport.css">
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
    <c:if test="${delete== 'NOK'}">
        <script>
            $(document).ready(function (){
                swal({
                    text: "Aby usunąć wyposażenie bazy element nie może być powiazany z innymi stanowiskami."
                });
            });
        </script>
    </c:if>
</head>
<body class="d-flex flex-column min-vh-100">
<%@include file="navbar.jsp" %>
<div id="bodycontainer" class="container rounded ">
    <button onclick="history.back()" id="addmessage" type="button" class="btn btn-success">
        <i class="bi bi-back"></i> Powrót
    </button>

    <div class="bodycontainerlab" class="container rounded">
        <h1 class="text-center" style="color:#FFE8E8;">${laboratoryequipment.equipmentDetails.title}</h1>


        <div style="color:#FFE8E8; font-size: 24px;"><b style="color:#D5D587;">Typ/Rodzaj:</b> ${laboratoryequipment.equipmentDetails.type}</div>
        <div style="color:#FFE8E8; font-size: 24px;"><b style="color:#D5D587;">Stanowisko/Miejsce przechowywania:</b> ${laboratoryequipment.location}</div>
        <div style="color:#FFE8E8; font-size: 24px;"><b style="color:#D5D587;">Informację dodatkowe:</b> ${laboratoryequipment.additionalInformation}</div>
        <div style="color:#FFE8E8; font-size: 24px;"><b style="color:#D5D587;">Opis:</b> ${laboratoryequipment.equipmentDetails.description}</div>


    </div>

</div>

<%@include file="footer.jsp" %>
</body>

</html>

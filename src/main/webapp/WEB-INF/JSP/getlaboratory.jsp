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
    <sec:authorize access="hasAnyRole('ROLE_SUPERVISOR','ROLE_EMPLOYEE')"> <button onclick="window.location='/nowezgloszenie/${laboratory.id}'" id="addbutton" type="button" class="btn btn-warning">
        Nowe zgłoszenie <i class="bi bi-exclamation-triangle-fill"></i></button></sec:authorize>

    <button onclick="window.location='/kalendarz/${laboratory.id}'" id="addbutton" type="button" class="btn btn-info">
                Wyświetl harmonogram  <i class="bi bi-calendar3"></i>
    </button>
    <div class="bodycontainerlab" class="container rounded">
        <c:if test="${editor== 'yes'}">
          <button style="margin-top: 10px;margin-right: 40px;" onclick="window.location='/edytujlaboratorium/${laboratory.id}'" id="addbutton" type="button" class="btn btn-primary" >
               Edytuj laboratorium <i class="bi bi-pencil-square"></i>
            </button></c:if>
        <h1 class="text-center" style="color:#FFE8E8;">${laboratory.name}</h1>
<c:if test="${laboratory.additionalInformation !=null }">
<c:if test="${laboratory.additionalInformation !='' }"> <div style="color:#FFE8E8; font-size: 24px;"><b style="color:darkred;">Informacje dodatkowe:</b> ${laboratory.additionalInformation} </div></c:if></c:if>
        <div style="color:#FFE8E8; font-size: 24px;"><b style="color:#D5D587;">Przeznaczenie/Typ:</b> ${laboratory.intended}</div>
        <div style="color:#FFE8E8; font-size: 24px;"><b style="color:#D5D587;">Ilość miejsc:</b> ${laboratory.seats}</div>


        <div style="color:#FFE8E8; font-size: 24px;"><b style="color:#D5D587;">Powierzchnia:</b> ${laboratory.area} &#13217</div>


        <div style="color:#FFE8E8; font-size: 24px;"><b style="color:#D5D587;">Opiekun:</b> ${laboratory.supervisorId.username} </div>

        <div style="color:#FFE8E8; font-size: 24px;"><b style="color:#D5D587;">Opis:</b>${laboratory.description}</div>
    </div>


    <div class="bodycontainerlabequipment" class="container rounded">
<c:if test="${editor== 'yes'}">
<button onclick="return JSAddLabEquipment('Wybierz Opcję dodawania','/dodajwyposazeniezbazy/${laboratory.id}','/dodajwyposazenie/${laboratory.id}')" id="addbutton" type="button" class="btn btn-success">
           Dodaj wyposażenie <i class="bi bi-plus-circle"></i>
        </button></c:if>
        <c:if test="${laboratory.laboratoryEquipment.size() == 0}" >
            <div style="margin-top: 60px;" class="alert alert-warning" role="alert">
                Do sali nie dodano jeszcze wyposażenia.
            </div>
        </c:if>
<c:if test="${laboratory.laboratoryEquipment.size() > 0}" >
            <table class="table table-borderless table-responsive card-1 p-4">
                <thead><tr class="border-bottom">
                    <th colspan="4"><center><h1 style="color:#FFE8E8;">Wyposażenie</h1></center></th>

                </tr></thead>
                <thead>
                <tr class="border-bottom">
                    <th> Tytuł</th>
                    <th>Stanowisko</th>
                    <th>Typ</th>
    <c:if test="${editor== 'yes'}"><th>Zarządzanie</th> </c:if>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="laboratoryEquipment" items="${laboratory.laboratoryEquipment}">
                    <tr class="border-top">
                        <td style="cursor: pointer;" onclick="window.location='/wyposazenie/${laboratoryEquipment.id}'">
                                ${laboratoryEquipment.equipmentDetails.title}
                        </td>

                        <td style="cursor: pointer;" onclick="window.location='/wyposazenie/${laboratoryEquipment.id}'">
                                ${laboratoryEquipment.location}
                        </td>
                        <td style="cursor: pointer;" onclick="window.location='/wyposazenie/${laboratoryEquipment.id}'">
                                ${laboratoryEquipment.equipmentDetails.type}
                        </td>
                        <c:if test="${editor== 'yes'}"> <td>
                            <button type="button" class="btn btn-primary" onclick="window.location='/edytujwyposazenie/${laboratoryEquipment.id}'">

                                <i class="bi bi-pencil-square"></i> Edytuj
                            </button>
                            <button type="button" class="btn btn-danger" onclick="return JSconfirmDeleteLabEquipment('Wybierz Opcję usuniecia','/usunwyposazenie/${laboratoryEquipment.id}','/usunstanowisko/${laboratoryEquipment.id}')">
                                <i class="bi bi-trash"></i>
                                Usuń
                            </button>

                        </td></c:if>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>



</div>

<%@include file="footer.jsp" %>
</body>

</html>

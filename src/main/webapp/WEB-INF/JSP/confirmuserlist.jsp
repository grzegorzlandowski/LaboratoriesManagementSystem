<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity5">
<head>
    <title>Użytkownicy do potwierdzenia</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/../css/navbar.css">
    <link rel="stylesheet" href="/../css/body.css">
    <link rel="stylesheet" href="/../css/footer.css">
    <link rel="stylesheet" href="/../css/table.css">
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <script src="/../js/confirmbox.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-sweetalert/1.0.1/sweetalert.css">
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

    <c:if test="${confirmuserlist == []}" >
        <div class="alert alert-warning" role="alert">
            Brak Użytkowników do zatwierdzenia.
        </div>
    </c:if>
    <c:if test="${confirmuserlist != []}" >
        <table class="table table-borderless table-responsive card-1 p-4">
            <thead><tr class="border-bottom">
                <th colspan="5"><center><h1 style="color:#FFE8E8;">Lista Użytkowników</h1></center></th>

            </tr></thead>
            <thead>
            <tr class="border-bottom">
                <th>Nazwa Użytkownika</th>
                <th>Imię</th>
                <th>Nazwisko</th>
                <th>Email</th>
                <th>Zarządzanie</th>

            </tr>
            </thead>
            <tbody>
            <c:forEach var="User" items="${confirmuserlist}">
                <tr class="border-top">
                    <td >
                            ${User.username}
                    </td>

                    <td >
                            ${User.firstname}
                    </td>
                    <td>
                        ${User.surname}
                    </td>
                    <td>
                            ${User.email}
                    </td>
                    <td>
                        <button onclick="return JSconfirm('Czy na pewno chcesz potwierdzić użytkownika ${User.username}?','/potwierdzuzytkownika/${User.id}')"
                                type="button" class="btn btn-success">Potwierdź</button>
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

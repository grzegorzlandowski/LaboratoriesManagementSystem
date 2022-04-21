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
    <link rel="stylesheet" href="/../css/table.css">
    <script src="/../js/confirmbox.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-sweetalert/1.0.1/sweetalert.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
</head>
<body class="d-flex flex-column min-vh-100">
<%@include file="navbar.jsp" %>
<div id="bodycontainer" class="container rounded">
    <c:if test="${userlist == []}" >
        <div class="alert alert-warning" role="alert">
            W systemie nie został stworzony jeszcze żaden użytkownik
        </div>
    </c:if>
    <c:if test="${userlist != []}" >
        <table class="table table-borderless table-responsive card-1 p-4">
            <thead><tr class="border-bottom">
                <th colspan="5"><center><h1 style="color:#FFE8E8;">Lista użytkowników - Zarządzanie</h1></center></th>

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
            <c:forEach var="User" items="${userlist}">
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
                        <button type="button" class="btn btn-primary" onclick="return JSconfirmEdit('Wybierz czy chcesz edytować dane użytkownika ${User.username} czy hasło?','/edytujuzytkownika/${User.id}','/edytujhaslouzytkownika/${User.id}')">

                            <i class="bi bi-pencil-square"></i> Edytuj
                        </button>
                        <c:if test="${User.enabled == 'true'}" >
                        <button type="button" class="btn btn-warning" onclick="return JSconfirm('Czy na pewno chcesz Zablokować użytkownika ${User.username}?','/zablokujuzytkownika/${User.id}')">
                            <i class="bi bi-archive-fill"></i> Zablokuj
                        </button>
                        </c:if>
                        <c:if test="${User.enabled == 'false'}" >
                        <button type="button" class="btn btn-warning" onclick="return JSconfirm('Czy na pewno chcesz odblokować użytkownika ${User.username}?','/odblokujuzytkownika/${User.id}')">
                            <i class="bi bi-archive"></i> Odblokuj
                        </button>
                        </c:if>
                        <button type="button" class="btn btn-outline-danger" onclick="return JSconfirm('Czy na pewno chcesz Usunąć użytkownika ${User.username}?','/usunuzytkownika/${User.id}')">
                            <i class="bi bi-trash"></i>
                          Usuń
                        </button>
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

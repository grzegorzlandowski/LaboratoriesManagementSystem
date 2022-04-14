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
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <script src="/../js/confirmbox.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-sweetalert/1.0.1/sweetalert.css">
</head>
<body class="d-flex flex-column min-vh-100">
<%@include file="navbar.jsp" %>
<div id="bodycontainer" class="container rounded">

        <table class="table table-dark table-hover">
            <tr class="table-dark">
                <td class="table-dark">Nazwa Użytkownika</td>
                <td class="table-dark">Imię</td>
                <td class="table-dark">Nazwisko</td>
                <td class="table-dark">Email</td>
                <td class="table-dark">Zarządzanie</td>
            </tr>
            <tbody>
            <c:forEach var="User" items="${confirmuserlist}">
                <tr class="table-dark">
                    <th   class="table-dark" data-column="id"> ${User.username}</th>
                    <th   class="table-dark" data-column="nazwa">${User.firstname}</th>
                    <th   class="table-dark" data-column="nazwa">${User.surname}</th>
                    <th   class="table-dark" data-column="nazwa">${User.email}</th>
                    <th class="table-dark" data-column="nazwa">
                        <button onclick="return JSconfirm('Czy na pewno chcesz potwierdzić użytkownika ${User.username}?','/potwierdzuzytkownika/${User.id}')" type="button" class="btn btn-success">Potwierdź</button>
                    </th>
                </tr>
            </c:forEach>
            </tbody>
        </table>
</div>

<%@include file="footer.jsp" %>
</body>

</html>

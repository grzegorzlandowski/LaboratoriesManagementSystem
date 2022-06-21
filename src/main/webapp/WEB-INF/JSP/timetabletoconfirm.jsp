<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity5">
<head>
    <title>Lista terminów do potwierdzenia</title>
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
    <c:if test="${timetableList == []}" >
        <div class="alert alert-warning" role="alert">
            Brak terminów do potwierdzenia
        </div>
    </c:if>
    <c:if test="${timetableList != []}" >
        <table class="table table-borderless table-responsive card-1 p-4">
            <thead><tr class="border-bottom">
                <th colspan="5"><center><h1 style="color:#FFE8E8;">Harmonogram - lista terminów do potwierdzenia</h1></center></th>

            </tr></thead>
            <thead>
            <tr class="border-bottom">
                <th> Nazwa Laboratorium</th>
                <th>Rozpoczęcie</th>
                <th>Zakończenie</th>
                <th>Opis</th>
                <th>Użytkownik</th>
                <th>Zarządzanie</th>

            </tr>
            </thead>
            <tbody>
            <c:forEach var="Timetable" items="${timetableList}">
                <tr class="border-top">
                    <td >
                            ${Timetable.laboratory.name}
                    </td>
                    <td >
                            ${Timetable.startFormatter}
                    </td>
                    <td >
                            ${Timetable.endFormatter}

                    </td>
                    <td >
                            ${Timetable.text}

                    </td>
                    <td >
                            ${Timetable.user.username}

                    </td>
                    <td>

                        <button onclick="return JSconfirm('Czy na pewno chcesz potwierdzić Termin?','/potwierdztermin/${Timetable.id}')"
                                type="button" class="btn btn-success"><i class="bi bi-check-square-fill"></i> Potwierdź</button>
                        <button type="button" class="btn btn-danger" onclick="return JSconfirm('Czy na pewno chcesz anulować termin zgłoszony przez użytkownika?','/anulujtermin/${Timetable.id}')">
                            <i class="bi bi-x-circle-fill"></i>
                            Anuluj

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

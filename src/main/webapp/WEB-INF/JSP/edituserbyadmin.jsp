<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity5">
<head>
    <title>Edycja konta ${edituser.username}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/../css/navbar.css">
    <link rel="stylesheet" href="/../css/body.css">
    <link rel="stylesheet" href="/../css/footer.css">
    <link rel="stylesheet" href="/../css/registration.css">
    <script type="text/javascript" src="//code.jquery.com/jquery-3.6.0.min.js"></script>
    <script type="text/javascript" src="/../js/emailValidator.js"></script>
</head>
<body class="d-flex flex-column min-vh-100">
<%@include file="navbar.jsp" %>
<div id="bodycontainer" class="container rounded">

    <h1 class="text-center" style="color: #FFE8E8">Edycja Użytkownika ${edituser.username}</h1>
    <div class="bodycontainer" class="container rounded">
        <form:form id="form1" name="form1" modelAttribute="edituser" method="POST" action="/edytujuzytkownika/zapiszuzytkownika" class="rounded p-4 p-sm-3">
            <form:input required="required" type="hidden" id="usernames" class="form-control" path="id"/>
            <div class="mb-3">
                <label class="form-label">Nazwa Użytkownika</label>
                <form:input required="required" readonly="true" class="form-control" path="username"/>
            </div>
            <div class="mb-3">
                <label class="form-label">Rola</label>
                <form:select required="required" path="role" class="form-control">
                    <form:option value="ROLE_EMPLOYEE">Pracownik</form:option>
                    <form:option value="ROLE_SUPERVISOR">Opiekun Sali</form:option>
                </form:select>
            </div>
            <div class="mb-3">
                <label class="form-label">Czy Aktywne</label>
                <form:select required="required" path="enabled" class="form-control">
                    <form:option value="true">Aktywne</form:option>
                    <form:option value="false"> Zablokowane</form:option>
                </form:select>
            </div>
            <div class="mb-3">
                <label class="form-label">Imię</label>
                <form:input required="required" class="form-control" path="firstname"/>
            </div>
            <div class="mb-3">
                <label class="form-label">Nazwisko</label>
                <form:input required="required" class="form-control" path="surname"/>
            </div>
            <div class="mb-3">
                <label class="form-label">E-mail</label>
                <form:input required="required" id="email" class="form-control" path="email"/>
                <div class="alert alert-danger" id="emailcheck" role="alert">
                </div>
                <c:if test="${wrongemail != null}" >
                    <div id="emailcheck" class="alert alert-danger alert-dismissible fade show" role="alert">
                            ${wrongemail}
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                </c:if>
            </div>
            <div class="mb-3">
                <label class="form-label">Nr Telefonu</label>
                <form:input required="required" class="form-control" path="telephone"/>
            </div>
            <div class="mb-3">
                <label class="form-label">Wydział</label>
                <form:input required="required" class="form-control" path="faculty"/>
            </div>
            <div class="mb-3">
                <label class="form-label">Katedra</label>
                <form:input required="required" class="form-control" path="cathedral"/>
            </div>
            <p id="validate-status" style="color:white"></p>
            <div class="d-flex justify-content-center"><button id="submitbtn" class="btn btn-primary" type="submit">Zapisz Użytkownika</button></div>
        </form:form>
    </div>

</div>

<%@include file="footer.jsp" %>
</body>

</html>

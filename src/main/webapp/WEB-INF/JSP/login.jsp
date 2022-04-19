<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style><%@include file="/css/login.css"%></style>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

       <title>Strona Logowania</title>
</head>
<body>
<div class="d-flex justify-content-center" id="header">
    <img src="/../gfx/logopk.png" alt="logopk" id="pic" width="350" height="100"/>

</div>


<div class="full-width d-flex justify-content-center and align-items-center">

    <form:form method="POST" action="/login" class="rounded p-4 p-sm-3">
        <div class="mb-3">
            <label for="username" class="form-label">Nazwa Użytkownika</label>
            <input type="text" name="username" class="form-control" id="username" required>
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Hasło</label>
            <input type="password" name="password" class="form-control" id="password" required>
        </div>
        <c:if test="${param.error != null}" >
            <div class="alert alert-danger" role="alert">
                Nieprawidłowa nazwa użytkownika lub hasło!
            </div>
        </c:if>
        <div class="d-flex justify-content-center"><button class="btn btn-primary" type="submit">Zaloguj</button></div>
        <div class="d-flex justify-content-center"><a href="/rejestracja" class="btn btn-secondary" id="registerbutton">Nie masz Jeszcze konta? Zarejestruj się!</a></div>


    </form:form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="utf-8" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity5">
<head>

    <meta charset="uft-8" />
    <title>Edytowanie aktualności</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.1/summernote.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-sweetalert/1.0.1/sweetalert.css">
    <link rel="stylesheet" href="/../css/navbar.css">
    <link rel="stylesheet" href="/../css/body.css">
    <link rel="stylesheet" href="/../css/footer.css">
    <link rel="stylesheet" href="/../css/addinformationdashboard.css">
    <script type="text/javascript" src="/../js/filemanagement.js"></script>
</head>
<body class="d-flex flex-column min-vh-100">
<%@include file="navbar.jsp" %>
<div id="bodycontainer" class="container rounded">
    <h1 class="text-center" style="color: #FFE8E8">Edycja ogłoszenia</h1>
    <div class="bodycontainer" class="container rounded">

        <form:form id="form1" name="form1" modelAttribute="editinformationdashboard" method="POST" action="/zaktualizujaktualnosc/${editinformationdashboard.id}" class="rounded p-4 p-sm-3">
            <div class="mb-3">
                <label class="form-label">Tytuł</label>
                <form:input required="required" class="form-control" path="title"/>
            </div>
            <div class="mb-3">
                <label class="form-label">Data dodania</label>

                <form:input required="required" readonly="true" class="form-control" path="date" value="${dateString}"/>
            </div>
            <div class="mb-3">
                <label  class="form-label">Opis</label>
                <form:textarea id="summernote" path="description"/>
            </div>
            <div class="d-flex justify-content-center"><button class="btn btn-primary" type="submit">Zapisz Aktualność</button></div>
        </form:form>
    </div>
</div>
<script type="text/javascript" src="//code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
<script type="text/javascript" src="/../js/summernoteconfig.js"></script>
<%@include file="footer.jsp" %>
</body>

</html>

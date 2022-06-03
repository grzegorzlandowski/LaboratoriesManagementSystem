<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="utf-8" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity5">
<head>

    <meta charset="uft-8" />
    <title>Dodaj wyposażenie laboratorium</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.1/summernote.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/../css/navbar.css">
    <link rel="stylesheet" href="/../css/body.css">
    <link rel="stylesheet" href="/../css/footer.css">
    <link rel="stylesheet" href="/../css/addinformationdashboard.css">
    <script type="text/javascript" src="/../js/filemanagement.js"></script>
</head>
<body class="d-flex flex-column min-vh-100">
<%@include file="navbar.jsp" %>
<div id="bodycontainer" class="container rounded">
    <h1 class="text-center" style="color: #FFE8E8">Dodaj Wyposażenie</h1>
    <div class="bodycontainer" class="container rounded">

        <form:form id="form1" name="form1" modelAttribute="laboratoryequipment" method="POST" action="/zapiszwyposazeniezbazy/${laboratoryid}" class="rounded p-4 p-sm-3">
            <div class="mb-3">
                <label class="form-label">Stanowisko/Miejsce przechowywania</label>
                <form:input required="required" class="form-control" path="location"/>
            </div>
            <div class="mb-3">
                <label class="form-label">Informacje dodatkowe</label>
                <form:input class="form-control" path="additionalInformation"/>
            </div>
            <div class="mb-3">
                <label class="form-label">Wybierz sprzęt z listy</label>
                <form:select  path="equipmentDetails" class="form-control">
                    <form:options items="${equipmentdetails}" itemValue="id" itemLabel="title" />

                </form:select>
            </div>
                <div class="d-flex justify-content-center"><button class="btn btn-primary" type="submit">Dodaj wyposażenie</button></div>

        </form:form>
    </div>
</div>
</div>
<script type="text/javascript" src="//code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
<script type="text/javascript" src="/../js/summernoteconfig.js"></script>
<%@include file="footer.jsp" %>
</body>

</html>

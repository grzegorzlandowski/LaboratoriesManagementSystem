<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<sec:authorize access="isAuthenticated()">
<script src="/../js/getCountUserToActiveAndNotifications.js"></script>
</sec:authorize>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
<nav class="navbar navbar-expand-lg navbar-dark ">
    <div class="container-fluid">
        <a class="navbar-brand" href="/"> <img id="logopkwhite" src="/gfx/logopkwhite.png" alt="" width="160" height="45" class="d-inline-block align-text-top"></a> <a class="navbar-brand" href="/">System Zarządzania Laboratoriami Wydziałowymi</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
<sec:authorize access="isAuthenticated()">

            <ul class="navbar-nav">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="/" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Ogłoszenia
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <li><a class="dropdown-item" href="/dodajaktualnosc">Nowe ogłoszenie</a></li>
                        <li><a class="dropdown-item"  href="/archiwalneaktualnosci">Archiwalne</a></li>
                    </ul>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Użytkownicy
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <li><a class="dropdown-item" href="/zarzadzanieuzytkownikami/lista">Zarządzanie</a></li>
                        <li><a class="dropdown-item" id="doakceptacji" href="/potwierdzuzytkownika"></a></li>
                        <li><a class="dropdown-item" href="/nowyuzytkownik">Nowy użytkownik</a></li>
                    </ul>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Sale laboratoryjne
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <li><a class="dropdown-item" href="/dodajlaboratorium">Dodaj nowe laboratorium</a></li>
                        <li><a class="dropdown-item" href="/listalaboratoriow">Lista Laboratoriów</a></li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a class="nav-link" >xxxxx</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Konto
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <li><a class="dropdown-item" href="/edytujmojekonto">Edytuj dane konta</a></li>
                        <li><a class="dropdown-item" href="/edytujmojehaslo">Edytuj Hasło</a></li>
                    </ul>
                </li>
                <li class="nav-item">
                    <form id="logoutForm" method="POST" action="${contextPath}/logout">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                    <a class="nav-link" onclick="document.forms['logoutForm'].submit()">Wyloguj</a>
                </li>
                <li id="bell" class="nav-item dropdown" >

                    <a class="nav-link" href="#" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <i class="bi bi-bell fa-lg"></i>
                    </a>
                    <ul class="dropdown-menu notification-ui_dd">


                            <div class="notification-ui_dd-header">
                                <h3 class="text-center">Powiadomienia</h3>
                            </div>
                            <div id="notifakcje"  class="notification-ui_dd-content">

                            </div>



                            <div class="notification-ui_dd-footer">
                                <a href="/pokazpowiadomienia" id="viewallbutton" class="btn btn-success btn-block">Wyświetl wszystkie</a>
                            </div>
                        </div>
                    </ul>

                </li>

            </ul>
            </sec:authorize>
        </div>
    </div>
</nav>
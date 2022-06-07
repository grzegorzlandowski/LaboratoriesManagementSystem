<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="//code.jquery.com/jquery-3.6.0.min.js"></script>
<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
<script src="/../js/getCountUserToActiveAndNotifications.js"></script>
</sec:authorize>
<sec:authorize access="hasAnyRole('ROLE_SUPERVISOR','ROLE_EMPLOYEE')"><script src="/../js/showNotifications.js"></script></sec:authorize>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
<nav class="navbar navbar-expand-lg navbar-dark ">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">System Zarządzania Laboratoriami Wydziałowymi</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
<sec:authorize access="isAuthenticated()">

            <ul class="navbar-nav">
                <sec:authorize access="hasAnyRole('ROLE_ADMIN')">
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
                    </sec:authorize>
                    <sec:authorize access="hasAnyRole('ROLE_SUPERVISOR')">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Sale laboratoryjne
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <li><a class="dropdown-item" href="/listalaboratoriow">Lista Laboratoriów</a></li>
                            <li><a class="dropdown-item" href="/mojelaboratoria">Moje laboratoria</a></li>
                        </ul>
                    </li>
                    </sec:authorize>
                    <sec:authorize access="hasAnyRole('ROLE_EMPLOYEE')">
                    <li class="nav-item">
                        <a class="nav-link" href="/listalaboratoriow">Lista Laboratoriów</a>
                    </li>

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            System zgłoszeń
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <li><a class="dropdown-item" href="/nowezgloszenie">Nowe zgłoszenie</a></li>
                            <li><a class="dropdown-item" href="/mojezgloszenia">Moje zgłoszenia</a></li>
                        </ul>
                    </li>
                </sec:authorize>
                <sec:authorize access="hasAnyRole('ROLE_SUPERVISOR')">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        System zgłoszeń
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <li><a class="dropdown-item" href="/nowezgloszenie">Nowe zgłoszenie</a></li>
                        <li><a class="dropdown-item" href="/opiekun/mojezgloszenia">Zgłoszenia od użytkowników</a></li>
                        <li><a class="dropdown-item" href="/mojezgloszenia">Moje zgłoszenia</a></li>
                    </ul>
                </li>
                </sec:authorize>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Kalendarz
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <li><a class="dropdown-item" href="/kalendarz">Harmonogram Sal</a></li>
                        <li><a class="dropdown-item" href="/nowytermin">Dodaj nowy termin</a></li>
                        <sec:authorize access="hasAnyRole('ROLE_SUPERVISOR')"> <li><a class="dropdown-item" href="/potwierdztermin">Potwierdź Termin</a></li> </sec:authorize>
                    </ul>
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
                    <a class="nav-link" style="cursor: pointer;" onclick="document.forms['logoutForm'].submit()">Wyloguj <sec:authentication property="name"/></a>
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
                    </ul>
                 </div>
                </li>

            </ul>

            </sec:authorize>
        </div>
    </div>
</nav>
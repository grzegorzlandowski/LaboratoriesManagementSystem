<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity5">
<head>
    <title>Harmonogram Sali ${laboratoryname}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/../css/navbar.css">
    <link rel="stylesheet" href="/../css/body.css">
    <link rel="stylesheet" href="/../css/footer.css">
    <script src="/../js/confirmbox.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-sweetalert/1.0.1/sweetalert.css">
    <script type="text/javascript" src="//code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">

    <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,700" rel="stylesheet"/>
    <script src="/../js/daypilot-all.min.js"></script>


</head>
<body class="d-flex flex-column min-vh-100">
<%@include file="navbar.jsp" %>
<div id="bodycontainer" class="container rounded">
    <h1 class="text-center" style="color:#FFE8E8;">Harmonogram Sali ${laboratoryname}</h1>

    <div class="main" style="display: flex;">
        <div style="margin-right: 10px;">
            <div id="nav"></div>
                    </div>
        <div style="flex-grow: 1">
            <div id="dp"></div>
        </div>
    </div>

    <script>
        const nav = new DayPilot.Navigator("nav");
        nav.showMonths = 3;
        nav.skipMonths = 3;
        nav.selectMode = "week";
        nav.locale= "pl-pl";
        nav.onTimeRangeSelected = (args) => {
            dp.startDate = args.day;
            dp.update();
            dp.events.load("/kalendarz/terminy/${laboratoryid}");
        };
        nav.init();


        const dp = new DayPilot.Calendar("dp", {
            viewType: "Week",
            headerDateFormat: "dddd",
            eventMoveHandling: "Disabled",
            eventClickHandling: "Disabled",
            eventResizeHandling: "Disabled",
            eventRightClickHandling: "Disabled",
            locale:"pl-pl",
            businessBeginsHour:7,
            businessEndsHour :21,
            onBeforeEventRender: args => {
                args.data.barBackColor = "transparent";
                if (!args.data.barColor) {

                }
            }
        <c:if test="${editor== 'yes'}">  ,
            onTimeRangeSelected: async args => {

                const form = [
                    {name: "Tytuł", id: "text"}
                ];

                const data = {
                    text: ""
                };

                const modal = await DayPilot.Modal.form(form, data);
                dp.clearSelection();

                if (modal.canceled) {
                    return;
                }

                var params = {
                    start: args.start.toString(),
                    end: args.end.toString(),
                    text: modal.result.text
                };
                DayPilot.Http.ajax({
                    url: '/kalendarz/termin/stworz/${laboratoryid}',
                    data: params,
                    type : "POST",
                    success: function (ajax) {
                        var data = ajax.data;
                        dp.events.add(new DayPilot.Event(data));
                        //dp.message("Event created");
                    }
                });
            }
            </c:if>
        });
<c:if test="${editor== 'yes'}">
dp.eventDeleteHandling = "Update";

        dp.onEventDelete = function(args) {
            if (!confirm("Czy na pewno chcesz usunąć Wydarzenie?")) {
                args.preventDefault();
            }
        };

        dp.onEventDeleted = function(args) {


            $.post("/termin/usun/" + args.e.id(), function(result) {
                //dp.message("Termin Usunięty");
            });

        };
        </c:if>

        dp.init();
        dp.events.load("/kalendarz/terminy/${laboratoryid}");

    </script>



</div>

<%@include file="footer.jsp" %>
</body>

</html>

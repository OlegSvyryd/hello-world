<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <!-- CSS -->
    <link rel="stylesheet" type="text/css" href="<c:url value='../../../../CSS/Welcome.css' />" >
    <link rel="stylesheet" type="text/css" href="<c:url value='../../../../CSS/Main.css' />" >
    <link rel="stylesheet" type="text/css" href="<c:url value='../../../../CSS/jquery.datetimepicker.css' />" >
    <link rel="stylesheet" type="text/css" href="<c:url value='../../../../CSS/theme.css' />" >
    <link rel="stylesheet" type="text/css" href="<c:url value='../../../../CSS/jquery.dataTables.min.css' />" >

    <!-- Scripts -->
    <script src="<c:url value="../../../../JS/jquery.min.js" />"></script>
    <script src="<c:url value="../../../../JS/jquery.datetimepicker.js" />"></script>
    <script src="<c:url value="../../../../JS/jquery.dataTables.min.js" />"></script>
    <script src="<c:url value="../../../../JS/jQuery.print.js" />"></script>

    <title>StoreBase | Звіти інтернет-магазинів</title>
</head>
<body>
<div id="wrapper">
    <c:url value="/j_spring_security_logout" var="logoutUrl" />
    <jsp:directive.include file="../../Components/Header.html" />

    <div id = "dates">
    <label for="beginDate">Введіть початкову дату:</label>
    <input id="beginDate" type="text"/><br>
    <label for="endDate">Введіть кінцеву дату:</label>
    <input id="endDate" type="text"/><br>
    <input id="send" type="button" value="Показати"/>
    </div>

    <div id="result"></div>
    <span id="empty-span" style="visibility:hidden;">for-print-caption</span>
    <div class="footer-push"></div>
</div>
<jsp:directive.include file="../../Components/Footer.html" />
<script type="text/javascript">
    $('#beginDate').datetimepicker();
    $('#endDate').datetimepicker();
    var repId = ${reportId};

    $(document).on("click", "div #dates #send", function() {
        var clickedValue1 = $(this).parents('#dates');
        var clickedValue2 = $(this).parents('#dates');

        clickedValue1 = $('#beginDate', clickedValue1).val();
        clickedValue2 = $('#endDate', clickedValue2).val();
        var savedata = {
            beginDate: clickedValue1,
            endDate: clickedValue2
        };
        $.ajax({
            url: (repId == 1) ? "/webShoeOborotReport" : "error",
            type: "POST",
            datatype: "json",
            data: savedata,
            error: function () {
                alert("Помилка");
            },
            success: function () {
                $.get( "/wsrep" + repId, function( data ) {
                    $( '#result' ).html( data );
                    $('#result #stores-table').dataTable();
                });
                return false;
            }
        });
        return false;
    });

    $(document).on("click", "div #result #print-report", function() {
        $("#stores-table").print({
            globalStyles: true,
            mediaPrint: false,
            stylesheet: null,
            noPrintSelector: ".no-print",
            iframe: true,
            append: "#dates #beginDate, #dates #endDate",
            prepend: "#empty-span",
            manuallyCopyFormValues: true,
            deferred: $.Deferred()
        });
    });
</script>
</body>
</html>

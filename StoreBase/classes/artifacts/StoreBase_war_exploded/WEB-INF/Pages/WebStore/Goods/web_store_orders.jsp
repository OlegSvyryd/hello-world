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
    <link rel="stylesheet" type="text/css" href="<c:url value='../../../../CSS/jquery.dataTables.min.css' />" >
    <link rel="stylesheet" type="text/css" href="<c:url value='../../../../CSS/sweetalert.css' />" >

    <!-- Scripts -->
    <script src="<c:url value="../../../../JS/jquery.min.js" />"></script>
    <script src="<c:url value="../../../../JS/jquery.dataTables.min.js" />"></script>
    <script src="<c:url value="../../../../JS/sweetalert.min.js" />"></script>

    <title>StoreBase | Замовлення</title>
</head>
<body>
<div id="wrapper">
    <c:url value="/j_spring_security_logout" var="logoutUrl" />
    <jsp:directive.include file="../../Components/Header.html" />

<c:if test="${!empty orders}">
    <table id="stores-table">
    <caption align="center"><h1 id = "table-stores">Список замовлень інтернет-магазину</h1></caption>
    <thead>
        <tr>
        <th>Дата операції</th>
        <th>Клієнт</th>
        <th>Товар, що замовляється</th>
        <th>Кількість замовленого товару</th>
        <th>Ціна</th>
        <th>Клієнт отримав товар</th>
        <th>Підтвердження</th>
        <th>Скасування</th>
    </tr>
    </thead>
        <tbody>
    <c:forEach items="${orders}" var="orders">
        <tr class = "editField">
            <td class='too-width'><c:out value="${orders[0]}" />
            </td>
            <td class='too-width'>
                <c:out value="${orders[1]}" /><br>
                <c:out value="${orders[2]}" /><br>
                <c:out value="${orders[3]}" /><br>
            </td>
            <td class='too-width'><c:out value="${orders[4]}" />
            </td>
            <td>
                <c:out value="${orders[5]}" />
            </td>
            <td>
                <c:out value="${orders[6]}" />
            </td>
            <td>
                <input type = "checkbox" id="is_paid" about="${orders[7]}">
            </td>
            <td>
                <c:choose>
                    <c:when test="${orders[8] eq(0)}">
                        <input type="button" id = "confirm-success-field" about="${orders[7]}" value = "Підтвердити" />
                    </c:when>
                    <c:when test="${orders[8] eq(1)}">
                        Підтверджено
                    </c:when>
                </c:choose>
            </td>
            <td>
                 <input type="button" id = "confirm-fail-field" about="${orders[7]}" value = "Скасувати" />
            </td>
        </tr>
    </c:forEach>
        </tbody>
    </table>
    </c:if>
        <div class="footer-push"></div>
</div>
<jsp:directive.include file="../../Components/Footer.html" />
<script>
    $(document).ready(function(){
        $("#stores-table").dataTable({
            "aoColumnDefs": [
                {'bSortable': false, 'aTargets': [5,6,7]}
            ]
        });
    })
</script>
    <!-- Confirm and cancel order -->
<script type="text/javascript">
    $(document).on("click", "table .editField #confirm-success-field", function() {
        var clickedValue = $(this).parents('.editField');
        $(clickedValue).css("background-color", "#BDBDBD");
        swal({
            title: "Підтвердити дане замовлення?",
            type: "warning",
            showCancelButton: true,
            closeOnConfirm: false,
            confirmButtonText: "Підтвердити!",
            confirmButtonColor: "#26A69A"
        }, function(isConfirmed) {
            if (isConfirmed) {
                var clickedValue1 = $(this).parents('.editField');
                clickedValue = $('td #confirm-success-field', clickedValue).attr('about');
                var savedata = {
                    idOrder: clickedValue
                };
                $.ajax({
                    url: "/confirm_web_order_success",
                    type: "POST",
                    datatype: "json",
                    data: savedata,
                    error: function () {
                        $(clickedValue).css("background-color", "WHITE");
                        swal("Помилка", "Неможливо підключитися до сервера!", "error");
                    },
                    success: function () {
                            swal("Замовлення підтверджено!", "", "success");
                            location.reload(true);
                            clickedValue1.remove();
                    }
                });
            }
            else {
                $(clickedValue).css("background-color", "WHITE");
            }
        });
    });

    $(document).on("click", "table .editField #confirm-fail-field", function() {
        var clickedValue = $(this).parents('.editField');
        $(clickedValue).css("background-color", "#BDBDBD");
        swal({
            title: "Скасувати вибране замовлення?",
            type: "warning",
            showCancelButton: true,
            closeOnConfirm: false,
            confirmButtonText: "Скасувати!",
            confirmButtonColor: "#ec6c62"
        }, function(isConfirmed) {
            if (isConfirmed) {
                var clickedValue1 = $(this).parents('.editField');

                clickedValue = $('td #confirm-fail-field', clickedValue).attr('about');
                var savedata = {
                    idOrder: clickedValue
                };
                $.ajax({
                    url: "/confirm_web_order_fail",
                    type: "POST",
                    datatype: "json",
                    data: savedata,
                    error: function () {
                        $(clickedValue).css("background-color", "WHITE");
                        swal("Помилка", "Неможливо підключитися до сервера!", "error");
                    },
                    success: function () {
                        swal("Замовлення скасовано!", "", "success");
                        location.reload(true);
                        clickedValue1.remove();
                    }
                });
            }
            else {
                $(clickedValue).css("background-color", "WHITE");
            }
        });
    });

    $(document).on("change", "table .editField #is_paid", function() {
        var clickedValue = $(this).parents('.editField');
        var clickedValue1 = $(this).parents('.editField');

        clickedValue = $('td #is_paid', clickedValue).attr('about');
        var savedata = {
            idOrder: clickedValue
        };
        $.ajax({
            url: "/confirmed_web_order_paid",
            type: "POST",
            datatype: "json",
            data: savedata,
            error: function () {
                swal("Помилка", "Неможливо підключитися до сервера!", "error");
            },
            success: function () {
                clickedValue1.remove();
            }
        });
    });
</script>
</body>
</html>
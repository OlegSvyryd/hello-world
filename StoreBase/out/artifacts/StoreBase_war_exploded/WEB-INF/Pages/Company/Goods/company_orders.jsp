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

    <button id = "show-confirmed-orders" class="button" onclick="document.location.href='company_confirmed_orders${company_id}'">Переглянути підтверджені замовлення</button>

<c:if test="${!empty orders}">
    <table id="stores-table">
    <caption align="center"><h1 id = "table-stores">Список замовлень компанії</h1></caption>
    <thead>
        <tr>
        <th>Дата операції</th>
        <th>Магазин, який робить замовлення</th>
        <th>Товар, що замовляється</th>
        <th>Кількість замовленого товару</th>
        <th>Кількість товару на складі</th>
        <th>Підтвердження</th>
        <th>Скасування</th>
    </tr>
    </thead>
        <tbody>
    <c:forEach items="${orders}" var="order">
        <tr class = "editField">
            <td><c:out value="${order.date}" />
            </td>
            <td><c:out value="${order.storeCatalog.store.name}" />
            </td>
            <td><c:out value="${order.companyCatalog.name}" />
            </td>
            <td><c:out value="${order.amount}" />
            </td>
            <td><c:out value="${order.companyCatalog.amount}" />
            </td>
            <td>
                <input type="button" id = "confirmOrderSuccess-field" about="${order.id}" value = "Підтвердити замовлення" />
            </td>
            <td>
                <input type="button" id = "confirmOrderFail-field" about="${order.id}" value = "Скасувати замовлення" />
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
                {'bSortable': false, 'aTargets': [5,6]}
            ]
        });
    })
</script>
    <!-- Confirm and cancel order -->
<script type="text/javascript">
    $(document).on("click", "table .editField #confirmOrderSuccess-field", function() {
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
                clickedValue = $('td #confirmOrderSuccess-field', clickedValue).attr('about');
                var savedata = {
                    idOrder: clickedValue
                };
                $.ajax({
                    url: "/confirm_order_success",
                    type: "POST",
                    datatype: "json",
                    data: savedata,
                    error: function () {
                        $(clickedValue).css("background-color", "WHITE");
                        swal("Помилка", "Неможливо підключитися до сервера!", "error");
                    },
                    success: function (res) {
                        if (res == 'success') {
                            swal("Замовлення підтверджено!", "", "success");
                            location.reload(true);
                            clickedValue1.remove();
                        }
                        else {
                            swal("Помилка!", "Помилка, компанія має в наявності всього " + res + " одиниць даного товару.", "success");
                        }
                    }
                });
            }
            else {
                $(clickedValue).css("background-color", "WHITE");
            }
        });
    });

    $(document).on("click", "table .editField #confirmOrderFail-field", function() {
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

                clickedValue = $('td #confirmOrderFail-field', clickedValue).attr('about');
                var savedata = {
                    idOrder: clickedValue
                };
                $.ajax({
                    url: "/confirm_order_fail",
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
</script>
</body>
</html>
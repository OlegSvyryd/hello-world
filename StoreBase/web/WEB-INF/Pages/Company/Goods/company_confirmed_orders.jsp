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
            <caption align="center"><h1 id = "table-stores">Список замовлень компанії</h1></caption>
            <thead>
            <tr>
                <th>Дата операції</th>
                <th>Магазин, який робить замовлення</th>
                <th>Товар, що замовляється</th>
                <th>Кількість замовленого товару</th>
                <th>Кількість товару на складі(без даного)</th>
                <th>Оплачено</th>
                <th>Доставка</th>
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
                        <input type = "checkbox" id="is_paid" about="${order.id}" value="Оплачено">
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${order.delivery eq(false)}">
                                <input type="button" id = "confirmDeliverySuccess-field" about="${order.id}" value = "Доставлено" />
                            </c:when>

                            <c:otherwise>
                                Доставлено
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${order.delivery eq(false)}">
                                <input type="button" id = "confirmDeliveryFail-field" about="${order.id}" value = "Скасувати доставку" />
                            </c:when>

                            <c:otherwise>
                                Доставлено
                            </c:otherwise>
                        </c:choose>
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
                {'bSortable': false, 'aTargets': [6,7]}
            ]
        });
    })
</script>

<!-- Confirmed commission success -->
<script type="text/javascript">
    $(document).on("click", "table .editField #confirmDeliverySuccess-field", function() {
        var clickedValue = $(this).parents('.editField');
        clickedValue = $('td #confirmDeliverySuccess-field', clickedValue).attr('about');
        var savedata = {
            idOrder: clickedValue
        };
        $.ajax({
            url: "/confirmed_order_success",
            type: "POST",
            datatype: "json",
            data: savedata,
            error: function () {
                swal("Помилка", "Неможливо підключитися до сервера!", "error");
            },
            success: function () {
                swal("Замовлення доставлено", "", "success");
                location.reload(true);
                clickedValue.remove();
            }
        });
    });
</script>

<!-- Confirmed commission fail -->
<script type="text/javascript">
    $(document).on("click", "table .editField #confirmDeliveryFail-field", function() {

        var clickedValue = $(this).parents('.editField');
        $(clickedValue).css("background-color", "#BDBDBD");
        swal({
            title: "Скасувати підтверджене замовлення?",
            type: "warning",
            showCancelButton: true,
            closeOnConfirm: false,
            confirmButtonText: "Скасувати!",
            confirmButtonColor: "#ec6c62"
        }, function(isConfirmed) {
            if (isConfirmed) {
                var clickedValue1 = $(this).parents('.editField');
                clickedValue = $('td #confirmDeliveryFail-field', clickedValue).attr('about');
                var savedata = {
                    idOrder: clickedValue
                };
                $.ajax({
                    url: "/confirmed_order_fail",
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

<!-- Confirmed commission paid -->
<script type = 'text/javascript'>
    $(document).on("change", "table .editField #is_paid", function() {
        var clickedValue = $(this).parents('.editField');
            var clickedValue1 = $(this).parents('.editField');

            clickedValue = $('td #is_paid', clickedValue).attr('about');
            var savedata = {
                idOrder: clickedValue
            };
            $.ajax({
                url: "/confirmed_order_paid",
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

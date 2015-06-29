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

    <title>StoreBase | Товари</title>
</head>
<body>
<div id="wrapper">
    <c:url value="/j_spring_security_logout" var="logoutUrl" />
    <jsp:directive.include file="../../Components/Header.html" />

    <c:if test="${!empty orders}">
        <table id="stores-table">
            <caption align="center"><h1 id = "table-stores">Список замовлень магазину</h1></caption>
            <thead>
            <tr>
                <th>Дата операції</th>
                <th>Компанія, у якої здійснюється замовлення</th>
                <th>Товар, що замовляється</th>
                <th>Кількість замовленого товару</th>
                <th>Кількість товару у магазині</th>
                <th>Оплачено</th>
                <th>Етап</th>
                <th>Видалити рядок</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${orders}" var="order">
                <tr class = "editField">
                    <td><c:out value="${order.date}" />
                    </td>
                    <td><c:out value="${order.companyCatalog.company.name}" />
                    </td>
                    <td><c:out value="${order.storeCatalog.name}" />
                    </td>
                    <td><c:out value="${order.amount}" />
                    </td>
                    <td><c:out value="${order.storeCatalog.amount}" />
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${order.paid eq(false)}">
                                Неоплачено
                            </c:when>
                            <c:when test="${order.paid eq(true)}">
                                Оплачено
                            </c:when>
                            <c:otherwise>
                                Помилка
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${order.confirm eq(-1)}">
                                Замовлення скасовано
                            </c:when>
                            <c:when test="${(order.delivery eq(false) and order.confirm eq(0))}">
                                Розглядається
                            </c:when>
                            <c:when test="${(order.confirm eq(1) and order.delivery eq(false))}">
                                Виконується доставка
                            </c:when>
                            <c:when test="${(order.delivery eq(true) and order.confirm eq(1))}">
                                Доставлено
                            </c:when>

                            <c:otherwise>
                                Помилка
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${order.confirm eq(-1)}">
                                <input type = "button" id="deleteRow-field" about="${order.id}" value="Видалити">
                            </c:when>

                            <c:otherwise>
                                <span title="Видалити можна тільки скасовані замовлення">Видалити</span>
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
                {'bSortable': false, 'aTargets': [7]}
            ]
        });
    })
</script>
<!-- Hide commission row(only confirm=false) -->
<script type="text/javascript">
    $(document).on("click", "table .editField #deleteRow-field", function() {
        var clickedValue = $(this).parents('.editField');
        var clickedValue1 = $(this).parents('.editField');

        clickedValue = $('td #deleteRow-field', clickedValue).attr('about');
        var savedata = {
            idOrder: clickedValue
        };
        $.ajax({
            url: "/hide_order_checking",
            type: "POST",
            datatype: "json",
            data: savedata,
            error: function () {
                swal("Помилка", "Неможливо підключитися до сервера!", "error");
            },
            success: function () {
                swal("Дані про замовлення видалено", "", "success");
                location.reload(true);
                clickedValue1.remove();
            }
        });
    });
</script>

</body>
</html>

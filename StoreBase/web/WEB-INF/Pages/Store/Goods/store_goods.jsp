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
    <link rel='stylesheet' type="text/css" href="<c:url value='../../../../CSS/jquery-ui.min.css' />" />
    <link rel='stylesheet' type="text/css" href="<c:url value='../../../../CSS/theme.css' />" />
    <link rel="stylesheet" type="text/css" href="<c:url value='../../../../CSS/jquery.dataTables.min.css' />" >
    <link rel="stylesheet" type="text/css" href="<c:url value='../../../../CSS/sweetalert.css' />" >

    <!-- Scripts -->
    <script src="<c:url value="../../../../JS/jquery.min.js" />"></script>
    <script src="<c:url value="../../../../JS/jquery.validate.min.js" />"></script>
    <script src="<c:url value="../../../../JS/jquery-ui.min.js" />"></script>
    <script src="<c:url value="../../../../JS/jquery.dataTables.min.js" />"></script>
    <script src="<c:url value="../../../../JS/sweetalert.min.js" />"></script>

    <title>StoreBase | Товари</title>
</head>
<body>
<div id="wrapper">
    <c:url value="/j_spring_security_logout" var="logoutUrl" />
    <jsp:directive.include file="../../Components/Header.html" />

    <button id = "show-confirmed-orders" class="button" onclick="document.location.href='store_reports${store_id}'">Отримати звіти</button>

    <c:if test="${!empty store_goods}">
    <table id="stores-table">
        <caption align="center"><h1 id = "table-stores">Список товарів вибраного магазину</h1></caption>
        <thead>
        <tr>
            <th>Назва товару</th>
            <th>Тип товару</th>
            <th>Ціна товару</th>
            <th>Кількість у магазині</th>
            <th>Інформація про товар</th>
            <th>Компанія - постачальник</th>
            <th>Зробити замовлення</th>
            <th>Товар, який продався</th>
            <th>Редагування даних про товар</th>
            <th>Видалити даний товар</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${store_goods}" var="goods">
        <tr class = "editField">
            <td class="too-width"><c:out value="${goods.name}" />
            </td>
            <td class="too-width"><c:out value="${goods.goodsType}" />
            </td>
            <td><c:out value="${goods.price}" />
            </td>
            <td><c:out value="${goods.amount}" /> <c:out value="${goods.dimension}" />
            </td>
            <td class="too-width"><c:out value="${goods.description}" />
            </td>
            <td class="too-width"><c:out value="${goods.companyName}" />
            </td>
            <td>
                <input type="button" id = "addAmountPopup-field" about="${goods.id}" value = "Замовити" />
            </td>
            <td>
                <input type="button" id = "delAmountPopup-field" about="${goods.id}" value = "Продано" />
            </td>
            <td>
                <input type="button" id = "updStore-field" value = "Редагувати"
                       onclick="document.location.href='upd_store_goods${goods.id}'"/>
            </td>
            <td>
                <input type="button" id = "delGoods-field" about="${goods.id}" value = "Видалити" />
            </td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

        <div class="footer-push"></div>
</div>

<!-- Popup add amount -->
<div id = "amount-popup-add" style="visibility: hidden;" class = "popup-service">
    <label for="input-amount-field-add">Введіть скільки одиниць товару ви хочете замовити:</label>
    <input type="text" id = "input-amount-field-add">
    <input type="button" id = "submit-amount-field-add" value = "Замовити">
</div>

<div id = "amount-popup-del" style="visibility: hidden;" class = "popup-service">
    <label for="input-amount-field-del">Введіть скільки одиниць товару ви продали:</label>
    <input type="text" id = "input-amount-field-del">
    <input type="button" id = "submit-amount-field-del" value = "Продано">
</div>

<jsp:directive.include file="../../Components/Footer.html" />
<script>
    $(document).ready(function(){
        $("#stores-table").dataTable({
            "aoColumnDefs": [
                {'bSortable': false, 'aTargets': [6,7,8,9]}
            ]
        });
    })
</script>

<!-- Delete goods script -->
<script type="text/javascript">
    $(document).on("click", "table .editField #delGoods-field", function() {
        var clickedValue = $(this).parents('.editField');
        $(clickedValue).css("background-color", "#BDBDBD");

        swal({
            title: "Видалити вибраний товар?",
            type: "warning",
            showCancelButton: true,
            closeOnConfirm: false,
            confirmButtonText: "Видaлити!",
            confirmButtonColor: "#ec6c62"
        }, function(isConfirmed) {
            if (isConfirmed) {
                var clickedValue1 = $(this).parents('.editField');

                clickedValue = $('td #delGoods-field', clickedValue).attr('about');
                var savedata = {
                    idGoods: clickedValue
                };
                $.ajax({
                    url: "/del_store_goods_success",
                    type: "POST",
                    datatype: "json",
                    data: savedata,
                    error: function () {
                        $(clickedValue).css("background-color", "WHITE");
                        swal("Помилка", "Неможливо підключитися до сервера!", "error");
                    },
                    success: function () {
                        swal("Видалено!", "Вам товар успішно видалено", "success");
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

<!-- Popup add amount -->
<script type="text/javascript">

    //get goods id
    var goodsId;

    $(document).on("click", "table .editField #addAmountPopup-field", function() {
        var clickedValue = $(this).parents('.editField');
        var clickedValueTr = $(this).parents('.editField');

        $(clickedValue).css("background-color", "#BDBDBD");
        clickedValue = $('td #addAmountPopup-field', clickedValue).attr('about');
        goodsId = clickedValue;

        swal({
            title: "Введіть кількість товару для замовлення:",
            type: "input",
            showCancelButton: true,
            closeOnConfirm: false,
            confirmButtonText: "Замовити!",
            confirmButtonColor: "#26A69A"
        }, function (amount) {
            if(amount === false){
                $('tr').css("background-color", "WHITE");
                return false;
            }
            if (amount === "") {
                swal.showInputError("Введіть числове значення!");
                return false
            }
            $.ajax({
                url: "/add_amount_goods",
                type: "POST",
                datatype: "json",
                data: {idGoods: goodsId,
                        amount: amount},
                error: function () {
                    $(clickedValueTr).css("background-color", "WHITE");
                    if(isNaN(amount))
                        swal("Помилка", "Введіть ціле числове значення!", "error");
                    else
                        swal("Помилка", "Неможливо підключитися до сервера!", "error");
                },
                success: function (res) {
                    $(clickedValueTr).css("background-color", "WHITE");
                    if (res == "success") {
                        swal("Замовлення прийнято!", "", "success");
                    }
                    else if (res == 'stop') {
                        swal("Помилка, компанія зупинила випуск даного товару.", "", "error");
                    }
                    else if (res == 'undef') {
                        swal("Невідома помилка!", "", "error");
                    }
                    else if (res == 'out') {
                        swal("Помилка, значення не може бути від\'ємним.", "", "error");
                    }
                    else {
                        swal('Помилка, в наявності всього ' + res + ' одиниць товару.', "", "error");
                        amount=res;
                    }
                }
            });
        });
    });

    $(document).on("click", "table .editField #delAmountPopup-field", function() {
        var clickedValue = $(this).parents('.editField');
        $(clickedValue).css("background-color", "#BDBDBD");
        clickedValue = $('td #delAmountPopup-field', clickedValue).attr('about');
        goodsId = clickedValue;

        swal({
            title: "Введіть кількість товару для продажу:",
            type: "input",
            showCancelButton: true,
            closeOnConfirm: false,
            confirmButtonText: "Продано!",
            confirmButtonColor: "#26A69A"
        }, function (amount) {
            if(amount === false) {
                $('tr').css("background-color", "WHITE");
                return false;
            }
            if (amount === "") {
                swal.showInputError("Введіть ціле числове значення!");
                return false
            }
            $.ajax({
                url: "/del_amount_goods",
                type: "POST",
                datatype: "json",
                data: {idGoods: goodsId,
                    amount: amount},
                error: function () {
                    $(clickedValue).css("background-color", "WHITE");
                    if(isNaN(amount))
                        swal("Помилка", "Введіть ціле числове значення!", "error");
                    else
                        swal("Помилка", "Неможливо підключитися до сервера!", "error");
                },
                success: function (res) {
                    $(clickedValue).css("background-color", "WHITE");
                    if (res == "success") {
                        swal("Товар продано!", "", "success");
                        location.reload(true);
                    }
                    else if (res == 'out') {
                        swal("Помилка, значення не може бути від\'ємним.", "", "error");
                    }
                    else {
                        swal('Помилка, в наявності всього ' + res + ' одиниць товару.', "", "error");
                        amount.val(res);
                    }
                }
            });
        });
    });
</script>
</body>
</html>
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
    <script src="<c:url value="../../../../JS/jquery.validate.min.js" />"></script>
    <script src="<c:url value="../../../../JS/jquery.dataTables.min.js" />"></script>
    <script src="<c:url value="../../../../JS/sweetalert.min.js" />"></script>

    <title>StoreBase | Товари</title>
</head>
<body>
<div id="wrapper">
    <c:url value="/j_spring_security_logout" var="logoutUrl" />
    <jsp:directive.include file="../../Components/Header.html" />

        <button id = "show-confirmed-orders" class="button" onclick="document.location.href='add_company_goods${companyId}'" style="margin-bottom:10px !important">Додати новий товар</button>
        <button id = "get-company-reports" class="button" onclick="document.location.href='company_reports${companyId}'">Отримати звіти</button>

    <c:if test="${!empty company_goods}">
    <table id="stores-table">
        <caption align="center"><h1 id = "table-stores">Список товарів вибраної компанії</h1></caption>
        <thead>
        <tr>
            <th>Назва товару</th>
            <th>Тип товару</th>
            <th>Ціна товару</th>
            <th>Кількість на складі</th>
            <th>Інформація про товар</th>
            <th>Редагування даних про товар</th>
            <th>Видалити товар</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${company_goods}" var="goods">
        <tr class = "editField">
            <td class="too-width"><c:out value="${goods.name}" />
            </td>
            <td><c:out value="${goods.goodsType.name}" />
            </td>
            <td><c:out value="${goods.price}" />
            </td>
            <td><c:out value="${goods.amount}" /> <c:out value="${goods.dimension}" />
            </td>
            <td class="too-width"><c:out value="${goods.description}" />
            </td>
            <td>
                <input type="button" id = "updGoods-field" value = "Редагувати"
                       onclick="document.location.href='upd_company_goods${goods.id}'"/>
            </td>
            <td>
                <input type="button" id = "delGoods-field" about="${goods.id}" alt="${goods.company.id}" value = "Видалити" />
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
                    url: "/del_company_goods_success",
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
</body>
</html>
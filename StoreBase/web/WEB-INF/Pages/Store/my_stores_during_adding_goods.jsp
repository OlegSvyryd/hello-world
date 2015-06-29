<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <!-- CSS -->
    <link rel="stylesheet" type="text/css" href="<c:url value='../../../CSS/Welcome.css' />" >
    <link rel="stylesheet" type="text/css" href="<c:url value='../../../CSS/Main.css' />" >
    <link rel="stylesheet" type="text/css" href="<c:url value='../../../CSS/jquery.dataTables.min.css' />" >
    <link rel="stylesheet" type="text/css" href="<c:url value='../../../CSS/sweetalert.css' />" >

    <!-- Scripts -->
    <script src="<c:url value="../../../JS/jquery.min.js" />"></script>
    <script src="<c:url value="../../../JS/jquery.validate.min.js" />"></script>
    <script src="<c:url value="../../../JS/jquery.dataTables.min.js" />"></script>
    <script src="<c:url value="../../../JS/sweetalert.min.js" />"></script>

    <title>StoreBase | Мої магазини</title>
</head>
<body>
<div id="wrapper">
    <c:url value="/j_spring_security_logout" var="logoutUrl" />
    <jsp:directive.include file="../Components/Header.html" />

    <c:if test="${!empty stores}">
        <table id="stores-table">
            <caption align="center"><h1 id = "table-stores">Список моїх магазинів</h1></caption>
            <thead>
            <tr>
                <th>Назва магазину</th>
                <th>Тип магазину</th>
                <th>Адреса магазину</th>
                <th>Додавання товару в магазин</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${stores}" var="store">
                <tr class="editField">
                    <td class="too-width"><c:out value="${store.name}" />
                    </td>
                    <td><c:out value="${store.storeType.name}" />
                    </td>
                    <td class="too-width"><c:out value="${store.address}" />
                    </td>
                    <td>
                        <input type="button" id = "store-field" about="${store.id}" value = "Додати"/>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>

    <div class="footer-push"></div>
</div>
<jsp:directive.include file="../Components/Footer.html" />
<script>
    $(document).ready(function(){
        $("#stores-table").dataTable({
            "aoColumnDefs": [
                {'bSortable': false, 'aTargets': [3]}
            ]
        });
    })
</script>
<script type="text/javascript">
    $(document).on("click", "table .editField #store-field", function() {

        var clickedValue = $(this).parents('.editField');
        $(clickedValue).css("background-color", "#BDBDBD");

        swal({
            title: "Додати товар у вибраний магазин?",
            type: "warning",
            showCancelButton: true,
            closeOnConfirm: false,
            confirmButtonText: "Додати!",
            confirmButtonColor: "#26A69A"
        }, function (isConfirmed) {
            if (isConfirmed) {
                var clickedValue1 = $(this).parents('.editField');

                clickedValue = $('td #store-field', clickedValue).attr('about');
                var savedata = {
                    idStore: clickedValue
                };
                $.ajax({
                    url: "/add_goods_in_my_store_success",
                    type: "POST",
                    datatype: "json",
                    data: savedata,
                    error: function () {
                        $(clickedValue).css("background-color", "WHITE");
                        swal("Помилка", "Неможливо підключитися до сервера!", "error");
                    },
                    success: function () {
                        swal("Товар додано!", "Вибраний товар успішно додано у ваш магазин", "success");
                        location.reload(true);
                        clickedValue1 = $('#store-field', clickedValue1).attr('disabled', true);
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

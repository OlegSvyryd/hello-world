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

    <title>StoreBase | Мої інтернет-магазини</title>
</head>
<body>
<div id="wrapper">
<c:url value="/j_spring_security_logout" var="logoutUrl" />
<jsp:directive.include file="../Components/Header.html" />
    <c:if test="${!empty web_stores}">
        <table id="stores-table">
            <caption align="center"><h1 id = "table-stores">Список моїх інтернет-магазинів</h1></caption>
            <thead>
            <tr>
                <th class='too-width'>Назва</th>
                <th class='too-width'>Тип</th>
                <th class='too-width'>Адреса</th>
                <th class='too-width'>Номер телефону</th>
                <th class='too-width'>Ел. пошта</th>
                <th class='too-width'>Опис</th>
                <th class='too-width'>Редагувати дані</th>
                <th class='too-width'>Видалити</th>
                <th class='too-width'>Переглянути замовлення</th>
                <th class='too-width'>Переглянути каталог товарів</th>
            </tr>
            </thead>
            <tbody class="list">
            <c:forEach items="${web_stores}" var="web_store">
                <tr class="editField">
                    <td class="too-width name"><c:out value="${web_store.name}" />
                    </td>
                    <td class="too-width">
                        <c:choose>
                            <c:when test="${web_store.type_id eq(1)}">
                                <c:out value="Книжковий" />
                            </c:when>
                            <c:otherwise>
                                <c:out value="Взуттєвий" />
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td class="too-width address"><c:out value="${web_store.address}" />
                    </td>
                    <td class="too-width"><c:out value="${web_store.phone}" />
                    </td>
                    <td class="too-width"><c:out value="${web_store.email}" />
                    </td>
                    <td class="too-width"><c:out value="${web_store.description}" />
                    </td>
                    <td class="too-width">
                        <input type="button" id = "updStore-field" value = "Редагувати"
                                onclick="document.location.href='update_web_store${web_store.id}'"/>
                    </td>
                    <td class="too-width">
                        <input type="button" id = "delStore-field" about="${web_store.id}" value = "Видалити" />
                    </td>
                    <td class="too-width">
                        <c:choose>
                            <c:when test="${web_store.type_id eq(1)}">
                                <input type="button" id = "checkCommission-field" value = "Замовлення" onclick="window.location.href='web_store_book_orders${web_store.id}';"/>
                            </c:when>
                            <c:otherwise>
                                <input type="button" id = "checkCommission-field" value = "Замовлення" onclick="window.location.href='web_store_shoe_orders${web_store.id}';"/>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td class="too-width">
                        <c:choose>
                            <c:when test="${web_store.type_id eq(1)}">
                                <input type="button" id = "showStoreGoods-field" value = "Товари" onclick="window.location.href='web_store_goods${web_store.id}';"/>
                            </c:when>
                            <c:otherwise>
                                <input type="button" id = "showStoreGoods-field" value = "Товари" onclick="window.location.href='web_store_shoe_goods${web_store.id}';"/>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
                </tbody>
        </table>
    </c:if>
    <c:if test="${empty web_stores}">
        <jsp:directive.include file="../Components/Empty.html" />
    </c:if>

    <div class="footer-push"></div>
</div>
<jsp:directive.include file="../Components/Footer.html" />

<script>
    $(document).ready(function(){
        $("#stores-table").dataTable({
            "aoColumnDefs": [
                {'bSortable': false, 'aTargets': [6,7,8]}
            ]
        });
    })
</script>
<!-- Delete store script -->
<script type="text/javascript">
    $(document).on("click", "table .editField #delStore-field", function() {
        var clickedValue = $(this).parents('.editField');
        $(clickedValue).css("background-color", "#BDBDBD");
        swal({
            title: "Видалити вибраний інтернет-магазин?",
            type: "warning",
            showCancelButton: true,
            closeOnConfirm: false,
            confirmButtonText: "Видaлити!",
            confirmButtonColor: "#ec6c62"
        }, function(isConfirmed) {
            if (isConfirmed) {
                var clickedValue1 = $(this).parents('.editField');

                clickedValue = $('td #delStore-field', clickedValue).attr('about');
                var savedata = {
                    idWebStore: clickedValue
                };
                $.ajax({
                    url: "/delete_web_store",
                    type: "POST",
                    datatype: "json",
                    data: savedata,
                    error: function () {
                        $(clickedValue).css("background-color", "WHITE");
                        swal("Помилка", "Неможливо підключитися до сервера!", "error");
                    },
                    success: function () {
                        swal("Видалено!", "Ваг інтернет-магазин успішно видалено", "success");
                        location.reload(true);
                        clickedValue1.remove();
                    }
                });
                return false;
            } else {
                $(clickedValue).css("background-color", "WHITE");
            }
        });
    });
</script>
</body>
</html>

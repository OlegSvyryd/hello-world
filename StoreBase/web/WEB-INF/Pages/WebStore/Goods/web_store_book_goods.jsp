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

    <button id = "show-confirmed-orders" class="button" onclick="document.location.href='add_web_store_book_goods${web_store_id}'" style="margin-bottom: 10px!important">Додати новий товар</button>
    <button id = "show-confirmed-orders" class="button" onclick="document.location.href='web_store_book_reports${web_store_id}'" style="margin-bottom: 100px!important">Отримати звіти</button>

    <c:if test="${!empty web_store_goods}">
    <table id="stores-table">
        <caption align="center"><h1 id = "table-stores">Список товарів вибраного магазину</h1></caption>
        <thead>
        <tr>
            <th>Назва</th>
            <th>Ціна</th>
            <th>Жанр</th>
            <th>Автор</th>
            <th>Мова</th>
            <th>Кількість сторінок</th>
            <th>Рік</th>
            <th>ISBN</th>
            <th>В наявності</th>
            <th>Зображення</th>
            <th>Редагувати дані про товар</th>
            <th>Видалити даний товар</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${web_store_goods}" var="goods">
        <tr class = "editField">
            <td class="too-width"><c:out value="${goods.name}" />
            </td>
            <td class="too-width"><c:out value="${goods.price}" />
            </td>
            <td class="too-width"><c:out value="${goods.genre}" />
            </td>
            <td class="too-width"><c:out value="${goods.author}" />
            </td>
            <td class="too-width"><c:out value="${goods.lang}" />
            </td>
            <td class="too-width"><c:out value="${goods.page}" />
            </td>
            <td class="too-width"><c:out value="${goods.year}" />
            </td>
            <td class="too-width"><c:out value="${goods.isbn}" />
            </td>
            <td class="too-width">
                <c:choose>
                    <c:when test="${goods.inStock eq(true)}">
                        Є в наявності
                    </c:when>
                    <c:otherwise>
                        Немає в наявності
                    </c:otherwise>
                </c:choose>
            </td>
            <td class="too-width">
                <c:choose>
                    <c:when test="${empty goods.image}">
                        Немає зображення
                    </c:when>
                    <c:when test="${not empty goods.image}">
                        <img width="200px;" style="max-height: 175px;" src="/imageControllerB/${goods.id}" alt="Image" />
                    </c:when>
                </c:choose>

                <form id = "send-image-form" method="POST" enctype="multipart/form-data"
                      action="/upload_book_image">
                    Змінити: <input type="file" id="file_box" name="file"/>
                    <br />
                    Завантажити: <input type="button" id="save-image" value="Upload">
                </form>
            </td>
            <td>
                <input type="button" id = "updStore-field" value = "Редагувати"
                       onclick="document.location.href='update_web_store_book_goods${goods.id}'"/>
            </td>
            <td>
                <input type="button" id = "delGoods-field" about="${goods.id}" value = "Видалити" />
            </td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
    <c:if test="${empty web_store_goods}">
        <jsp:directive.include file="../../Components/Empty.html" />
    </c:if>

        <div class="footer-push"></div>
</div>

<jsp:directive.include file="../../Components/Footer.html" />
<script>
    $(document).ready(function(){
        $("td:contains('Є в наявності')").css('color', 'green');
        $("td:contains('Немає в наявності')").css('color', 'red');

        $("#stores-table").dataTable({
            "aoColumnDefs": [
                {'bSortable': false, 'aTargets': [9,10,11]}
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
                    url: "/del_web_store_goods_book_success",
                    type: "POST",
                    datatype: "json",
                    data: savedata,
                    error: function () {
                        $(clickedValue).css("background-color", "WHITE");
                        swal("Помилка", "Неможливо підключитися до сервера!", "error");
                    },
                    success: function () {
                        swal("Видалено!", "Ваш товар успішно видалено", "success");
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

    $(document).on("click", "table .editField #save-image", function() {
        var clickedValue = $(this).parents('.editField');
        $(clickedValue).css("background-color", "#BDBDBD");
        swal({
            title: "Змінити зображення для даного товару?",
            type: "warning",
            showCancelButton: true,
            closeOnConfirm: false,
            confirmButtonText: "Змінити!",
            confirmButtonColor: "#26A69A"
        }, function(isConfirmed) {
            if (isConfirmed) {
        var _file = $("#file_box", clickedValue).val();
        if(_file == ''){
            swal("Помилка", "Зображення не вибрано!", "error");
        }
        else{
            clickedValue = $('td #delGoods-field', clickedValue).attr('about');
        var savedata = {
            idGoods: clickedValue,
            webStoreId: ${web_store_id}
        };
        $.ajax({
            url: "/get_goods_id_for_image",
            type: "POST",
            datatype: "json",
            data: savedata,
            error: function () {
                $(clickedValue).css("background-color", "WHITE");
                swal("Помилка", "Неможливо підключитися до сервера!", "error");
            },
            success: function () {
                $("#send-image-form").submit();
            }
        });
        }
        }
        else{
            $(clickedValue).css("background-color", "WHITE");
        }
        });
    });

    $("#stores-table td #file_box").click(function () {
        $("#stores-table td").click(function () {
            $(this).parent().prependTo("#stores-table");
            $("html, body").animate({ scrollTop: 100 }, "slow");
        });
    });

</script>
</body>
</html>
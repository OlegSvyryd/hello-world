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
    <script src="<c:url value="../../../../JS/jquery.dataTables.min.js" />"></script>
    <script src="<c:url value="../../../../JS/jquery-ui.min.js" />"></script>
    <script src="<c:url value="../../../../JS/jquery.dataTables.min.js" />"></script>
    <script src="<c:url value="../../../../JS/jquery.validate.min.js" />"></script>
    <script src="<c:url value="../../../../JS/jquery.maskedinput.js" />"></script>
    <script src="<c:url value="../../../../JS/jquery.zoom.min.js" />"></script>
    <script src="<c:url value="../../../../JS/sweetalert.min.js" />"></script>

    <title>StoreBase | ${title} ${web_store.type_id}</title>
</head>
<body>
<div id="wrapper">
    <c:url value="/j_spring_security_logout" var="logoutUrl" />
    <jsp:directive.include file="../../Components/ClientHeader.html" />

    <c:choose>
        <c:when test="${web_store.type_id eq(2)}">
            <c:if test="${!empty shoe_goods}">
                <table id="stores-table" style="margin-top:125px;">
                    <caption><h1 id = "table-stores" align="center">Список товарів</h1></caption>
                    <thead>
                    <tr>
                        <th>Назва</th>
                        <th>Ціна</th>
                        <th>Розміри</th>
                        <th>Опис</th>
                        <th>В наявності</th>
                        <th>Зображення</th>
                        <th>Замовити</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${shoe_goods}" var="goods">
                        <tr class="editField">
                            <td class="too-width"><c:out value="${goods.name}" />
                            </td>
                            <td><c:out value="${goods.price}" />
                            </td>
                            <td class="too-width"><c:out value="${goods.size}" />
                            </td>
                            <td class="too-width"><c:out value="${goods.description}" />
                            </td>
                            <td id = "is_in_stock" class="too-width">
                                <c:choose>
                                    <c:when test="${goods.inStock eq(true)}">
                                        Є в наявності
                                    </c:when>
                                    <c:otherwise>
                                        Немає в наявності
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${empty goods.image}">
                                        Немає зображення
                                    </c:when>
                                    <c:when test="${not empty goods.image}">
                                        <img width="200px;" style="max-height: 175px;" src="/imageS/${goods.id}" alt="Image" />
                                    </c:when>
                                </c:choose>
                            </td>
                            <td>
                                <input type="button" id = "order-field" value = "Замовити" about = "${goods.id}"/>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <!-- Order add -->
                <div id = "order-add" style="display: none;" class = "popup-service">
    <form:form id = "add_store_form" method="POST" action="/web-order-during" commandName="order">
        <table id = "add_store_table" style="margin-top:0px !important;">
            <tr>
                <th><form:label path="name">Ваше ім'я</form:label></th>
            </tr>
            <tr>
                <td><form:input path="name" cssClass="error"/><form:errors path="name" /></td>
            </tr>
            <tr>
                <th><form:label path="phone">Номер телефону</form:label></th>
            </tr>
            <tr>
                <td><form:input path="phone" cssClass="tf3"/><form:errors path="phone" /></td>
            </tr>
            <tr>
                <th><form:label path="email">Електронна пошта</form:label></th>
            </tr>
            <tr>
                <td><form:input path="email" cssClass="error"/><form:errors path="email" /></td>
            </tr>
            <tr>
                <th><form:label path="amount">Виберіть кількість товару для замовлення</form:label></th>
            </tr>
            <tr>
                <td>
                <form:select path="amount" style="width:70px;">
                    <c:forEach items="${amount_order}" var="_amount_order" >
                        <form:option value = "${_amount_order  +1}"><c:out value="${_amount_order +1}" /></form:option>
                    </c:forEach>
                </form:select>
                </td>
            </tr>
            <tr>
                <th><form:label path="size">Виберіть розмір взуття</form:label></th>
            </tr>
            <tr>
                <td>
                <form:select path="size" style="width:70px;">
                    <c:forEach items="${sizes}" var="_size" >
                        <form:option value = "${_size}"><c:out value="${_size}" /></form:option>
                    </c:forEach>
                </form:select>
                </td>
            </tr>
            <tr>
                <td colspan="3">
                    <input id = 'send-field' type="submit" value="Замовити!"/>
                </td>
            </tr>
        </table>

    </form:form>
                </div>

                <!-- WebOrder validation -->
                <script type="text/javascript">
                    $(document).ready(function () {
                        $("#add_store_form").validate({
                            rules: {
                                name: {
                                    maxlength: 25,
                                    minlength: 2,
                                    required: true
                                },
                                phone : {
                                    maxlength: 17,
                                    minlength: 5,
                                    required: true
                                },
                                email: {
                                    email: true,
                                    required: true,
                                    maxlength: 30,
                                    minlength: 5
                                },
                                amount: {
                                    required: true,
                                    number: true
                                },
                                size: {
                                    required: true,
                                    number: true
                                }
                            },
                            messages: {
                                name: {
                                    required: "Поле не може бути порожнє",
                                    maxlength: $.format("Поле не може містити більше, ніж {0} символів"),
                                    minlength: $.format("Поле не може містити менше, ніж {0} символів")
                                },
                                phone : {
                                    required: "Поле не може бути порожнє",
                                    maxlength: $.format("Поле не може містити більше, ніж {0} символів"),
                                    minlength: $.format("Поле не може містити менше, ніж {0} символів")
                                },
                                email: {
                                    required: "Поле не може бути порожнє",
                                    email: "Поле призначене для емейлу",
                                    maxlength: $.format("Поле не може вміщати більше, ніж {0} символів"),
                                    minlength: $.format("Поле не може містити менше, ніж {0} символів")
                                },
                                amount: {
                                    required: "Поле не може бути порожнє",
                                    number: "Поле повинно містити числове значення"
                                },
                                size: {
                                    required: "Поле не може бути порожнє",
                                    number: "Поле повинно містити числове значення"
                                }
                            }
                        });

                        $(".tf3").mask("+99(999)99-99-999");
                    });
                </script>

                <script>
                    $(document).ready(function(){
                        $("#stores-table").dataTable({
                            "aoColumnDefs": [
                                {'bSortable': false, 'aTargets': [5,6]}
                            ]
                        });
                    })
                </script>

            </c:if>
        </c:when>

        <c:when test="${web_store.type_id eq(1)}">

            <c:if test="${!empty book_goods}">
            <table id="stores-table" style="margin-top:125px;">
                <caption><h1 id = "table-stores" align="center">Список товарів</h1></caption>
                <thead>
                <tr>
                    <th>Назва</th>
                    <th>Ціна</th>
                    <th>Автор</th>
                    <th>Мова</th>
                    <th>Жанр</th>
                    <th>Сторінок</th>
                    <th>Рік</th>
                    <th>ISBN</th>
                    <th>В наявності</th>
                    <th>Зображення</th>
                    <th>Замовити</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${book_goods}" var="goods">
                    <tr class="editField">
                        <td class="too-width"><c:out value="${goods.name}" />
                        </td>
                        <td><c:out value="${goods.price}" />
                        </td>
                        <td class="too-width"><c:out value="${goods.author}" />
                        </td>
                        <td class="too-width"><c:out value="${goods.lang}" />
                        </td>
                        <td class="too-width"><c:out value="${goods.genre}" />
                        </td>
                        <td><c:out value="${goods.page}" />
                        </td>
                        <td><c:out value="${goods.year}" />
                        </td>
                        <td class="too-width"><c:out value="${goods.isbn}" />
                        </td>
                        <td id = "is_in_stock" class="too-width">
                            <c:choose>
                                <c:when test="${goods.inStock eq(true)}">
                                    Є в наявності
                                </c:when>
                                <c:otherwise>
                                    Немає в наявності
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${empty goods.image}">
                                    Немає зображення
                                </c:when>
                                <c:when test="${not empty goods.image}">
                                    <img width="200px;" style="max-height: 175px;" src="/imageB/${goods.id}" alt="Image" />
                                </c:when>
                            </c:choose>
                        </td>
                        <td>
                            <input type="button" id = "order-field" value = "Замовити" about = "${goods.id}"/>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <!-- Order add -->
            <div id = "order-add" style="display: none;" class = "popup-service">
                <form:form id = "add_store_form" method="POST" action="/web-order-during" commandName="order">
                    <table id = "add_store_table" style="margin-top:0px !important;">
                        <tr>
                            <th><form:label path="name">Ваше ім'я</form:label></th>
                        </tr>
                        <tr>
                            <td><form:input path="name" cssClass="error"/><form:errors path="name" /></td>
                        </tr>
                        <tr>
                            <th><form:label path="phone">Номер телефону</form:label></th>
                        </tr>
                        <tr>
                            <td><form:input path="phone" cssClass="tf3"/><form:errors path="phone" /></td>
                        </tr>
                        <tr>
                            <th><form:label path="email">Електронна пошта</form:label></th>
                        </tr>
                        <tr>
                            <td><form:input path="email" cssClass="error"/><form:errors path="email" /></td>
                        </tr>
                        <tr>
                            <th><form:label path="amount">Виберіть кількість товару для замовлення</form:label></th>
                        </tr>
                        <tr>
                            <td>
                                <form:select path="amount" style="width:70px;">
                                    <c:forEach items="${amount_order}" var="_amount_order" >
                                        <form:option value = "${_amount_order +1}"><c:out value="${_amount_order +1}" /></form:option>
                                    </c:forEach>
                                </form:select>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3">
                                <input id = 'send-field' type="submit" value="Замовити!"/>
                            </td>
                        </tr>
                    </table>

                </form:form>
            </div>

            <!-- WebOrder validation -->
            <script type="text/javascript">
                $(document).ready(function () {
                    $("#add_store_form").validate({
                        rules: {
                            name: {
                                maxlength: 25,
                                minlength: 2,
                                required: true
                            },
                            phone : {
                                maxlength: 17,
                                minlength: 5,
                                required: true
                            },
                            email: {
                                email: true,
                                required: true,
                                maxlength: 30,
                                minlength: 5
                            },
                            amount: {
                                required: true,
                                number: true
                            }
                        },
                        messages: {
                            name: {
                                required: "Поле не може бути порожнє",
                                maxlength: $.format("Поле не може містити більше, ніж {0} символів"),
                                minlength: $.format("Поле не може містити менше, ніж {0} символів")
                            },
                            phone : {
                                required: "Поле не може бути порожнє",
                                maxlength: $.format("Поле не може містити більше, ніж {0} символів"),
                                minlength: $.format("Поле не може містити менше, ніж {0} символів")
                            },
                            email: {
                                required: "Поле не може бути порожнє",
                                email: "Поле призначене для емейлу",
                                maxlength: $.format("Поле не може вміщати більше, ніж {0} символів"),
                                minlength: $.format("Поле не може містити менше, ніж {0} символів")
                            },
                            amount: {
                                required: "Поле не може бути порожнє",
                                number: "Поле повинно містити числове значення"
                            }
                        }
                    });
                    $(".tf3").mask("+99(999)99-99-999");
                });
            </script>

            <script>
                    $(document).ready(function(){
                        $("#stores-table").dataTable({
                            "aoColumnDefs": [
                                {'bSortable': false, 'aTargets': [9,10]}
                            ]
                        });
                    })
                </script>

            </c:if>
        </c:when>
    </c:choose>

    <div class="footer-push"></div>
</div>

<jsp:directive.include file="../../Components/ClientFooter.html" />

<!-- Popup add amount -->
<script type="text/javascript">
    $("td:contains('Є в наявності')").css('color', 'green');
    $("td:contains('Немає в наявності')").css('color', 'red');

    //zoom image
    $(document).ready(function(){
        $('img')
                .wrap('<span style="display:inline-block"></span>')
                .css('display', 'block')
                .parent()
                .zoom();
    });

    //get goods id
    var goodsId;

    $('#order-add.popup-service').on('dialogclose', function() {
        $('#stores-table tr').css("background-color", "WHITE");
    });

    // Open popup and inputing add
    $(document).on("click", "table .editField #order-field", function() {
        $( "#order-add").css("display",  "block");
        $( "#order-add" ).dialog( {
            height: 500,
            width: 600,
            title: "Замовлення товару",
            draggable:true,
            resizable:false
        });
        var clickedValue = $(this).parents('.editField');
            $(clickedValue).css("background-color", "#FAFAFA");

        clickedValue = $('td #order-field', clickedValue).attr('about');
        goodsId = clickedValue;
    });

    // Send data on server del
    $(document).on("click", "div .popup-service #send-field", function() {
        var savedata = {
            idGoods: goodsId
        };
        $.ajax({
            url: "/web-order-during-id",
            type: "POST",
            datatype: "json",
            data: savedata,
            error: function () {
                swal("Помилка", "Неможливо підключитися до сервера!", "error");
            },
            success: function () {
                if($('#add_store_form').valid()){
                    $('#add_store_form').submit();
                    swal("Замовлення прийнято", "Очікуйте телефонного дзвінка!", "success");
                }
            }
        });
        return false;
    });

</script>
</body>
</html>

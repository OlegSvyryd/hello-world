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

    <!-- Scripts -->
    <script src="<c:url value="../../../../JS/jquery.min.js" />"></script>
    <script src="<c:url value="../../../../JS/jquery.validate.min.js" />"></script>

    <title>StoreBase | Оновлення товару</title>
</head>
<body>
<div id="wrapper">
    <c:url value="/j_spring_security_logout" var="logoutUrl" />
    <jsp:directive.include file="../../Components/Header.html" />

    <form:form id = "add_store_form" method="POST" action="/upd_store_goods_success" commandName="store_goods">
        <form:hidden path="id"/>
        <form:hidden path="companyGoodsId"/>
        <form:hidden path="companyName"/>
        <form:hidden path="goodsType"/>
        <form:hidden path="amount"/>
        <form:hidden path="store.id"/>

        <table id = "add_store_table">
            <caption><h3>Редагування даних про товар</h3></caption>
            <tr>
                <th class="too-width"><form:label path="name">Назва товару(попередня назва - ${store_goods.name})</form:label></th>
            </tr>
            <tr>
                <td><form:input path="name" cssClass="error"/><form:errors path="name" /></td>
            </tr>
            <tr>
                <th><form:label path="price">Ціна товару(попередня ціна - ${store_goods.price})</form:label></th>
            </tr>
            <tr>
                <td><form:input path="price" cssClass="error"/><form:errors path="price" /></td>
            </tr>
            <tr>
                <th class="too-width"><form:label path="description">Дані про товар(попередні дані - ${store_goods.description})</form:label></th>
            </tr>
            <tr>
                <td><form:textarea path="description" cssClass="error"/><form:errors path="description" /></td>
            </tr>
            <tr>
                <th><form:label path="dimension">Одиниці виміру товару(попередня одиниця - ${store_goods.dimension})</form:label></th>
            </tr>
            <tr>
                <td><form:input path="dimension" cssClass="error"/><form:errors path="dimension" /></td>
            </tr>
            <tr>
                <td colspan="3">
                    <input id = 'send-field' type="submit" value="Оновити дані"/>
                </td>
            </tr>
        </table>
    </form:form>

    <div class="footer-push"></div>
</div>
<jsp:directive.include file="../../Components/Footer.html" />
<!-- Store update validation -->
<script type="text/javascript">
    $(document).ready(function () {
        $("#add_store_form").validate({
            rules: {
                name: {
                    maxlength: 100,
                    minlength: 2,
                    required: true
                },
                desciption: {
                    maxlength: 500,
                    required: false
                },
                price: {
                    maxlength: 15,
                    number: true,
                    required: true
                },
                dimension: {
                    maxlength: 25,
                    required: false
                }
            },
            messages: {
                name: {
                    required: "Поле не може бути порожнє",
                    maxlength: $.format("Поле не може містити більше, ніж {0} символів"),
                    minlength: $.format("Поле не може містити менше, ніж {0} символів")
                },
                description: {
                    required: "Поле не може бути порожнє",
                    maxlength: $.format("Поле не може містити більше, ніж {0} символів"),
                },
                price: {
                    required: "Поле не може бути порожнє",
                    maxlength: $.format("Поле не може містити більше, ніж {0} символів"),
                    number: $.format("Поле може містити тільки числове значення")
                },
                dimension: {
                    required: "Поле не може бути порожнє",
                    maxlength: $.format("Поле не може містити більше, ніж {0} символів"),
                }
            }
        });
    });
</script>
</body>
</html>

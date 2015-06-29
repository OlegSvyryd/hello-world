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

    <!-- Scripts -->
    <script src="<c:url value="../../../JS/jquery.min.js" />"></script>
    <script src="<c:url value="../../../JS/jquery.validate.min.js" />"></script>
    <script src="<c:url value="../../../JS/jquery.maskedinput.js" />"></script>

    <title>StoreBase | Редагувати інтернет-магазин</title>
</head>
<body>
<div id="wrapper">
<c:url value="/j_spring_security_logout" var="logoutUrl" />
<jsp:directive.include file="../Components/Header.html" />

<form:form id = "add_store_form" method="POST" action="/upd_web_store_success" commandName="web_store">
    <form:hidden path="id"/>
    <form:hidden path="type_id"/>

    <table id = "add_store_table">
        <caption><h3>Оновлення інтернет-магазину</h3></caption>
        <tr>
            <th><form:label path="name">Назва інтернет-магазину(попередня назва - ${web_store.name})</form:label></th>
        </tr>
        <tr>
            <td><form:input path="name" cssClass="error"/><form:errors path="name" /></td>
        </tr>
        <tr>
            <th><form:label path="address">Адреса інтернет-магазину(попередня адреса - ${web_store.address})</form:label></th>
        </tr>
        <tr>
            <td><form:input path="address" cssClass="error"/><form:errors path="address" /></td>
        </tr>
        <tr>
            <th><form:label path="phone">Телефон інтернет-магазину(попередній телефон - ${web_store.phone})</form:label></th>
        </tr>
        <tr>
            <td><form:input path="phone" cssClass="tf3"/><form:errors path="phone" /></td>
        </tr>
        <tr>
            <th><form:label path="email">Електронна пошта інтернет-магазину(попереднія ел. пошта - ${web_store.email})</form:label></th>
        </tr>
        <tr>
            <td><form:input path="email" cssClass="error"/><form:errors path="email" /></td>
        </tr>
        <tr>
            <th><form:label path="description">Опис інтернет-магазину(попередній опис - ${web_store.description})</form:label></th>
        </tr>
        <tr>
            <td><form:input path="description" cssClass="error"/><form:errors path="description" /></td>
        </tr>
        <tr>
            <th><form:label path="url">Інтернет адреса інтернет-магазину(попередня назва - ${web_store.url})</form:label></th>
        </tr>
        <tr>
            <td><form:input path="url" placeholder = "www.storebase.com/web_store/" cssClass="error"/><form:errors path="url" />${errorLabelUrl}</td>
        </tr>
        <tr>
            <td colspan="3">
                <input id = 'send-field' type="submit" value="Оновити"/>
            </td>
        </tr>
    </table>

</form:form>
    <div class="footer-push"></div>
</div>
<jsp:directive.include file="../Components/Footer.html" />
<!-- User registration validation -->
<script type="text/javascript">
    $(document).ready(function () {
        $("#add_store_form").validate({
            rules: {
                name: {
                    maxlength: 30,
                    minlength: 2,
                    required: true
                },
                address: {
                    maxlength: 150,
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
                description: {
                    required: true,
                    maxlength: 250,
                    minlength: 10
                },
                url: {
                    required: true,
                    maxlength: 50,
                    minlength: 3
                }
            },
            messages: {
                name: {
                    required: "Поле не може бути порожнє",
                    maxlength: $.format("Поле не може містити більше, ніж {0} символів"),
                    minlength: $.format("Поле не може містити менше, ніж {0} символів")
                },
                address: {
                    required: "Поле не може бути порожнє",
                    maxlength: $.format("Поле не може містити більше, ніж {0} символів"),
                    minlength: $.format("Поле не може містити менше, ніж {0} символів")
                },
                phone : {
                    required: "Поле не може бути порожнє",
                    maxlength: $.format("Поле не може містити більше, ніж {0} символів"),
                    minlength: $.format("Поле не може містити менше, ніж {0} символів")
//                    digits: "Поле може містити тільки цифри"
                },
                email: {
                    required: "Поле не може бути порожнє",
                    email: "Поле призначене для емейлу",
                    maxlength: $.format("Поле не може вміщати більше, ніж {0} символів"),
                    minlength: $.format("Поле не може містити менше, ніж {0} символів")
                },
                description: {
                    required: "Поле не може бути порожнє",
                    maxlength: $.format("Поле не може містити більше, ніж {0} символів"),
                    minlength: $.format("Поле не може містити менше, ніж {0} символів")
                },
                url: {
                    required: "Поле не може бути порожнє",
                    maxlength: $.format("Поле не може містити більше, ніж {0} символів"),
                    minlength: $.format("Поле не може містити менше, ніж {0} символів"),
                }
            }
        });

        $(".tf3").mask("+99(999)99-99-999");
    });
</script>
</body>
</html>

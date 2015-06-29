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

    <form:form id = "add_store_form" method="POST" action="/update_web_store_book_goods_success" commandName="goods">
        <form:hidden path="id"/>
        <form:hidden path="webStore.id"/>

        <table id = "add_store_table">
            <caption><h3>Редагування даних про товар</h3></caption>
            <tr>
                <th><form:label path="name">Назва книжки</form:label></th>
            </tr>
            <tr>
                <td><form:input path="name" cssClass="error"/><form:errors path="name" /></td>
            </tr>
            <tr>
                <th><form:label path="price">Ціна</form:label></th>
            </tr>
            <tr>
                <td><form:input path="price" cssClass="error"/><form:errors path="price" /></td>
            </tr>
            <tr>
                <th><form:label path="author">Автор</form:label></th>
            </tr>
            <tr>
                <td><form:input path="author" cssClass="error"/><form:errors path="author" /></td>
            </tr>
            <tr>
                <th><form:label path="lang">Мова</form:label></th>
            </tr>
            <tr>
                <td><form:input path="lang" cssClass="error"/><form:errors path="lang" /></td>
            </tr>
            <tr>
                <th><form:label path="genre">Жанр</form:label></th>
            </tr>
            <tr>
                <td><form:input path="genre" cssClass="error"/><form:errors path="genre" /></td>
            </tr>
            <tr>
                <th><form:label path="page">Кількість сторінок</form:label></th>
            </tr>
            <tr>
                <td><form:input path="page" cssClass="error"/><form:errors path="page" /></td>
            </tr>
            <tr>
                <th><form:label path="year">Рік виходу</form:label></th>
            </tr>
            <tr>
                <td><form:input path="year" cssClass="error"/><form:errors path="year" /></td>
            </tr>
            <tr>
                <th><form:label path="isbn">ISBN</form:label></th>
            </tr>
            <tr>
                <td><form:input path="isbn" cssClass="error"/><form:errors path="isbn" /></td>
            </tr>
            <tr>
                <th><form:label path="inStock">Є в наявності?</form:label></th>
            </tr>
            <tr>
                <td><form:checkbox path="inStock" cssClass="error"/><form:errors path="inStock" /></td>
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
<!-- Company goods adding validation -->
<script type="text/javascript">
    $(document).ready(function () {
        $("#add_store_form").validate({
            rules: {
                name: {
                    maxlength: 100,
                    minlength: 2,
                    required: true
                },
                author: {
                    maxlength: 50,
                    minlength: 2,
                    required: true
                },
                lang: {
                    maxlength: 25,
                    minlength: 2,
                    required: true
                },
                genre: {
                    maxlength: 100,
                    minlength: 2,
                    required: true
                },
                isbn: {
                    maxlength: 25
                },
                price: {
                    required: true,
                    maxlength: 15,
                    number: true
                },
                year: {
                    digits: true,
                    min:1000,
                    max:2015
                },
                page: {
                    min:0,
                    digits: true
                }
            },
            messages: {
                name: {
                    required: "Поле не може бути порожнє",
                    maxlength: $.format("Поле не може містити більше, ніж {0} символів"),
                    minlength: $.format("Поле не може містити менше, ніж {0} символів")
                },
                price: {
                    required: "Поле не може бути порожнє",
                    maxlength: $.format("Поле не може містити більше, ніж {0} символів"),
                    number: "Поле повинно містити числове значення"
                },
                page: {
                    digits: "Поле повинно містити числове значення"
                },
                year: {
                    digits: "Поле повинно містити числове значення"
                },
                author: {
                    required: "Поле не може бути порожнє",
                    maxlength: $.format("Поле не може містити більше, ніж {0} символів"),
                    minlength: $.format("Поле не може містити менше, ніж {0} символів")
                },
                lang: {
                    required: "Поле не може бути порожнє",
                    maxlength: $.format("Поле не може містити більше, ніж {0} символів"),
                    minlength: $.format("Поле не може містити менше, ніж {0} символів")
                },
                genre: {
                    required: "Поле не може бути порожнє",
                    maxlength: $.format("Поле не може містити більше, ніж {0} символів"),
                    minlength: $.format("Поле не може містити менше, ніж {0} символів")
                },
                isbn: {
                    maxlength: $.format("Поле не може містити більше, ніж {0} символів")
                }
            }
        });
    });
</script>
</body>
</html>

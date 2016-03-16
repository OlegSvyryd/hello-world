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
    <link rel="stylesheet" type="text/css" href="<c:url value='../../../../CSS/sweetalert.css' />" >

    <!-- Scripts -->
    <script src="<c:url value="../../../../JS/jquery.min.js" />"></script>
    <script src="<c:url value="../../../../JS/jquery.validate.min.js" />"></script>
    <script src="<c:url value="../../../../JS/sweetalert.min.js" />"></script>

    <title>StoreBase | Новий товар</title>
</head>
<body>
<div id="wrapper">
    <c:url value="/j_spring_security_logout" var="logoutUrl" />
    <jsp:directive.include file="../../Components/Header.html" />

    <form:form id = "add_store_form" method="POST" action="/add_company_goods_success" commandName="companyGoods">
        <table id = "add_store_table">
            <caption><h3>Додавання нового товару</h3></caption>
            <tr>
                <th><form:label path="name">Найменування</form:label></th>
            </tr>
            <tr>
                <td><form:input path="name" cssClass="error"/><form:errors path="name" /></td>
            </tr>
            <tr>
                <th><form:label path="goodsType.id">Тип товару</form:label></th>
            </tr>
            <tr>
                <td><form:select path="goodsType.id" >
                    <form:options items="${goodsType}" itemValue="id" itemLabel="name"/>
                </form:select><form:errors path="goodsType.id" /></td>
            </tr>
            <tr>
                <th><form:label path="price">Ціна</form:label></th>
            </tr>
            <tr>
                <td><form:input path="price" cssClass="error"/><form:errors path="price" /></td>
            </tr>
            <tr>
                <th><form:label path="amount">Кількість на складі</form:label></th>
            </tr>
            <tr>
                <td><form:input path="amount" cssClass="error"/><form:errors path="amount" /></td>
            </tr>
            <tr>
                <th><form:label path="dimension">Одиниця виміру</form:label></th>
            </tr>
            <tr>
                <td><form:input path="dimension" cssClass="error"/><form:errors path="dimension" /></td>
            </tr>
            <tr>
                <th><form:label path="description">Опис</form:label></th>
            </tr>
            <tr>
                <td><form:textarea path="description" cssClass="error"/><form:errors path="description" /></td>
            </tr>
            <tr>
                <td colspan="3">
                    <input id = 'send-field' type="submit" value="Додати"/>
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
                price: {
                    required: true,
                    maxlength: 15,
                    number: true
                },
                amount: {
                    required: true,
                    digits: true
                },
                dimension: {
                    maxlength: 25
                },
                description: {
                    maxlength: 500
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
                amount: {
                    required: "Поле не може бути порожнє",
                    digits: "Поле повинно містити числове значення"
                },
                dimension: {
                    maxlength: $.format("Поле не може містити більше, ніж {0} символів")
                },
                description: {
                    maxlength: $.format("Поле не може містити більше, ніж {0} символів")
                }
            }
            });
    });

    $(document).on("click", "#send-field", function(){
        if($("#add_store_form").valid()) {
            swal({
                title: "Товар успішно додано",
                type: "success",
                confirmButtonText: "Додати",
                confirmButtonColor: "#263238"
            });

            $("#add_store_form").submit();
        }
    });

</script>

</body>
</html>

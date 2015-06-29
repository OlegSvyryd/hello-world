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
    <link rel="stylesheet" type="text/css" href="<c:url value='../../../CSS/sweetalert.css' />" >

    <!-- Scripts -->
    <script src="<c:url value="../../../JS/jquery.min.js" />"></script>
    <script src="<c:url value="../../../JS/jquery.validate.min.js" />"></script>
    <script src="<c:url value="../../../JS/sweetalert.min.js" />"></script>

    <title>StoreBase | Нова компанія</title>
</head>
<body>
<div id="wrapper">
<c:url value="/j_spring_security_logout" var="logoutUrl" />
<jsp:directive.include file="../Components/Header.html" />

<form:form id = "add_store_form" method="POST" action="/add_company_success" commandName="company">
    <table id = "add_store_table">
        <caption><h3>Реєстрація компанії</h3></caption>
        <tr>
            <th><form:label path="name">Назва компанії</form:label></th>
        </tr>
        <tr>
            <td><form:input path="name" cssClass="error"/><form:errors path="name" /></td>
        </tr>
        <tr>
            <th><form:label path="companyType.id">Тип компанії</form:label></th>
        </tr>
        <tr>
            <td><form:select path="companyType.id" >
                <form:options items="${company_types}" itemValue="id" itemLabel="name"/>
            </form:select><form:errors path="companyType.id" /></td>
        </tr>
        <tr>
            <th><form:label path="address">Адреса компанії</form:label></th>
        </tr>
        <tr>
            <td><form:input path="address" cssClass="error"/><form:errors path="address" /></td>
        </tr>
        <tr>
            <td colspan="3">
                <input id = 'send-field' type="submit" value="Зареєструвати"/>
            </td>
        </tr>
    </table>

</form:form>
    <div class="footer-push"></div>
</div>
<jsp:directive.include file="../Components/Footer.html" />
<!-- Company registration validation -->
<script type="text/javascript">
    $(document).ready(function () {
        $("#add_store_form").validate({
            rules: {
                name: {
                    maxlength: 50,
                    minlength: 2,
                    required: true
                },
                address: {
                    maxlength: 100,
                    minlength: 2,
                    required: true
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
                }
            }
        });
    });

    $(document).on("click", "#send-field", function(){
        if($("#add_store_form").valid()) {
            swal({
                title: "Компанію успішно додано",
                type: "success",
                confirmButtonText: "Гаразд",
                confirmButtonColor: "#263238"
            });

            $("#add_store_form").submit();
        }
    });

</script>
</body>
</html>

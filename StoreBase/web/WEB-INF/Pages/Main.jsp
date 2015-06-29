<%--
  Created by IntelliJ IDEA.
  User: oleg
  Date: 21.05.2015
  Time: 13:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <!-- CSS -->
    <link rel="stylesheet" type="text/css" href="<c:url value='../../CSS/Welcome.css' />" >
    <link rel="stylesheet" type="text/css" href="<c:url value='../../CSS/animate.css' />" >
    <link rel="stylesheet" type="text/css" href="<c:url value='../../CSS/Main.css' />" >
    <link rel="stylesheet" type="text/css" href="<c:url value='../../CSS/sweetalert.css' />" >

    <script src="<c:url value="../../JS/sweetalert.min.js" />"></script>
    <script src="<c:url value="../../JS/jquery.min.js" />"></script>

    <title>StoreBase | Головна</title>
</head>
<body>
<div id="wrapper-main">
<c:url value="/j_spring_security_logout" var="logoutUrl" />
<jsp:directive.include file="Components/Header.html" />

    <table id = "main_page_table">
        <tr>
            <th>Секція магазини</th>
            <th>Секція компанії</th>
            <th>Секція інтернет-магазин</th>
        </tr>
        <tr>
            <td>
                <button id = "create_store" class="button" onclick="document.location.href='add_store'">Додати новий магазин</button>
            </td>
            <td>
                <button id = "create_company" class="button" onclick="document.location.href='add_company'">Додати нову компанію</button>
            </td>
            <td>
                <button id = "create_web_store" class="button" onclick="document.location.href='add_web_store'">Додати новий інтернет-магазин</button>
            </td>
        </tr>
        <tr>
            <td>
                <button id = "show_my_stores" class="button" onclick="document.location.href='my_stores'">Переглянути свої магазини</button>
            </td>
            <td>
                <button id = "show_my_companies" class="button" onclick="document.location.href='my_companies'">Переглянути свої компанії</button>
            </td>
            <td>
                <button id = "show_my_web_stores" class="button" onclick="document.location.href='my_web_stores'">Переглянути свої інтернет-магазини</button>
            </td>
        </tr>
        <tr>
            <td>
                <button id = "show_all_stores" class="button" onclick="document.location.href='all_stores'">Переглянути всі магазини</button>
            </td>
            <td>
                <button id = "show_all_companies" class="button" onclick="document.location.href='all_companies'">Переглянути всі компанії</button>
            </td>
            <td>
                <button id = "show_all_web_stores" class="button" onclick="document.location.href='all_web_stores'">Переглянути всі інтернет-магазини</button>
            </td>
        </tr>
    </table>
    <div class="footer-push"></div>
</div>
<jsp:directive.include file="Components/MainFooter.html" />
<script type="application/javascript">
    $(document).on("click", "#tech-support", function() {
        swal({
            title: "Технічна підтримка:",
            text: "Опишіть коротко проблему, яка у вас виникла або задайте питання",
            type: "input",
            showCancelButton: true,
            closeOnConfirm: false,
            confirmButtonText: "Надіслати!",
            confirmButtonColor: "#26A69A"
        }, function (text) {
            if(text === false){
                return false;
            }
            if (text === "") {
                swal.showInputError("Введіть ваше повідомлення!");
                return false
            }
            $.ajax({
                url: "/support",
                type: "POST",
                datatype: "json",
                data: {text: text},
                error: function () {
                        swal("Помилка", "Неможливо підключитися до сервера!", "error");
                },
                success: function (res) {
                    if (res == "success") {
                        swal("Повідомлення надіслоно!", "", "success");
                    }
                    else {
                        swal('Невідома помилка.', "", "error");
                    }
                }
            });
        });
    });
</script>
</body>
</html>

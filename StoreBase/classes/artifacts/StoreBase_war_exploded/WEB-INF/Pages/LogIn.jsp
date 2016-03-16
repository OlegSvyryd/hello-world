<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <!-- CSS -->
    <link rel="stylesheet" type="text/css" href="<c:url value='../../CSS/Welcome.css' />" >
    <link rel="stylesheet" type="text/css" href="<c:url value='../../CSS/log_reg.css' />" >
    <link rel="stylesheet" type="text/css" href="<c:url value='../../CSS/sweetalert.css' />" >

    <!-- Scripts -->
    <script src="<c:url value="../../JS/jquery.min.js" />"></script>
    <script src="<c:url value="../../JS/sweetalert.min.js" />"></script>

    <title>StoreBase | Вхід</title>
</head>

<body>
<header>
    <div>
        <spun class = 'logo'>StoreBase</spun>
        <spun class = 'content'>
            <ul>
                <li onclick="document.location.href='registration'">Приєднатись</li>
                |
                <li onclick="document.location.href='welcome'">На головну</li>
            </ul>
        </spun>
    </div>
</header>

<center>
<form class="login-form" action="<c:url value='/j_spring_security_check' />" method="post">
    <h3>Вхід у систему:</h3>

    <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
        <p class="error">Пароль або емейл не правильний, будь ласка введіть коректні дані</p><br/>
    </c:if>

    <label for="text-field1">Електронна пошта:</label><br>
    <input type="email" name="login" id="text-field1"/><br>
    <label for="text-field2">Пароль:</label><br>
    <input type="password" name="password" id="text-field2"/><br>
    <input type="checkbox" name="remember-me"/>

    <spun class="text-under-form">
        <spun style = 'margin-right: 50px;' class="remember-me">Запам'ятати мене</spun>
        <spun class="Ive-forgotten-password">Я забув пароль</spun><br>
    </spun>
    <br>

    <input type="submit" value="Увійти">
</form>
</center>

<footer CLASS="login-form-footer">
    <div>Oleg Svyryd 2015</div>
</footer>

<script type="text/javascript">
    $(document).ready(function() {
        $(window).on('beforeunload', function () {
            <c:remove var = "SPRING_SECURITY_LAST_EXCEPTION" scope = "session" />
        });
    });

    $(document).on("click", ".Ive-forgotten-password", function() {
        swal({
            title: "Забули пароль?",
            text: "Введіть адресу вашої електронної пошти:",
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
                swal.showInputError("Введіть дані");
                return false
            }
            $.ajax({
                url: "/forgot-password",
                type: "POST",
                datatype: "json",
                data: {text: text},
                error: function () {
                    swal("Помилка", "Неможливо підключитися до сервера!", "error");
                },
                success: function (res) {
                    if (res == "success") {
                        swal("Ваш пароль надіслано вам на пощту!", "", "success");
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
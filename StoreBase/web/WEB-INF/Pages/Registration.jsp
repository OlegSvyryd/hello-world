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
    <script src="<c:url value="../../JS/jquery.maskedinput.js" />"></script>
    <script src="<c:url value="../../JS/jquery.validate.min.js" />"></script>
    <script src="<c:url value="../../JS/sweetalert.min.js" />"></script>

    <title>StoreBase | Реєстрація</title>
</head>

<body>
<header>
    <div>
        <spun class = 'logo'>StoreBase</spun>
        <spun class = 'content'>
            <ul>
                <li onclick="document.location.href='login'">Вхід</li>
                |
                <li onclick="document.location.href='welcome'">На головну</li>
            </ul>
        </spun>
    </div>
</header>

<!-- Registration -->
<center>
    <div class="registration" id='registration'>
        <form:form id = "userRegistration-form" method="POST" action="/registration_during" commandName="users">
            <caption align="center"><h3>Реєстрація користувача</br>${error_list}</h3></caption>
            <table>
                <tr>
                    <th><form:label path="name">Ім'я</form:label></th>
                </tr>
                <tr>
                    <td ><form:input path="name" cssClass="tf1"/></td>
                </tr>
                <tr>
                    <th><form:label path="surname">Прізвище</form:label></th>
                </tr>
                <tr>
                    <td><form:input path="surname" cssClass="tf2"/></td>
                </tr>
                <tr>
                    <th><form:label path="phone">Номер телефону</form:label></th>
                </tr>
                <tr>
                    <td><form:input path="phone" cssClass="tf3"/>${errorLabelPhone}</td>
                </tr>
                <tr>
                    <th><form:label path="email">Електронна пошта</form:label></th>
                </tr>
                <tr>
                    <td><form:input path="email" cssClass="tf4"/>${errorLabelEmail}</td>
                </tr>
                <tr>
                    <th><form:label path="password">Пароль</form:label></th>
                </tr>
                <tr>
                    <td><form:password path="password" cssClass="tf5"/></td>
                </tr>
                <tr>
                    <th><form:label path="confirmPassword">Повторіть пароль</form:label></th>
                </tr>
                <tr>
                    <td><form:password path="confirmPassword" cssClass="tf5"/></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input id="submit_reg" type="submit" value="Зареєструватися"/>
                    </td>
                </tr>
            </table>
        </form:form>
    </div>
</center>

<footer>
    <div>Oleg Svyryd 2015</div>
</footer>

<!-- User registration validation -->
<script type="text/javascript">
    $(document).ready(function () {
        $("#registration #userRegistration-form").validate({
            rules: {
                name: {
                    maxlength: 15,
                    minlength: 2,
                    required: true
                },
                surname: {
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
                password: {
                    required: true,
                    maxlength: 60,
                    minlength: 5
                },
                confirmPassword: {
                    equalTo: password,
                    required: true,
                    maxlength: 60,
                    minlength: 5
                }
            },
            messages: {
                name: {
                    required: "Поле не може бути порожнє",
                    maxlength: $.format("Поле не може містити більше, ніж {0} символів"),
                    minlength: $.format("Поле не може містити менше, ніж {0} символів")
                },
                surname: {
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
                password: {
                    required: "Поле не може бути порожнє",
                    maxlength: $.format("Поле не може містити більше, ніж {0} символів"),
                    minlength: $.format("Поле не може містити менше, ніж {0} символів")
                },
                confirmPassword: {
                    required: "Поле не може бути порожнє",
                    maxlength: $.format("Поле не може містити більше, ніж {0} символів"),
                    minlength: $.format("Поле не може містити менше, ніж {0} символів"),
                    equalTo: "Поле повинно бути ідентичне з полем \'Пароль\'"
                }
            }
        });

        $(".tf3").mask("+99(999)99-99-999");
    });

    $(document).on("click", "#submit_reg", function(){
        if($("#userRegistration-form").valid()) {
            swal({
                title: "Реєстрація пройшла успішно! Будь ласка, увійдіть у систему.",
                type: "success",
                confirmButtonText: "Увійти",
                confirmButtonColor: "#263238"
            });

            $("#userRegistration-form").submit();
        }
    });
</script>
</body>
</html>
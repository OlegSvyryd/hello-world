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

    <title>StoreBase | Звіти магазинів</title>
</head>
<body>
<div id="wrapper">
    <c:url value="/j_spring_security_logout" var="logoutUrl" />
    <jsp:directive.include file="../../Components/Header.html" />

    <table id="stores-table">
        <caption><h1 id = "table-stores" align="center">Список звітів</h1></caption>
    <tr>
        <th>
            <input type = "button" id = "oborot" onclick = "window.location.href='/str${1}'" value = "Оборот">
        </th>
        <td>
            <p>На яку суму продано товару.</p>
        </td>
    </tr>
        <tr>

        <th>
            <input type = "button" id = "pruhidTovary" onclick = "window.location.href='/str${2}'" value = "Прихід товару">
        </th>
        <td>
            <p>На яку суму замовлено товарів.</p>
        </td>
        </tr>
        <tr>
        <th>
            <input type = "button" id = "prubytok" onclick = "window.location.href='/str${3}'" value = "Прибуток">
        </th>
        <td>
            <p>Сума, на яку доходи перевищують пов'язані з ними витрати.</p>
        </td>
        </tr>
        <tr>
        <th>
            <input type = "button" id = "rentabelnist" onclick = "window.location.href='/str${4}'" value = "Рентабельність">
        </th>
        <td>
            <p>Відношення прибутку від реалізації до собівартості реалізованої продукції.</p>
        </td>
        </tr>
        </table>
    <div class="footer-push"></div>
</div>
<jsp:directive.include file="../../Components/Footer.html" />
</body>
</html>

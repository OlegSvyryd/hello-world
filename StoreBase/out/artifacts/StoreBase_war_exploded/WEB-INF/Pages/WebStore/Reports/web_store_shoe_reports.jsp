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

    <title>StoreBase | Звіти інтернет-магазинів</title>
</head>
<body>
<div id="wrapper">
    <c:url value="/j_spring_security_logout" var="logoutUrl" />
    <jsp:directive.include file="../../Components/Header.html" />

    <table id="stores-table">
        <caption><h1 id = "table-stores" align="center">Список звітів</h1></caption>
    <tr>
        <th>
            <input type = "button" id = "oborot" onclick = "window.location.href='/sstr${1}'" value = "Оборот">
        </th>
        <td>
            <p>На яку суму продано товару.</p>
        </td>
    </tr>
    </table>
    <div class="footer-push"></div>
</div>
<jsp:directive.include file="../../Components/Footer.html" />
</body>
</html>

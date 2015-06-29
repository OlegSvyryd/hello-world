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
    <link rel="stylesheet" type="text/css" href="<c:url value='../../../../CSS/jquery.dataTables.min.css' />" >

    <!-- Scripts -->
    <script src="<c:url value="../../../../JS/jquery.min.js" />"></script>
    <script src="<c:url value="../../../../JS/jquery.dataTables.min.js" />"></script>

    <title>StoreBase | Товари на замовлення</title>
</head>
<body>
<div id="wrapper">
    <c:url value="/j_spring_security_logout" var="logoutUrl" />
    <jsp:directive.include file="../../Components/Header.html" />

    <c:if test="${!empty company_goods}">
        <table id="stores-table">
            <caption align="center"><h1 id = "table-stores">Список товарів вибраної компанії</h1></caption>
            <thead>
            <tr>
                <th>Назва товару</th>
                <th>Тип товару</th>
                <th>Ціна товару</th>
                <th>Інформація про товар</th>
                <th>Додати собі в магазин</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${company_goods}" var="goods">
                <tr class = "editField">
                    <td class="too-width"><c:out value="${goods.name}" />
                    </td>
                    <td><c:out value="${goods.goodsType.name}" />
                    </td>
                    <td><c:out value="${goods.price}" />
                    </td>
                    <td class="too-width"><c:out value="${goods.description}" />
                    </td>
                    <td>
                        <input type="button" id = "addGoodsInMyStore-field" value = "Додати собі"
                               onclick="document.location.href='add_goods_in_my_store${goods.id}'"/>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>

    <div class="footer-push"></div>
</div>
<jsp:directive.include file="../../Components/Footer.html" />
<script>
    $(document).ready(function(){
        $("#stores-table").dataTable({
            "aoColumnDefs": [
                {'bSortable': false, 'aTargets': [4]}
            ]
        });
    })
</script>

</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

<c:if test="${!empty report}">
    <table id="stores-table" style="margin-top: -50px !important;">
        <caption><h1 id = "table-stores" align="center">Звіт: оборот</h1></caption>
        <thead>
        <tr>
            <th>Назва товару</th>
            <th>Кількість проданого товару</th>
            <th>Ціна за одиницю</th>
            <th>Сума, на яку продано товар</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${report}" var="report">
            <tr>
                <td class="too-width"><c:out value="${report[0]}" />
                </td>
                <td><c:out value="${report[1]}" />
                </td>
                <td><c:out value="${report[2]}" />
                </td>
                <td><c:out value="${report[3]}" />
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <button id="print-report">Друкувати</button>
</c:if>
</body>
</html>
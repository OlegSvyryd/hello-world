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
        <caption><h1 id = "table-stores" align="center">Звіт: рентабельність</h1></caption>
        <thead>
        <tr>
            <th>Назва товару</th>
            <th>Компанія-постачальник</th>
            <th>Рентабельність</th>
            <th>Чи рентабельний даний товар</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${report}" var="report">
            <tr>
                <td class="too-width"><c:out value="${report[0]}" />
                </td>
                <td class="too-width"><c:out value="${report[1]}" />
                </td>
                <td><c:out value="${report[2]}" />
                </td>
                <td>
                    <c:choose>
                        <c:when test="${report[2] >= 1}">
                            Рентабельний
                        </c:when>
                        <c:otherwise>
                            Не рентабельний
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <button id="print-report">Друкувати</button>
</c:if>
</body>
</html>
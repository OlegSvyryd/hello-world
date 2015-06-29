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

<c:if test="${!empty web_stores}">
    <table id="stores-table">
        <caption><h1 id = "table-stores" align="center">Список інтернет-магазинів даного типу</h1></caption>
        <thead>
        <tr>
            <th>Назва web-магазину</th>
            <th>Тип web-магазину</th>
            <th>Адреса web-магазину</th>
            <th>Телефон web-магазину</th>
            <th>Ел. пошта web-магазину</th>
            <th>Посилання</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${web_stores}" var="web_store">
            <tr>
                <td class="too-width"><c:out value="${web_store.name}" />
                </td>
                <td>
                    <c:choose>
                        <c:when test="${web_store.type_id eq(1)}">
                            <c:out value="Книжковий" />
                        </c:when>
                        <c:otherwise>
                            <c:out value="Взуттєвий" />
                        </c:otherwise>
                    </c:choose>
                </td>
                <td class="too-width"><c:out value="${web_store.address}" />
                </td>
                <td><c:out value="${web_store.phone}" />
                </td>
                <td><c:out value="${web_store.email}" />
                </td>
                <td><a href="/web-store/${web_store.url}">Посилання</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

</body>
</html>
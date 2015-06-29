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

<c:if test="${!empty stores}">
    <table id="stores-table">
        <caption><h1 id = "table-stores" align="center">Список магазинів даного типу</h1></caption>
        <thead>
        <tr>
            <th>Назва магазину</th>
            <th>Тип магазину</th>
            <th>Адреса магазину</th>
            <th>Власник</th>
            <th>Телефон власника</th>
            <th>Ел. пошта власника</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${stores}" var="store">
            <tr>
                <td class="too-width"><c:out value="${store.name}" />
                </td>
                <td><c:out value="${store.storeType.name}" />
                </td>
                <td class="too-width"><c:out value="${store.address}" />
                </td>
                <td><c:out value="${store.users.name} ${store.users.surname}" />
                </td>
                <td><c:out value="${store.users.phone}" />
                </td>
                <td><c:out value="${store.users.email}" />
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

</body>
</html>
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

<c:if test="${!empty companies}">
    <table id="stores-table">
        <caption><h1 id = "table-stores" align="center">Список компаній даного типу</h1></caption>
        <thead>
        <tr>
            <th>Назва компанії</th>
            <th>Тип компанії</th>
            <th>Адреса компанії</th>
            <th>Власник</th>
            <th>Телефон власника</th>
            <th>Ел. пошта власника</th>
            <th>Товари компанії</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${companies}" var="company">
            <tr>
                <td class="too-width"><c:out value="${company.name}" />
                </td>
                <td><c:out value="${company.companyType.name}" />
                </td>
                <td class="too-width"><c:out value="${company.address}" />
                </td>
                <td><c:out value="${company.users.name} ${company.users.surname}" />
                </td>
                <td><c:out value="${company.users.phone}" />
                </td>
                <td><c:out value="${company.users.email}" />
                </td>
                <td>
                    <input type="button" id = "showCompanyGoodsForOrder-field" value = "Товари компанії"
                           onclick="document.location.href='company_goods_for_order${company.id}'"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <!-- CSS -->
    <link rel="stylesheet" type="text/css" href="<c:url value='../../../CSS/Welcome.css' />" >
    <link rel="stylesheet" type="text/css" href="<c:url value='../../../CSS/Main.css' />" >
    <link rel="stylesheet" type="text/css" href="<c:url value='../../../CSS/jquery.dataTables.min.css' />" >
    <link rel="stylesheet" type="text/css" href="<c:url value='../../../CSS/sweetalert.css' />" >

    <!-- Scripts -->
    <script src="<c:url value="../../../JS/jquery.min.js" />"></script>
    <script src="<c:url value="../../../JS/jquery.validate.min.js" />"></script>
    <script src="<c:url value="../../../JS/jquery.dataTables.min.js" />"></script>
    <script src="<c:url value="../../../JS/sweetalert.min.js" />"></script>

    <title>StoreBase | Мої компанії</title>
</head>
<body>
<div id="wrapper">
<c:url value="/j_spring_security_logout" var="logoutUrl" />
<jsp:directive.include file="../Components/Header.html" />
    <c:if test="${!empty companies}">
        <table id="stores-table">
            <caption align="center"><h1 id = "table-stores">Список моїх компаній</h1></caption>
            <thead>
            <tr>
                <th>Назва компанії</th>
                <th>Тип компанії</th>
                <th>Адреса компанії</th>
                <th>Редагувати дані про компанію</th>
                <th>Видалити компанію</th>
                <th>Переглянути замовлення компанії</th>
                <th>Переглянути каталог товарів</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${companies}" var="company">
                <tr class="editField">
                    <td class="too-width"><c:out value="${company.name}" />
                    </td>
                    <td><c:out value="${company.companyType.name}" />
                    </td>
                    <td class="too-width"><c:out value="${company.address}" />
                    </td>
                    <td>
                        <input type="button" id = "updStore-field" value = "Редагувати дані про компанію"
                                onclick="document.location.href='update_company${company.id}'"/>
                    </td>
                    <td>
                        <input type="button" id = "delCompany-field" about="${company.id}" value = "Видалити компанію" />
                    </td>
                    <td>
                        <input type="button" id = "checkCommission-field" value = "Замовлення" onclick="window.location.href='company_orders${company.id}';"/>
                    </td>
                    <td>
                        <input type="button" id = "showGoods-field" value = "Товари"
                               onclick="document.location.href='company_goods${company.id}'"/>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>

    <div class="footer-push"></div>
</div>
<jsp:directive.include file="../Components/Footer.html" />
<script>
    $(document).ready(function(){
        $("#stores-table").dataTable({
            "aoColumnDefs": [
                {'bSortable': false, 'aTargets': [3,4,5,6]}
            ]
        });
    })
</script>
<!-- Delete company script -->
<script type="text/javascript">
    $(document).on("click", "table .editField #delCompany-field", function() {
        var clickedValue = $(this).parents('.editField');
        $(clickedValue).css("background-color", "#BDBDBD");
        swal({
            title: "Видалити вибрану компанію?",
            type: "warning",
            showCancelButton: true,
            closeOnConfirm: false,
            confirmButtonText: "Видaлити!",
            confirmButtonColor: "#ec6c62"
        }, function(isConfirmed) {
            if(isConfirmed) {
            var clickedValue1 = $(this).parents('.editField');

            clickedValue = $('td #delCompany-field', clickedValue).attr('about');
            console.log(clickedValue);
            var savedata = {
                idCompany: clickedValue
            };
            $.ajax({
                url: "/delete_company",
                type: "POST",
                datatype: "json",
                data: savedata,
                error: function () {
                    $(clickedValue).css("background-color", "WHITE");
                    swal("Помилка", "Неможливо підключитися до сервера!", "error");
                },
                success: function () {
                    swal("Видалено!", "Вашу компанію успішно видалено", "success");
                    location.reload(true);
                    clickedValue1.remove();
                }
            });
        }
        else{
            $(clickedValue).css("background-color", "WHITE");
        }
        });
    });
</script>
</body>
</html>

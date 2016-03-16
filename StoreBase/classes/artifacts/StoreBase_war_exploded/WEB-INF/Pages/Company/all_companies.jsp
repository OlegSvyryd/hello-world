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

    <!-- Scripts -->
    <script src="<c:url value="../../../JS/jquery.min.js" />"></script>
    <script src="<c:url value="../../../JS/jquery.validate.min.js" />"></script>
    <script src="<c:url value="../../../JS/jquery.dataTables.min.js" />"></script>

    <title>StoreBase | Список компаній</title>
</head>
<body>
<div id="wrapper">
    <c:url value="/j_spring_security_logout" var="logoutUrl" />
    <jsp:directive.include file="../Components/Header.html" />

    <ul id = "allTypes">
        <c:forEach items="${list_company_types}" var="company_type" varStatus="loop">
            <li class='editFieldStore' about="${company_type.id}"><a>${company_type.name}</a></li>
        </c:forEach>
    </ul>

    <!-- Get all stores by type script -->
    <script type="text/javascript">
        $(document).on("click", "ul li a", function() {
            var clickedValue = $(this).parents('.editFieldStore');
            clickedValue = $(clickedValue).attr('about');
            console.log(clickedValue);

            $.get( "/all_companies/" + clickedValue, function( data ) {
                $( "#ajaxButton" ).html( data );
                $('#ajaxButton #stores-table').dataTable({
                    "aoColumnDefs": [
                        {'bSortable': false, 'aTargets': [6]}
                    ]
                });

            });
        });
    </script>

    <span id="ajaxButton"></span>

    <div class="footer-push"></div>
</div>
<jsp:directive.include file="../Components/Footer.html" />
</body>
</html>

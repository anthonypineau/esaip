<%-- 
    Document   : page1
    Created on : 15 oct. 2021, 10:50:59
    Author     : Anthony
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Bonus calculation</h1>
        <c:forEach items="${data}" var="item">
            <p>Social Security Number : ${item.securityNumber}</p>
            <p>Bonus amoount : ${item.bonus}</p>
        </c:forEach>
        
    </body>
</html>
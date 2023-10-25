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
            <p>Social Security Number : ${item.securityNumber!=-1 ? item.securityNumber : "Please specify a social security number"}</p>
            <p>Multiplier : ${item.multiplier!=-1 ? item.multiplier : "Please specify a multiplier"}</p>
            <p>Bonus amoount : ${item.bonus!=-100 ? item.bonus : "Empty calculated bonus"}</p>
        </c:forEach>
        
    </body>
</html>

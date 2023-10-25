<%-- 
    Document   : page1
    Created on : 15 oct. 2021, 10:50:59
    Author     : Anthony
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <h1>Bonus calculation</h1>
        <p>Social Security Number : ${data.securityNumber!=-1 ? data.securityNumber : "Please specify a social security number"}</p>
        <p>Multiplier : ${data.multiplier!=-1 ? data.multiplier : "Please specify a multiplier"}</p>
        <p>Bonus amoount : ${data.bonus!=-100 ? data.bonus : "Empty calculated bonus"}</p>
    </body>
</html>

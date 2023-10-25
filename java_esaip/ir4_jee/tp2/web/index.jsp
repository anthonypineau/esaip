<%-- 
    Document   : index
    Created on : 15 oct. 2021, 11:14:37
    Author     : Anthony
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Form</title>
    </head>
    <body>
        <form action="/tp2/Servlet" method="get">
            <label for="securityNumber">Enter Social Security Number : </label>
            <input type="text" id="securityNumber" name="securityNumber">
            <br>
            <label for="multiplier">Enter multiplier : </label>
            <input type="text" id="multiplier" name="multiplier">
            <br>
            <button type="submit">Submit</button>
            <button type="reset">Reset</button>
        </form>
    </body>
</html>

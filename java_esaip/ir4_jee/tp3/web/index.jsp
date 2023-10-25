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
        <form action="/tp3/Servlet" method="get">
            <table>
                <thead>
                    <tr>
                        <td>Social Security Number</td>
                        <td>Multiplier</td>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>
                            <input type="text" name="securityNumber">
                        </td>
                        <td>
                            <input type="text" name="multiplier">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="text" name="securityNumber">
                        </td>
                        <td>
                            <input type="text" name="multiplier">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="text" name="securityNumber">
                        </td>
                        <td>
                            <input type="text" name="multiplier">
                        </td>
                    </tr>
                </tbody>
            </table>
            <button type="submit">Submit</button>
            <button type="reset">Reset</button>
        </form>
    </body>
</html>

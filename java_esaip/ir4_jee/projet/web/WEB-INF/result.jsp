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
        <title>Users</title>
        <style>
            table, th, td {
                border: 1px solid black;
                border-collapse: collapse
            }
            td{
                padding: 0 20px;
            }
        </style>
    </head>
    <body>
        <c:if test="${!search}">
            <h1>Show all users</h1>
        </c:if>
        <c:if test="${search}">
            <h1>Result of your search</h1>
        </c:if>
        <p>Total user count : ${count}</p>
        <table>
            <thead>
                <tr>
                    <td>ID</td>
                    <td>First name</td>
                    <td>Last name</td>
                    <td>Login (username)</td>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${data}" var="item">
                    <tr>
                        <td><p>${item.id}</p></td>
                        <td><p>${item.firstname}</p></td>
                        <td><p>${item.name}</p></td>
                        <td><p>${item.login}</p></td>                       
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <form action="/projet/Servlet" method="get">
            <h1>Search for users</h1>
            <p>WARNING! Case sensitive</p>
            <select name="search">
                <option value="all">All names</option>
                <option value="name">Last name</option>
                <option value="firstname">First name</option>
                <option value="login">Login</option>
            </select>
            <input type="text" name="clause">
            <button type="submit">Search</button>
        </form>        
    </body>
</html>
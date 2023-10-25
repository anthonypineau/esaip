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
        <title>Projet</title>
        <style>
            table, th, td {
                border: 1px solid black;
                border-collapse: collapse
            }
            td{
                padding: 0 20px;
            }
            thead td{
                padding: 5px 10px;
                text-align: center;
            }
            input{
                margin: 10px;
            }
            table{
                margin-bottom: 10px;
            }
            table button{
                display: block;
                max-width: 50px;
                margin: 0 auto;
                background: inherit;
                border: none;
                cursor: pointer;
            }
        </style>
    </head>
    <body>
        <h1>Input new user(s)</h1>
        <p>WARNING! Put no accents on the names</p>
        <p>To see the list of existing names delete all lines and submit</p>
        <form action="/projet/Servlet" method="get">
            <table>
                <thead>
                    <tr>
                        <td>First name</td>
                        <td>Last name</td>
                        <td>Login (username)</td>
                        <td>Delete this line</td>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>
                            <input type="text" name="firstname" required>
                        </td>
                        <td>
                            <input type="text" name="name" required>
                        </td>
                        <td>
                            <input type="text" name="login" required>
                        </td>
                        <td>
                            <button onclick="this.parentNode.parentNode.remove()">❌</button>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="text" name="firstname" required>
                        </td>
                        <td>
                            <input type="text" name="name" required>
                        </td>
                        <td>
                            <input type="text" name="login" required>
                        </td>
                        <td>
                            <button onclick="this.parentNode.parentNode.remove()">❌</button>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="text" name="firstname" required>
                        </td>
                        <td>
                            <input type="text" name="name" required>
                        </td>
                        <td>
                            <input type="text" name="login" required>
                        </td>
                        <td>
                            <button onclick="this.parentNode.parentNode.remove()">❌</button>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="text" name="firstname" required>
                        </td>
                        <td>
                            <input type="text" name="name" required>
                        </td>
                        <td>
                            <input type="text" name="login" required>
                        </td>
                        <td>
                            <button onclick="this.parentNode.parentNode.remove()">❌</button>
                        </td>
                    </tr>
                </tbody>
            </table>
            <button type="submit">Submit</button>
            <button type="reset">Reset</button>
        </form>
    </body>
</html>
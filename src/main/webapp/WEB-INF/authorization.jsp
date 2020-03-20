<%--
  Created by IntelliJ IDEA.
  User: Игорь
  Date: 17.03.2020
  Time: 19:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<%-- Форма авторизации--%>
<div align="center">
    <h2>Authorization</h2>
    <form action="<c:url value="/j_spring_security_check"></c:url>" method="post" role="form">
        <fieldset>
            <table cellspacing="0">
                <tr>
                    <td>
                        <c:if test="${param.error != null}">
                            <div id="error">
                                <p>Incorrect login or password</p> <!-- Поле вывода ошибок -->
                            </div>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <th><label for="email">Email</label></th>
                    <td><input id="email"
                               name="j_username"
                               type="text" /> <!-- Поле ввода имени пользователя -->
                    </td>
                </tr>
                <tr>
                    <th><label for="password">Password</label></th>
                    <td><input id="password"
                               name="j_password"
                               type="password" /> <!-- Поле ввода пароля -->
                    </td>
                </tr>
                <tr>
                    <th></th>
                    <td><input id="remember_me"
                               name="j_remember_me"
                               type="checkbox"/> <!-- Флажок "запомнить меня" -->
                        <label for="remember_me"
                               class="inline">Remember me</label></td>
                </tr>
                <tr>
                    <th></th>
                    <td><input name="commit" type="submit" value="Sign In" /></td>
                </tr>
            </table>
        </fieldset>
    </form>
</div>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Registration</title>
</head>
<body>
    <h1>Form registration</h1>
    <div align="center">
        <fieldset>
            <f:form action="/registration/user" modelAttribute="registeredUser" method="post">
                <table>
                    <tbody>
                    <tr>
                        <td>
                            <br/>
                            <div><f:errors path="authorization.login" /></div>
                            <f:label path="authorization.login">Login:</f:label>
                        </td>
                        <td><f:input type="text" path="authorization.login" maxlength="10"/></td>
                        <br/>
                    </tr>
                    <tr>
                        <td>
                            <br/>
                            <div><f:errors path="authorization.password" /></div>
                            <f:label path="authorization.password">Password:</f:label>
                        </td>
                        <td><f:input type="password" path="authorization.password" maxlength="10"/></td>
                        <br/>
                    </tr>
                    <tr>
                        <td>Password repeat:</td>
                        <td><input type="password" name="passwordRepeat" maxlength="10"/></td>
                        <br/>
                    </tr>
                    <tr>
                        <td>
                            <br/>
                            <div><f:errors path="firstName" /></div>
                            <f:label path="firstName">First Name:</f:label>
                        </td>
                        <td><f:input type="text" path="firstName" maxlength="10"/></td>
                        <br/>
                    </tr>
                    <tr>
                        <td>
                            <br/>
                            <div><f:errors path="secondName" /></div>
                            <f:label path="secondName">Second Name:</f:label>
                        </td>
                        <td><f:input type="text" path="secondName" maxlength="10"/></td>
                        <br/>
                    </tr>
                    <tr>
                        <td>
                            <br/>
                            <div><f:errors path="phoneNumber" /></div>
                            <f:label path="phoneNumber">Phone number:</f:label>
                        </td>
                        <td> <f:input type="tel" path="phoneNumber" maxlength="13"/></td>
                        <br/>
                    </tr>
                    <tr>
                        <td>
                            <br/>
                            <div><f:errors path="email" /></div>
                            <f:label path="email">E-mail:</f:label>
                        </td>
                        <td><f:input type="email" path="email" maxlength="30"/></td>
                    </tr>
                    </tbody>
                    <tfoot>
                        <input type="submit" value="Registration"/>
                    </tfoot>
                </table>
            </f:form>
        </fieldset>
    </div>
</body>
</html>

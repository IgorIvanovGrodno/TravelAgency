<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="contentVerticalCenter">
    <h1><spring:message key="registration"/></h1>
            <%--@elvariable id="registeredUser" type="User"--%>
            <f:form action="/registration/user" modelAttribute="registeredUser" method="post">
            <fieldset  class="field">
                <table>
                    <tr>
                        <td>
                            <div><f:errors cssClass="errorMessage" path="authorization.login" /></div>
                            <f:label path="authorization.login"><spring:message key="login"/></f:label>
                        </td>
                        <td><f:input type="text" path="authorization.login" maxlength="10"/></td>
                    </tr>
                    <tr>
                        <td>
                            <br/>
                            <div><f:errors cssClass="errorMessage" path="authorization.password" /></div>
                            <f:label path="authorization.password"><spring:message key="password"/></f:label>
                        </td>
                        <td><f:input type="password" path="authorization.password" maxlength="10"/></td>
                    </tr>
                    <tr>
                        <td><spring:message key="password.repeat"/></td>
                        <td><input type="password" name="passwordRepeat" maxlength="10"/></td>

                    </tr>
                    <tr>
                        <td>
                            <br/>
                            <div><f:errors cssClass="errorMessage" path="firstName" /></div>
                            <f:label path="firstName"><spring:message key="first.name"/></f:label>
                        </td>
                        <td><f:input type="text" path="firstName" maxlength="10"/></td>
                    </tr>
                    <tr>
                        <td>
                            <br/>
                            <div><f:errors cssClass="errorMessage" path="secondName" /></div>
                            <f:label path="secondName"><spring:message key="second.name"/></f:label>
                        </td>
                        <td><f:input type="text" path="secondName" maxlength="10"/></td>
                    </tr>
                    <tr>
                        <td>
                            <br/>
                            <div><f:errors cssClass="errorMessage" path="phoneNumber" /></div>
                            <f:label path="phoneNumber"><spring:message key="phone.number"/></f:label>
                        </td>
                        <td> <f:input type="tel" path="phoneNumber" maxlength="13"/></td>
                    </tr>
                    <tr>
                        <td>
                            <br/>
                            <div><f:errors cssClass="errorMessage" path="email" /></div>
                            <f:label path="email"><spring:message key="email"/></f:label>
                        </td>
                        <td><f:input type="email" path="email" maxlength="30"/></td>
                    </tr>
                        <input type="submit" value="Registration"/>
                </table>
            </fieldset>
            </f:form>
</div>

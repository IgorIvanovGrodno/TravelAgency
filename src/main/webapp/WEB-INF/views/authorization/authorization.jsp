<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="contentVerticalCenter">
    <h2><spring:message key="authorization"/></h2>
    <form action="<c:url value="/j_spring_security_check"/>" method="post" role="form">
        <fieldset class="field">
            <table cellspacing="0">
                <tr>
                    <td>
                        <c:if test="${param.error != null}">
                            <div class="errorMessage" id="error">
                                <p><spring:message key="incorrect.login.password"/></p> <!-- Error field -->
                            </div>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <th><label for="email"><spring:message key="login"/></label></th>
                    <td><input id="email"
                               name="j_username"
                               type="text" /> <!-- Input login -->
                    </td>
                </tr>
                <tr>
                    <th><label for="password"><spring:message key="password"/></label></th>
                    <td><input id="password"
                               name="j_password"
                               type="password" /> <!-- Input password -->
                    </td>
                </tr>
                <tr>
                    <th></th>
                    <td><input name="commit" type="submit" value="Sign In" /></td>
                </tr>
            </table>
        </fieldset>
    </form>
</div>


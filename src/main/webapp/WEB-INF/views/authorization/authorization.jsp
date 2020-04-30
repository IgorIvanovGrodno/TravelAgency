<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="contentVerticalCenter">
    <h2>Authorization</h2>
    <form action="<c:url value="/j_spring_security_check"/>" method="post" role="form">
        <fieldset class="field">
            <table cellspacing="0">
                <tr>
                    <td>
                        <c:if test="${param.error != null}">
                            <div class="errorMessage" id="error">
                                <p>Incorrect authorization or password</p> <!-- Error field -->
                            </div>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <th><label for="email">Login</label></th>
                    <td><input id="email"
                               name="j_username"
                               type="text" /> <!-- Input login -->
                    </td>
                </tr>
                <tr>
                    <th><label for="password">Password</label></th>
                    <td><input id="password"
                               name="j_password"
                               type="password" /> <!-- Input password -->
                    </td>
                </tr>
                <tr>
                    <th></th>
                    <td><input id="remember_me"
                               name="j_remember_me"
                               type="checkbox"/> <!-- Flag "remember me" -->
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


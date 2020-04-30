<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<div class="contentVerticalCenter">
    <h1>Form registration</h1>
            <%--@elvariable id="registeredUser" type="User"--%>
            <f:form action="/registration/user" modelAttribute="registeredUser" method="post">
            <fieldset  class="field">
                <table>
                    <tr>
                        <td>
                            <div><f:errors cssClass="errorMessage" path="authorization.login" /></div>
                            <f:label path="authorization.login">Login:</f:label>
                        </td>
                        <td><f:input type="text" path="authorization.login" maxlength="10"/></td>
                    </tr>
                    <tr>
                        <td>
                            <br/>
                            <div><f:errors cssClass="errorMessage" path="authorization.password" /></div>
                            <f:label path="authorization.password">Password:</f:label>
                        </td>
                        <td><f:input type="password" path="authorization.password" maxlength="10"/></td>
                    </tr>
                    <tr>
                        <td>Password repeat:</td>
                        <td><input type="password" name="passwordRepeat" maxlength="10"/></td>

                    </tr>
                    <tr>
                        <td>
                            <br/>
                            <div><f:errors cssClass="errorMessage" path="firstName" /></div>
                            <f:label path="firstName">First Name:</f:label>
                        </td>
                        <td><f:input type="text" path="firstName" maxlength="10"/></td>
                    </tr>
                    <tr>
                        <td>
                            <br/>
                            <div><f:errors cssClass="errorMessage" path="secondName" /></div>
                            <f:label path="secondName">Second Name:</f:label>
                        </td>
                        <td><f:input type="text" path="secondName" maxlength="10"/></td>
                    </tr>
                    <tr>
                        <td>
                            <br/>
                            <div><f:errors cssClass="errorMessage" path="phoneNumber" /></div>
                            <f:label path="phoneNumber">Phone number:</f:label>
                        </td>
                        <td> <f:input type="tel" path="phoneNumber" maxlength="13"/></td>
                    </tr>
                    <tr>
                        <td>
                            <br/>
                            <div><f:errors cssClass="errorMessage" path="email" /></div>
                            <f:label path="email">E-mail:</f:label>
                        </td>
                        <td><f:input type="email" path="email" maxlength="30"/></td>
                    </tr>
                        <input type="submit" value="Registration"/>
                </table>
            </fieldset>
            </f:form>
</div>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<div align="center">
    <h2 >Create tour package</h2>
    <%--@elvariable id="newTourPackage" type="com.company.utils.ModelTourPackage"--%>
    <f:form modelAttribute="newTourPackage" action="tourPackage" method="get">
        <fieldset>
            <table cellspacing="15">
                <tr>
                    <td>
                        <f:label path="idOfType">Type</f:label>
                        <f:select path="idOfType">
                            <f:options itemValue="id" items="${sessionScope.types}"/>
                        </f:select>
                    </td>
                    <td>
                        <f:label path="idOfFoodSystem">Food System</f:label>
                        <f:select path="idOfFoodSystem">
                            <f:options itemValue="id" items="${sessionScope.foodSystemList}"/>
                        </f:select>
                    </td>
                    <td>
                        <f:label path="idOfTransport">Transport</f:label>
                        <f:select path="idOfTransport">
                            <f:options itemValue="id" items="${sessionScope.transports}"/>
                        </f:select>
                    </td>
                    <td>
                        <f:label path="statusHot">Hot</f:label>
                        <f:checkbox path="statusHot"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <br/>
                        <div><f:errors cssClass="errorMessage" path="day" /></div>
                        <div>
                            <f:label path="day">Days</f:label>
                            <f:input type="number" path="day" maxlength="6" min="1"/>
                        </div>

                    </td>
                    <td>
                        <br/>
                        <div><f:errors cssClass="errorMessage" path="price" /></div>
                        <div>
                            <f:label path="price">Price</f:label>
                            <f:input type="number" path="price" maxlength="10" min="1"/>
                        </div>
                    </td>
                    <td>
                        <br/>
                        <div><f:errors cssClass="errorMessage" path="description" /></div>
                        <div>
                            <f:label path="description">Description</f:label>
                            <f:input type="text" path="description" maxlength="30"/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Create"/></td>
                    <td></td>
                </tr>
            </table>
        </fieldset>
    </f:form>

</div>

</body>
</html>

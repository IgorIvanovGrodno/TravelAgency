
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Admin</title>
</head>
<body>

<%-- Form creating tourPackage --%>
<h2 align="center">Create tour package</h2>
<div align="center">
    <f:form modelAttribute="newTourPackage" action="tourPackage" method="get">
        <fieldset>
            <table cellspacing="15">
                <tr>
                    <td>
                        <f:label path="valueOfType">Type</f:label>
                        <f:select path="valueOfType">
                            <f:options items="${sessionScope.types}"/>
                        </f:select>
                    </td>
                    <td>
                        <f:label path="valueOfFoodSystem">Food System</f:label>
                        <f:select path="valueOfFoodSystem">
                            <f:options items="${sessionScope.foodSystemList}"/>
                        </f:select>
                    </td>
                    <td>
                        <f:label path="valueOfTransport">Transport</f:label>
                        <f:select path="valueOfTransport">
                            <f:options items="${sessionScope.transports}"/>
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
                        <div><f:errors path="day" /></div>
                        <div>
                            <f:label path="day">Days</f:label>
                            <f:input type="number" path="day" maxlength="6" min="1"/>
                        </div>

                    </td>
                    <td>
                        <br/>
                        <div><f:errors path="price" /></div>
                        <div>
                            <f:label path="price">Price</f:label>
                            <f:input type="number" path="price" maxlength="10" min="1"/>
                        </div>
                    </td>
                    <td>
                        <br/>
                        <div><f:errors path="description" /></div>
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

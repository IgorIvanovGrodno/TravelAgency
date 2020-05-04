<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div align="center">
    <h2 ><spring:message key="create.tour.package"/></h2>
    <%--@elvariable id="newTourPackage" type="com.company.utils.ModelTourPackage"--%>
    <f:form cssClass="form" modelAttribute="newTourPackage" action="tourPackage" method="post">
        <fieldset>
            <table cellspacing="15">
                <tr>
                    <td>
                        <f:label path="idOfType"><spring:message key="index.type"/></f:label>
                        <f:select path="idOfType">
                            <f:options itemValue="id" items="${sessionScope.types}"/>
                        </f:select>
                    </td>
                    <td>
                        <f:label path="idOfFoodSystem"><spring:message key="index.food.system"/></f:label>
                        <f:select path="idOfFoodSystem">
                            <f:options itemValue="id" items="${sessionScope.foodSystemList}"/>
                        </f:select>
                    </td>
                    <td>
                        <f:label path="idOfTransport"><spring:message key="index.transport"/></f:label>
                        <f:select path="idOfTransport">
                            <f:options itemValue="id" items="${sessionScope.transports}"/>
                        </f:select>
                    </td>
                    <td>
                        <f:label path="statusHot"><spring:message key="index.hot"/></f:label>
                        <f:checkbox path="statusHot"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <br/>
                        <div><f:errors cssClass="errorMessage" path="day" /></div>
                        <div>
                            <f:label path="day"><spring:message key="index.days"/></f:label>
                            <f:input type="number" path="day" maxlength="6" min="1"/>
                        </div>

                    </td>
                    <td>
                        <br/>
                        <div><f:errors cssClass="errorMessage" path="price" /></div>
                        <div>
                            <f:label path="price"><spring:message key="index.price"/></f:label>
                            <f:input type="number" path="price" maxlength="10" min="1"/>
                        </div>
                    </td>
                    <td>
                        <br/>
                        <div><f:errors cssClass="errorMessage" path="description" /></div>
                        <div>
                            <f:label path="description"><spring:message key="index.description"/></f:label>
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

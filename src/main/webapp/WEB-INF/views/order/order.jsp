<%@ page contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>


    <div class="order" align="center">
        <h1><spring:message key="order"/></h1>
        <c:set var="tourPackageForOrder" value="${sessionScope.tourPackageForOrder}"/>
        <table>
            <tr>
                <%--@elvariable id="order" type="Order"--%>
                <f:form modelAttribute="order" action="/user/order/pay" method="get">
                    <input type="hidden" name="totalCost" value="${totalPrice}"/>
                        <table>

                            <tr>
                                <td>
                                    <div><f:errors cssClass="errorMessage" path="numberCard" /></div>
                                    <f:label path="numberCard"><spring:message key="order.number.card"/></f:label>
                                    <f:input type="text" path="numberCard"/>
                                    <input type="submit" value="Pay">
                                </td>
                            </tr>
                            <tr><td>
                                    <p><spring:message key="order.total.price"/></p>
                                    <c:out value="${totalPrice}"/>

                                </td>
                            </tr>

                        </table>
                </f:form>

            </tr>
            <tr>
                <table cellspacing="15" align="center">
                    <thead>
                    <tr style="color: darkorange">
                        <td><p><spring:message key="index.description"/></p></td>
                        <td><p><spring:message key="index.type"/></p></td>
                        <td><p><spring:message key="index.food.system"/></p></td>
                        <td><p><spring:message key="index.transport"/></p></td>
                        <td><p><spring:message key="index.days"/></p></td>
                        <td><p><spring:message key="index.price"/></p></td>
                    </tr>
                    </thead>
                    <tbody>

                            <td>${tourPackageForOrder.name}</td>
                            <td>${tourPackageForOrder.type}</td>
                            <td>${tourPackageForOrder.foodSystem}</td>
                            <td>${tourPackageForOrder.transport}</td>
                            <td>${tourPackageForOrder.days}</td>
                            <td>${tourPackageForOrder.price}</td>
                        </tr>
                    </tbody>
                </table>
            </tr>
        </table>
    </div>

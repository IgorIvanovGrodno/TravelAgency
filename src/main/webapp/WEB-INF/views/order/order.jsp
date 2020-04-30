
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
    <title>Order</title>
</head>
<body>
    <h1>Order</h1>
    <c:set var="tourPackageForOrder" value="${sessionScope.tourPackageForOrder}"/>
    <div align="center">
        <table>
            <tr>

                <f:form modelAttribute="order" action="/user/order/pay" method="get">
                    <input type="hidden" name="totalCost" value="${totalPrice}"/>
                        <table>

                            <tr>
                                <td>
                                    <div><f:errors path="numberCard" /></div>
                                    <f:label path="numberCard">Number card</f:label>
                                    <f:input type="text" path="numberCard"/>
                                    <input type="submit" value="Pay">
                                </td>
                            </tr>
                            <tr><td>
                                    <p>Total Price</p>
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
                        <td><p>Description</p></td>
                        <td><p>Type</p></td>
                        <td><p>Food System</p></td>
                        <td><p>Transport</p></td>
                        <td><p>Days</p></td>
                        <td><p>Price</p></td>
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
</body>
</html>

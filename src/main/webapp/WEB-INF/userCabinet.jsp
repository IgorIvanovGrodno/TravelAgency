<%--
  Created by IntelliJ IDEA.
  User: Игорь
  Date: 17.03.2020
  Time: 22:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>User</title>
</head>
<body>
    <h1>User</h1>
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
                <c:forEach var="paidTour" items="${paidTourPackages}">
                <tr>
                    <td>${paidTour.name}</td>
                    <td>${paidTour.type}</td>
                    <td>${paidTour.foodSystem}</td>
                    <td>${paidTour.transport}</td>
                    <td>${paidTour.days}</td>
                    <td>${paidTour.price}</td>
                </tr>
                </c:forEach>

        </tbody>
    </table>
</body>
</html>

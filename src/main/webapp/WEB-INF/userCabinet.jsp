
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>User</title>
</head>
<body>
    <h1>My orders:</h1>
    <div>
        <%-- Button return to index--%>
        <div align="right">
            <f:form action="/" method="get" align="center">
                <input  type="submit" value="Return to main page"/>
            </f:form>
        </div></div>
    <table cellspacing="15" align="center">
        <thead>
        <tr style="color: darkorange">
            <td><p>Description</p></td>
            <td><p>Type</p></td>
            <td><p>Food System</p></td>
            <td><p>Transport</p></td>
            <td><p>Days</p></td>
            <td><p>Total cost</p></td>
            <td><p>Create Date</p></td>
            <td><p>Number card</p></td>
            <td><p>Status</p></td>
        </tr>
        </thead>
        <tbody>
        <c:set var="orderPageList" value="${sessionScope.usersOrders}"/>
                <c:forEach var="order" items="${orderPageList.pageList}">
                <tr>
                    <td>${order.tourPackage.name}</td>
                    <td>${order.tourPackage.type}</td>
                    <td>${order.tourPackage.foodSystem}</td>
                    <td>${order.tourPackage.transport}</td>
                    <td>${order.tourPackage.days}</td>
                    <td>${order.totalCost}</td>
                    <td>${order.createDate}</td>
                    <td>${order.numberCard}</td>
                    <td>${order.status}</td>
                </tr>
                </c:forEach>

        </tbody>
        <tfoot>
        <tr align="center">
            <td></td>
            <td>
                <c:choose>
                    <%-- кнопка предыдущая страница --%>
                    <c:when test="${orderPageList.firstPage}">
                        <span>Prev</span>
                    </c:when>
                    <c:otherwise>
                        <c:url value="/user/prev" var="url" />
                        <a href='<c:out value="${url}" />'>Prev</a>
                    </c:otherwise>
                </c:choose>
                <c:forEach begin="1" end="${orderPageList.pageCount}" step="1" varStatus="tagStatus">
                    <c:choose>
                        <%-- Нумерация страниц --%>
                        <c:when test="${(orderPageList.page + 1) == tagStatus.index}">
                            <span>${tagStatus.index}</span>
                        </c:when>
                        <c:otherwise>
                            <c:url value="/user/${tagStatus.index}" var="url" />
                            <a href='<c:out value="${url}" />'>${tagStatus.index}</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <%-- кнопка следующая страница --%>
                <c:choose>
                    <c:when test="${orderPageList.lastPage}">
                        <span>Next</span>
                    </c:when>
                    <c:otherwise>
                        <c:url value="/user/next" var="url" />
                        <a href='<c:out value="${url}" />'>Next</a>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        </tfoot>
    </table>
</body>
</html>

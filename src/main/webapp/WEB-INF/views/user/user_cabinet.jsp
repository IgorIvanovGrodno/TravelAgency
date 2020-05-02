<%@ page contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>

        <div align="center">
            <h1 class="order"><spring:message key="order.my.orders"/></h1>
            <a href="/" class="buttonHeader"><spring:message key="main.page"/></a>
            <table cellspacing="15" align="center">
                <thead>
                <tr style="color: darkorange">
                    <td><p><spring:message key="index.description"/></p></td>
                    <td><p><spring:message key="index.type"/></p></td>
                    <td><p><spring:message key="index.food.system"/></p></td>
                    <td><p><spring:message key="index.transport"/></p></td>
                    <td><p><spring:message key="index.days"/></p></td>
                    <td><p><spring:message key="total.cost"/></p></td>
                    <td><p><spring:message key="create.date"/></p></td>
                    <td><p><spring:message key="order.number.card"/></p></td>
                    <td><p><spring:message key="status"/></p></td>
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
                            <%-- prev page --%>
                            <c:when test="${orderPageList.firstPage}">
                                <span><spring:message key="index.prev"/></span>
                            </c:when>
                            <c:otherwise>
                                <c:url value="/user/prev" var="url" />
                                <a href='<c:out value="${url}" />'><spring:message key="index.prev"/></a>
                            </c:otherwise>
                        </c:choose>
                        <c:forEach begin="1" end="${orderPageList.pageCount}" step="1" varStatus="tagStatus">
                            <c:choose>
                                <%-- number page --%>
                                <c:when test="${(orderPageList.page + 1) == tagStatus.index}">
                                    <span>${tagStatus.index}</span>
                                </c:when>
                                <c:otherwise>
                                    <c:url value="/user/${tagStatus.index}" var="url" />
                                    <a href='<c:out value="${url}" />'>${tagStatus.index}</a>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <%-- next page --%>
                        <c:choose>
                            <c:when test="${orderPageList.lastPage}">
                                <span><spring:message key="index.next"/></span>
                            </c:when>
                            <c:otherwise>
                                <c:url value="/user/next" var="url" />
                                <a href='<c:out value="${url}" />'><spring:message key="index.next"/></a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                </tfoot>
            </table>
        </div>

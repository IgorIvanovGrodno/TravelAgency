<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sping" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--@elvariable id="deleteTourPackage" type="com.company.utils.ModelTourPackage"--%>

        <div align="center">
            <fieldset>
            <table cellspacing="15" align="center">
                <thead>
                <tr style="color: darkorange">
                    <td><p><sping:message key="order.number.card"/></p></td>
                    <td><p><spring:message key="order.total.price"/></p></td>
                    <td><p><spring:message key="create.date"/></p></td>
                    <td><p><sping:message key="status"/></p></td>
                </tr>
                </thead>
                <tbody>
                <c:set var="newOrders" value="${sessionScope.newOrders}"/>
                <c:forEach var="newOrder" items="${newOrders.pageList}">
                    <tr>
                        <td>
                            <c:out value="${newOrder.numberCard}"/>
                        </td>
                        <td>
                            <c:out value="${newOrder.totalCost}"/>
                        </td>
                        <td>
                            <c:out value="${newOrder.createDate}"/>
                        </td>
                        <td>
                            <c:out value="${newOrder.status}"/>
                        </td>
                        <td>
                            <f:form action="/admin/orders/change/${newOrder.id}" method="post">
                                <input type="submit" value="Change status"/>
                            </f:form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
                <tfoot>
                <tr align="center">
                    <td></td>
                    <td>
                        <c:choose>
                            <%-- кнопка предыдущая страница --%>
                            <c:when test="${newOrders.firstPage}">
                                <span><sping:message key="index.prev"/></span>
                            </c:when>
                            <c:otherwise>
                                <c:url value="/admin/orders/prev" var="url" />
                                <a href='<c:out value="${url}" />'><sping:message key="index.prev"/></a>
                            </c:otherwise>
                        </c:choose>
                        <c:forEach begin="1" end="${newOrders.pageCount}" step="1"  varStatus="tagStatus">
                            <c:choose>
                                <%-- Нумерация страниц --%>
                                <c:when test="${(newOrders.page + 1) == tagStatus.index}">
                                    <span>${tagStatus.index}</span>
                                </c:when>
                                <c:otherwise>
                                    <c:url value="/admin/orders/${tagStatus.index}" var="url" />
                                    <a href='<c:out value="${url}" />'>${tagStatus.index}</a>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                            <%-- кнопка следующая страница --%>
                        <c:choose>
                            <c:when test="${newOrders.lastPage}">
                                <span><sping:message key="index.next"/></span>
                            </c:when>
                            <c:otherwise>
                                <c:url value="/admin/orders/next" var="url" />
                                <a href='<c:out value="${url}" />'><sping:message key="index.next"/></a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                </tfoot>
            </table>
            </fieldset>
        </div>

<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sping" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--@elvariable id="deleteTourPackage" type="com.company.utils.ModelTourPackage"--%>
<f:form cssClass="form" modelAttribute="deleteTourPackage" action="tourPackage" method="get">
    <fieldset>


                <div align="center">

                    <input type="submit" value="Delete"/>

                    <table cellspacing="15" align="center">
                        <thead>
                        <div><f:errors cssClass="errorMessage" path="id" /></div>
                        <tr style="color: darkorange">
                            <td><p><sping:message key="index.description"/></p></td>
                            <td><p><spring:message key="index.type"/></p></td>
                            <td><p><spring:message key="index.food.system"/></p></td>
                            <td><p><sping:message key="index.transport"/></p></td>
                            <td><p><spring:message key="index.days"/></p></td>
                            <td><p><spring:message key="index.price"/></p></td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:set var="tourPageList" value="${sessionScope.tourPackagesForDelete}"/>
                        <c:forEach var="tour" items="${tourPageList.pageList}">
                            <tr>
                                <td>
                                    <f:radiobutton  path="id" value="${tour.id}"/>
                                    <c:out value="${tour.name}"/>
                                </td>
                                <td>
                                   <c:out value="${tour.type}"/>
                                </td>
                                <td>
                                        <c:out value="${tour.foodSystem}"/>
                                </td>
                                <td>
                                        <c:out value="${tour.transport}"/>
                                </td>
                                <td>
                                        <c:out value="${tour.days}"/>
                                </td>
                                <td>
                                        <c:out value="${tour.price}"/>
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
                                    <c:when test="${tourPageList.firstPage}">
                                        <span><sping:message key="index.prev"/></span>
                                    </c:when>
                                    <c:otherwise>
                                        <c:url value="/admin/delete/prev" var="url" />
                                        <a href='<c:out value="${url}" />'><sping:message key="index.prev"/></a>
                                    </c:otherwise>
                                </c:choose>
                                <c:forEach begin="1" end="${tourPageList.pageCount}" step="1"  varStatus="tagStatus">
                                    <c:choose>
                                        <%-- Нумерация страниц --%>
                                        <c:when test="${(tourPageList.page + 1) == tagStatus.index}">
                                            <span>${tagStatus.index}</span>
                                        </c:when>
                                        <c:otherwise>
                                            <c:url value="/admin/delete/${tagStatus.index}" var="url" />
                                            <a href='<c:out value="${url}" />'>${tagStatus.index}</a>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                                    <%-- кнопка следующая страница --%>
                                <c:choose>
                                    <c:when test="${tourPageList.lastPage}">
                                        <span><sping:message key="index.next"/></span>
                                    </c:when>
                                    <c:otherwise>
                                        <c:url value="/admin/delete/next" var="url" />
                                        <a href='<c:out value="${url}" />'><sping:message key="index.next"/></a>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        </tfoot>
                    </table>
                </div>
    </fieldset>
</f:form>


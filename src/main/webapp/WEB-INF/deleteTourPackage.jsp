
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Admin</title>
</head>
<body>
<%-- Parameters --%>
<f:form modelAttribute="deleteTourPackage" action="tourPackage" method="get">
    <fieldset>
        <table cellspacing="15">

                <div align="center">
                    <table cellspacing="15" align="center">
                        <thead>
                        <div><f:errors path="id" /></div>
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
                        <c:set var="tourPageList" value="${sessionScope.tourPackagesForDelete}"/>
                        <c:forEach var="tour" items="${tourPageList.pageList}">
                            <tr>
                                <td>
                                    <f:radiobutton  path="id" value="${tour.id}"/>
                                    <f:input type="hidden" path="name" value="${tour.name}"/>
                                    <c:out value="${tour.name}"/>
                                </td>
                                <td>
                                    <f:input type="hidden" path="type" value="${tour.type}"/>
                                   <c:out value="${tour.type}"/>
                                </td>
                                <td>
                                    <f:input type="hidden" path="foodSystem" value="${tour.foodSystem}"/>
                                        <c:out value="${tour.foodSystem}"/>
                                </td>
                                <td>
                                    <f:input type="hidden" path="transport" value="${tour.transport}"/>
                                        <c:out value="${tour.transport}"/>
                                </td>
                                <td>
                                    <f:input type="hidden" path="days" value="${tour.days}"/>
                                        <c:out value="${tour.days}"/>
                                </td>
                                <td>
                                    <f:input type="hidden" path="price" value="${tour.price}"/>
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
                                        <span>Prev</span>
                                    </c:when>
                                    <c:otherwise>
                                        <c:url value="/admin/delete/prev" var="url" />
                                        <a href='<c:out value="${url}" />'>Prev</a>
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
                                        <span>Next</span>
                                    </c:when>
                                    <c:otherwise>
                                        <c:url value="/admin/delete/next" var="url" />
                                        <a href='<c:out value="${url}" />'>Next</a>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        </tfoot>
                    </table>
                </div>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Delete"/></td>
                <td></td>
            </tr>
        </table>
    </fieldset>
</f:form>
</body>
</html>

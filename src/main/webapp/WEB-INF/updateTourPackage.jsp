
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Admin</title>
</head>
<body>
    <%-- Parameters --%>
    <f:form modelAttribute="updateTourPackage" action="update" method="get">
        <fieldset>
            <table cellspacing="15">
                <tr>
                    <td>
                        <f:label path="type">Type</f:label>
                        <f:select path="type">
                            <f:options items="${sessionScope.types}"/>
                        </f:select>
                    </td>
                    <td>
                        <f:label path="foodSystem">Food System</f:label>
                        <f:select path="foodSystem">
                            <f:options items="${sessionScope.foodSystemList}"/>
                        </f:select>
                    </td>
                    <td>
                        <f:label path="transport">Transport</f:label>
                        <f:select path="transport">
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
                        <div><f:errors path="days" /></div>
                        <div>
                            <f:label path="days">Days</f:label>
                            <f:input type="number" path="days" maxlength="6" min="1"/>
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
                        <div><f:errors path="name" /></div>
                        <div>
                            <f:label path="name">Description</f:label>
                            <f:input type="text" path="name" maxlength="30"/>
                        </div>
                    </td>
                </tr>
                <tr>
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
                            <c:set var="tourPageList" value="${sessionScope.tourPackagesForUpdate}"/>
                            <c:forEach var="tour" items="${tourPageList.pageList}">
                                <tr>
                                    <td>
                                        <f:radiobutton  path="id" value="${tour.id}"/>${tour.name}
                                    </td>
                                    <td>${tour.type}</td>
                                    <td>${tour.foodSystem}</td>
                                    <td>${tour.transport}</td>
                                    <td>${tour.days}</td>
                                    <td>${tour.price}</td>
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
                                            <c:url value="/admin/update/tourPackage/prev" var="url" />
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
                                                <c:url value="/admin/update/tourPackage/${tagStatus.index}" var="url" />
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
                                            <c:url value="/admin/update/tourPackage/next" var="url" />
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
                    <td><input type="submit" value="Update"/></td>
                    <td></td>
                </tr>
            </table>
        </fieldset>
    </f:form>



</body>
</html>

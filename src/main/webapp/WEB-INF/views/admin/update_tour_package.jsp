<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Admin</title>
</head>
<body>
    <%-- Parameters --%>
    <%--@elvariable id="updateTourPackage" type="com.company.utils.ModelTourPackage"--%>
    <f:form modelAttribute="updateTourPackage" action="update" method="get">
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
                        <div><f:errors path="day" /></div>
                        <div>
                            <f:label path="day"><spring:message key="index.days"/></f:label>
                            <f:input type="number" path="day" maxlength="6" min="1"/>
                        </div>

                    </td>
                    <td>
                        <br/>
                        <div><f:errors path="price" /></div>
                        <div>
                            <f:label path="price"><spring:message key="index.price"/></f:label>
                            <f:input type="number" path="price" maxlength="10" min="1"/>
                        </div>
                    </td>
                    <td>
                        <br/>
                        <div><f:errors path="description" /></div>
                        <div>
                            <f:label path="description"><spring:message key="index.description"/></f:label>
                            <f:input type="text" path="description" maxlength="30"/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <div align="center">
                        <table cellspacing="15" align="center">
                            <thead>
                            <div><f:errors path="id" /></div>
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
                                            <span><spring:message key="index.prev"/></span>
                                        </c:when>
                                        <c:otherwise>
                                            <c:url value="/admin/update/tourPackage/prev" var="url" />
                                            <a href='<c:out value="${url}" />'><spring:message key="index.prev"/></a>
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
                                            <span><spring:message key="index.next"/></span>
                                        </c:when>
                                        <c:otherwise>
                                            <c:url value="/admin/update/tourPackage/next" var="url" />
                                            <a href='<c:out value="${url}" />'><spring:message key="index.next"/></a>
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

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
    <head>
        <meta charset="utf-8">
        <title>TourAgency</title>
    </head>
    <body>
        <h1 align="center">Tour Packages</h1>
        <%-- Приветствие --%>
        <div align="center">
            <div >
                <sec:authorize access="isAuthenticated()">
                    <h5 >Hello <sec:authentication property="principal.username" />!</h5>
                </sec:authorize>
            </div>
            <%-- Кнопка авторизации--%>
            <div align="right">
                <sec:authorize access="!isAuthenticated()">
                <f:form action="/authorization" method="get" align="center">
                    <input  type="submit" value="Log in"/>
                </f:form>
                </sec:authorize>
            </div>
            <%-- Кнопка Logout--%>
            <div align="right">
                <sec:authorize access="isAuthenticated()">
                <f:form action="/logout" method="get" align="center">
                    <input  type="submit" value="Log out"/>
                </f:form>
                </sec:authorize>
            </div>

            <%-- Кнопка Cabinet for admin--%>
            <div align="right">
                <sec:authorize access="hasRole('ADMIN')">
                    <f:form action="/admin" method="get" align="center">
                        <input  type="submit" value="Cabinet"/>
                    </f:form>
                </sec:authorize>
            </div>

            <%-- Кнопка Cabinet for user--%>
            <div align="right">
                <sec:authorize access="hasRole('USER')">
                    <f:form action="/user" method="get" align="center">
                        <input  type="submit" value="Cabinet"/>
                    </f:form>
                </sec:authorize>
            </div>
        </div>

        <c:set value="${sessionScope.tourPackages}" var="tourPageList" />
        <%-- Поле фильтра --%>
        <h2 align="center">Select tour package</h2>
        <div align="center">
        <f:form modelAttribute="selectsParameters" action="/select" method="get">
            <fieldset>
            <table cellspacing="15">
                <tr>
                    <td>
                        <p>Type</p>
                    <f:select path="valueOfType">
                        <f:option value="">--Select--</f:option>
                        <f:options items="${sessionScope.types}"/>
                    </f:select>
                    </td>
                    <td>
                        <p>Food System</p>
                        <f:select path="valueOfFoodSystem">
                            <f:option value="">--Select--</f:option>
                            <f:options items="${sessionScope.foodSystemList}"/>
                        </f:select>
                    </td>
                    <td>
                        <p>Transport</p>
                        <f:select path="valueOfTransport">
                            <f:option value="">--Select--</f:option>
                            <f:options items="${sessionScope.transports}"/>
                        </f:select>
                    </td>
                    <td>
                        <p>Hot</p>
                        <f:checkbox path="statusHot"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <p>Days between</p>
                        <br/>
                        <div><f:errors path="minDay" /></div>
                        <div>
                            <f:label path="minDay">minDay</f:label>
                            <f:input id="minD" type="number" path="minDay" maxlength="6" min="0"/>


                        </div>
                        <br/>
                        <div>
                            <f:label path="maxDay">maxDay</f:label>
                            <f:input id="maxD" type="number" path="maxDay" maxlength="6" min="0" />
                        </div>
                    </td>
                    <td>
                        <p>Price between</p>
                        <br/>
                        <div><f:errors path="minPrice" /></div>
                        <div>
                            <f:label path="minPrice">minPrice</f:label>
                            <f:input type="number" path="minPrice" maxlength="10" min="0"/>
                        </div>
                        <br/>

                        <div>
                            <f:label path="maxPrice">maxPrice</f:label>
                            <f:input type="number" path="maxPrice" maxlength="10" min="0"/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input type="submit" value="Select"/>
                    </td>
                    <td></td>
                </tr>
            </table>
            </fieldset>

        </f:form>
        </div>

        <%-- отображение туров--%>
        <div align="center">
            <table cellspacing="15" align="center">
                <thead>
                <tr style="color: darkorange">
                    <td><p>Description</p></td>
                    <td><p>Type</p></td>
                    <td><p>Food System</p></td>
                    <td><p>Transport</p></td>
                    <td><p>Days</p></td>
                    <td><p>Price</p></td>
                    <sec:authorize access="hasRole('ROLE_USER')">
                        <td><p>Discount price</p></td>
                    </sec:authorize>
                </tr>
                </thead>
                <tbody>

                 <f:form action="/user/order" modelAttribute="tourPackageForOrder" method="get" align="center">
                     <input type="hidden" name="discount" value="${discount}">
                     <div><f:errors path="id" /></div>
                 <c:forEach var="tour" items="${tourPageList.pageList}">
                    <tr>
                        <td>
                            <sec:authorize access="hasRole('ROLE_USER')">
                                <f:radiobutton  path="id" value="${tour.id}"/>
                            </sec:authorize>
                             ${tour.name}
                        </td>
                        <td>${tour.type}</td>
                        <td>${tour.foodSystem}</td>
                        <td>${tour.transport}</td>
                        <td>${tour.days}</td>
                        <td>${tour.price}</td>
                        <sec:authorize access="hasRole('ROLE_USER')">
                            <td>${tour.price*(1-discount/100)}</td>
                        </sec:authorize>
                    </tr>
                </c:forEach>
                     <tr>
                         <sec:authorize access="hasRole('ROLE_USER')">
                             <input type="submit" value="Buy"/>
                         </sec:authorize>
                     </tr>
                 </f:form>
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
                                <c:url value="/prev" var="url" />
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
                                    <c:url value="/${tagStatus.index}" var="url" />
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
                                <c:url value="/next" var="url" />
                                <a href='<c:out value="${url}" />'>Next</a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                </tfoot>
            </table>
        </div>
        <br/>

    </body>
</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div id="wrapper">
    <c:set value="${sessionScope.tourPackages}" var="tourPageList" />
    <%-- Field filter --%>
    <h2 class="textSelectedParameter">Select tour package</h2>
    <div id="selectField" align="center">
        <%--@elvariable id="selectsParameters" type="com.company.utils.ParametersSelectedForTourPackages"--%>
        <f:form modelAttribute="selectsParameters" action="/select" method="get">
            <fieldset>
                <table cellspacing="15">
                    <tr>
                        <td>
                            <p>Type</p>
                            <f:select path="idOfType">
                                <f:option value="">--Select--</f:option>
                                <f:options itemValue="id" items="${sessionScope.types}"/>
                            </f:select>
                        </td>
                        <td>
                            <p>Food System</p>
                            <f:select path="idOfFoodSystem">
                                <f:option value="">--Select--</f:option>
                                <f:options itemValue="id" items="${sessionScope.foodSystemList}"/>
                            </f:select>
                        </td>
                        <td>
                            <p>Transport</p>
                            <f:select path="idOfTransport">
                                <f:option value="">--Select--</f:option>
                                <f:options itemValue="id" items="${sessionScope.transports}"/>
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
                            <div><f:errors cssClass="errorMessage" path="minDay" /></div>
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
                            <div><f:errors cssClass="errorMessage" path="minPrice" /></div>
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

    <%-- view of tour packages--%>
    <div align="center">
        <table cellspacing="15" align="center">
            <thead>
            <tr class="headOfListTours">
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

            <%--@elvariable id="tourPackageForOrder" type="com.company.model.domain.tourPackage.TourPackage"--%>
            <f:form action="/user/order" modelAttribute="tourPackageForOrder" method="get" align="center">
                <div><f:errors cssClass="errorMessage" path="id" /></div>
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
                            <td>${tour.price*(1-sessionScope.discount/100)}</td>
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
                        <%-- link previous page --%>
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
                            <%-- number of page --%>
                            <c:when test="${(tourPageList.page + 1) == tagStatus.index}">
                                <span>${tagStatus.index}</span>
                            </c:when>
                            <c:otherwise>
                                <c:url value="/${tagStatus.index}" var="url" />
                                <a href='<c:out value="${url}" />'>${tagStatus.index}</a>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <%-- link next page --%>
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
</div>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<html>
    <head>
        <meta charset="utf-8">
        <title>TourAgency</title>
    </head>
    <body>
        <h1 align="center">Tour Packages</h1>

        <%-- Форма авторизации--%>
        <div align="right">
            <form modelAttribute="selectsParameters" action="select" method="get">
                <fieldset>
                    <table cellspacing="0">
                        <tr>
                            <th><label for="email">Email</label></th>
                            <td><input id="email"
                                       name="j_username"
                                       type="text" /> <!-- Поле ввода имени пользователя -->
                            </td>
                        </tr>
                        <tr>
                            <th><label for="password">Password</label></th>
                            <td><input id="password"
                                       name="j_password"
                                       type="password" /> <!-- Поле ввода пароля -->
                            </td>
                        </tr>
                        <tr>
                            <th></th>
                            <td><input id="remember_me"
                                       name="_spring_security_remember_me"
                                       type="checkbox"/> <!-- Флажок "запомнить меня" -->
                                <label for="remember_me"
                                       class="inline">Remember me</label></td>
                        </tr>
                        <tr>
                            <th></th>
                            <td><input name="commit" type="submit" value="Sign In" /></td>
                        </tr>
                    </table>
                </fieldset>
            </form>
        </div>

        <%-- Поле фильтра --%>
        <h2 align="center">Select tour package</h2>
        <c:set value="${sessionScope.tourPackages}" var="tourPageList" />
        <div align="center">
        <f:form modelAttribute="selectsParameters" action="select" method="get">
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
                        <div>
                            <f:input path="minDay" maxlength="6"/>
                            <f:errors path="minDay"/>
                        </div>
                        <br/>
                        <div>
                            <f:input path="maxDay" maxlength="6"/>
                            <f:errors path="maxDay"/>
                        </div>
                    </td>
                    <td>
                        <p>Price between</p>
                        <br/>
                        <div><f:errors path="minPrice"/></div>
                        <div>
                            <f:input path="minPrice" maxlength="10"/>
                        </div>
                        <br/>
                        <div><f:errors path="maxPrice"/></div>
                        <div>
                            <f:input path="maxPrice" maxlength="10"/>
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
                <tr style="color: darkorange">
                    <td><p>Description</p></td>
                    <td><p>Food System</p></td>
                    <td><p>Transport</p></td>
                </tr>
                <c:forEach var="tour" items="${tourPageList.pageList}">
                    <tr>
                      <td>${tour.name}</td>
                      <td>${tour.foodSystem}</td>
                      <td>${tour.transport}</td>
                    </tr>
                </c:forEach>
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
                    <td></td>
                </tr>
             </table>
        </div>
        <br/>

        <%-- отображение туров--%>
        <div align="center">
            <table cellspacing="15" align="center">
                <tr style="color: darkorange">
                    <td><p>Description</p></td>
                    <td><p>Food System</p></td>
                    <td><p>Transport</p></td>
                </tr>
                <c:forEach var="tour" items="${tourPageList.pageList}">
                    <tr>
                        <td>${tour.name}</td>
                        <td>${tour.foodSystem}</td>
                        <td>${tour.transport}</td>
                    </tr>
                </c:forEach>
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
                    <td></td>
                </tr>
            </table>
        </div>
        <br/>

    </body>
</html>
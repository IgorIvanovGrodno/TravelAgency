<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>

    <%--@elvariable id="userWithUpdateDiscount" type="User"--%>
    <f:form cssClass="form" modelAttribute="userWithUpdateDiscount" action="set" method="post">
        <fieldset>
            <table cellspacing="15">
                <tr>
                    <td>
                        <f:errors cssClass="errorMessage" path="discount" />
                        <f:label path="discount"><spring:message key="set.discount"/></f:label>
                        <f:input type="number" path="discount" maxlength="6" min="0"/>
                    </td>
                    <td>
                        <input type="submit" value="Set"/>
                    </td>
                </tr>
            </table>

            <div align="center">
                <table cellspacing="15" align="center">
                    <thead>
                    <tr style="color: darkorange">
                        <td><p><spring:message key="user.name"/></p></td>
                        <td><p><spring:message key="user.discount"/></p></td>
                    </tr>
                    <tr>
                        <div>
                            <f:errors cssClass="errorMessage" path="id" />
                        </div>
                    </tr>
                    </thead>
                    <tbody>
                    <c:set var="users" value="${sessionScope.usersForSetDiscount}"/>
                    <c:forEach var="user" items="${users.pageList}">
                        <tr>
                            <td>
                                <f:radiobutton  path="id" value="${user.id}"/>${user.authorization.login}
                            </td>
                            <td>${user.discount}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                    <tfoot>
                    <tr align="center">
                        <td></td>
                        <td>
                            <c:choose>

                                <c:when test="${users.firstPage}">
                                    <span><spring:message key="index.prev"/></span>
                                </c:when>
                                <c:otherwise>
                                    <c:url value="/admin/set/discount/prev" var="url" />
                                    <a href='<c:out value="${url}" />'><spring:message key="index.prev"/></a>
                                </c:otherwise>
                            </c:choose>
                            <c:forEach begin="1" end="${users.pageCount}" step="1"  varStatus="tagStatus">
                                <c:choose>

                                    <c:when test="${(users.page + 1) == tagStatus.index}">
                                        <span>${tagStatus.index}</span>
                                    </c:when>
                                    <c:otherwise>
                                        <c:url value="/admin/set/discount/${tagStatus.index}" var="url" />
                                        <a href='<c:out value="${url}" />'>${tagStatus.index}</a>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>

                            <c:choose>
                                <c:when test="${users.lastPage}">
                                    <span><spring:message key="index.next"/></span>
                                </c:when>
                                <c:otherwise>
                                    <c:url value="/admin/set/discount/next" var="url" />
                                    <a href='<c:out value="${url}" />'><spring:message key="index.next"/></a>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    </tfoot>
                </table>
            </div>
        </fieldset>
    </f:form>


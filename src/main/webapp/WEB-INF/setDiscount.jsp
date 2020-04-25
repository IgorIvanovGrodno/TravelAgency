
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
    <title>Admin</title>
</head>
<body>
    <f:form modelAttribute="userWithUpdateDiscount" action="set" method="get">
        <fieldset>

            <table cellspacing="15">
                <tr>
                    <td>
                        <f:errors path="discount" />
                        <f:label path="discount">Set discount</f:label>
                        <f:input type="number" path="discount" maxlength="6" min="0"/>
                    </td>
                    <td>
                        <input type="submit" value="Set"/>
                    </td>
                </tr>
            </table>

            <%-- отображение users--%>
            <div align="center">
                <table cellspacing="15" align="center">
                    <thead>
                    <tr style="color: darkorange">
                        <td><p>name</p></td>
                        <td><p>discount</p></td>
                    </tr>
                    <tr>
                        <div>
                            <f:errors path="id" />
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
                                <%-- кнопка предыдущая страница --%>
                                <c:when test="${users.firstPage}">
                                    <span>Prev</span>
                                </c:when>
                                <c:otherwise>
                                    <c:url value="admin/set/discount/prev" var="url" />
                                    <a href='<c:out value="${url}" />'>Prev</a>
                                </c:otherwise>
                            </c:choose>
                            <c:forEach begin="1" end="${users.pageCount}" step="1"  varStatus="tagStatus">
                                <c:choose>
                                    <%-- Нумерация страниц --%>
                                    <c:when test="${(users.page + 1) == tagStatus.index}">
                                        <span>${tagStatus.index}</span>
                                    </c:when>
                                    <c:otherwise>
                                        <c:url value="admin/set/discount/${tagStatus.index}" var="url" />
                                        <a href='<c:out value="${url}" />'>${tagStatus.index}</a>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                            <%-- кнопка следующая страница --%>
                            <c:choose>
                                <c:when test="${users.lastPage}">
                                    <span>Next</span>
                                </c:when>
                                <c:otherwise>
                                    <c:url value="admin/set/discount/next" var="url" />
                                    <a href='<c:out value="${url}" />'>Next</a>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    </tfoot>
                </table>
            </div>
        </fieldset>
    </f:form>
</body>
</html>

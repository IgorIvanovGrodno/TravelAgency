<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
    <body>
        <h2>Tour Packages</h2>
        <ol>
            <c:forEach var="spittle" items="${tourPackage}">
                <td><c:out value="${spittle.getName()}" /></td>
            </c:forEach>
        </ol>
    </body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: Игорь
  Date: 17.03.2020
  Time: 22:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<html>
    <head>
        <title>Admin</title>
    </head>
    <body>
        <%-- Button creating tour packages--%>
        <div align="right">
            <f:form action="admin/create/" method="get" align="center">
                <input  type="submit" value="Create tour package"/>
            </f:form>
        </div>

        <%-- Button updating tour packages--%>
        <div align="right">
            <f:form action="admin/update/tourPackage/" method="get" align="center">
                <input  type="submit" value="Update tour package"/>
            </f:form>
        </div>

        <%-- Button updating tour packages--%>
        <div align="right">
            <f:form action="admin/delete/" method="get" align="center">
                <input  type="submit" value="Delete tour package"/>
            </f:form>
        </div>

    </body>
</html>

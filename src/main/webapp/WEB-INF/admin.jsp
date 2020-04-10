<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<html>
    <head>
        <title>Admin</title>
    </head>
    <body>
        <%-- Button creating tour packages--%>
        <div align="right">
            <f:form action="/admin/create/" method="get" align="center">
                <input  type="submit" value="Create tour package"/>
            </f:form>
        </div>

        <%-- Button updating tour packages--%>
        <div align="right">
            <f:form action="/admin/update/tourPackage/" method="get" align="center">
                <input  type="submit" value="Update tour package"/>
            </f:form>
        </div>

        <%-- Button deletting tour packages--%>
        <div align="right">
            <f:form action="/admin/delete/" method="get" align="center">
                <input  type="submit" value="Delete tour package"/>
            </f:form>
        </div>
        <%-- Button setting discount--%>
        <div align="right">
            <f:form action="/admin/set/discount/" method="get" align="center">
                <input  type="submit" value="Set discount"/>
            </f:form>
        </div>

        <%-- Button return to index --%>
        <div align="right">
            <f:form action="/" method="get" align="center">
                <input  type="submit" value="Return to main page"/>
            </f:form>
        </div>
    </body>
</html>

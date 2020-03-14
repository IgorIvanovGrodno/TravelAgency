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

        <h2 align="center">Select tour package</h2>
        <div align="center">
        <f:form modelAttribute="foodSystem" action="select" method="get">
            <table>
                <tr>
                    <td>
                        <label>Type</label>
                    <f:select path="valueOfType">
                        <f:option value="NONE">--Select--</f:option>
                        <f:options items="${types}"/>
                    </f:select>
                    </td>
                    <td>
                        <label>Food System</label>
                        <f:select path="valueOfFoodSystem">
                            <f:option value="NONE">--Select--</f:option>
                            <f:options items="${foodSystemList}"/>
                        </f:select>
                    </td>
                    <td>
                        <label>Transport</label>
                        <f:select path="valueOfTransport">
                            <f:option value="NONE">--Select--</f:option>
                            <f:options items="${transports}"/>
                        </f:select>
                    </td>
                    <td>
                        <labe>Hot</labe>
                        <f:checkbox path="statusHot"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>Days between</label>
                        <div>
                            <f:input path="minDay"/>
                        </div>
                        <div>
                            <f:input path="maxDay"/>
                        </div>
                    </td>
                    <td>
                        <label>Price between</label>
                        <div>
                            <f:input path="minPrice"/>
                        </div>
                        <div>
                            <f:input path="maxPrice"/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <input type="submit" value="Select"/>
                </tr>
            </table>

        </f:form>
        </div>


        <table cellspacing="15" align="center">
            <tr style="color: darkorange">
                <td><p>Description</p></td>
                <td><p>Food System</p></td>
                <td><p>Transport</p></td>
            </tr>
            <c:forEach var="tours" items="${tourPackages}">
                <tr>
                  <td><c:out value="${tours.name}" /></td>
                  <td><c:out value="${tours.foodSystem}"></c:out></td>
                  <td><c:out value="${tours.transport}"></c:out></td>
                </tr>
            </c:forEach>
         </table>

    </body>
</html>

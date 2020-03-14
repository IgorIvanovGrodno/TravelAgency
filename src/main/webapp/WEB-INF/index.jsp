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
        <c:set value="${sessionScope.tourPackages}" var="tourPageList" />
        <div align="center">
        <f:form modelAttribute="foodSystem" action="select" method="get">
            <table cellspacing="15">
                <tr>
                    <td>
                        <label>Type</label>
                    <f:select path="valueOfType">
                        <f:option value="">--Select--</f:option>
                        <f:options items="${types}"/>
                    </f:select>
                    </td>
                    <td>
                        <label>Food System</label>
                        <f:select path="valueOfFoodSystem">
                            <f:option value="">--Select--</f:option>
                            <f:options items="${foodSystemList}"/>
                        </f:select>
                    </td>
                    <td>
                        <label>Transport</label>
                        <f:select path="valueOfTransport">
                            <f:option value="">--Select--</f:option>
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
                            <f:input path="minDay" maxlength="6"/>
                        </div>
                        <div>
                            <f:input path="maxDay" maxlength="6"/>
                        </div>
                    </td>
                    <td>
                        <label>Price between</label>
                        <div>
                            <f:input path="minPrice" maxlength="10"/>
                        </div>
                        <div>
                            <f:input path="maxPrice" maxlength="10"/>
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
            <c:forEach var="tours" items="${tourPageList.pageList}">
                <tr>
                  <td><c:out value="${tours.name}" /></td>
                  <td><c:out value="${tours.foodSystem}"></c:out></td>
                  <td><c:out value="${tours.transport}"></c:out></td>
                </tr>
            </c:forEach>
         </table>

    </body>
</html>

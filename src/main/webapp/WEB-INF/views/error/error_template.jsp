<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html xmlns:jsp="http://java.sun.com/JSP/Page">

<head>
    <meta charset="UTF-8">
    <title>Error</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/CSS/footer.css" />" />
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/CSS/styles.css" />" />
</head>
<body>
    <div class="errorWrapper" align="center">
        <tiles:insertAttribute name="body"/>
    </div>
    <div>
        <tiles:insertAttribute name="footer"/>
    </div>
</body>
</html>

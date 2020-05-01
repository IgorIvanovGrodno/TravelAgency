<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html xmlns:jsp="http://java.sun.com/JSP/Page">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Tour Agency</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/CSS/button.css" />" />
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/CSS/footer.css" />" />
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/CSS/styles.css" />" />
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/CSS/header.css" />" />
</head>

<body>
<header>
    <tiles:insertAttribute name="header"/>
</header>
<div>
    <aside>
        <tiles:insertAttribute name="sidebar"/>
    </aside>
    <div>
        <tiles:insertAttribute name="body"/>
    </div>
</div>
<footer>
    <tiles:insertAttribute name="footer"/>
</footer>
</body>

</html>

<%@ page contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/CSS/button.css" />" />
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/CSS/footer.css" />" />
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/CSS/styles.css" />" />
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/CSS/sidebar.css" />" />
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/CSS/header.css" />" />
    </head>
    <body class="bodyAdmin">
        <div class="adminWrapper">
            <header>
                <tiles:insertAttribute name="header"/>
            </header>
            <div>
                <aside class="sidebar">
                    <tiles:insertAttribute name="sidebar"/>
                </aside>
                <div class="adminContent" align="center">
                    <tiles:insertAttribute name="body"/>
                </div>
            </div>
            <footer>
                <tiles:insertAttribute name="footer"/>
            </footer>
        </div>
    </body>
</html>

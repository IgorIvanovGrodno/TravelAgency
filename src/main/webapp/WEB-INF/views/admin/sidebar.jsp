<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div>
    <nav>
        <ul class="aside-menu">
            <li><a href="/admin/create/"><spring:message key="create.tour.package"/></a></li>
            <li><a href="/admin/update/tourPackage/"><spring:message key="update.tour.package"/></a></li>
            <li><a href="/admin/delete/"><spring:message key="delete.tour.package"/></a></li>
            <li><a href="/admin/set/discount/"><spring:message key="set.discount"/></a></li>
            <li><a href="/admin/orders/"><spring:message key="change.status.order"/></a></li>
            <li><a href="/"><spring:message key="main.page"/></a></li>
        </ul>
    </nav>
</div>

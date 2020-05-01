<%@ page contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="header">

        <div class="lang">
            <a class="headerLocale" href="/?languageVar=en">English</a>
            <a class="headerLocale" href="/?languageVar=ru">Русский</a>
        </div>
        <h1 class="headerText"><spring:message key="header.tour.agency"/></h1>
        <sec:authorize access="isAuthenticated()">
            <h3 class="hello"><spring:message key="header.hello"/> <sec:authentication property="principal.username" />!</h3>
        </sec:authorize>

        <div align="center">

            <ul class="top-menu">
                <sec:authorize access="!isAuthenticated()">
                    <li class="blockForButton">
                            <a href="/authorization" class="buttonHeader"><spring:message key="header.log.in"/></a>
                    </li>
                </sec:authorize>

                <sec:authorize access="!isAuthenticated()">
                    <li class="blockForButton">
                            <a href="/registration" class="buttonHeader"><spring:message key="header.registration"/></a>
                    </li>
                </sec:authorize>

                <sec:authorize access="isAuthenticated()">
                    <li class="blockForButton">
                            <a href="/logout" class="buttonHeader"><spring:message key="header.log.out"/></a>
                    </li>
                </sec:authorize>

                <sec:authorize access="hasRole('ADMIN')">
                    <li class="blockForButton">
                            <a href="/admin" class="buttonHeader"><spring:message key="header.cabinet"/></a>
                    </li>
                </sec:authorize>

                <sec:authorize access="hasRole('USER')">
                    <li class="blockForButton">
                            <a href="/user" class="buttonHeader"><spring:message key="header.cabinet"/></a>
                    </li>
                </sec:authorize>
            </ul>

        </div>
</div>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="header">

        <h1 class="headerText">Tour Packages</h1>
        <sec:authorize access="isAuthenticated()">
            <h3 class="hello">Hello <sec:authentication property="principal.username" />!</h3>
        </sec:authorize>

        <div align="center">

            <ul class="top-menu">
                <sec:authorize access="!isAuthenticated()">
                    <li class="blockForButton">
                            <a href="/authorization" class="buttonHeader">LOG IN</a>
                    </li>
                </sec:authorize>

                <sec:authorize access="!isAuthenticated()">
                    <li class="blockForButton">
                            <a href="/registration" class="buttonHeader">REGISTRATION</a>
                    </li>
                </sec:authorize>

                <sec:authorize access="isAuthenticated()">
                    <li class="blockForButton">
                            <a href="/logout" class="buttonHeader">LOG OUT</a>
                    </li>
                </sec:authorize>

                <sec:authorize access="hasRole('ADMIN')">
                    <li class="blockForButton">
                            <a href="/admin" class="buttonHeader">CABINET</a>
                    </li>
                </sec:authorize>

                <sec:authorize access="hasRole('USER')">
                    <li class="blockForButton">
                            <a href="/user" class="buttonHeader">CABINET</a>
                    </li>
                </sec:authorize>
            </ul>

        </div>
</div>

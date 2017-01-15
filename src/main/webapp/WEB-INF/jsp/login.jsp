<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page session="true"%>
<%@ page import="ru.inno.dao.UserDao" %>
<%@ page import="ru.inno.dao.UserDaoImpl" %>
<%@ page import="ru.inno.dao.DBConnection" %>
<%@ page import="ru.inno.pojo.User" %>
<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 12/25/16
  Time: 12:49 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <%--<spring:url value="/resource/js/mdl/material.min.js" var="material_min_js" />--%>
    <link rel="stylesheet" href="/js/mdl/material.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <%--<script src="${pageContext.request.contextPath}/WEB-INF/js/mdl/material.min.js"></script>--%>
    <%--<script src="<c:url value="/WEB-INF/js/mdl/material.min.js"/>"></script>--%>
    <%--<script src="<c:url value="/js/mdl/material.min.js" />"></script>  <!-- Работает!!! -->--%>
    <%--<script src="${pageContext.request.contextPath}/resource/js/mdl/material.min.js"></script>--%>
    <%--<script type="text/javascript" src="${pageContext.request.contextPath}/js/mdl/material.min.js"></script> <!-- Работает!!! -->--%>
    <script type="text/javascript" src="/js/mdl/material.min.js"></script> <!-- Работает!!! -->
    <%--<script src="${material_min_js}"></script>--%>
    <%--<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">--%>
    <%--<link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">--%>
    <%--<script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>--%>
    <title>Title</title>

    <style>
        #login-box {
            width: 300px;
            padding: 20px;
            margin: 100px auto;
            background: #fff;
            -webkit-border-radius: 2px;
            -moz-border-radius: 2px;
            border: 1px solid #000;
        }
    </style>
</head>
<body onload='document.loginForm.username.focus();'>
<div id="login-box">
${pageContext.request.userPrincipal.name}
<sec:authorize access="isAuthenticated()">
    <c:redirect url="/welcome"/>
</sec:authorize>

<c:if test="${param.error != null}">
    <p>Invalid username / password</p>
</c:if>
<%--<c:url var="loginUrl" value="/login"/>--%>

<c:if test="${not empty error}">
    <div class="error">${error}</div>
</c:if>
<c:if test="${not empty msg}">
    <div class="msg">${msg}</div>
</c:if>
<c:if test="${not empty logout}">
    <div class="msg">You logged out.</div>
</c:if>

<%--<form name='loginForm' action="<c:url value='/login' />" method="post">--%>

    <%--<p><label for="username">User:</label></p>--%>
    <%--<input type="text" id="username" name="username"/>--%>
    <%--&lt;%&ndash;<input type="hidden" id="username" name="username" value="bro"/>&ndash;%&gt;--%>

    <%--<p><label for="password">Password:</label></p>--%>
    <%--<input type="password" id="password" name="password">--%>
    <%--&lt;%&ndash;<input type="hidden" id="password" name="password" value="123456">&ndash;%&gt;--%>

    <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />--%>
    <%--&lt;%&ndash;<input type="hidden" name="login" value="bro" />&ndash;%&gt;--%>
    <%--<div>--%>
        <%--<input name="submit" type="submit"/>--%>
    <%--</div>--%>
<%--</form>--%>



    <form name='loginForm' action="<c:url value='/login' />" method="post">
        <%--<input type="text" placeholder="Введите логин" name="login">--%>
        <div class="mdl-textfield mdl-js-textfield">
            <input class="mdl-textfield__input" type="text" id="username" name="username">
            <label class="mdl-textfield__label" for="username">Введите логин</label>
        </div>
        <br>

        <%--<input type="password" placeholder="Введите пароль" name="password">--%>
            <div class="mdl-textfield mdl-js-textfield">
                <input class="mdl-textfield__input" type="password" id="password" name="password">
                <label class="mdl-textfield__label" for="password">Введите логин</label>
            </div>
            <br>
        <button class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored mdl-color--teal-500" type="submit" name="confirm">Войти</button>

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <%--<input type="hidden" name="username" value="bro" />--%>

    </form>

    <form action="/register" method="get">

        <button class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored mdl-color--teal-500" type="submit" >Зарегистрироваться</button>

    </form>

</div>
</body>
</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
    <script src="<c:url value="/js/mdl/material.min.js" />"></script>  <!-- Работает!!! -->
    <%--<script src="${pageContext.request.contextPath}/resource/js/mdl/material.min.js"></script>--%>
    <%--<script type="text/javascript" src="${pageContext.request.contextPath}/js/mdl/material.min.js"></script> <!-- Работает!!! -->--%>
    <%--<script src="${material_min_js}"></script>--%>
    <%--<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">--%>
    <%--<link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">--%>
<%--<script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>--%>
    <title>Title</title>
</head>
<body>
    <form action="/autority" method="post">
        <%--<input type="text" placeholder="Введите логин" name="login">--%>
        <div class="mdl-textfield mdl-js-textfield">
            <input class="mdl-textfield__input" type="text" id="login" name="login">
            <label class="mdl-textfield__label" for="login">Введите логин</label>
        </div>
        <br>

        <%--<input type="password" placeholder="Введите пароль" name="password">--%>
            <div class="mdl-textfield mdl-js-textfield">
                <input class="mdl-textfield__input" type="password" id="password" name="password">
                <label class="mdl-textfield__label" for="password">Введите логин</label>
            </div>
            <br>
        <button class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored mdl-color--teal-500" type="submit" name="confirm">Войти</button>
    </form>

    <form action="/register" method="get">

        <button class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored mdl-color--teal-500" type="submit" >Зарегистрироваться</button>

    </form>

</body>
</html>
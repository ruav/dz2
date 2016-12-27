<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<html >
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <form action="/autority" method="post">
        <input type="text" placeholder="Введите логин" name="login">
        <input type="password" placeholder="Введите пароль" name="password">

        <button type="submit" name="confirm">Войти</button>
    </form>

    <form action="/register" method="get">

        <button type="submit" >Зарегистрироваться</button>

    </form>

</body>
</html>
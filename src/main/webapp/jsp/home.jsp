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
<html>
<head>
    <title>Информационная система "Электронная библиотека им. А.В. Руднева"</title>
</head>
<body>


<c:set var="userId" scope="session" value="${sessionScope.userId}"/>
<c:if test="${not empty sessionScope.userId}">

    <h3>Вы зашли, как ${sessionScope.userId}</h3>
    <form action="/autority" method="post">
        <button type="submit" value="exit" name="exit">Выйти из текущего пользователя</button>
    </form>

    <table>
        <tr>
            <td>
                <output><a href="/users">Посмотреть список пользователей</a></output>
            </td>
        </tr>
        <tr>
            <td>
                <output><a href="/books">Посмотреть список литературы</a></output>
            </td>
        </tr>

    </table>
</c:if>
<%--<c:if test="${empty userId}">--%>
    <%--&lt;%&ndash;<div>Войдите в систему</div>&ndash;%&gt;--%>
    <%--<c:redirect url="/login"/>--%>
<%--</c:if>--%>

</body>
</html>

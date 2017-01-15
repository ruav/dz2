<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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

    <link rel="stylesheet" href="/js/mdl/material.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <script type="text/javascript" src="/js/mdl/material.min.js"></script>

    <style>
        a.block_button {
            display: inline-block;
            font-size: 12px;
            color: rgb(205, 216, 228);
            text-decoration: none;
            padding: .2em .8em;
            outline: none;
            border-right: 1px solid rgba(13, 20, 27, .5);
            border-top: 1px solid rgba(270, 278, 287, .01);
            background-color: rgb(64, 73, 82);
            background-image: radial-gradient(1px 60% at 0% 50%, rgba(255, 255, 255, .3), transparent),
            radial-gradient(1px 60% at 100% 50%, rgba(255, 255, 255, .3), transparent),
            linear-gradient(rgb(64, 73, 82), rgb(72, 81, 90));
        }

        a.block_button:hover {
            background-image: radial-gradient(1px 60% at 0% 50%, rgba(255, 255, 255, .3), transparent),
            radial-gradient(1px 60% at 100% 50%, rgba(255, 255, 255, .3), transparent),
            linear-gradient(rgb(51, 60, 67), rgb(58, 65, 72));
        }

        a.block_button:focus {
            color: rgb(245, 247, 250);
            border-top: 1px solid rgb(67, 111, 136);
            background-image: linear-gradient(rgb(46, 95, 122), rgb(36, 68, 92));
        }

        a.block_button:focus:hover {
            border-top: 1px solid rgb(49, 87, 107);
            background-image: linear-gradient(rgb(33, 77, 98), rgb(29, 57, 77));
        }
        a.list:link {
            color: #1824ff;
        }

        /* visited link */
        a.list:visited {
            color: green;
        }

        /* mouse over link */
        a.list:hover {
            color: #7f0d08;
        }

        /* selected link */
        a.list:active {
            color: blue;
        }

    </style>



    <title>Информационная система "Электронная библиотека им. А.В. Руднева"</title>
</head>
<body>


<%--<c:set var="userId" scope="session" value="${sessionScope.userId}"/>--%>

<sec:authorize access="hasRole('ROLE_USER')">
    <c:redirect url="/books"/>
</sec:authorize>


<%--<c:if test="${sessionScope.admin eq false}">
    <c:redirect url="/books"/>
</c:if>--%>


<sec:authorize access="hasRole('ROLE_ADMIN')">
    <h3>Вы зашли, как ${pageContext.request.userPrincipal.name}</h3>
    <%--<form action="/autority" method="post">--%>
        <%--<button type="submit" value="exit" name="exit">Выйти из текущего пользователя</button>--%>
    <%--</form>--%>

    <c:url var="logoutUrl" value="/logout" />
    <form action="${logoutUrl}" method="get" id="logoutForm" style="display:table-cell;vertical-align:center;">
        <a class="block_button" tabindex="1" href="javascript:document.getElementById('logoutForm').submit()">Выйти из текущего пользователя</a>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </form>
    <table>
        <tr>
            <td>
                <output><a class="list" href="/users">Посмотреть список пользователей</a></output>
            </td>
        </tr>
        <tr>
            <td>
                <output><a class="list" href="/books">Посмотреть список литературы</a></output>
            </td>
        </tr>

    </table>
</sec:authorize>



<%--<c:if test="${not empty sessionScope.userName}">

    <h3>Вы зашли, как ${sessionScope.userName}</h3>
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
</c:if>--%>

<%--<sec:authorize access="!hasRole('ROLE_ADMIN')   ">--%>
    <%--<c:redirect url="/books"/>--%>
<%--</sec:authorize>--%>

<sec:authorize access="isAnonymous()">
    <c:redirect url="/login"/>
</sec:authorize>

<%--<c:if test="${empty userId}">
    &lt;%&ndash;<div>Войдите в систему</div>&ndash;%&gt;
    <c:redirect url="/login"/>
</c:if>--%>

</body>
</html>

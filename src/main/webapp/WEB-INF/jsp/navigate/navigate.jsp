<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/js/mdl/material.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <script type="text/javascript" src="/js/mdl/material.min.js"></script>
    <%--<script type="text/javascript" src="${pageContext.request.contextPath}/js/mdl/material.min.js"></script>--%>

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
    </style>
    <title>Title</title>
</head>
<body>
<fieldset>
    <%--${requestScope['javax.servlet.forward.request_uri']}--%>
    <%--<c:if test="${not fn:contains(requestScope['javax.servlet.forward.request_uri'], 'users/user/')}"><a href="/users/user/${sessionScope.userId}" >О пользователе</a>--%>
    <%--TRUE</c:if>--%>
    <div>
        <table border="0">
            <tr>
                <td>
        Вы зашли, как

        <c:if test="${not fn:contains(requestScope['javax.servlet.forward.request_uri'], 'users/user/')}">
        <a class="block_button" tabindex="0" href="/users/user/${sessionScope.userId}">${pageContext.request.userPrincipal.name}</a>
        </c:if>
        <%--<c:if test="${not fn:contains(requestScope['javax.servlet.forward.request_uri'], 'users/user/')}">--%>
            <%--<a class="block_button" tabindex="0" href="/users/user/${sessionScope.userId}">${sessionScope.userName}</a>--%>
        <%--</c:if>--%>
        <c:if test="${fn:contains(requestScope['javax.servlet.forward.request_uri'], 'users/user/')}">
                ${sessionScope.userName}
        </c:if>
        <td>

                <c:url var="logoutUrl" value="/logout" />
                <form action="${logoutUrl}" method="get" id="logoutForm" style="display:table-cell;vertical-align:center;">
                    <a class="block_button" tabindex="1" href="javascript:document.getElementById('logoutForm').submit()">Выйти</a>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                </form>

                <%--<form action="/autority" method="post" id="logoutForm">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    <a class="block_button" tabindex="1" href="javascript:document.getElementById('autority').submit()">Выйти2</a>
                    <input type="hidden" name="exit" value="true">
                </form>--%>
<%--
        <form id="autority" action="/autority" method="post" style="display:table-cell;vertical-align:center;">
            <a class="block_button" tabindex="1" href="javascript:document.getElementById('autority').submit()">Выйти</a>
            &lt;%&ndash;<a class="block_button" tabindex="1" href="javascript:;"&ndash;%&gt;
            &lt;%&ndash;onclick="document.getElementById('autority').submit();">Выйти</a>&ndash;%&gt;
            <input type="hidden" name="exit" value="true">
        &lt;%&ndash;<button class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored mdl-color--teal-500"&ndash;%&gt;
            &lt;%&ndash;type="submit" name="exit">Выйти&ndash;%&gt;
            &lt;%&ndash;</button>&ndash;%&gt;
            &lt;%&ndash;<button type="submit" value="exit" name="exit">Выйти</button>&ndash;%&gt;
        </form>--%>
                </td>
            </tr>
        </table>
    </div>
        <a class="block_button" tabindex="1" href="/books">Перейти к списку книг</a>
    <a class="block_button" tabindex="1" href="/users">Перейти к списку пользователей</a>

    <%--<form>--%>

    <%--<input class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored mdl-color--teal-500" type="button"--%>
    <%--value="Перейти к списку книг" onClick='location.href="/books"'>--%>
    <%--<input class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored mdl-color--teal-500" type="button"--%>
    <%--value="Перейти к списку пользователей" onClick='location.href="/users"'>--%>
    <%--</form>--%>
</fieldset>


</body>
</html>
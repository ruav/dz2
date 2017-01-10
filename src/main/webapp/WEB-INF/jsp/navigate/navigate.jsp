<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/js/mdl/material.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <script src="<c:url value="/js/mdl/material.min.js" />"></script>

    <title>Title</title>
</head>
<body>
    <fieldset>
        <form action="/autority" method="post">
            <p>Вы зашли, как ${sessionScope.userId}
            <button class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored mdl-color--teal-500" type="submit" name="exit">Выйти</button>
            <%--<button type="submit" value="exit" name="exit">Выйти</button>--%>
            </p>
        </form>
        <form>

            <input class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored mdl-color--teal-500" type="button" value="Перейти к списку книг" onClick='location.href="/books"'>
            <input class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored mdl-color--teal-500" type="button" value="Перейти к списку пользователей" onClick='location.href="/users"'>
        </form>
    </fieldset>


</body>
</html>
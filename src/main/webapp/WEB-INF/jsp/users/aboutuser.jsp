<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%--
  Created by IntelliJ IDEA.
  User: student
  Date: 12/25/16
  Time: 5:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Регистрация</title>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Title</title>
    <link rel="stylesheet" href="/js/mdl/material.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <script src="<c:url value="/js/mdl/material.min.js" />"></script>

    <%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>--%>
    <%--<script src="js/2.5.3-crypto-md5.js"></script>--%>
    <script>

        function submitEdit(){
            var pass = document.getElementById("password").value;
            var cpass = document.getElementById("cpassword").value;

            if(pass != null && pass != "" && pass == cpass){
                document.getElementById("form").submit();
            } else {
                document.getElementById("password").style.border = "solid 2px red";
                document.getElementById("cpassword").style.border = "solid 2px red";
            }

        }

    </script>

</head>
<body>
<c:import url="/WEB-INF/jsp/navigate/navigate.jsp"/>

<%--<form method="post" action="/users" id="form">--%>
    <table class="mdl-data-table mdl-js-data-table  mdl-shadow--2dp">
        <tr>
            <td class="mdl-data-table__cell--non-numeric">
                <label>Логин пользователя</label>
            </td>
            <td class="mdl-data-table__cell--non-numeric">
                <output  type="text" name="login" id="login" >${user.login}</output>
            </td>
        </tr>

        <tr>
            <td class="mdl-data-table__cell--non-numeric">
                <label>Имя пользователя</label>
            </td>
            <td class="mdl-data-table__cell--non-numeric">
                <output readonly type="test" name="firstname" id="firstname" >${user.firstName}</output>
            </td>
        </tr>
        <tr>
            <td class="mdl-data-table__cell--non-numeric">
                <label>Фамилия пользователя</label>
            </td>
            <td class="mdl-data-table__cell--non-numeric">
                <output type="test" name="lastname" id="lastname" >${user.lastName}</output>
            </td>
        </tr>
        <tr>
            <td class="mdl-data-table__cell--non-numeric">
                <label>Прочитанные книги</label>
            </td>
            <td class="mdl-data-table__cell--non-numeric">
                <output type="test" name="lastname" id="readBooks" >Здесь будет много книг</output>
            </td>
        </tr>

    </table>

    <%--<input type="hidden" name="edit" value="${user.id}">--%>
    <%--<input type="hidden" name="id" value = "${user.id}">--%>

    <%--<button type="button" name="edituser" value="true" onclick = submitEdit()>Изменить</button>--%>
    <%--<input type="button" value="Отменить" onClick='location.href="/users"'>--%>
<%--</form>--%>

</body>
</html>

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
    <script type="text/javascript" src="/js/jquery/jquery-3.1.1.min.js" ></script>
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

<form method="post" action="${pageContext.request.contextPath}/users" id="form">
    <table>
        <tr>
            <td>
                <label>Логин пользователя</label>
            </td>
            <td>
                <input type="text" name="login" id="login" value="${user.login}">
            </td>
        </tr>
        <tr>
            <td>
                <label>Пароль</label>
            </td>
            <td>
                <input type="password" name="password" id="password" value="">
            </td>
        </tr>
        <tr>
            <td>
                <label>Подтвердить пароль</label>
            </td>
            <td>
                <input type="password" name="сpassword" id="cpassword" value="">
            </td>
        </tr>
        <tr>
            <td>
                <label>Имя пользователя</label>
            </td>
            <td>
                <input type="test" name="firstName" id="firstname" value="${user.firstName}">
            </td>
        </tr>
        <tr>
            <td>
                <label>Фамилия пользователя</label>
            </td>
            <td>
                <input type="test" name="lastName" id="lastname" value="${user.lastName}">
            </td>
        </tr>

    </table>

    <input type="hidden" name="version" value="${user.version}">
    <input type="hidden" name="edit" value="${user.id}">
    <input type="hidden" name="id" value = "${user.id}">
    <input type="hidden" name="admin" value="${user.admin}">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <button type="button" name="edituser" value="true" onclick = submitEdit()>Изменить</button>
    <input type="button" value="Отменить" onClick='location.href="${pageContext.request.contextPath}/users"'>
</form>

</body>
</html>

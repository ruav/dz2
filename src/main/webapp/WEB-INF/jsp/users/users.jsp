<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <script src="<c:url value="/js/mdl/material.min.js" />"></script>

    <title>Список пользователей </title>

    <%--<style type="text/css">--%>

      <%--table{--%>
        <%--border-collapse: collapse;--%>
        <%--border: 1px;--%>
      <%--}--%>
      <%--/*.tr{*/--%>
        <%--/*border-style: solid;*/--%>
        <%--/*border-collapse: collapse*/--%>
      <%--/*}*/--%>
      <%--th{--%>
        <%--background: #ccc; /* Цвет фона */--%>
        <%--text-align: left; /* Выравнивание по левому краю */--%>
      <%--}--%>
      <%--td, th {--%>
        <%--border: 1px solid #800; /* Параметры границы */--%>
        <%--padding: 4px; /* Поля в ячейках */--%>
      <%--}--%>


    <%--</style>--%>

  </head>
  <body>
  <%--<h3>Вы зашли, как ${sessionScope.userId}</h3>--%>
  <%--<form action="/autority" method="post">--%>
    <%--<button type="submit" value="exit" name="exit">Выйти из текущего пользователя</button>--%>
  <%--</form>--%>

  <%--<%@include file="/WEB-INF/jsp/navigate/navigate.jsp"%>--%>
  <c:import url="/WEB-INF/jsp/navigate/navigate.jsp"/>
  <%--<%@include file="../../html/navigate.html"%>--%>

  <table class="mdl-data-table mdl-js-data-table  mdl-shadow--2dp">

    <tr>
      <th class="mdl-data-table__cell--non-numeric">Логин</th>
      <th class="mdl-data-table__cell--non-numeric">Имя</th>
      <th class="mdl-data-table__cell--non-numeric">Фамилия</th>
      <c:if test="${sessionScope.admin eq true}">
        <th class="mdl-data-table__cell--non-numeric">Редактировать пользователя</th>
        <th class="mdl-data-table__cell--non-numeric">Удалить пользователя</th>
      </c:if>
      <th class="mdl-data-table__cell--non-numeric">Администратор</th>
    </tr>

    <c:forEach items="${users}" var="user">
      <tr>
        <td class="mdl-data-table__cell--non-numeric">${user.login}</td>
        <td class="mdl-data-table__cell--non-numeric">${user.firstName}</td>
        <td class="mdl-data-table__cell--non-numeric">${user.lastName}</td>
        <c:if test="${sessionScope.admin eq true}">
          <td class="mdl-data-table__cell--non-numeric">
            <form method="get" action="/users">
              <button class="mdl-button mdl-js-button mdl-button--raised" type="submit" value="${user.id}" name="edit">Редактировать</button>
            </form>
          </td>
          <td class="mdl-data-table__cell--non-numeric">
            <form method="post" action="/users">
              <button type="submit" value="${user.id}" name="remove">Удалить</button>
            </form>
          </td>
          </c:if>
          <td class="mdl-data-table__cell--non-numeric">
            <form method="post" action="/users" >
              <input type="checkbox" name="isadmin" value="true"
                <c:if test="${sessionScope.admin eq true}">
                  onclick="this.form.submit();"
                </c:if>
              <c:if test="${sessionScope.admin ne true}">
                     disabled="disabled"
              </c:if>
              <c:if test="${user.admin eq true}">
                      checked
              </c:if>
              >Администратор<Br>
              <input  type="hidden" name="adminconfig" value="${user.id}" >
                <%--<button type="submit" value="${user.id}" name="adminconfig">Изменить</button>--%>
            </form>
          </td>
      </tr>
    </c:forEach>

  </table>
  </body>
</html>

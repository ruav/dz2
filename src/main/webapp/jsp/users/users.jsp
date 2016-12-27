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
    <title>Список пользователей </title>

    <style type="text/css">

      table{
        border-collapse: collapse;
        border: 1px;
      }
      /*.tr{*/
        /*border-style: solid;*/
        /*border-collapse: collapse*/
      /*}*/
      th{
        background: #ccc; /* Цвет фона */
        text-align: left; /* Выравнивание по левому краю */
      }
      td, th {
        border: 1px solid #800; /* Параметры границы */
        padding: 4px; /* Поля в ячейках */
      }


    </style>

  </head>
  <body>
  <h3>Вы зашли, как ${sessionScope.userId}</h3>
  <form action="/autority" method="post">
    <button type="submit" value="exit" name="exit">Выйти из текущего пользователя</button>
  </form>

  <%@include file="/html/navigate.html"%>

  <table>

    <tr>
      <th>Логин</th>
      <th>Имя</th>
      <th>Фамилия</th>
      <c:if test="${sessionScope.admin eq true}">
        <th>Редактировать пользователя</th>
        <th>Удалить пользователя</th>
      </c:if>
      <th>Администратор</th>
    </tr>

    <c:forEach items="${users}" var="user">
      <tr>
        <td>${user.login}</td>
        <td>${user.firstName}</td>
        <td>${user.lastName}</td>
        <c:if test="${sessionScope.admin eq true}">
          <td>
            <form method="get" action="/users">
              <button type="submit" value="${user.id}" name="edit">Редактировать</button>
            </form>
          </td>
          <td>
            <form method="post" action="/users">
              <button type="submit" value="${user.id}" name="delete">Удалить</button>
            </form>
          </td>
          </c:if>
          <td>
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

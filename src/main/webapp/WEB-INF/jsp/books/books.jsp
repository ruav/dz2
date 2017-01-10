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
    <title>Список литературы </title>

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


  <%--<%@include file="../../html/navigate.html"%>--%>
  <c:import url="/WEB-INF/jsp/navigate/navigate.jsp"/>

  <div>
    <%--<form method="LINK" action="/createbook">--%>
      <%--<input type="submit" value="Go to CodeHelper!">--%>
    <%--</form>--%>
    <c:if test="${sessionScope.admin eq true}">
        <form>
          <input type="button" value="Добавить новую книгу" onClick='location.href="/books/addbook"'>
        </form>
    </c:if>

    </form>
  </div>

  <table>

    <tr>
      <th>Название книги</th>
      <th>Автор</th>
      <th>Год издания</th>
      <th>Издательство</th>
      <c:if test="${sessionScope.admin eq true}">
        <th>Редактировать книгу</th>
        <th>Удалить книгу</th>
      </c:if>
    </tr>

    <c:forEach items="${books}" var="book">
      <tr>
        <td>${book.title}</td>
        <td>${book.author}</td>
        <td>${book.yearPublishing}</td>
        <td>${book.publisher}</td>
        <c:if test="${sessionScope.admin eq true}">
          <td>
            <form method="get" action="/books">
              <button type="submit" value="${book.id}" name="edit">Редактировать</button>
            </form>
          </td>
          <td>
            <form method="post" action="/books">
              <button type="submit" value="${book.id}" name="remove">Удалить</button>
            </form>
          </td>
        </c:if>
      </tr>
    </c:forEach>
  </table>

  </body>
</html>

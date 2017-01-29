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

    <style type="text/css">

      a:link {
        color: #1824ff;
      }

      /* visited link */
      a:visited {
        color: green;
      }

      /* mouse over link */
      a:hover {
        color: #7f0d08;
      }

      /* selected link */
      a:active {
        color: blue;
      }


      table.books{
        border-collapse: collapse;
        border: 1px;
      }
      /*.tr{*/
        /*border-style: solid;*/
        /*border-collapse: collapse*/
      /*}*/
      th.books{
        background: #ccc; /* Цвет фона */
        text-align: left; /* Выравнивание по левому краю */
        border: 1px solid #800; /* Параметры границы */
        padding: 4px; /* Поля в ячейках */
      }
      td.books{
        border: 1px solid #800; /* Параметры границы */
        padding: 4px; /* Поля в ячейках */
      }
      table.tablesorter thead tr .header {
        background-image: url(<c:url value="/js/jqsort/bg.gif" />);
        background-repeat: no-repeat;
        background-position: center right;
        cursor: pointer;
      }

      table.tablesorter thead tr .headerSortUp {
        background-image: url(<c:url value="/js/jqsort/asc.gif" />);
        background-color: #a673ff;

      }
      table.tablesorter thead tr .headerSortDown {
        background-image: url(<c:url value="/js/jqsort/desc.gif" />);
        background-color: #159525;

      }

      /*th.headerSortUp {*/
        /*background-image: url(/js/jqsort/asc.png);*/
        /*!*background-color: #3399FF;*!*/
      /*}*/
      /*th.headerSortDown {*/
        /*background-image: url(/js/jqsort/desc.png);*/
        /*!*background-color: #3399FF;*!*/
      /*}*/

    </style>

    <%--<script src="<c:url value="/js/jqsort/jquery-latest.js" />"></script>--%>
    <%--<script src="<c:url value="/js/jqsort/jquery.tablesorter.js" />"></script>--%>
    <%--<script type="text/javascript" src="<c:url value="/js/jquery/jquery-3.1.1.min.js" />"></script>--%>
    <script type="text/javascript" src="/js/jquery/jquery-3.1.1.min.js" ></script>
    <script type="text/javascript" src="<c:url value="/js/jquery/jquery-3.1.1.min.js" />" ></script>
    <script type="text/javascript" src="/js/jquery/jquery.dataTables.min.js" ></script>
    <script type="text/javascript" src="<c:url value="/js/jquery/jquery.dataTables.min.js" />" ></script>
    <link rel="stylesheet" type="text/css" href="/js/jquery/jquery.dataTables.min.css">
    <link rel="stylesheet" type="text/css" href="<c:url value="/js/jquery/jquery.dataTables.min.css" />">

    <%--<script type="text/javascript" src="/js/jqsort/jquery-latest.js" ></script>--%>
    <%--<script type="text/javascript" src="/js/jqsort/jquery.tablesorter.js" ></script>--%>
    <title>Список литературы </title>


    <%--<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.13/css/jquery.dataTables.min.css">--%>
    <%--<script type="text/javascript" charset="utf8" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>--%>
    <%--<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script>--%>

    <%--<script>--%>
        <%--$(document).ready(function()--%>
            <%--{--%>
                <%--$("#myTable").tablesorter(--%>
                    <%--{headers: { 4: { sorter: false}, 5: {sorter: false} }}--%>
                    <%--);--%>
            <%--}--%>
        <%--);--%>
    <%--</script>--%>

    <script>
        $(document).ready(function() {
            $("#myTable").dataTable(
                {
                    serverSide: false,
                    processing: true,
                    paging: true,
                    pagingType: "full_numbers"
                }
            )
        })
    </script>
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
          <input type="button" value="Добавить новую книгу" onClick='location.href="${pageContext.request.contextPath}/books/addbook"'>
        </form>
    </c:if>

    <%--</form>--%>
  </div>
  <%--<div>--%>
  <%--ids size = ${ids.size()}--%>
  <%--</div>--%>
  <table id="myTable" class="books tablesorter" style="width: 100%;">
    <thead>
    <tr>
      <th class="books">Название книги</th>
      <th class="books">Автор</th>
      <th class="books">Год издания</th>
      <th class="books">Издательство</th>
      <th class="books">Прочитана</th>
      <c:if test="${sessionScope.admin eq true}">
        <th class="books">Редактировать книгу</th>
        <th class="books">Удалить книгу</th>
      </c:if>
    </tr>
    </thead>
    <tbody>

    <c:forEach items="${books}" var="book">
      <c:set value="${book.id}" var="bookid"/>
      <tr>
        <td class="books">
          <a href="${pageContext.request.contextPath}/books/book/${book.id}">${book.title}</a>
        </td>
        <td class="books">${book.author}</td>
        <td class="books">${book.yearPublishing}</td>
        <td class="books">${book.publisher}</td>
        <td class="books">
          <c:if test="${not empty ids[book.id]}">
            Прочитана
          </c:if>
          <%--<c:if test="${ids.containsKey(bookid)}">--%>
          <c:if test="${empty ids[book.id]}">
          <a  href="${pageContext.request.contextPath}/users/user/${sessionScope.userId}/addbook/${book.id}" >Прочитал</a>
          </c:if>
        </td>
        <c:if test="${sessionScope.admin eq true}">
          <td class="books">
            <a  href="${pageContext.request.contextPath}/books?edit=${book.id}" >Редактировать</a>
            <%--<form id="editbook${book.id}" method="get" action="/books">--%>
              <%--<a  href="javascript:;"--%>
                 <%--onclick="document.getElementById('editbook${book.id}').submit();">Редактировать</a>--%>
              <%--<input type="hidden" name="edit" value="${book.id}" >--%>
              <%--<button type="submit" value="${book.id}" name="edit">Редактировать</button>--%>
            <%--</form>--%>
          </td>
          <td  class="books">
            <form id="remove${book.id}" method="post" action="${pageContext.request.contextPath}/books/remove" style="display:table-cell;vertical-align:center;">
                <a  href="javascript:;"
                onclick="document.getElementById('remove${book.id}').submit();">Удалить</a>
                <input type="hidden" name="remove" value="${book.id}" >
              <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
              <%--<button type="submit" value="${book.id}" name="remove">Удалить</button>--%>
            </form>
          </td>
        </c:if>
      </tr>
    </c:forEach>
    </tbody>
  </table>

  </body>
</html>

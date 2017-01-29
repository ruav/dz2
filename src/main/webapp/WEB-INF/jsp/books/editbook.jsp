<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 27.12.2016
  Time: 12:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редактирование книги ${book.title}</title>
</head>
<body>

<%--private int id;--%>
<%--private String title;--%>
<%--private String author;--%>
<%--private int yearPublishing;--%>
<%--private String publisher;--%>
<c:import url="/WEB-INF/jsp/navigate/navigate.jsp"/>

<form method="post" action="${pageContext.request.contextPath}/books/edit">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

    <table>
        <tr>
            <td>
                <label>Название книги</label>
            </td>
            <td>
                <input type="text" name="title" value="${book.title}">
            </td>
        </tr>
        <tr>
            <td>
                <label>Автор книги</label>
            </td>
            <td>
                <input type="text" name="author" value="${book.author}">
            </td>
        </tr>
        <tr>
            <td>
                <label>Издательство</label>
            </td>
            <td>
                <input type="text" name="publisher" value="${book.publisher}">
            </td>
        </tr>
        <tr>
            <td>
                <label>Год издания</label>
            </td>
            <td>
                <input type="number" name="yearPublishing" value="${book.yearPublishing}">
            </td>
        </tr>
    </table>

    <input type="hidden" name="id" value = "${book.id}">
    <input type="hidden" name="version" value = "${book.version}">

    <button type="submit" name="editbook" value="true">Изменить</button>
    <input type="button" value="Отменить" onClick='location.href="${pageContext.request.contextPath}/books"'>
</form>


</body>
</html>

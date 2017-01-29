<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 26.12.2016
  Time: 21:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавление книги</title>
</head>
<body>
<c:import url="/WEB-INF/jsp/navigate/navigate.jsp"/>

<form method="get" action="${pageContext.request.contextPath}/books">

    <fieldset>
        <legend>Добавление новой книги</legend>
        <table>
            <tr>
                <td>
                    <label>Название книги</label>
                </td>
                <td>
                    <input type="text" name="title" id="title" placeholder="Введите название книги"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label>Автор</label>
                </td>
                <td>
                    <input type="text" name="author" id="author" placeholder="Введите автора книги"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label>Введите год издания</label>
                </td>
                <td>
                    <input type="number" name="yearPublishing"  value="0" id="yearpub" placeholder="Введите год издания"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label>Наименование издательства</label>
                </td>
                <td>
                    <input type="text" name="publisher" id="publisher" placeholder="Введите наименование издательства"/>
                </td>
            </tr>
        </table>
    </fieldset>

    <button type="submit" name="addbook" value="true" >Добавить книгу</button>
</form>
</body>
</html>

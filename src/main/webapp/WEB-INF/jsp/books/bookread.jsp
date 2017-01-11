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
    <title>${book.author}. ${book.title}</title>
</head>
<body>
<c:import url="/WEB-INF/jsp/navigate/navigate.jsp"/>

<%--private int id;--%>
<%--private String title;--%>
<%--private String author;--%>
<%--private int yearPublishing;--%>
<%--private String publisher;--%>

    <h4>${book.author}</h4>
    <h5>${book.title}</h5>
    <h6>${book.publisher}, ${book.yearPublishing}</h6>
    <%--<output type="text" name="title"></output>--%>
    <%--<output type="text" name="author">${book.author}</output>--%>
    <%--<output type="text" name="publisher">${book.publisher}</output>--%>
    <%--<output type="number" name="yearPublishing">${book.yearPublishing}</output>--%>

    <fieldset>
        <div>
            Здесь должен распологаться текст книги.
        </div>
    </fieldset>


</body>
</html>

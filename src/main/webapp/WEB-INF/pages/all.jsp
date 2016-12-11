<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Все инструменты</title>
</head>
<body>
<table width="600px">
    <tr>
        <td><b>ID</b></td>
        <td><b>Name</b></td>
        <td><b>Type</b></td>
        <td><b>Price</b></td>
        <td><b>Date</b></td>
        <td><b>Action</b></td>
    </tr>
    <c:forEach var="instrument" items="${instruments}">
        <tr>
            <td>${instrument.id}</td>
            <td>${instrument.name}</td>
            <td>${instrument.type}</td>
            <td>${instrument.price}</td>
            <td>${instrument.dateAdded}</td>
            <td><a href="/edit?id=${instrument.id}">Edit</a> | <a href="/delete?id=${instrument.id}">Delete</a></td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="5">
            <a href="/add">Добавить запись</a>
        </td>
    </tr>
</table>
</body>
</html>

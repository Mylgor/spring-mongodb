<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить контакт</title>
</head>
<body>
<form:form method="POST" action="/add" modelAttribute="instrument">
    <form:hidden path="Id" />
    <table>
        <tr>
            <td>Name:</td>
            <td><form:input path="Name" /></td>
        </tr>
        <tr>
            <td>Type:</td>
            <td><form:input path="Type" /></td>
        </tr>
        <tr>
            <td>Price:</td>
            <td><form:input path="Price" /></td>
        </tr>
        <tr>
            <td>Date of manufacture:</td>
            <td><form:input path="DateAdded" /></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" />
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>

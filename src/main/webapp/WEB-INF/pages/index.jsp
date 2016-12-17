<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%
    String date = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
%>
<c:set var="currentDate" value="<%=date%>"/>

<html>
<head>
    <meta charset="UTF-8">
    <title><spring:message code="label.title"/></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/index.css"/>">
</head>
<body>
<div class="first-block">

    <h1><spring:message code="label.title"/></h1>
    <div class="block1 float-left">
        <h3><spring:message code="label.h3"/></h3>

        <form method="POST" action="/add">
            <h4><spring:message code="data.name"/></h4>
            <input type="text" name="nameInst" value="" path="Name">
            <h4><spring:message code="data.type"/></h4>
            <input type="text" name="typeInst" value="" path="Type">
            <h4><spring:message code="data.price"/></h4>
            <input type="number" name="priceInst" value="" path="Price">
            <h4><spring:message code="data.date"/></h4>
            <input type="date" name="dateAddedInst" value=${currentDate} path="DateAdded">

            <p>
                <input class="buttonAdd" type="submit" value="<spring:message code="btn.text"/>" id="btnAdd">
            </p>
        </form>
        <label for="btnAdd">${isAdd}</label>
    </div>

    <div class="block2">
        <spring:message code="text"/>
    </div>
    <div class="clear"></div>
</div>


<div class="line"></div>


<div class="second-block">
    ${isPrint}
    <table width="600px">
        <tr>
            <td><b><spring:message code="table.id"/></b></td>
            <td><b><spring:message code="table.name"/></b></td>
            <td><b><spring:message code="table.type"/></b></td>
            <td><b><spring:message code="table.price"/></b></td>
            <td><b><spring:message code="table.date"/></b></td>
        </tr>
        <c:forEach var="instrument" items="${instruments}">
            <tr>
                <td>${instrument.id}</td>
                <td>${instrument.name}</td>
                <td>${instrument.type}</td>
                <td>${instrument.price}</td>
                <td>${instrument.dateAdded}</td>
            </tr>
        </c:forEach>
    </table>
</div>

<div style="position: absolute; left: 10px">
    <h1>Text from EXAMPLE.COM</h1>
    <p>${siteText}</p>
</div>

</body>
</html>
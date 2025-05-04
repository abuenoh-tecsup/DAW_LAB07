<%--
  Created by IntelliJ IDEA.
  User: ALVARO
  Date: 3/05/2025
  Time: 18:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Manipulación de Datos con JSTL</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css">
</head>
<body class="container mt-5">
<h2 class="mb-4">Manipulación de Datos con JSTL</h2>

<c:set var="nombres" value="ana,juan,carlos,maria,beatriz"/>

<c:set var="nombresList" value="${fn:split(nombres, ',')}"/>

<table class="table table-bordered">
    <thead class="table-light">
    <tr>
        <th>#</th>
        <th>Nombre original</th>
        <th>Mayúsculas</th>
        <th>Número de carácteres</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="nombre" items="${nombresList}" varStatus="estado">
        <tr>
            <td>${estado.count}</td>
            <td>${nombre}</td>
            <td>${fn:toUpperCase(nombre)}</td>
            <td>${fn:length(nombre)}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<h4 class="mt-4">Nombres con más de 5 letras:</h4>
<ul>
    <c:forEach var="nombre" items="${nombresList}">
        <c:if test="${fn:length(nombre) > 5}">
            <li>${fn:toUpperCase(nombre)} (${fn:length(nombre)} letras)</li>
        </c:if>
    </c:forEach>
</ul>
</body>
</html>

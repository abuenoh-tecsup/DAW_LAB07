<%--
  Created by IntelliJ IDEA.
  User: ALVARO
  Date: 30/04/2025
  Time: 17:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Listado de cursos</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css">
</head>
<body class="container mt-5">
    <h2 class="mb-4">Listado de cursos</h2>
    <c:set var="listaCursos" value="${[
        {'chrCurCodigo':'C001', 'vchCurNombre':'Matemáticas', 'intCurCreditos':3},
        {'chrCurCodigo':'C002', 'vchCurNombre':'Física', 'intCurCreditos':4},
        {'chrCurCodigo':'C003', 'vchCurNombre':'Historia', 'intCurCreditos':2}
    ]}"/>

    <table class="table table-bordered table-striped">
        <thead class="table-dark">
            <tr>
                <th>Código</th>
                <th>Nombre</th>
                <th>Créditos</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="curso" items="${listaCursos}">
                <tr>
                    <td>${curso.chrCurCodigo}</td>
                    <td>${curso.vchCurNombre}</td>
                    <td>${curso.intCurCreditos}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>

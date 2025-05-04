<%--
  Created by IntelliJ IDEA.
  User: ALVARO
  Date: 30/04/2025
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css">
</head>
<body class="container mt-5">

    <h2 class="mb-4">Calculadora JSP con JSTL</h2>

    <form method="post" class="mb-4">
        <div class="row mb-3">
            <div class="col">
                <input type="number" class="form-control" name="num1" placeholder="Ingrese nro.">
            </div>
            <div class="col">
                <input type="number" class="form-control" name="num2" placeholder="Ingrese nro.">
            </div>

            <div class="col">
                <select name="operacion" class="form-select">
                    <option value="sumar">Sumar</option>
                    <option value="restar">Restar</option>
                    <option value="multiplicar">Multiplicar</option>
                    <option value="dividir">Dividir</option>
                </select>
            </div>
            <div class="col">
                <button type="submit" class="btn btn-primary">Calcular</button>
            </div>
        </div>
    </form>

    <c:set var="n1" value="${param.num1}"/>
    <c:set var="n2" value="${param.num2}"/>
    <c:set var="op" value="${param.operacion}"/>
    <c:set var="result" value="0"/>

    <c:choose>
        <c:when test="${op=='sumar'}">
            <c:set var="result" value="${n1+n2}"/>
        </c:when>
        <c:when test="${op=='restar'}">
            <c:set var="result" value="${n1-n2}"/>
        </c:when>
        <c:when test="${op=='multiplicar'}">
            <c:set var="result" value="${n1*n2}"/>
        </c:when>
        <c:when test="${op=='dividir'}">
            <c:if test="${n2 != 0}">
                <c:set var="result" value="${n1 / n2}"/>
            </c:if>
        </c:when>
    </c:choose>

    <div class="alert alert-success">
        <strong>Resultado:</strong>
        ${n1}
        <c:choose>
            <c:when test="${op == 'sumar'}"> + </c:when>
            <c:when test="${op == 'restar'}"> - </c:when>
            <c:when test="${op == 'multiplicar'}"> * </c:when>
            <c:when test="${op == 'dividir'}"> / </c:when>
        </c:choose>
        ${n2} = <strong> ${result}</strong>
    </div>

    <c:if test="${op == 'dividir' and n2 == 0}">
        <div class="alert alert-danger">❌ No se puede dividir entre 0</div>
    </c:if>

</body>
</html>

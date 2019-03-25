<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Trabajador</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<script type="text/javascript"
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>

</head>
<body>
	<center>
		<h2>Trabajadores</h2>
	</center>
	<table class="table">
		<thead class="thead-light">
			<tr>
				<th scope="col">Id</th>
				<th scope="col">Nombre</th>
				<th scope="col">Sueldo</th>
				<th scope="col">Ganancia</th>
			</tr>
		</thead>
		<c:forEach var="trabajador" items="${lista}">
			<tbody>
				<tr>
					<td><c:out value="${trabajador.id}"></c:out></td>
					<td><c:out value="${trabajador.nombre}"></c:out></td>
					<td><c:out value="${trabajador.sueldo}"></c:out></td>
					<c:if test="${trabajador.sueldo<250}">
						<c:set var="salary" scope="session" value="${(trabajador.sueldo*20)/100}" />
						<td><c:out value="${salary+trabajador.sueldo}"/></td>
					</c:if>
					<c:if test="${trabajador.sueldo>=300}">
						<td>No tiene ganancia</td>
					</c:if>
				</tr>
			</tbody>
		</c:forEach>
	</table>

</body>
</html>
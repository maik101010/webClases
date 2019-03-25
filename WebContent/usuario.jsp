<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"
    prefix="fn" %>
<%@ page import="com.pruebas.modelo.*"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>


<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<script type="text/javascript"
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
<title>Lista de Usuarios</title>
</head>
<body>

	<table class="table table-striped">
		<thead>
			<tr>
				<th scope="col">#</th>
				<th scope="col">Nick</th>
				<th scope="col">Password</th>

			</tr>
		</thead>
		<c:forEach var="usuario" items="${miLista}">
			<tbody>
				<tr>
					<td><c:out value="${usuario.id}"></c:out></td>
					<td><c:out value="${usuario.nick}"></c:out></td>
					<td><c:out value="${usuario.password}"></c:out></td>

				</tr>

			</tbody>
		</c:forEach>
	</table>

	<c:if test="${fn:length(miLista) > 0}">
		<%@include file="login.jsp"%>
	</c:if>


</body>
</html>
<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.pruebas.modelo.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>principal</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<script type="text/javascript"
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
</head>
<body>
	<%
		/**
			Validamos que el usuario tenga una sesión iniciada
		*/
		HttpSession sesion = request.getSession();
		if (sesion.getAttribute("usuarioSesion") == null) {
			out.print("<script>alert('Inicie sesión para acceder al recurso');");
			out.print("window.location.href = 'login.jsp';");
			out.print("</script>");			
		}else {
			Usuario us = (Usuario) sesion.getAttribute("usuarioSesion");
	%>
	<h2>Bienvenido</h2>

	<div class="container">
		<div class="row justify-content-center">
			<div class="col-md-8">
				<div class="card">
					<div class="card-header">Información de usuario</div>
					<div class="dispayingTextDiv">
						<p>Bienvenido <%=us.getNick()%> </p>
					</div>

				</div>
			</div>
		</div>
		<a href="cerrar_sesion.jsp">Cerrar Sesión</a>
	</div>
	<%
		}
	%>
</body>
</html>
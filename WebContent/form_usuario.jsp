<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.pruebas.modelo.*"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Formulario de registro</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<script type="text/javascript"
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>

</head>
<body>
	<h2>Formulario de registro</h2>

	<form action="UsuarioControlador" method="POST">
		<div class="form-row">
			<div class="form-group col-md-2">
				<label for="inputEmail4">Nick</label> <input type="text"
					class="form-control" id="nick" placeholder="Nick" name="nick">
			</div>
			<div class="form-group col-md-2">
				<label for="inputPassword4">Password</label> <input type="password"
					class="form-control" id="password" placeholder="Password"
					name="password">
			</div>
		</div>
		<div class="form-row">
			<div class="form-group col-md-2">
				<label for="inputZip">Fecha</label> <input type="date"
					class="form-control" id="fecha" name="fecha">
			</div>
		</div>

		<div class="form-group col-md-2">
			<label for="inputZip">Ciudades</label> <select name="ciudad">
				<%
					List<Ciudad> ciudadLista = (List<Ciudad>) request.getAttribute("listaCiudad");
					for (Ciudad ciudad : ciudadLista) {
						//Inicia el for
				%>
				<option value="<%=ciudad.getId()%>"><%=ciudad.getNombre()%></option>
				<%
					} //Termina el for
				%>
			</select>

		</div>


		<button type="submit" class="btn btn-primary">Ingresar
			Registro</button>
	</form>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
<title>Login</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<script type="text/javascript"
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<h2>Login</h2>
	</div>
	<form action="LoginControlador" method="post">
		<div class="form-group">
			<div class="col-3">
				<label for="exampleInputEmail1">Email address</label> <input
					type="email" class="form-control" id="exampleInputEmail1"
					aria-describedby="emailHelp" placeholder="Enter email" name="nick">
				<small id="emailHelp" class="form-text text-muted"></small>
			</div>
		</div>
		<div class="form-group">
			<div class="col-3">
				<label for="exampleInputPassword1">Password</label> <input
					type="password" class="form-control" id="exampleInputPassword1"
					placeholder="Password" name="password">
			</div>
		</div>
		<div class="form-check">
			<div class="col-3">
				<input type="checkbox" class="form-check-input" id="exampleCheck1">
				<label class="form-check-label" for="exampleCheck1">Check me
					out</label>
			</div>
		</div>
		<button type="submit" class="btn btn-primary">Login</button>
		<button type="button" class="btn btn-primary" id="olvido-contrasenia">Olvido
			su contraseña?</button>
	</form>
	<script type="text/javascript">
		var boton = document.getElementById("olvido-contrasenia");
		boton.addEventListener("click", enviarPaginaRecovery);
		function enviarPaginaRecovery() {
			window.location.href = 'recoverypassword.jsp';
		}
	</script>
	
</body>
</html>
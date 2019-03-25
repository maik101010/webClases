<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
<title>Validación</title>
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
		<h2>Validación código</h2>
	</div>
	<%
		HttpSession sesion = request.getSession();
		int codigo = (int) sesion.getAttribute("codigoValidacionSesion");
		
	%>

	<form action="validar.jsp" method="post">
		<div class="form-group">
			<div class="col-3">
				<label for="exampleInputEmail1">Código para validar usuario:</label>
<!-- 				<input type="text" class="form-control" id="codigo" placeholder="Codigo" name="codigo"> -->
				<input type="text" id="test">
				<input
					type="hidden" value="<%out.print(codigo);%>" name="codigoEmail" id="codigoEmail">
			</div>
		</div>
		<button type="button" class="btn btn-primary" id="validar">Validar</button>
	</form>

</body>
<script>

</script>


</html>
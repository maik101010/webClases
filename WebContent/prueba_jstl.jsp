<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<c:forEach var="i" begin="1" end="5">
		<c:if test="${ i>3 }">
			<p>
				<c:out value="${ i }"></c:out>
				 Este número es mayor que 3

			</p>
		</c:if>
	</c:forEach>

</body>
</html>
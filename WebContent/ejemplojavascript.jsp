<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<p>Press any key or click the mouse to get the event type.</p>
<p id="log"></p>
<script type="text/javascript">

function getEventType(event) {
	  const log = document.getElementById('log');
	  log.innerText = event.type + '\n' + log.innerText;
	}

	// Keyboard events
	document.addEventListener('keydown', getEventType, false); // first
	document.addEventListener('keypress', getEventType, false); // second
	document.addEventListener('keyup', getEventType, false); // third

	// Mouse events
	document.addEventListener('mousedown', getEventType, false); // first
	document.addEventListener('mouseup', getEventType, false); // second
	document.addEventListener('click', getEventType, false); // third
</script>
</body>
</html>
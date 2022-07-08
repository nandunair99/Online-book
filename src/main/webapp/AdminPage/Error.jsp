<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Error</title>
</head>
<body>
	<center>
		<div class="wrapper">
			<div class="row errormsg">
				<h1>Opps! something went wrong!</h1>
			</div>
			<div class="row errorimg">
				<h3>${errormsg}</h3>
				<img src="${pageContext.request.contextPath}/resource/img/page/errorpage.png">
			</div>
		</div>
	</center>
</body>
</html>
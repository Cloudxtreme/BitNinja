<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${not user.name eq 'Raul Hess' }">
	<c:redirect url="/bit-ninja/"></c:redirect>
</c:if>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Bit Ninja</title>
	<link rel="stylesheet" href="../css/main.css">
	<link rel="icon" type="image/png" href="../imgs/favicon.png?v=2">
</head>
<body>
	<div class="wrapper">
		<div class="menu">
			<ul>
				<li><a href="?">Home</a></li>
				<li><a href="?object=locations">Administrate Locations</a></li>
				<li><hr></li>
				<li><a href="/bit-ninja/Control?action=logout">Logout (${user.name })</a></li>
				<li><hr></li>
				<li style="color:white;font-size:11;">
					Stick War RPG<br>
					All Rights Reserved
					Brazil 2014
				</li>
			</ul>
		</div>
		<div class="container">
			<c:choose>
			<c:when test="${param.object eq 'locations' }">
			<h1>Administrate Locations</h1>
			<hr>
			<h2>Create New Location</h2>
			<form action="../Control?action=add&object=location" method="post">
				Name:<br>
				<input type="text" maxlength="45" size="45" name="bit-name"><br>
				Description:<br>
				<textarea rows="4" cols="70" style="resize:none;" name="bit-description"></textarea>
				<input type="submit" value="Create">
			</form>
			<hr>
			<h2>Create New Location Background</h2>
			<form action="../Control?action=add&object=background" method="post">
				Name:<br>
				<input type="text" maxlength="45" size="45" name="bit-bg-name"><br>
				Description:<br>
				<textarea rows="4" cols="70" style="resize:none;" name="bit-bg-description"></textarea><br>
				Bonuses:<br>
				<input type="text" maxlength="90" size="45" name="bit-bg-bonuses"><br>
				<input type="submit" value="Create">
			</form>
			</c:when>
			
			<c:otherwise>
			<h1>Welcome</h1>
			This is the administration page
			</c:otherwise>
			
			</c:choose>
		</div>
	</div>
</body>
</html>
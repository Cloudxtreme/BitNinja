<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${not empty user }">
	<c:redirect url="/game"></c:redirect>
</c:if>

<c:if test="${empty data}">
	<c:redirect url="/DataInit"></c:redirect>
</c:if>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Bit Ninja</title>
	<link rel="stylesheet" href="css/main.css">
	<link rel="stylesheet" href="css/menu.css">
	<link rel="icon" type="image/png" href="imgs/favicon2.png">
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script type="text/javascript" src="js/lib.js"></script>
</head>
<body>
	<div class="navigation">
		<div class="logo-img" style="height: 100px;margin-bottom:16px;">
			<img>
		</div>
		<form action="Control?action=login" method="post">
		<ul class="menu">
			<li class="menu-heading">Login</li>
			<li><a><input class="input-form" type="text" name="bit-email" placeholder="User Email"></a></li>
			<li><a><input class="input-form" type="password" name="bit-password" placeholder="User Password"></a></li>
			<li><a><input class="btn btn-red" type="submit" value="Enter"></a></li>
			<li class="menu-heading">Menu</li>
			<li><a href="/bit-ninja/" >Home</a></li>
			<li><a href="?page=register" >Register</a></li>
		</ul>
		</form>
	</div>
	
	<input type="checkbox" id="nav-trigger" class="nav-trigger" />
	<label for="nav-trigger">&#9776;</label>

	<div class="wrapper">
	<div class="header">
		<img alt="" src="imgs/header.png">
	</div>
	<c:if test="${not empty param.error }">
		<div class="message error">
		<c:choose>
			<c:when test="${param.error eq 1}">
				User e-mail or password were no filled.
			</c:when>
			<c:when test="${param.error eq 2}">
				User e-mail or password incorrect.
			</c:when>
			<c:when test="${param.error eq 3}">
				There is already a user logged in with this account.
			</c:when>
		</c:choose>
		</div>
	</c:if>
	<div class="container">
	<c:choose>
		
		<c:when test="${param.page eq 'register' }">
		<h1>Register</h1>
		<form action="Control?action=register" method="post">
			<p><b>Display Name:</b></p>
			<input class="input-form" id="display-input" type="text" size="45" maxlength="45" name="bit-name"><br><br>
			<p><b>Email:</b></p>
			<input class="input-form" id="email-input" type="text" size="45" maxlength="45" name="bit-email"><br><br>
			<p><b>Password:</b></p>
			<input class="input-form" id="password-input" type="password" size="45" maxlength="45" name="bit-password"><br><br>
			<p><b>Password Confirmation:</b></p>
			<input class="input-form" id="passwordConfirm-input" type="password" size="45" maxlength="45" name="bit-password"><br><br>
			<input class="btn btn-red" type="submit" value="Register"> <input class="btn btn-red" type="reset" value="Clean Form">
		</form>
		</c:when>
		
		<c:otherwise>
		<c:if test="${not empty news }">
		<c:forEach items="${news }" var="item">
		<div class="new">
			<h1>${item.title}</h1>
			<h5>${item.subtitle } - ${item.date }</h5>
			<p>${item.info }</p>
		</div>
		</c:forEach>
		</c:if>
		<h1>Hi!</h1>
		<p>This is just a test for the theme. :)</p>
		
		<hr>
		
		<p><b>Users Logged In:</b> ${data.userLogged }</p>
		<p><b>Number of Softcore Characters:</b> ${data.softcore}</p>
		<p><b>Number of Hardcore Characters:</b> ${data.hardcore}</p>
		</c:otherwise>
		
	</c:choose>	
	</div>
</div>
</body>
</html>
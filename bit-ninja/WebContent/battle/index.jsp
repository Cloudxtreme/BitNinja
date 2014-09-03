<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Stick War</title>
	<link rel="stylesheet" href="main.css">
	<link rel="icon" type="image/png" href="../imgs/favicon.png">
</head>
<body>
	<div class="wrapper">
			<div class="menu">
				<div style="font-size:18px;color: white;">
					<a class="character-name" href="#">Melcor Almerion</a>
				</div>
				<div style="font-size:11px; margin: 2px 0;color:#DDD;"> Wizard (1) </div>
				<br>
				<div style="font-size:13px; margin: 2px 0;color:white;">Experience Points</div>
				<progress id="expbar" max="100" value="27"></progress>
				<div class="exp">27 / 100</div>
<!-- 				<div class="label-level-up">Ready to Level-up!</div> -->
				<ul>
					<li><hr></li>
					<li><a href="#">Home</a></li>
					<li><a href="#">Battle</a></li>
					<li><a href="#">Inventory</a></li>
					<li><a href="#">Map</a></li>
					<li><a href="#">Change Character</a></li>
					<li><hr></li>
					<li style="color:white;font-size:11;">
						Stick War RPG<br>
						All Rights Reserved
						Brazil 2014
					</li>
				</ul>
			</div>
			<div class="container">
				<h1>Section title</h1>
				<h2>Secondary Title</h2>
				<h3>Bold Names</h3>
				<h4>Names</h4>
				<h5>Subtitles</h5>
				<hr>
				<form action="#" method="GET">
					<h1>Character Creation</h1>
					<p></p>
					<div class="description">
						This is where the selected class for the character creation's description will
						be placed.
					</div>
					<ul style="list-style:none; padding: 0; margin: 0; float: left;">
						<li><input type="radio" name="stick-class">Cleric</li>
						<li><input type="radio" name="stick-class">Fighter</li>
						<li><input type="radio" name="stick-class">Ranger</li>
						<li><input type="radio" name="stick-class">Wizard</li>
					</ul>
				</form>
			</div>
		</div>
</body>
</html>
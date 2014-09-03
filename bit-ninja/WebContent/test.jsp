<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bit-Ninja Test</title>
<script type="text/javascript" src="js/popWindow.js">

	
</script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<style type="text/css">
.blanket{
	position: absolute;
	background-color: #333;
	opacity: 0.8;
	z-index: 9001;
	top:0px;
	left:0px;
	width:100%;
	height: 100%;
}

.pop-window{
	border-style: outset;
	border-color: darkRed;
	padding: 5px;
	padding-top: 10px;
	width: 600px;
	margin: auto;
	background-color: white;
	margin-top: 10%;
	display: none;
}

.pop-title{
	display: block;
	position: relative;
	border-style: outset;
	border-color: darkRed;
	top:-20px;
	background-color: white;
	width: 250px;
	padding: 3px;
	margin-bottom: -10px;
	border-radius: 10px;
}

.pop-buttons{
	margin-top: 5px;
}

.pop-buttons button{
	border: solid thin darkRed;
	background-color: #E8D756;
	border-radius: 5px;
	margin: 5px;
}

.pop-buttons button:HOVER{
	background-color: #C9B938;
	cursor: pointer;
}
</style>
</head>
<body>
${user }
	<button type="button" onclick="popWindow('A');">Abre Janela</button>
	<div class="blanket" id="blanket-A" style="display:none;">
		<div class="pop-window" id="pop-window-A">
			<div id="pop-title-A" class="pop-title">Aqui fica o título!</div>
			Aqui fica o conteúdo
			<div class="pop-buttons">
				<button type="button">Opção 1</button>
				<button type="button">Opção 2</button>
				<button type="button" onclick="popWindow('A');">Fechar</button>
			</div>
		</div>
	</div>
</body>
</html>
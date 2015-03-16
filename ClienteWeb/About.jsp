<%@page pageEncoding="ISO-8859-1" contentType="text/html; charset=ISO-8859-1" %>
<html>
<head>
<style>
body {
	background-color: #f2f3f4;
	color: #333333;
	font-family:Verdana;
}
h1, h3 {
	text-align:center;
}
h1 {
	font-size:40px;
}
div.item
{
	text-align: left;
	width:700px;
	margin-top:10px;
	margin-bottom:10px;
	height:140px;
	clear:both;
}
div.footer
{
	text-align: center;
	margin-top:50px;
	clear:both;
}
img
{
	float: left;
	margin-right:20px;
}
img.gato {
    position: absolute;
    top: 0px;
    right: 20px;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
<title>Ranking Taller II - Sobre nosotros</title>
</head>
<body>
<div>
	<img class="gato" src="${pageContext.request.contextPath}/Imagenes/upCat.png" />
</div>
<div>
	<div align="center">
		<h1>Grupo de trabajo</h1>
		<div class="item">
			<img src="${pageContext.request.contextPath}/Imagenes/Leonardo.jpg" />
			<b>Leonardo Fernández</b>
			<br>
			<br>
			A los cinco años, diseñó un algoritmo recursivo con un ábaco de la escuela. Rechazó un trabajo en Google para no abandonar su pasión: el carnaval de frontera. Traduce en 4 lenguas muertas. Le enseño a su gato a programar en Java.
		</div>
		<div class="item">
			<img src="${pageContext.request.contextPath}/Imagenes/Michell.jpg" />
			<b>Michell Andrada</b>
			<br>
			<br>
			Su vocación siempre estuvo con el teclado. Cuando pequeño, de la sopa de letras solo tomaba los unos y ceros. Tiene la extraña habilidad de ejecutar binario mentalmente. Su hobby es programar virus en PL-SQL. Fan de Justin Bieber.
		</div>
		<div class="item">
			<img src="${pageContext.request.contextPath}/Imagenes/Leonel.jpg" />
			<b>Leonel More</b>
			<br>
			<br>
			Ex agente de inteligencia de la NSA, hoy programador Java encubierto. Tiene el superpoder de ver el mundo maquetado en HTML, lo que hace que se maree con el tráfico y los movimientos bruscos. Se presume que es Anonymous. Le gusta programar en Visual Basic.
		</div>
		<h1>Mentor</h1>
		<div class="item">
			<img src="${pageContext.request.contextPath}/Imagenes/Ariel.jpg" />
			<b>Ariel Ron</b>
			<br>
			<br>
			Esta es una de las pocas fotos que se conocen de Ariel "Ron", antiguo ciberpunk de Fing. Su verdadero apellido es desconocido. El alcohol hace en él lo que las espinacas en Popeye, de allí su apodo. Puede debuggear Assember con solo mirar el código fuente.
		</div>
	</div>
	<div class="footer">
		<p>Haga click <a href="Ranking">aqui</a> para volver.</p>
	</div>
</div>
</body>
</html>

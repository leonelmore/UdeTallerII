<%@page pageEncoding="ISO-8859-1" contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:useBean id='mensajeError' scope='session' class='java.lang.String' />

<html>
<head>
<style>
body {
	background-color: #f2f3f4;
	color: #333333;
	font-family:Verdana;
}
h1 {
	font-size:60px;
}
h2 {
	font-size:30px;
}
img {
    position: absolute;
    bottom: 0px;
    right: 70px;
}
</style>
<meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-1"/>
<title>Ranking Taller II - Error</title>
</head>
<body>
<p style="text-align:right">Materia: Taller II <br/>
<a href="About.jsp">Grupo</a>: A2 <br/>
Año: 2015</p>

<div>
<h1>Esto es embarazoso</h1>
<h2>Ha ocurrido un error :(</h2>
<p style="max-width:500px; padding-left:30px; padding-top:30px; padding-bottom: 20px;"> ${mensajeError} </p>
<p>Haga click <a href="Ranking">aqui</a> para volver a intentar.</p>
<img src="${pageContext.request.contextPath}/Imagenes/cats.gif" />
</body>
</html>

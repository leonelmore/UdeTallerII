<%@page pageEncoding="ISO-8859-1" contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:useBean id='listaRanking' scope='session' class='java.util.ArrayList' />

<html>
<head>
<style>
table, th, td {
    border: 2px solid;
    border-collapse: collapse;
    background-color: #FFFFFF;
    border-color: #333333;
}
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
</style>
<meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-1"/>
<title>Ranking Taller II</title>
</head>
<body>

<p style="text-align:right">Materia: Taller II <br/>
<a href="About.jsp">Grupo</a>: A2 <br/>
Año: 2015</p>

<div>
<h1>Ranking General</h1>
<h3> El <c:out value="${listaRanking[0].nombre}" /> va ganando!</h3>
<table align=center cellpadding=5 style="text-align:center">
<tr style="text-align:center">
	<th> Nombre </th>	
	<th> Puntaje </th>
	<th> Películas acertadas </th>
	<th> Películas erradas </th>
</tr>

<c:forEach items="${listaRanking}" var="x">
	<tr style="text-align:left;">
		<td> ${x.nombre} </td>	
		<td> ${x.puntaje} </td>
		<td> ${x.peliculasAcertadas} </td>
		<td> ${x.peliculasErradas} </td>
	</tr>
</c:forEach>
</table>
</div>

<p style="text-align:center">Haga click <a href="Ranking">aqui</a> para actualizar el ranking. </p>

</body>
</html>

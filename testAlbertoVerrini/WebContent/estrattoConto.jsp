<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Risultato estratto conto</title>
</head>
<body>

<h1>Risultato estratto conto</h1>
<br>
Amount : <% String totale = request.getParameter("amount"); out.print(totale);%>
<br>
Result status : <% String risultato = request.getParameter("risultatoEC"); out.print(risultato);%>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Risultato invio bonifico</title>
</head>
<body>

<h1>Risultato invio bonifico</h1>
<br>
Receiver name : <% String inviato = request.getParameter("receiverName"); out.print(inviato);%>
<br>
Result status : <% String risultato = request.getParameter("risultatoBonifico"); out.print(risultato);%>
<br>
Status description : <% String descrizione = request.getParameter("descrizioneRisultatoBonifico"); out.print(descrizione);%>
</body>
</html>
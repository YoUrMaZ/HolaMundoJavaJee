<%@ page import="java.net.CookieHandler" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<%
    // leyendo datos del formulario
    String ciudad_favorita = request.getParameter("ciudad_favorita");

    //crear la cookie
    Cookie laCookie = new Cookie("agencia_viajes.ciudad_favorita", ciudad_favorita);

    // vida d la cookie

    laCookie.setMaxAge(365*24*60*60); //un año

    //  Enviar a Usuario

    response.addCookie(laCookie);

%>

Gracias por enviar tus preferencias

<a href="pagina_personalizada.jsp"> Ir a agencia</a>


</body>
</html>
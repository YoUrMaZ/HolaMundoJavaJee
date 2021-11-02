<%@ page import="jakarta.servlet.http.Cookie" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h1>Agencia de viajes</h1>

<%
    //valor por defecto
    String ciudad_favorita = "Madrid";

    //Lee la peticion del navegador

    Cookie[] lasCookies = request.getCookies();

    //Buscar las preferencias. La ciudad favorita

    if (lasCookies != null){
        for (Cookie cookie_temporal: lasCookies){
            if ("agencia_viajes.ciudad_favorita".equals(cookie_temporal.getName())){

                ciudad_favorita = cookie_temporal.getValue();

                break;

            }
        }

    }

%>

<h3>Vuelos a <%= ciudad_favorita %></h3>
<p>Esto es un texto de ejemplo</p>

<h3>Hoteles a <%= ciudad_favorita %></h3>
<p>Esto es un texto de ejemplo</p>

</body>
</html>
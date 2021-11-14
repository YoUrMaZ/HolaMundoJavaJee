<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
</head>
<body>
<%
  if (request.getParameter("usuario") == null){%>
<form method="post" action="Recepcion">
  <table>
    <tr>
      <td>Nombre de Usuario: </td>
      <td> <input type="text" name="usuario" size="35"/></td>
      </td>
    </tr>
    <tr>
      <td>Contrasena</td>
      <td><input type="password" name="contrasena" size="35"/></td>
    </tr>
    <tr>
      <td><input type="submit" value="enviar"/></td>
    </tr>
  </table>
</form>
<%
  }
%>
</body>
</html>
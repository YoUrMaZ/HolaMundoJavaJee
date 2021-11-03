<%@ page import="jakarta.servlet.http.Cookie" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/Recepcion2">
    <table>
        <tr>
            <td>Nombre de Usuario: </td>
            <%
                Cookie[] cookies = request.getCookies();
                String valor = " ";
                for (Cookie c: cookies){
                    if (c.getName().equals("usuario")){
                        valor = c.getValue();
                    } else {
                        out.println("No encontrado");

                    }
                }
            %>
            <td> <input type="text" name="usuario" size="35" value="<%=valor%> "/></td>

            </td>
        </tr>
        <tr>
            <td>Contraseña</td>
            <td><input type="text" name="contrasena" size="35"/></td>
        </tr>
        <tr>
            <td></td>
            <input name="ckbox" type="checkbox" checked="checked">Recordar mis datos</input></td>
        </tr>
        <tr>
            <td><input type="submit" value="enviar"/></td>
        </tr>
    </table>
</form>
</body>
</html>
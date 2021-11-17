package es.manu.holamundo;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
@WebServlet("/Recepcion")
public class Recepcion extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {

        String usuario = request.getParameter("usuario");
        Cookie usuarios = new Cookie("Nombre_usuarios", usuario);
        usuarios.setMaxAge(1000000);
        response.addCookie(usuarios);

        procesaSolicitud(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        Cookie[] ContadorPersonas = request.getCookies();
        Cookie usuarios = BuscarUsuarios(ContadorPersonas);

        out.println("<p> Bienvenido: " + usuarios.getValue());
        out.println("<br/>");
        out.println("<form method=\"post\" action=\"Cookies\">");
        out.println("<input type=\"radio\" name=\"objeto\" value=\"Alfarero\">Alfarero</input>");
        out.println("<input type=\"radio\" name=\"objeto\" value=\"Brujo\">Brujo</input>");
        out.println("<input type=\"radio\" name=\"objeto\" value=\"Curtidor\">Curtidor</input>");
        out.println("<input type=\"submit\" value=\"Desconectar\"></input>");
        out.println("</form>");

        Cookie[] Contadorcookies = request.getCookies();
        Cookie contador = buscaCookie(Contadorcookies);

        Cookie[] oficios = request.getCookies();
        Cookie oficio = BuscarOficio(oficios);

        if (contador == null) {
            // Creamos la cookie con el contador
            Cookie cookie = new Cookie("Contador" + usuarios.getValue(), "1");
            cookie.setMaxAge(180);
            response.addCookie(cookie);
            // Mostramos el mensaje de primera visita

            out.println("<br/> <br/> <br/>");
            out.println("Primera visita");
            out.println("<BR>");

        } else {
            // Obtenemos el valor actual del contador
            int cont = Integer.parseInt(contador.getValue());
            cont++;

            Cookie cookie = new Cookie("Contador" + usuarios.getValue(), "" + cont);
            cookie.setMaxAge(180);
            response.addCookie(cookie);
            // Modificamos el valor de la cookie
            // incrementando el contador

            // Mostramos el numero de visitas
            PrintWriter outa = response.getWriter();
            outa.println("<HTML>");
            outa.println("<BODY>");
            outa.println("Visita numero " + cont);
            outa.println("<BR>");
            outa.println("</BODY>");
            outa.println("</HTML>");
        }

    }
    private Cookie BuscarUsuarios(Cookie[] cookies) {
        if (cookies == null) {
            return null;
        } else {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("Nombre_usuarios")) {
                    return cookies[i];
                }
            }
        }
        return null;
    }
    private String BuscarOficio(Cookie[] cookies) {
        Cookie usuario = BuscarUsuarios(cookies);
        String nombreUsu = usuario.getValue();

        if (cookies == null) {
            return null;
        } else {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("oficio" + nombreUsu)) {
                    return cookies[i].getValue();
                }
            }
        }
        return null;
    }

    private Cookie buscaCookie(Cookie[] cookies) {
        Cookie usuario = BuscarUsuarios(cookies);
        String nombreUsu = usuario.getValue();

        if (cookies == null)
            return null;
        for (int i = 0; i < cookies.length; i++){
            if (cookies[i].getName().equals("contador" + nombreUsu)) {
            return cookies[i];
        }
        }
        return null;
    }



    protected void procesaSolicitud(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        Map<String, String> usuarios = new HashMap<String, String>();
        usuarios.put("Manuel", "f1");
        usuarios.put("Rodri", "f1");
        usuarios.put("Gonzalo", "f3");
        String user = " ";
        String clave = " ";
        user = request.getParameter("usuario");
        clave = request.getParameter("contrasena");
        if (usuarios.containsKey(user) && Objects.equals(usuarios.get(user), clave)) {
            request.getSession().setAttribute("usuario",user);
            request.getSession().setAttribute("contrasena",clave);
            response.sendRedirect(getServletContext().getContextPath() + "/Recepcion");
        } else {
            response.sendRedirect(getServletContext().getContextPath()+"/index.html");
        }
    }
}

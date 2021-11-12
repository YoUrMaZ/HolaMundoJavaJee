package es.manu.holamundo;

import jakarta.servlet.ServletException;
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        procesaSolicitud(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        response.setContentType("text/html");
        String usuario = (String) request.getSession().getAttribute("usuario");
        Cookie miCookieUsu = new Cookie("nombre", usuario);
        miCookieUsu.setMaxAge(120);
        response.addCookie(miCookieUsu);

        PrintWriter out = response.getWriter();
        out.println("<p> Bienvenido: " + miCookieUsu.getValue());
        out.println("<br/> <br/> <br/>");

        out.println("<br/>");
        out.println("<form method=\"post\" action=\"Cookie\">");
        out.println("<input type=\"radio\" name=\"objeto\" value=\"Alfarero\">Alfarero</input>");
        out.println("<input type=\"radio\" name=\"objeto\" value=\"Brujo\">Brujo</input>");
        out.println("<input type=\"radio\" name=\"objeto\" value=\"Curtidor\">Curtidor</input>");
        out.println("<input type=\"submit\" value=\"Desconectar\"></input>");
        out.println("</form>");

        Cookie[] Contadorcookies = request.getCookies();
        Cookie contador = buscaPersona(Contadorcookies);
        if (contador == null) {
            // Creamos la cookie con el contador
            Cookie cookie = new Cookie("nombre", "1");
            cookie.setMaxAge(180);
            response.addCookie(cookie);
            // Mostramos el mensaje de primera visita
            out.println("<HTML>");
            out.println("<BODY>");
            out.println("Primera visita");
            out.println("<BR>");
            out.println("</BODY>");
            out.println("</HTML>");
        } else {
            // Obtenemos el valor actual del contador
            int cont = Integer.parseInt(contador.getValue());
            cont++;
            // Modificamos el valor de la cookie
            // incrementando el contador
            Cookie cookie = new Cookie("contador", "" + cont);
            cookie.setMaxAge(180);
            response.addCookie(cookie);
            // Mostramos el numero de visitas
            PrintWriter outa = response.getWriter();
            outa.println("<HTML>");
            outa.println("<BODY>");
            outa.println("Visita numero " + cont);
            outa.println("<BR>");
            outa.println("</BODY>");
            outa.println("</HTML>");
        }
        PrintWriter outc = response.getWriter();
        outc.println("<html><body>");
        outc.println("<p>Curtidor</p>" + "<input type=\"radio\">");


    }
    private Cookie buscaCookie(Cookie[] cookies) {
        if (cookies == null)
            return null;
        for (int i = 0; i < cookies.length; i++)
            if (cookies[i].getName().equals("contador"))
                return cookies[i];
        return null;
    }
    private Cookie buscaPersona(Cookie[] cookies) {
        if (cookies == null)
            return null;
        for (int i = 0; i < cookies.length; i++)
            if (cookies[i].getName().equals("Nombre"))
                return cookies[i];
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

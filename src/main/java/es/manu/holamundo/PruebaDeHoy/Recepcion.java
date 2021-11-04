package es.manu.holamundo.PruebaDeHoy;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "/Recepcion", value = "/Recepcion")
public class Recepcion extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
/*
        String usuario = request.getParameter("usuario");
        String contrasena = request.getParameter("contrasena");

 */
        Cookie miCookieUsu = new Cookie ("nombre", "Pepe");
        miCookieUsu.setMaxAge(120);
        response.addCookie(miCookieUsu);
        PrintWriter out = response.getWriter();
        out.println("<p> Bienvenido: " + miCookieUsu.getValue());
        out.println("<br/> <br/> <br/>");

        Cookie miCookiePass = new Cookie ("contrasena", "Pepe");
        miCookiePass.setMaxAge(120);
        response.addCookie(miCookiePass);
        PrintWriter outb = response.getWriter();
        outb.println("<p> Bienvenido: " + miCookiePass.getValue());
        outb.println("<br/> <br/> <br/>");





        Cookie[] Contadorcookies = request.getCookies();
        Cookie contador = buscaCookie(Contadorcookies);



        if (contador == null)
        {
            // Creamos la cookie con el contador

            Cookie cookie = new Cookie ("contador", "1");
            cookie.setMaxAge(180);
            response.addCookie(cookie);

            // Mostramos el mensaje de primera visita


            out.println ("<HTML>");
            out.println ("<BODY>");
            out.println ("Primera visita");
            out.println ("<BR>");
            out.println ("</BODY>");
            out.println ("</HTML>");

        } else {

            // Obtenemos el valor actual del contador

            int cont = Integer.parseInt(contador.getValue());
            cont++;

            // Modificamos el valor de la cookie
            // incrementando el contador

            Cookie cookie = new Cookie ("contador", "" + cont);
            cookie.setMaxAge(180);
            response.addCookie(cookie);

            // Mostramos el numero de visitas

            PrintWriter outa = response.getWriter();
            outa.println ("<HTML>");
            outa.println ("<BODY>");
            outa.println ("Visita numero " + cont);
            outa.println ("<BR>");
            outa.println ("</BODY>");
            outa.println ("</HTML>");
        }
    }

    private Cookie usuariosTotal(String nombre, Cookie[] usuariosTotal) {
        if (usuariosTotal == null)
            return null;

        for (int i = 0; i < usuariosTotal.length; i++)
            if (usuariosTotal[i].getName().equals(nombre))
                return usuariosTotal[i];

        return null;
    }


    private Cookie buscaCookie(Cookie[] cookies)
    {
        if (cookies == null)
            return null;

        for (int i = 0; i < cookies.length; i++)
            if (cookies[i].getName().equals("contador"))
                return cookies[i];

        return null;
    }


    protected void probemos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

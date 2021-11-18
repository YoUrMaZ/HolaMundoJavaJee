package es.manu.holamundo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Cookies", value = "/Cookies")
public class Cookies extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String trabajo = request.getParameter("objeto");
        Cookie[] cookies = request.getCookies();
        String usu = null;
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals("Nombre_usuarios")) {
                usu = cookies[i].getValue();
            }
        }


        Cookie oficio = new Cookie("oficio" + usu, "" + trabajo);
        response.addCookie(oficio);

        String rutaContext = request.getContextPath();
        String destino = "/index.html";
        response.sendRedirect(rutaContext + destino);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}

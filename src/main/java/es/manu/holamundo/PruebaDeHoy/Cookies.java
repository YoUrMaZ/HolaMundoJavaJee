package es.manu.holamundo.PruebaDeHoy;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Cookies", value = "/Cookies")
public class Cookies extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*
        Cookie[] cookies = request.getCookies();
        String nombre = " ";
        for (int i = 0; i < cookies.length; i++){
            if (cookies[i].getName().equals("nombre")){
                nombre = cookies[i].getValue();
            }
        }
 */
        String nombre = "pepe";

        //lee la cookie de la peticion del navegador
        Cookie[] lasCookies = request.getCookies();

        if (lasCookies != null){
            for (Cookie cookie_temporal: lasCookies){
                if ("nombre".equals(cookie_temporal.getName())){
                    nombre = cookie_temporal.getValue();

                    break;
                }
            }
        }
    }
}

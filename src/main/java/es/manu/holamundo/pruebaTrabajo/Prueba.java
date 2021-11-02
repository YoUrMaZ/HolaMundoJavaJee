package es.manu.holamundo.pruebaTrabajo;

import jakarta.servlet.annotation.WebServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "/prueba", value = "/prueba")
public class Prueba extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("Nombre introducido " + request.getParameter("nombre"));

        out.println("<br><br>");
        out.println("Nombre introducido " + request.getParameter("apellido"));

        out.println("</body></html>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("Nombre introducido " + request.getParameter("nombre"));

        out.println("<br><br>");
        out.println("Nombre introducido " + request.getParameter("apellido"));

        out.println("</body></html>");
    }
}

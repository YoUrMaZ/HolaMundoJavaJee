package es.manu.holamundo;

import jakarta.inject.Inject;
import jakarta.jms.Message;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet (HelloServletCDI.URl)
public class HelloServletCDI extends HttpServlet {

    public static final String URl = "HelloServletCDI";

    @Inject
    private Message mensaje;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        PrintWriter printWriter = response.getWriter();
        response.setContentType("text/plain;charset=UTF-8");


    }
}
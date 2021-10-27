package es.manu.holamundo.cdi;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.MediaType;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(ScopesServlet.URL)
class ScopesServlet extends HttpServlet {

    public static final String URL = "scopesServlet";

    private final ApplicationScopedDependency applicationScoped;

    @Inject
    public ScopesServlet(ApplicationScopedDependency applicationScoped) {
        this.applicationScoped = applicationScoped;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter printWriter = response.getWriter();
        response.setContentType(MediaType.TEXT_PLAIN);
        printWriter.print(applicationScoped.getTimestamp());
    }

}
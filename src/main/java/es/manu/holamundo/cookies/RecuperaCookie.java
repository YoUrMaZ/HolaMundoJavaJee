package es.manu.holamundo.cookies;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RecuperaCookie")
public class RecuperaCookie extends HttpServlet {
  private static final String UTF_8 = "UTF-8";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        procesaSolicitud(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        procesaSolicitud(request, response);
    }

    protected void procesaSolicitud(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Crea y Recupera</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Recupera Cookie</h1>");
            out.println("<h2>Cookies recibidas:</h2>");

            // Recepción de cookies
            Cookie[] arrayCookies = request.getCookies();
            if (arrayCookies.length > 0) {
              for (Cookie arrayCookie : arrayCookies) {
                String contenidoCookie = arrayCookie.getValue();
                out.print("Nombre: <b>" + arrayCookie.getName() + "</b>" + " <br />");
                out.print("Contenido: " + contenidoCookie + "<br />");
                Map<String, List<String>> parametros = splitQuery(contenidoCookie);

                if (!parametros.isEmpty()) {
                  for (Entry<String, List<String>> elemento : parametros.entrySet()) {
                    String clave = elemento.getKey();
                    List<String> valores = elemento.getValue();
                    out.print("&emsp;&emsp;" + clave + " = [&nbsp;");
                    for (String valor : valores) {
                      out.print(valor + "&nbsp;");
                    }
                    out.print("]" + "<br />");
                  }
                }

              }
            }
            else {
                out.print("El navegador no ha enviado ninguna cookie");
            }

            out.println("</body>");
            out.println("</html>");
        }
        catch (Exception e){
            out.println("Se produce una excepción <br />");
            e.printStackTrace(out);
        }
        finally {
            out.close();
        }
    }

    // extrae los pares campo=valor de una QUERY_STRING
    // trata a cada parámetro de manera independiente, aunque se llamen igual
    // no permite valores nulos
    private static Map<String, String> extraeDatosDeQueryString(String queryString) throws UnsupportedEncodingException {
        Map<String, String> queryPairs = new LinkedHashMap<>();
        String[] parametros = queryString.split("&");
        if (parametros.length > 0) {  // la cadena de texto tiene estructura de QUERY_STRING
	        for (String pair : parametros) {
	            int idx = pair.indexOf("=");
	            queryPairs.put(URLDecoder.decode(pair.substring(0, idx), UTF_8), URLDecoder.decode(pair.substring(idx + 1), UTF_8));
	        }
        }
        return queryPairs;
    }

    // extrae los pares campo=valor de una QUERY_STRING
    // si un parámetro se envía con múltiples valores, lo "procesa" como si fuera un array
    // permite valores nulos
    private static Map<String, List<String>> splitQuery(String queryString) throws UnsupportedEncodingException {
    	  Map<String, List<String>> queryPairs = new LinkedHashMap<>();
    	  String[] parametros = queryString.split("&");
    	  for (String pair : parametros) {
    		  int idx = pair.indexOf("=");
    		  String clave = idx > 0 ? URLDecoder.decode(pair.substring(0, idx), UTF_8) : pair;
    		  if (!queryPairs.containsKey(clave)) {
    			  queryPairs.put(clave, new LinkedList<>());
    		  }
    		  String valor = idx > 0 && pair.length() > idx + 1 ? URLDecoder.decode(pair.substring(idx + 1), UTF_8) : null;
    		  queryPairs.get(clave).add(valor);
    	  }
    	  return queryPairs;
    }
}

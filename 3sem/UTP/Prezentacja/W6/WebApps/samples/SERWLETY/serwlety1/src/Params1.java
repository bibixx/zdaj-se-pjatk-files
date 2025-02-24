import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.text.*;

public class Params1 extends HttpServlet {

  public void serviceRequest(HttpServletRequest req,
                             HttpServletResponse resp)
                             throws ServletException, IOException
  {
    PrintWriter out = resp.getWriter();
    out.println("Metoda: " + req.getMethod());

    // Przy zleceniu POST
    // Albo uzyskujemy parametry przez metody getParameter...
    // albo czytamy je ze strumienia jako "cia³o" zlecenia
    // ale nie równoczeœnie i to i to

    boolean readBodyStream = true;  // czytamy jako parametry

    if (!readBodyStream) {
      out.println("Query : " + req.getQueryString());
      out.println("Parametry:");
      Enumeration pnams = req.getParameterNames();
      while (pnams.hasMoreElements()) {
        String name = (String) pnams.nextElement();
        String value = req.getParameter(name);
        out.println(name + " = "  + value);
      }
      out.println("Dostêp przez mapê");
      Map map = req.getParameterMap();
      String[] val = (String[]) map.get("ident");
      out.println("Parametr o nazwie ident");
      out.println("- z mapy uzyskujemy tablice String[]");
      out.println("- jej rozmiar " + val.length );
      out.println("- jej elementy:" );
      for (int i=0; i<val.length; i++) out.println(val[i]);
    }
    else {
      out.println("Czytanie treœci (cia³a) zlecenia ze strumienia:");
      BufferedReader br = req.getReader();
      String line;
      while ((line = br.readLine()) != null) out.println(line);
      br.close();
    }

    out.close();
  }


  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
                 throws ServletException, IOException
  {
      serviceRequest(request, response);
  }

  public void doPost(HttpServletRequest request,
                    HttpServletResponse response)
                 throws ServletException, IOException
  {
      serviceRequest(request, response);
  }

}
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.text.*;

public class ClientTester extends HttpServlet {

  public void serviceRequest(HttpServletRequest req,
                             HttpServletResponse resp)
                             throws ServletException, IOException
  {

    Locale loc = req.getLocale();
    req.setCharacterEncoding("UTF-8");
    resp.setCharacterEncoding("UTF-8");
    PrintWriter out = resp.getWriter();
    out.println("Klient wys³a³ zlecenie");
    String method = req.getMethod();
    out.println("Metoda: " + method);
    out.println("Locale: " + req.getLocale());
    out.println("Character encoding: " + req.getCharacterEncoding());

    boolean readBodyStream = false;
    out.println("Nag³ówki:");
    Enumeration headers = req.getHeaderNames();
    while(headers.hasMoreElements()) {
      String header = (String) headers.nextElement();
      String val = req.getHeader(header);
      if (method.equals("POST") && header.equals("read-body-stream"))
        if (val.equals("true")) readBodyStream = true;
      out.println(header + ": " + val);
    }

    if (!readBodyStream) {
      out.println("Treœæ zlecenia : " + req.getQueryString());
      out.println("Parametry:");
      Enumeration pnams = req.getParameterNames();
      while (pnams.hasMoreElements()) {
        String name = (String) pnams.nextElement();
        String value = req.getParameter(name);
        out.println(name + " = "  + value);
      }
    }
    else {
      out.println("Co serwer odczyta³ ze strumienia zlecenia:");
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
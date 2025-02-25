import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.text.*;

public class Time1 extends HttpServlet {

  public void doGet(HttpServletRequest request,
                       HttpServletResponse response)
                 throws ServletException, java.io.IOException
  {
     // Jaki jest preferowany j�zyk klienta?
     Locale locale = request.getLocale();

     // Uzyskanie odpowiedniego zloaklizowanego zasobu
     ResourceBundle msg = ResourceBundle.getBundle(
                             "international.Messages", locale);

     // Zlokalizowane komunikaty i odpowiednia strona kodowa
     String hello = msg.getString("hello");
     String now = msg.getString("now");
     String charset = msg.getString("charset");

     // Ustalenie typu i kodowania odpowiedzi
     // Musi by� ustalone przed uzyskaniem strumienia wyj�ciowego
     response.setContentType("text/html; charset=" + charset);

     // Pobranie strumienia wyj�ciowego
     // z zapewnieniem w�asciwego kodowania
     PrintWriter out = response.getWriter();

     out.println("<h2>" + hello + "<br>" + now + "<br>" );
     out.println(getDate(locale) + "</h2>");
     out.close();
  }


  private String getDate(Locale loc) {
    Date data = new Date();
    DateFormat df = DateFormat.getDateTimeInstance(DateFormat.LONG,
                                                   DateFormat.MEDIUM,
                                                   loc);
    return df.format(data);
  }


}
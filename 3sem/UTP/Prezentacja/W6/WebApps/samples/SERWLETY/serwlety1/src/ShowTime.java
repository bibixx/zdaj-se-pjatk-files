import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.text.*;
import java.nio.charset.*;

public class ShowTime extends HttpServlet {


  public void doGet(HttpServletRequest request,
                       HttpServletResponse response)
                 throws ServletException, java.io.IOException
  {
     HttpSession ses = request.getSession();
     ResourceBundle msgBundle = (ResourceBundle) ses.getAttribute("Messages");

     Locale locale = request.getLocale();
     if (msgBundle == null) {
         msgBundle = ResourceBundle.getBundle(
                     "international.Messages", locale);
         ses.setAttribute("Messages", msgBundle);
     }

     String hello = msgBundle.getString("hello");
     String now = msgBundle.getString("now");
     String charset = msgBundle.getString("charset");
     String data = getDate(new Date(), locale);

     String creSesDat = getDate(new Date(ses.getCreationTime()), locale);
     String lastAccDat = getDate(new Date(ses.getLastAccessedTime()), locale);

     response.setContentType("text/html; charset=" + charset);

     PrintWriter out = new PrintWriter(
        new OutputStreamWriter(response.getOutputStream(), charset),
        true);

     out.println("<h2>" + hello + "<br>" + now + "<br>" );
     out.println(data + "</h2><hr>");
     out.println("<br><br>Session ID: " + ses.getId());
     out.println("<br>Created: " + creSesDat);
     out.println("<br>Last accessed: " + lastAccDat);
     out.println("Dziekuje");
     out.close();
  }


  private String getDate(Date data, Locale loc) {
    DateFormat df = DateFormat.getDateTimeInstance(DateFormat.LONG,
                                                   DateFormat.MEDIUM,
                                                   loc);
    return df.format(data);
  }


}
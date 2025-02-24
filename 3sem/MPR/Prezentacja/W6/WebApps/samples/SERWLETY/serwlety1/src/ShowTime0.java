import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.text.*;

public class ShowTime0 extends HttpServlet {

  public void serviceRequest(HttpServletRequest req,
                             HttpServletResponse resp)
                             throws ServletException, IOException
  {
     Locale locale = req.getLocale();
     ResourceBundle msg = ResourceBundle.getBundle(
                          "international.Messages", locale);
     String hello = msg.getString("hello");
     String now = msg.getString("now");

     String charset = msg.getString("charset");
     resp.setContentType("text/html; charset=" + charset);

     PrintWriter out = new PrintWriter(
        new OutputStreamWriter(resp.getOutputStream(), charset),
        true);

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

//--- Stanadardowa czêœæ serwletu -----------------------------------
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
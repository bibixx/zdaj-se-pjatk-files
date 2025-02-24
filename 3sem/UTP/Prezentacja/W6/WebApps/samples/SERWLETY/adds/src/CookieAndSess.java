import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class CookieAndSess extends HttpServlet {

  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {

      resp.setContentType("text/html");
      PrintWriter out = resp.getWriter();

      Cookie[] cookies = req.getCookies();
      Cookie countCookie = null;
      HttpSession session = null;

      if (cookies != null) {
        for (int i=0; i<cookies.length; i++) {
          String name = cookies[i].getName();
          String value = cookies[i].getValue();
          out.println("<br>" + name + " " + value);
          if (name.equals("count")) {
            countCookie = cookies[i];
            int count = Integer.parseInt(countCookie.getValue()) + 1;
            countCookie.setValue(String.valueOf(count));
            if (count > 3) session = req.getSession();
            }
          }
        }
      if (session != null) {
        out.println("<hr>");
        out.println("Sesja: " + session.getId());
      }
      if (countCookie == null) countCookie = new Cookie("count", "1");
      resp.addCookie(countCookie);
      out.close();
  }

}
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import listeners.Report;
import listeners.SwiadomyAtrybut;

public class ListenersTest extends HttpServlet {

  static int count = 0;  // dla zmian "Liczby" - atrybuty kontekstu


  SwiadomyAtrybut sa = new SwiadomyAtrybut(
                       "jestem atrybutem, co wie kiedy go dodaj¹ lub usuwaj¹"
   );


  public void init() {
    Report.add("Inicjacja serwletu");
  }

  public void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
      resp.setContentType("text/html; charset=windows-1250");
      PrintWriter out = resp.getWriter();
      out.println("<center><h2>Testowanie s³uchaczy</h2></center>");
      req.getRequestDispatcher("form.html").include(req,resp);

      HttpSession ses = req.getSession();
      if (req.getParameter("setsesatt1") != null) {
          Report.add("<b><i>--- wybrano 'Normalny atrybut sesji (set)'</b></i>");
          ses.setAttribute("atr_ses1", "jestem zwyk³ym atrybutem sesji");
      }
      else if (req.getParameter("remsesatt1") != null) {
        Report.add("<b><i>--- wybrano 'Usuñ normalny atrybut sesji'</b></i>");
          ses.removeAttribute("atr_ses1");
      }
      else if (req.getParameter("setsesatt2") != null) {
        Report.add("<b><i>--- wybrano 'Œwiadomy atrybut sesji (set)'</b></i>");
        ses.setAttribute("atr_ses2", sa);
      }
      else if (req.getParameter("remsesatt2") != null) {
        Report.add("<b><i>--- wybrano 'Usuñ œwiadomy atrybut sesji'</b></i>");
        ses.removeAttribute("atr_ses2");
      }
      else if (req.getParameter("chgatt") != null) {
        count++;
        if (count > 1) {
           Report.add("<b><i>--- wybrano 'Zmieñ atrybut kontekstu'</b></i>");
           getServletContext().setAttribute("Liczba", new Integer(count));
        }
      }

      out.println("<hr>");
      out.println("<u>Co siê dzia³o ?</u>");
      List list = Report.get();
      out.println("<ol>");
      for (Iterator it = list.iterator(); it.hasNext(); ) {
        out.println("<li>" + it.next() + "</li>");
      }
      out.println("</ol>");
      out.close();
  }
}
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.text.*;

public class ResultPresent extends HttpServlet {


  public void serviceRequest(HttpServletRequest req,
                             HttpServletResponse resp)
                             throws ServletException, IOException
  {
    ServletContext context = getServletContext();

    // W³¹czenie strony generowanej przez serwlet pobierania parametrów
    // (formularz)
    String getParamsServ = context.getInitParameter("getParamsServ");
    RequestDispatcher disp = context.getRequestDispatcher(getParamsServ);
    disp.include(req,resp);

    // Uzyskanie wyników i wyprowadzenie ich
    // Controller po wykonaniu Command zapisa³ w atrybutach sesji
    // - referencje do listy wyników jako atrybut "Results"
    // - wartoœc kodu wyniku wykonania jako atrybut "StatusCode"

    HttpSession ses = req.getSession();
    List results = (List) ses.getAttribute("Results");
    Integer code = (Integer) ses.getAttribute("StatusCode");

    PrintWriter out = resp.getWriter();
    out.println("<hr>");

    // Uzyskanie napisu w³aœciwego dla danego "statusCode"
    String msg = BundleInfo.getStatusMsg()[code.intValue()];
    out.println("<h2>" + msg + "</h2>");

    // Elementy danych wyjœciowych (wyników) mog¹ byæ
    // poprzedzane jakimiœ opisami (zdefiniowanymi w ResourceBundle)
    String[] dopiski = BundleInfo.getResultDescr();

    // Generujemy raport z wyników
    out.println("<ol>");
    for (Iterator iter = results.iterator(); iter.hasNext(); ) {
      out.println("<li>");

      int dlen = dopiski.length;  // d³ugoœæ tablicy dopisków
      Object res = iter.next();
      if (res.getClass().isArray()) {  // jezeli element wyniku jest tablic¹
        Object[] res1 = (Object[]) res;
        int i;
        for (i=0; i < res1.length; i++) {
          String dopisek = (i < dlen ? dopiski[i] + " " : "");
          out.print(dopisek + res1[i] + " ");
        }
        if (dlen > res1.length) out.println(dopiski[i]);
      }
      else {                                      // mo¿e nie byæ tablic¹
        if (dlen > 0) out.print(dopiski[0] + " ");
        out.print(res);
        if (dlen > 1) out.println(" " + dopiski[1]);
      }
      out.println("</li>");
    }
    out.println("</ol>");
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
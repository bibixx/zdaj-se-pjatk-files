import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.nio.charset.*;


// SERWLET POBIERANIA PARAMETRÓW

public class GetParamsServ extends HttpServlet {

  private ServletContext context;
  private String resBundleServ;    // nazwa serwletu przygotowuj¹cego
                                   // sparmetryzowan¹ informacje


  // Inicjacja
  public void init() {
    context = getServletContext();
    resBundleServ = context.getInitParameter("resBundleServ");
  }

  // Obs³uga zleceñ
  public void serviceRequest(HttpServletRequest req,
                             HttpServletResponse resp)
                             throws ServletException, IOException
  {


    // W³¹czenie serwletu przygotowuj¹cego informacje z z zasobów
    // (ResourceBundle). Informacja bêdzie dostêpna poprzez
    // statyczne metody klasy BundleInfo

    RequestDispatcher disp = context.getRequestDispatcher(resBundleServ);
    disp.include(req, resp);

    // Pobranie potrzebnej informacji
    // ktora zosta³a wczesniej przygotowana
    // przez klasê BundleInfo na podstawie zlokalizowanych zasobów

    // Zlokalizowana strona kodowa
    String charset = BundleInfo.getCharset();

    // Napisy nag³ówkowe
    String[] headers = BundleInfo.getHeaders();

    // Nazwy parametrów (pojawi¹ siê w formularzu,
    // ale równie¿ s¹ to nazwy parametrów dla Command)
    String[] pnames = BundleInfo.getCommandParamNames();

    // Opisy parametrów - aby by³o wiadomo co w formularzu wpisywaæ
    String[] pdes   = BundleInfo.getCommandParamDescr();

    // Napis na przycisku
    String submitMsg = BundleInfo.getSubmitMsg();

    // Ew. koñcowe napisy na stronie
    String[] footers = BundleInfo.getFooters();

    // Ustalenie w³aœciwego kodowania zlecenia
    // - bez tego nie bêdzie mo¿na w³asciwie odczytaæ parametrów
    req.setCharacterEncoding(charset);

    // Pobranie aktualnej sesji
    // w jej atrybutach s¹/bêd¹ przechowywane
    // wartoœci parametrów

    HttpSession session = req.getSession();

    // Generowanie strony

    resp.setCharacterEncoding(charset);
    PrintWriter out = resp.getWriter();

    out.println("<center><h2>");
    for (int i=0; i<headers.length; i++)
       out.println(headers[i]);
    out.println("</center></h2><hr>");

       // formularz
    out.println("<form method=\"post\">");
    for (int i=0; i<pnames.length; i++) {
     out.println(pdes[i] + "<br>");
     out.print("<input type=\"text\" size=\"70\" name=\"" +
                   pnames[i] +  "\"");

       // Jezeli s¹ ju¿ wartoœci parametrów - poka¿emy je w formularzu
      String pval = (String) session.getAttribute("param_"+pnames[i]);
      if (pval != null) out.print(" value=\"" + pval + "\"");
      out.println("><br>");
    }
    out.println("<br><input type=\"submit\" value=\"" + submitMsg + "\">");
    out.println("</form>");

    // Pobieranie parametrów z formularza

    for (int i=0; i<pnames.length; i++) {
      String paramVal = req.getParameter(pnames[i]);
         // Je¿eli brak parametru (ów) - konczymy
      if (paramVal == null) return;

      // Jest parametr - zapiszmy jego wartoœæ jako atrybut sesji.
      // Zostanie on pobrany przez Controller
      // który ustali te wartoœci dla wykonania Command

      session.setAttribute("param_" + pnames[i], paramVal);

    }
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
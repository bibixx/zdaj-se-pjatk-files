import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.util.regex.*;

public class RegexTest extends HttpServlet {

  private PrintWriter out;
  private void printEndTag() { out.println("</body></html>"); }

  public void serviceRequest(HttpServletRequest req,
                             HttpServletResponse resp)
                             throws ServletException, IOException
  {

    String charset = "ISO8859-2";
    resp.setContentType("text/html; charset=" + charset);
    out = resp.getWriter();
    out.println("<html>");
    out.println("<head><title>Testowanie</title></head>");
    out.println("<body>");

    // Nazw� pliku z formularzem dostarczymy
    // jako parametr inicjalny serwletu
    String formFile = getInitParameter("regexFormFile");

    // Przeczytamy go i wpiszemy na generowan� stron�

    ServletContext context = getServletContext();
    InputStream in = context.getResourceAsStream("/WEB-INF/"+formFile);
    BufferedReader br = new BufferedReader( new InputStreamReader(in));
    String line;
    while ((line = br.readLine()) != null) out.println(line);

    // Pobieramy parametry formularza
    String regex = req.getParameter("regex");
    String input = req.getParameter("input");

    // Przy pierwszym odwo�aniu do serwletu - parametr�w nie ma
    // zatem poprzestajemy na wygenerowaniu formularza

    if (regex == null || input == null) {
      printEndTag();
      out.close();
      return;
    }

    // W przeciwnym razie jakie� parametry (chco�by puste) ju� s�
    // Przetwarzamy je i uzupe�niamy stron� z formularzem

    out.println("<hr>");
    out.println("Wzorzec: \"" + regex + "\"<br>");
    out.println("Tekst  : \"" + input + "\"<br>");

    try {
      Pattern pattern = Pattern.compile(regex);
      Matcher matcher = pattern.matcher(input);
      boolean found = matcher.find();
      if (!found)
         out.println("<h3>Nie znaleziono �adnego pod�a�cucha " +
                      "pasuj�cego do wzorca</h3>");
      else {
          out.println("<h3>Dopasowano:</h3>");
          out.println("<ol>");
        do {
          out.println("<li>pod�a�cuch \"" + matcher.group() +
                   "\" od pozycji " + matcher.start() +
                   " do pozycji " + (matcher.end()-1) + "</li>");
        } while(matcher.find());
        out.println("</ol>");
      }
    } catch (PatternSyntaxException exc) {
        out.println("<h2>B��d w wyra�eniu</h2>");
    } finally {
        printEndTag();
        out.close();
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
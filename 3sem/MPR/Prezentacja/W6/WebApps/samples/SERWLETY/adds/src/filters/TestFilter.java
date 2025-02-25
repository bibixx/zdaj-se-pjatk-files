package filters;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class TestFilter implements Filter{

  private static int ind = 0;  // indeks og�oszenia

  // szablon og�oszenia
  private String szablon =
  "<table cellpadding=\"2\" cellspacing=\"2\" border=\"1\" width=\"100%\">"+
  "<tbody><tr><td valign=\"Top\" bgcolor=\"#000099\">" +
  "<div align=\"Center\"><font color=\"#ffffff\">@</font></div></td>"+
  "</tr></tbody></table>";

  // Lista og�osze�
  private List oglosz = new  ArrayList();

  // Tablica kodowa� dla r�nych "locale"
  private Properties encodings = new Properties();

  public void init(FilterConfig fc) throws ServletException {

    ServletContext sc = fc.getServletContext();

    // Strumie� dla tablicy kodowa�
    InputStream props = sc.getResourceAsStream("WEB-INF/encodings.properties");
    try {
      // za�adowanie tablicy kodowa�
      if (props != null) encodings.load(props);

      // Plik z og�oszeniami
      InputStream is = sc.getResourceAsStream("WEB-INF/ogloszenia.txt");
      BufferedReader br = new BufferedReader(
                          new InputStreamReader(is)
                          );
      String line;
      while ((line = br.readLine()) != null) oglosz.add(line);
      br.close();
    } catch (Exception exc) { oglosz.add("Witamy!"); }
  }


  public void doFilter(ServletRequest req, ServletResponse resp,
                       FilterChain chain)
                       throws IOException,ServletException
  {
    // Ustalenie kolejnego og�oszenia
    String msg;
    synchronized(this) {
      msg = szablon.replaceFirst("@", (String) oglosz.get(ind));
      if (ind < oglosz.size() - 1) ind++;
      else ind = 0;
    }

    // Ustalenie kodowania
    Locale locale =  req.getLocale();
    String charset = (String) encodings.get(locale);
    if (charset == null) charset = "windows-1250";
    resp.setContentType("text/html; charset=" + charset);

    // Generacja pocz�tku strony
    PrintWriter out = ((HttpServletResponse) resp).getWriter();
    out.println(msg);

    // Wywo�anie nast�pnego elementu FilterChain
    // zwykle ju� bezpo�rednio serwletu

    chain.doFilter(req, resp);
  }

  public void destroy() {

  }
}
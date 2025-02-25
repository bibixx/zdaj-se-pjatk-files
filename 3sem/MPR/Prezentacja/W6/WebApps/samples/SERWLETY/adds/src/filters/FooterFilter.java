package filters;
import java.io.*;
import java.util.*;
import java.text.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class FooterFilter implements Filter{

  public void init(FilterConfig p0) throws ServletException {
  }

  public void doFilter(ServletRequest req, ServletResponse resp,
                       FilterChain chain) throws IOException,ServletException {

    Locale locale = req.getLocale();
    StringResponseWrapper newResp = new StringResponseWrapper(
                                        (HttpServletResponse) resp
                                     );
    chain.doFilter(req, newResp);
    StringWriter sw = newResp.getStringWriter();

    // Uzyskujemy tre�� wygenrowanej odpowiedzi
    String cont = sw.toString();

    // Teraz mo�emy zrobi� cokolwiek z odpowiedzi�
    // tu tylko dopiszemy do niej "stopk�"

    // Bierzemy strumie� oryginalnej odpowiedzi
    PrintWriter out = resp.getWriter();

    // Przepisujemy otrzyman� odpowied�
    out.println(cont);

    // Dopisujemy stopk�
    DateFormat df = DateFormat.getDateTimeInstance(DateFormat.LONG,
                                                   DateFormat.MEDIUM,
                                                   locale);

    out.println("<hr><i><b>" + df.format(new Date()) + "</i></b>");
    out.close();
  }

  public void destroy() {
  }
}
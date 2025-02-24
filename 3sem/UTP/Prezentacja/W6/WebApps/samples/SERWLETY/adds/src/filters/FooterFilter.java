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

    // Uzyskujemy treœæ wygenrowanej odpowiedzi
    String cont = sw.toString();

    // Teraz mo¿emy zrobiæ cokolwiek z odpowiedzi¹
    // tu tylko dopiszemy do niej "stopkê"

    // Bierzemy strumieñ oryginalnej odpowiedzi
    PrintWriter out = resp.getWriter();

    // Przepisujemy otrzyman¹ odpowiedŸ
    out.println(cont);

    // Dopisujemy stopkê
    DateFormat df = DateFormat.getDateTimeInstance(DateFormat.LONG,
                                                   DateFormat.MEDIUM,
                                                   locale);

    out.println("<hr><i><b>" + df.format(new Date()) + "</i></b>");
    out.close();
  }

  public void destroy() {
  }
}
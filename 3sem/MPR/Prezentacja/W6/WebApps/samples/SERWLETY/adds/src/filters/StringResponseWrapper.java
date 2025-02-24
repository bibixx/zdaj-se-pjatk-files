package filters;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;


public class StringResponseWrapper extends HttpServletResponseWrapper {

  // Strumien do którego bêd¹ pisane odpowiedzi
  private StringWriter stringWriter = null;

  public StringResponseWrapper(HttpServletResponse response) {
    super(response);
  }

  // Przedefiniowanie metody getWriter()
  // ka¿dy kto ejj u¿yje - otrzyma nasz strumieñ StringWriter
  // i nic o tym nie wiedz¹c bêdzie pisa³ do niego
  // a nie do strumienia
  // zwi¹zanego z oryginalnym obiektem HttpServletResponse


  public PrintWriter getWriter() throws IOException {
    if (stringWriter == null) stringWriter = new StringWriter();
    return new PrintWriter(stringWriter);
  }

  // Nie jesteœmy przygotowani na obs³ugê strumieni binarnych
  // - wykluczamy ich zastosowanie (chocia¿ nie musimy tego robiæ)

  public ServletOutputStream getOutputStream() throws IOException {
    throw new IllegalStateException(
      "getOutputStream() not allowed for StringResponseWrapper"
      );
  }

  // Nasza w³asna metoda, pozwlaj¹ca uzyskaæ dostêp do strumioenia
  // i do jego zawartoœci

  public StringWriter getStringWriter() {
    return stringWriter;
  }

}


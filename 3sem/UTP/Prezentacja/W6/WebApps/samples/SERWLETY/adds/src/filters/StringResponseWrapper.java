package filters;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;


public class StringResponseWrapper extends HttpServletResponseWrapper {

  // Strumien do kt�rego b�d� pisane odpowiedzi
  private StringWriter stringWriter = null;

  public StringResponseWrapper(HttpServletResponse response) {
    super(response);
  }

  // Przedefiniowanie metody getWriter()
  // ka�dy kto ejj u�yje - otrzyma nasz strumie� StringWriter
  // i nic o tym nie wiedz�c b�dzie pisa� do niego
  // a nie do strumienia
  // zwi�zanego z oryginalnym obiektem HttpServletResponse


  public PrintWriter getWriter() throws IOException {
    if (stringWriter == null) stringWriter = new StringWriter();
    return new PrintWriter(stringWriter);
  }

  // Nie jeste�my przygotowani na obs�ug� strumieni binarnych
  // - wykluczamy ich zastosowanie (chocia� nie musimy tego robi�)

  public ServletOutputStream getOutputStream() throws IOException {
    throw new IllegalStateException(
      "getOutputStream() not allowed for StringResponseWrapper"
      );
  }

  // Nasza w�asna metoda, pozwlaj�ca uzyska� dost�p do strumioenia
  // i do jego zawarto�ci

  public StringWriter getStringWriter() {
    return stringWriter;
  }

}


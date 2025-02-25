import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class Msg extends HttpServlet {

  // Pocz�tek HTML i w�a�ciwo�ci <body> - t�o, kolor tekstu i link�w 
  private String prolog =
                 "<html><title>Przyk�ad</title>" +
                 "<body background=\"images/os2.jpg\" text=\"antiquewhite\"" +
                 "link=\"white\" vlink=\"white\">";

  // Tagi zamykaj�ce
  private String epilog = "</body></html>";


  // Metoda obs�ugi zlecenia GET

  public void doGet(HttpServletRequest request,
                       HttpServletResponse response)
                 throws ServletException, IOException
  {
     // Mo�emy w ten spos�b ustali� typ tre�ci i stron� kodow�
     // �atwiej ni� przez generowanie metatag�w HTML

     response.setContentType("text/html; charset=ISO-8859-2");

     // Strumie� wyj�ciowy, tu generowana tre�� strony HTML 
     // PrintWriter umo�liwia u�ycie metod print i println

     PrintWriter out = response.getWriter();


     out.println(prolog);  // piszemy pocz�tek html i tag <body ... >

     // Piszemy tre��
     out.println("<h1>Dokument HTML<br>wygenerowany przez serwlet</h1>");
     out.println("<br><br><a href=\"Bye.html\">Po�egnanie</a>");

     // Znaczniki zamykaj�ce
     out.println(epilog);
     out.close();
  }

}
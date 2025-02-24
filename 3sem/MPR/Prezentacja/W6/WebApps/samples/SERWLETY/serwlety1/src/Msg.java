import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class Msg extends HttpServlet {

  // Pocz¹tek HTML i w³aœciwoœci <body> - t³o, kolor tekstu i linków 
  private String prolog =
                 "<html><title>Przyk³ad</title>" +
                 "<body background=\"images/os2.jpg\" text=\"antiquewhite\"" +
                 "link=\"white\" vlink=\"white\">";

  // Tagi zamykaj¹ce
  private String epilog = "</body></html>";


  // Metoda obs³ugi zlecenia GET

  public void doGet(HttpServletRequest request,
                       HttpServletResponse response)
                 throws ServletException, IOException
  {
     // Mo¿emy w ten sposób ustaliæ typ treœci i stronê kodow¹
     // ³atwiej ni¿ przez generowanie metatagów HTML

     response.setContentType("text/html; charset=ISO-8859-2");

     // Strumieñ wyjœciowy, tu generowana treœæ strony HTML 
     // PrintWriter umo¿liwia u¿ycie metod print i println

     PrintWriter out = response.getWriter();


     out.println(prolog);  // piszemy pocz¹tek html i tag <body ... >

     // Piszemy treœæ
     out.println("<h1>Dokument HTML<br>wygenerowany przez serwlet</h1>");
     out.println("<br><br><a href=\"Bye.html\">Po¿egnanie</a>");

     // Znaczniki zamykaj¹ce
     out.println(epilog);
     out.close();
  }

}
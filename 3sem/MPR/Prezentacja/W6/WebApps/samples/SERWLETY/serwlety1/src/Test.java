import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class Test extends HttpServlet {

  static String loadMsg = "Klasa zaladowana " + new Date();
  String createMsg = "\nSerwlet utworzony  " + new Date();
  String initMsg;
  String config1Msg = "\nW konstruktorze ServletConfig ",
         config2Msg = "\nW metodzie init ServletConfig ";

  public Test() {
    ServletConfig conf = getServletConfig();
    if (conf == null)
      config1Msg += "nie istnieje,\n" +
         "zatem nie ma dostepu do kontekstu i inicjalnych parametr�w";
    else config1Msg +=  " istnieje !!??";
  }

  public void init() {
    initMsg  = "\nSerwlet zainicjowany " + new Date();
    ServletConfig conf = getServletConfig();
    if (conf == null) config2Msg +=  "nie istnieje !!??";
    else {
      config2Msg += "istnieje.\nMozemy odwolac sie do kontekstu" +
                     " i inicjalnych parametr�w:\n";

    // Uzyskanie kontekstu i dost�p do niego - dwa r�wnowa�ne sposoby
    ServletContext context1 = conf.getServletContext();
          // poni�sze odwolanie oznacza getConfig().getServletContext()
    ServletContext context2 = getServletContext();
    String name = context2.getServletContextName();
    }
  }

  PrintWriter out;

  public void service(HttpServletRequest req, HttpServletResponse resp)
         throws ServletException, IOException
  {
     out = resp.getWriter();
     out.println(loadMsg);
     out.println(createMsg);
     out.println(config1Msg);
     out.println(config2Msg);
     out.println("obsluga zlecenia przez metode service " + new Date());

     // Jezeli przedefinujemy metod� servis
     // zazwyczaj b�dziemy wo�a� super.service(...)
     // by przekaza� zlecenie do obs�ugi przez konkretne metody
     // np. doGet lub doPost




     super.service(req, resp);
     out.close();
  }


  public void doGet(HttpServletRequest req, HttpServletResponse resp)
                 throws ServletException, IOException
  {


    out.println("\nWywolana metoda doGet " + new Date());
    out.println("\nJakiej klasy sa zlecenia?");
    Class c = req.getClass();
    out.println("Oryginalna klasa zlecenia: " + c.getName());
    while (c != Object.class){
      c = c.getSuperclass();
      out.println(" - dziedziczy " + c.getName());
    }
    out.println("\nCo zawiera zlecenie?");
    out.println("Query string: " + req.getQueryString());
    BufferedReader br = new BufferedReader( req.getReader());
    String line;
    while ((line = br.readLine()) != null) out.println(line);
    out.close();
  }


}
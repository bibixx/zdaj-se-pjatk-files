import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class Test2 extends HttpServlet {

  static String loadMsg = "Klasa zaladowana ";
  static int servletInstance = 0;
  String createMsg;
  String initMsg;
  String config1Msg = "\nW konstruktorze ServletConfig ",
         config2Msg = "\nW metodzie init ServletConfig ";

  public Test2() {
    servletInstance++;
    createMsg = "\nUtworzony " + servletInstance + " egzemparz serwletu";
    ServletConfig conf = getServletConfig();
    if (conf == null)
      config1Msg += "nie istnieje,\n" +
         "zatem nie ma dostepu do kontekstu i inicjalnych parametrów";
    else config1Msg +=  " istnieje !!??";
  }

  public void init() {
    String initMsg  =
           "\nSerwlet, egzemplarz " + servletInstance + " zainicjowany";
    ServletConfig conf = getServletConfig();
    if (conf == null) config2Msg +=  "nie istnieje !!??";
    else
      config2Msg += "istnieje.\nMozemy odwolac sie do kontekstu" +
                     " i inicjalnych parametrów:\n";
    log(loadMsg);
    log(createMsg);
    log(config1Msg);
    log(initMsg);
    log(config2Msg);
  }


  public void service(HttpServletRequest req, HttpServletResponse resp)
         throws ServletException, IOException
  {
     log("Obsluga zlecenia przez metode service");
     log("Egzemplarz sewrwletu " + servletInstance);
     log("Metoda " + req.getMethod());
     log("User-agent " + req.getHeader("user-agent"));
     super.service(req, resp);
  }


  public void doGet(HttpServletRequest req, HttpServletResponse resp)
                 throws ServletException, IOException
  {
    PrintWriter out = resp.getWriter();
    out.println("\nWywolana metoda doGet " + new Date());
    out.close();
  }

  public void destroy() {
    log("Serwlet, egzemplarz " + servletInstance + " usuniety");
  }

}
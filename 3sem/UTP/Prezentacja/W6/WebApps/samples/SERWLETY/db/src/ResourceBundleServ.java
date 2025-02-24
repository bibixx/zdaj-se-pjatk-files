import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;


class BundleInfo {

  static private String[] commandParamNames;
  static private String[] commandParamDescr;
  static private String[] statusMsg;
  static private String[] headers;
  static private String[] footers;
  static private String[] resultDescr;
  static private String charset;
  static private String submitMsg;

  static void generateInfo(ResourceBundle rb) {

    synchronized (BundleInfo.class) {  // konieczne ze wzglêdu
                                       // na mo¿liwoœæ odwo³añ
      List cpn = new ArrayList();      // z wielu egzemplarzy serwletów
      List cpv = new ArrayList();
      Enumeration keys = rb.getKeys();
      while (keys.hasMoreElements()) {
        String key = (String) keys.nextElement();
        if (key.startsWith("param_")) {
          cpn.add(key.substring(6));
          cpv.add(rb.getString(key));
        }
        else if (key.equals("header")) headers = rb.getStringArray(key);
        else if (key.equals("footer")) footers = rb.getStringArray(key);
        else if (key.equals("resCode")) statusMsg = rb.getStringArray(key);
        else if (key.equals("resDescr")) resultDescr = rb.getStringArray(key);
        else if (key.equals("charset")) charset = rb.getString(key);
        else if (key.equals("submit")) submitMsg = rb.getString(key);
      }
      commandParamNames = (String[]) cpn.toArray(new String[0]);
      commandParamDescr = (String[]) cpv.toArray(new String[0]);
    }
  }

  public static String getCharset() {
    return charset;
  }

  public static String getSubmitMsg() {
    return submitMsg;
  }

    public static String[] getCommandParamNames() {
    return commandParamNames;
  }

  public static String[] getCommandParamDescr() {
    return commandParamDescr;
  }

  public static String[] getStatusMsg() {
    return statusMsg;
  }

  public static String[] getHeaders() {
    return headers;
  }

  public static String[] getFooters() {
    return footers;
  }

  public static String[] getResultDescr() {
    return resultDescr;
  }

}


// Serwlet w³¹czany wy³¹cznie z serwletu pobierania parametrów
// £aduje  ResourceBundle i przekazuje go klasie BundleInfo,
// która odczytuje info i daje wygodn¹ formê jej pobierania
// w innych serwletach.
// £adowanie zasobów i ich przygotowanie przez klasê BundleInfo
// nastêpuje tylko raz na sesjê.


public class ResourceBundleServ extends HttpServlet {

  private String resBundleName;

  public void init() {
    resBundleName = getServletContext().getInitParameter("resBundleName");
  }

  public void serviceRequest(HttpServletRequest req,
                             HttpServletResponse resp)
                             throws ServletException, IOException
  {
    HttpSession ses = req.getSession();
    ResourceBundle paramsRes = (ResourceBundle) ses.getAttribute("resBundle");

    // W tej sesji jeszcze nie odczytaliœmy zasobów
    if (paramsRes == null) {
       Locale loc = req.getLocale();
       paramsRes = ResourceBundle.getBundle(resBundleName, loc);
       ses.setAttribute("resBundle", paramsRes);

       // Przygotowanie zasobów w wygodnej do odczytu formie
       BundleInfo.generateInfo(paramsRes);
    }

    // ... a jeœli sesja siê nie zmieni³a - to nie mamy nic do roboty
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



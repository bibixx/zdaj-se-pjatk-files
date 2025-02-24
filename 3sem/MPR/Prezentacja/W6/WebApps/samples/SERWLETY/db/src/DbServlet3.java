import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import javax.naming.*;
import java.sql.*;
import javax.sql.*;

public class DbServlet3 extends HttpServlet {

  DataSource dataSource;  // Ÿrod³o danych

  public void init() throws ServletException {
    try {
      Context init = new InitialContext();
      Context ctx = (Context) init.lookup("java:comp/env");
      dataSource = (DataSource) ctx.lookup("jdbc/ksidb");
     } catch (NamingException exc) {
        throw new ServletException(
          "Nie mogê uzyskaæ Ÿród³a java:comp/env/jdbc/ksidb", exc);
     }
  }

  public void serviceRequest(HttpServletRequest req,
                             HttpServletResponse resp)
                             throws ServletException, IOException
  {
    resp.setContentType("text/html; charset=windows-1250");
    PrintWriter out = resp.getWriter();
    out.println("<h2>Lista dostêpnych ksi¹¿ek</h2>");

    Connection con = null;
    try {
      synchronized (dataSource) {
        con = dataSource.getConnection();
      }
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("select * from pozycje");
      out.println("<ol>");
      while (rs.next())  {
        String tytul = rs.getString("tytul");
        float cena  = rs.getFloat("cena");
        out.println("<li>" + tytul + " - cena: " + cena + "</li>");
      }
      rs.close();
      stmt.close();
    } catch (Exception exc)  {
       exc.printStackTrace(out);
       out.println(exc.getMessage());
    } finally {
        try { con.close(); } catch (Exception exc) {}
    
    }

    out.close();


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
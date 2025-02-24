import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.sql.*;
import javax.sql.*;

public class DbServlet1 extends HttpServlet {

  String url = "jdbc:derby://localhost/ksidb";
  String uid = "APP";
  String pwd = "APP";
  Connection con;


  public void init() throws ServletException {
    try {
     con = DriverManager.getConnection(url, uid, pwd);
    } catch (Exception exc) {
        throw new ServletException("Nie ustanowiono po³aczenia z baz¹", exc);
    }
  }


  public void serviceRequest(HttpServletRequest req,
                             HttpServletResponse resp)
                             throws ServletException, IOException
  {
   resp.setContentType("text/html; charset=windows-1250");
   PrintWriter out = resp.getWriter();
   out.println("<h2>Lista dostêpnych ksi¹¿ek</h2>");
   String sel = "select * from pozycje";
   out.println("<ol>");
   try  {
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(sel);
      while (rs.next())  {
        String tytul = rs.getString("tytul");
        float cena  = rs.getFloat("cena");
        out.println("<li>" + tytul + " - cena: " + cena + "</li>");
      }
      rs.close();
      stmt.close();
    } catch (SQLException exc)  {
       out.println(exc.getMessage());
    }
    out.close();
  }

   public void destroy() {
     try {
       con.close();
     } catch (Exception exc) {}
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
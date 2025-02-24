import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.sql.*;
import javax.sql.*;

public class DbServlet2 extends HttpServlet {

  public void serviceRequest(HttpServletRequest req,
                             HttpServletResponse resp)
                             throws ServletException, IOException
  {
   resp.setContentType("text/html; charset=windows-1250");
   PrintWriter out = resp.getWriter();
   out.println("<h2>Lista dostêpnych ksi¹¿ek</h2>");

   Connection con;
   String sel = "select * from pozycje";
   try  {
      Class.forName("com.mysql.jdbc.Driver");
      con = DriverManager.getConnection("jdbc:mysql://localhost/ksidb",
                                       "pies", "kuba");
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(sel);
      out.println("<ol>");
      while (rs.next())  {
        String tytul = rs.getString("tytul");
        float cena  = rs.getFloat("cena");
        out.println("<li>" + tytul + " - cena: " + cena + "</li>");
      }
      rs.close();
      stmt.close();
      con.close();
    } catch (Exception exc)  {
       out.println(exc.getMessage());
    }
    out.close();
  }

   public void destroy() {
     try {

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
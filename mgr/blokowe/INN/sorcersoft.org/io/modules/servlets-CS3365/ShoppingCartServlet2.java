import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ShoppingCartServlet2 extends HttpServlet {
  protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException
  {
    HttpSession session = req.getSession(false);
    res.setContentType("text/html");
    res.setHeader("pragma", "no-cache");
    PrintWriter out = res.getWriter();
    out.print("<HTML><HEAD><TITLE>Online Shop</TITLE>"+
	      "</HEAD><BODY><FORM METHOD=POST ACTION=");
    out.print(res.encodeUrl(req.getRequestURI()));
    out.print("><INPUT TYPE=SUBMIT NAME=foo VALUE="+
	      "\"Put a FOO into the shopping cart\">"+
	      "<INPUT TYPE=SUBMIT NAME=bar VALUE="+
	      "\"Put a BAR into the shopping cart\">"+
	      "<INPUT TYPE=SUBMIT NAME=see VALUE="+
	      "\"See the shopping cart contents\">"+
	      "<INPUT TYPE=SUBMIT NAME=buy VALUE="+
	      "\"Buy the shopping cart contents\">"+
	      "</FORM></BODY></HTML>");
    out.close();
  }

  protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
    String msg;

    HttpSession session = req.getSession(true);
    if(session.isNew()) {
      session.putValue("foo", new int[] { 0 });
      session.putValue("bar", new int[] { 0 });
    }

    int[] foo = (int[])session.getValue("foo");
    int[] bar = (int[])session.getValue("bar");

    if(req.getParameter("foo") != null) {
      foo[0]++;
      msg = "Bought a FOO. You now have "+foo[0]+".";
    }
    else if(req.getParameter("bar") != null) {
      bar[0]++;
      msg = "Bought a BAR. You now have "+bar[0]+".";
    }
    else if(req.getParameter("buy") != null) {
      session.invalidate();
      msg = "Your order for "+foo[0]+" FOOs and "+bar[0]+
	" BARs has been accepted. Your shopping cart is empty now.";
    }
    else {
      msg = "You have "+foo[0]+" FOOs and "+bar[0]+
	" BARs in your shopping cart.";
    }

    res.setContentType("text/html");
    res.setHeader("pragma", "no-cache");
    PrintWriter out = res.getWriter();
    out.print("<HTML><HEAD><TITLE>Shopping Cart</TITLE></HEAD><BODY>");
    out.print(msg);
    out.print("<HR><A HREF=\"");
    out.print(res.encodeUrl(req.getRequestURI()));
    out.print("\">Back to the shop</A></BODY></HTML>");
    out.close();
  }

  public String getServletInfo() {
    return "ShoppingCartServlet2 1.0";
  }
}

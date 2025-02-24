package listeners;
import javax.servlet.*;

public class TestContextListener implements ServletContextListener{


  public void contextInitialized(ServletContextEvent p0) {
     Report.add("Kontekst utworzony");
     ServletContext context = p0.getServletContext();
     context.setAttribute("Liczba", new Integer(1));
  }

  public void contextDestroyed(ServletContextEvent p0) {
  }
}
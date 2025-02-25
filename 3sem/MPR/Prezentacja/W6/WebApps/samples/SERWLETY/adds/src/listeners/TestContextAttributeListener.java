package listeners;
import javax.servlet.*;

public class TestContextAttributeListener implements ServletContextAttributeListener{



  public void attributeAdded(ServletContextAttributeEvent p0) {
    Report.add("Do kontekstu dodano atrybut " + p0.getName() +
    " = " + p0.getValue());
  }

  public void attributeRemoved(ServletContextAttributeEvent p0) {
    Report.add("Z kontekstu usuni�to atrybut " + p0.getName() +
    " = " + p0.getValue());
  }

  public void attributeReplaced(ServletContextAttributeEvent p0) {
    Report.add("W kontek�cie zmieniono atrybut " + p0.getName() +
    " = " + p0.getValue());
  }

}
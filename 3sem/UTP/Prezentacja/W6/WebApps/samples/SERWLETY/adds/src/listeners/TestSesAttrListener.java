package listeners;
import javax.servlet.http.*;

public class TestSesAttrListener implements HttpSessionAttributeListener{



  public void attributeAdded(HttpSessionBindingEvent p0) {
    Report.add("Do sesji dodany atrybut " + p0.getName() + " = " + p0.getValue());
  }

  public void attributeRemoved(HttpSessionBindingEvent p0) {
    Report.add("Z sesji usuniêty atrybut " + p0.getName() + " = " + p0.getValue());
  }

  public void attributeReplaced(HttpSessionBindingEvent p0) {
    Report.add("Zmieniony sesji atrybut " + p0.getName() + " = " + p0.getValue());
  }
}
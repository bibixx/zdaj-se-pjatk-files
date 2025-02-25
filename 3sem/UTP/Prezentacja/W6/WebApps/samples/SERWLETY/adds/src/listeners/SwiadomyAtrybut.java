package listeners;
import javax.servlet.http.*;

public class SwiadomyAtrybut  implements HttpSessionBindingListener{

  private String val;

  public SwiadomyAtrybut(String val) {
    this.val = val;
  }


  public void valueBound(HttpSessionBindingEvent p0) {
    Report.add("Jestem \"�wiadomym\" atrybutem " +
         p0.getName() + " i wiem, �e w�asnie zosta�em dodany do sesji");
  }

  public void valueUnbound(HttpSessionBindingEvent p0) {
    Report.add("Jestem \"�wiadomym\" atrybutem " +
         p0.getName() + " i wiem, �e w�asnie zosta�em usuni�ty z sesji");

  }

  public String toString() { return val; }
}
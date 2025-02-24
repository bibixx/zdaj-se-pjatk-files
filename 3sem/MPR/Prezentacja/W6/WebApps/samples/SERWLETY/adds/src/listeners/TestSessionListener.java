package listeners;
import javax.servlet.http.*;

public class TestSessionListener implements HttpSessionActivationListener{

  public void sessionWillPassivate(HttpSessionEvent p0) {
    Report.add("Sesja zdeaktywizowana");
  }

  public void sessionDidActivate(HttpSessionEvent p0) {
    Report.add("Sesja aktywizowana");
  }
}
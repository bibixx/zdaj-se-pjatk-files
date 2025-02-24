import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.Handler;

public class LoggingLevels {
    private static Logger
        lgr = Logger.getLogger("ttu"),
        lgr2 = Logger.getLogger("ttu.cs"),
        util = Logger.getLogger("ttu.cs.util"),
        test = Logger.getLogger("ttu.cs.test"),
        rand = Logger.getLogger("random");
    
    private static void logMessages() {
        lgr.info("ttu : info");
        lgr2.info("ttu.cs : info");
        util.info("util : info");
        test.severe("test : severe");
        rand.info("random : info");
  }
  
  public static void main(String[] args) {
        Logger root = Logger.getLogger("");
        System.out.println("root level: " + root.getLevel());
        logMessages();
        
        lgr.setLevel(Level.SEVERE);
        System.out.println("ttu level: SEVERE");
        logMessages();
        
        util.setLevel(Level.FINEST);
        test.setLevel(Level.FINEST);
        rand.setLevel(Level.FINEST);
        System.out.println("individual loggers set to FINEST");
        logMessages();
        
        lgr2.setLevel(Level.SEVERE);
        System.out.println("ttu.cs level: SEVERE");
        logMessages();
        
        root.getHandlers()[0].setLevel(Level.SEVERE);
        System.out.println("root handler level: SEVERE");
        logMessages();
    }
}

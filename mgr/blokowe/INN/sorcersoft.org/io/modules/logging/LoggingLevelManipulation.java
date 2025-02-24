import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.Handler;
import java.util.logging.LogManager;

public class LoggingLevelManipulation {
  private static Logger
    lgr = Logger.getLogger("ttu"),
    lgr2 = Logger.getLogger("ttu.cs"),
    util = Logger.getLogger("ttu.cs.util"),
    test = Logger.getLogger("ttu.cs.test"),
    rand = Logger.getLogger("random");
    
  static void printLogMessages(Logger logger) {
    logger.finest(logger.getName() + " Finest");
    logger.finer(logger.getName() + " Finer");
    logger.fine(logger.getName() + " Fine");
    logger.config(logger.getName() + " Config");
    logger.info(logger.getName() + " Info");
    logger.warning(logger.getName() + " Warning");
    logger.severe(logger.getName() + " Severe");
  }
  
  static void logMessages() {
    printLogMessages(lgr);
    printLogMessages(lgr2);
    printLogMessages(util);
    printLogMessages(test);
    printLogMessages(rand);
  }
  
  static void printLevels() {
    System.out.println(" -- printing levels -- "
      + lgr.getName() + " : " + lgr.getLevel()
      + " " + lgr2.getName() + " : " + lgr2.getLevel()
      + " " + util.getName() + " : " + util.getLevel()
      + " " + test.getName() + " : " + test.getLevel()
      + " " + rand.getName() + " : " + rand.getLevel());
  }
  
  public static void main(String[] args) {
    printLevels();
    lgr.setLevel(Level.SEVERE);
    printLevels();
    System.out.println("com level: SEVERE");
    logMessages();
    util.setLevel(Level.FINEST);
    test.setLevel(Level.FINEST);
    rand.setLevel(Level.FINEST);
    printLevels();
    System.out.println(
      "individual loggers set to FINEST");
    logMessages();
    lgr.setLevel(Level.FINEST);
    printLevels();
    System.out.println("com level: FINEST");
    logMessages();
  }
}

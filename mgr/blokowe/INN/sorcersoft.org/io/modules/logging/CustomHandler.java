import java.util.logging.*;
import java.util.*;

public class CustomHandler {
  private static Logger logger = Logger.getLogger("CustomHandler");
  private static List<String> strHolder = new ArrayList<String>();
  
  public static void main(String[] args) {
    logger.addHandler(new Handler() {
      public void publish(LogRecord logRecord) {
        strHolder.add(logRecord.getLevel() + ":");
        strHolder.add(logRecord.getSourceClassName()+":");
        strHolder.add(logRecord.getSourceMethodName()+":");
        strHolder.add("<" + logRecord.getMessage() + ">");
        strHolder.add("\n");
      }
      public void flush() {}
      public void close() {}
    });
    logger.warning("Logging Warning");
    logger.info("Logging Info");
    System.out.print(strHolder);
  }
} 

import java.util.logging.*;

public class LogToFile {
  private static Logger logger = Logger.getLogger("LogToFile");
    
  public static void main(String[] args) throws Exception {
    logger.addHandler(new FileHandler("LogToFile.xml"));
    
    FileHandler logFile= new FileHandler("LogToFile.txt");
    logFile.setFormatter(new SimpleFormatter());
    logger.addHandler(logFile);
    
    logger.info("A message logged to the file");
  }
}

import java.util.logging.*;
import java.io.*;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailLogger {
  private static Logger logger =
    Logger.getLogger("EmailLogger");
    
  public static void main(String[] args) throws Exception {
    logger.setUseParentHandlers(false);
    Handler conHdlr = new ConsoleHandler();
    conHdlr.setFormatter(new Formatter() {
      public String format(LogRecord record) {
        return record.getLevel() + "  :  "
          + record.getSourceClassName() + ":"
          + record.getSourceMethodName() + ":"
          + record.getMessage() + "\n";
      }
    });
    logger.addHandler(conHdlr);
    logger.addHandler(
      new FileHandler("EmailLoggerOutput.xml"));
    logger.addHandler(new MailingHandler());
    logger.log(Level.INFO, "Testing Multiple Handlers", "SendMailTrue");
  }
}

// A handler that sends mail messages
class MailingHandler extends Handler {

  public void publish(LogRecord record) {
    Object[] params = record.getParameters();
    if(params == null) return;
    // Send mail only if the parameter is true
    if(params[0].equals("SendMailTrue")) {
      new MailInfo("mike.sobolewski@ttu.edu",
        new String[] { "msobol@cox.net" },
        "smtp.ttu.edu", "Test Subject",
        "Test Content").sendMail();
    }
  }
  
  public void close() {}
  
  public void flush() {}
}

class MailInfo {
  private String fromAddr;
  private String[] toAddr;
  private String serverAddr;
  private String subject;
  private String message;
  
  public MailInfo(String from, String[] to,
    String server, String subject, String message) {
    fromAddr = from;
    toAddr = to;
    serverAddr = server;
    this.subject = subject;
    this.message = message;
  }
  
  public void sendMail() {
    try {
      Properties prop = new Properties();
      prop.put("mail.smtp.host", serverAddr);
      prop.put("mail.transport.protocol", "smtp");
      prop.put("mail.smtp.starttls.enable","true");
      prop.put("mail.smtp.auth", "true");
      javax.mail.Authenticator auth = new javax.mail.Authenticator() {
          public PasswordAuthentication getPasswordAuthentication() {
              return new PasswordAuthentication("msobolew", "xxxxxx");  
            }
        };
      Session session =
        Session.getDefaultInstance(prop, auth);
      session.setDebug(true);
      // Create a message
      Message mimeMsg = new MimeMessage(session);
      // Set the from and to address
      Address addressFrom = new InternetAddress(fromAddr);
      mimeMsg.setFrom(addressFrom);
      Address[] to = new InternetAddress[toAddr.length];
      for(int i = 0; i < toAddr.length; i++)
        to[i] = new InternetAddress(toAddr[i]);
      mimeMsg.setRecipients(Message.RecipientType.TO, to);
      mimeMsg.setSubject(subject);
      mimeMsg.setText(message);
      Transport.send(mimeMsg);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}

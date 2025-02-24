import java.net.*;
import java.io.*;
import java.util.regex.*;

class RequestHandler extends Thread {

  private PhoneDirectory pd = null;
  private Socket connection = null;
  private BufferedReader in = null;
  private PrintWriter out = null;

  private static Pattern reqPatt = Pattern.compile(" +", 3);

  private static String msg[] = { "Ok", "Invalid request", "Not found",
                                  "Couldn't add - entry already exists",
                                  "Couldn't replace non-existing entry",
                                  };

  public RequestHandler(PhoneDirectory pd, Socket connection) {
    this.pd = pd;
    this.connection = connection;
    try {
      in = new BufferedReader(
               new InputStreamReader(
                   connection.getInputStream()));
      out = new PrintWriter(
                connection.getOutputStream(), true);
    } catch (Exception exc) {
        exc.printStackTrace();
        try { connection.close(); } catch(Exception e) {}
        return;
    }
  }

  public void run() {
    try {
      for (String line; (line = in.readLine()) != null; ) {
        String resp;
        String[] req = reqPatt.split(line, 3);
        String cmd = req[0];
        if (cmd.equals("bye")) {
          writeResp(0, null);
          break;
        }
        else if (cmd.equals("get")) {
          if (req.length != 2) writeResp(1, null);
          else {
            String phNum = (String) pd.getPhoneNumber(req[1]);
            if (phNum == null) writeResp(2, null);
            else writeResp(0, phNum);
          }
        }
        else if (cmd.equals("add"))  {
          if (req.length != 3) writeResp(1, null);
          else {
            boolean added = pd.addPhoneNumber(req[1], req[2]);
            if (added) writeResp(0, null);
            else writeResp(3, null);
          }
        }
        else if (cmd.equals("replace"))  {
          if (req.length != 3) writeResp(1, null);
          else {
            boolean replaced = pd.replacePhoneNumber(req[1], req[2]);
            if (replaced) writeResp(0, null);
            else writeResp(4, null);
          }
        }
        else writeResp(1, null);
      }
    } catch (Exception exc) {
        exc.printStackTrace();

    } finally {
        try {                  // by nie byæ zbyt drobiazgowym:
          connection.close();  // przy zamkniêciu gniazda
          connection = null;   // strumienie s¹ zamykane automatycznie
        } catch (Exception exc) { }
    }
  }

  private void writeResp(int rc, String addMsg)
               throws IOException {
    out.println(rc + " " + msg[rc]);
    if (addMsg != null) out.println(addMsg);
  }
}



public class PhoneBookServerMT2  {

  private PhoneDirectory pd = null;
  private ServerSocket ss = null;

  public PhoneBookServerMT2(PhoneDirectory pd,ServerSocket ss) {
    this.pd = pd;
    this.ss = ss;
    System.out.println("Server started");
    System.out.println("listening at port: " + ss.getLocalPort());
    System.out.println("bind address: " + ss.getInetAddress());

    serviceConnections();
  }


  private void serviceConnections() {
    boolean serverRunning = true;
    while (serverRunning) {
      try {
        Socket conn = ss.accept();
        System.out.println("Connection established");

        // start w¹tku obs³ugi zleceñ
        new RequestHandler(pd, conn).start();

      } catch (Exception exc) {
          exc.printStackTrace();
      }
    }
    try { ss.close(); } catch (Exception exc) {}
  }


  public static void main(String[] args) {

    PhoneDirectory pd = null;
    ServerSocket ss = null;
    try {
      String phdFileName = args[0];
      String host = args[1];
      int port = Integer.parseInt(args[2]);
      pd = new PhoneDirectory(phdFileName);
      InetSocketAddress isa = new InetSocketAddress(host, port);
      ss =  new ServerSocket();
      ss.bind(isa);
    } catch(Exception exc) {
        exc.printStackTrace();
        System.exit(1);
    }
    new PhoneBookServerMT2(pd, ss);
  }

}
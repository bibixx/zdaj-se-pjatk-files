import java.net.*;
import java.io.*;
import java.util.regex.*;

public class PhoneBookServerMT1 extends Thread {

  private PhoneDirectory pd = null;
  private ServerSocket ss = null;
  private BufferedReader in = null;
  private PrintWriter out = null;

  private volatile boolean serverRunning = true; // mo¿na zakoñczyæ w¹tek
                                         // metod¹ ustalaj¹c¹
                                         // wartoœæ tej zmiennej na false

  private String serverTID;              // identyfakator w¹tku

  public PhoneBookServerMT1(String serverTID, PhoneDirectory pd,
                          ServerSocket ss) {
    this.serverTID = serverTID;
    this.pd = pd;
    this.ss = ss;
    System.out.println("Server " + serverTID + " started");
    System.out.println("listening at port: " + ss.getLocalPort());
    System.out.println("bind address: " + ss.getInetAddress());

    start();    // uruchomienie w¹tku
  }


  public void run() {
    while (serverRunning) {
      try {
        Socket conn = ss.accept();

        System.out.println("Connection established by " + serverTID);

        serviceRequests(conn);

      } catch (Exception exc) {
          exc.printStackTrace();
      }
    }                               // zamkniêcie gniazda serwera
    try { ss.close(); } catch (Exception exc) {}
  }

  private static Pattern reqPatt = Pattern.compile(" +", 3);

  private static String msg[] = { "Ok", "Invalid request", "Not found",
                                  "Couldn't add - entry already exists",
                                  "Couldn't replace non-existing entry",
                                  };


  private void serviceRequests(Socket connection)
                              throws IOException {
    try {
      in = new BufferedReader(                   // utworzenie strumieni
               new InputStreamReader(
                   connection.getInputStream()));
      out = new PrintWriter(
                connection.getOutputStream(), true);

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
        try {
          in.close();
          out.close();
          connection.close();
          connection = null;
        } catch (Exception exc) { }
    }
  }

  private void writeResp(int rc, String addMsg)
               throws IOException {
    out.println(rc + " " + msg[rc]);
    if (addMsg != null) out.println(addMsg);
  }

  public static void main(String[] args) {
    final int SERVERS_NUM = 4;   // liczba serwerów
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

    // Start wielu w¹tków (serwerow) dzialaj¹cych równolegle
    // na tym samym gnie¿dzie serwera

    for (int i=1; i <= SERVERS_NUM; i++) {
      new PhoneBookServerMT1("serv thread " + i, pd, ss);
    }
  }

}
import java.net.*;
import java.io.*;

public class PhoneBookClients extends Thread {

  private Socket sock = null;
  private PrintWriter out = null;
  private BufferedReader in = null;
  private String nameToSearch;

  public PhoneBookClients(String host, int port, String name ) {
    try {
      sock = new Socket(host, port);
      out = new PrintWriter(sock.getOutputStream(), true);
      in = new BufferedReader(
               new InputStreamReader(
                   sock.getInputStream()));

      nameToSearch = name;

    } catch (Exception exc) {
        exc.printStackTrace();
        System.exit(4);
    }
    start();
  }

  public void run() {
    try {
      for (int i=1; i <= 5; i++) {
        find(nameToSearch);
        Thread.sleep(500);
      }
      out.println("bye");
    } catch (Exception exc) {
        exc.printStackTrace();
    }
  }

  private void find(String name) throws IOException {
    out.println("get " + name);
    String resp = in.readLine();
    boolean ok = resp.startsWith("0");
    String tel = ok ? in.readLine() : " - not found";
    System.out.println(name + " - tel. " + tel);
  }

  public static void main(String[] args) {
    String host = args[0];
    int port = Integer.parseInt(args[1]);
    String[] names = { "Asia", "Adam", "Jacek" };
    for (int i=0; i<names.length; i++)
      new PhoneBookClients(host, port, names[i]);
  }
}
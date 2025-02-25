import java.net.*;
import java.io.*;
import java.util.regex.*;

public class PhoneBookServer1  {

  private PhoneDirectory pd = null;  // mapa osoby-numery tel.
  private ServerSocket ss = null;
  private BufferedReader in = null;  // strumienie gniazda
  private PrintWriter out = null;    // komunikacji z klientem

  public PhoneBookServer1(PhoneDirectory pd, ServerSocket ss) {
    this.pd = pd;
    this.ss = ss;
    System.out.println("Server started");
    System.out.println("at port: " + ss.getLocalPort());
    System.out.println("bind address: " + ss.getInetAddress());

    serviceConnections();  // nas�uchiwanie po��cze�
  }

  // Metoda nas�uchuje po��cze� od klient�w
  // po zaakceptowaniu po��czenia - tworzy gniazdo komunikacyjne
  // i przekazuje obs�ug� zlece� metdodzie serviceRequest

  private void serviceConnections() {
    boolean serverRunning = true;   // serwer dzia�a ci�gle
    while (serverRunning) {
      try {
        Socket conn = ss.accept();  // nas�uch i akceptaccja po��cze�

        System.out.println("Connection established");

        serviceRequests(conn);      // obs�uga zlece� dla tego po��czenia

      } catch (Exception exc) {
          exc.printStackTrace();
      }
    }                               // zamkni�cie gniazda serwera
    try { ss.close(); } catch (Exception exc) {}
  }

  // wzorzec do rozbioru zlecenia (maks. trzy "s�owa" rozdzielone spacjami)
  private static Pattern reqPatt = Pattern.compile(" +", 3);

  // S�owne komunikaty serwera
  // odpowidaj�ce im indeksy tablicy - kody wyniku
  private static String msg[] = { "Ok", "Invalid request", "Not found",
                                  "Couldn't add - entry already exists",
                                  "Couldn't replace non-existing entry",
                                  };


  // Obs�uga zlece� od klienta
  private void serviceRequests(Socket connection)
                              throws IOException {
    try {
      in = new BufferedReader(                   // utworzenie strumieni
               new InputStreamReader(
                   connection.getInputStream()));
      out = new PrintWriter(
                connection.getOutputStream(), true);

      // Odczytywanie zlece� (line zawiera kolejne zlecenie)
      for (String line; (line = in.readLine()) != null; ) {

        String resp;                           // odpowied�
        String[] req = reqPatt.split(line, 3); // rozbi�r zlecenia
        String cmd = req[0];                   // pierwsze s�owo - polecenie

        if (cmd.equals("bye")) {        // zlecenie "bye" - koniec komunikacji
          writeResp(0, null);
          break;
        }
        else if (cmd.equals("get")) {   // "get" - klient chce dosta� nr tel.
          if (req.length != 2) writeResp(1, null);
          else {
            String phNum = (String) pd.getPhoneNumber(req[1]); // pobranie
            if (phNum == null) writeResp(2, null);             // numeru tel.
            else writeResp(0, phNum);                          // i zapis
          }
        }
        else if (cmd.equals("add"))  {  // "add" - klient chce doda� numer
          if (req.length != 3) writeResp(1, null);
          else {
            boolean added = pd.addPhoneNumber(req[1], req[2]); // dodany?
            if (added) writeResp(0, null);                     // tak - ok
            else writeResp(3, null);                           // nie
          }
        }
        else if (cmd.equals("replace"))  {  // klient chce zmieni� nr tel.
          if (req.length != 3) writeResp(1, null);
          else {
            boolean replaced = pd.replacePhoneNumber(req[1], req[2]);
            if (replaced) writeResp(0, null);
            else writeResp(4, null);
          }
        }
        else writeResp(1, null);             // nieznane zlecenie
      }
    } catch (Exception exc) {
        exc.printStackTrace();

    } finally {
        try {                                // zamkni�cie strumieni
          in.close();                        // i gniazda
          out.close();
          connection.close();
          connection = null;
        } catch (Exception exc) { }
    }
  }


  // Przekazanie odpowiedzi klientowi poprzez zapis do strumienia
  // gniazda komuniakcyjnego
  private void writeResp(int rc, String addMsg)
               throws IOException {
    out.println(rc + " " + msg[rc]);
    if (addMsg != null) out.println(addMsg);
  }

  public static void main(String[] args) {
    PhoneDirectory pd = null;
    ServerSocket ss = null;
    try {
      String phdFileName = args[0];
      String host = args[1];
      int port = Integer.parseInt(args[2]);

      pd = new PhoneDirectory(phdFileName); // utworzenie mapy numer�w z pliku

      InetSocketAddress isa = new InetSocketAddress(host, port);

      ss =  new ServerSocket();             // Utworzenie gniazda serwera
      ss.bind(isa);                         // i zwi�zanie go z adresem

    } catch(Exception exc) {
        exc.printStackTrace();
        System.exit(1);
    }
    new PhoneBookServer1(pd, ss);
  }

}


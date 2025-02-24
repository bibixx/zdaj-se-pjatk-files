import java.net.*;
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;

public class PhoneBookServerNB {

  private PhoneDirectory pd = null;
  private ServerSocketChannel ssc = null;
  private Selector selector = null;

  public PhoneBookServerNB(PhoneDirectory pd, String host, int port ) {
    this.pd = pd;
    try {
      // Utworzenie kana≥u dla gniazda serwera
      ssc = ServerSocketChannel.open();

      // Tryb nieblokujπcy
      ssc.configureBlocking(false);

      // Ustalenie adresu (host+port) gniazda kana≥u
      ssc.socket().bind(new InetSocketAddress(host, port));

      // Utworzenie selektora
      selector = Selector.open();

      // Zarejestrowanie kana≥u do obs≥ugi przez selektor
      // dla tego kana≥u interesuje nas tylko nawiπzywanie po≥πczeÒ
      // tryb - OP_ACCEPT
      ssc.register(selector,SelectionKey.OP_ACCEPT);

    } catch(Exception exc) {
        exc.printStackTrace();
        System.exit(1);
    }
    System.out.println("Server started and ready for handling requests");
    serviceConnections();
  }

  private void serviceConnections() {
    boolean serverIsRunning = true;

    while(serverIsRunning) {
      try {
        // Wywo≥anie blokujπce
        // czeka na zajúcie  zdarzenia zwiπzanego z kana≥ami
        // zarejestrowanymi do obslugi przez selektor
        selector.select();

        // Coú siÍ wydarzy≥o na kana≥ach
        // ZbiÛr kluczy opisuje zdarzenia
        Set keys = selector.selectedKeys();

        Iterator iter = keys.iterator();
        while(iter.hasNext()) {   // dla kaødego klucza

          SelectionKey key = (SelectionKey) iter.next(); // pobranie klucza
          iter.remove();                                 // usuwamy, bo juø
                                                         // go zaraz obs≥uøymy

          if (key.isAcceptable()) { // jakiú klient siÍ po≥πczy≥

            // Uzyskanie kana≥u do komunikacji z klientem
            // accept jest nieblokujπce, bo juø jest po≥πczenie
            SocketChannel cc = ssc.accept();

            // Komunikacja z klientem - nieblokujπce we/wy
            cc.configureBlocking(false);

            // rejestrujemy kana≥ komunikacji z klientem
            // do obs≥ugi przez selektor
            // - typ zdarzenia - dane gotowe do czytania przez serwer
            cc.register(selector, SelectionKey.OP_READ);
            continue;
          }

          if (key.isReadable()) {  // ktÛryú z kana≥Ûw gotowy do czytania
            // Uzyskanie kana≥u na ktÛrym czekajπ dane do odczytania
            SocketChannel cc = (SocketChannel) key.channel();
            serviceRequest(cc);    // obsluga zlecenia
            continue;
          }
        }
      } catch(Exception exc) {
          exc.printStackTrace();
          continue;
      }
    }
  }

  private static Pattern reqPatt = Pattern.compile(" +", 3);

  private static String msg[] = { "Ok", "Invalid request", "Not found",
                                  "Couldn't add - entry already exists",
                                  "Couldn't replace non-existing entry",
                                  };

  // Strona kodowa do kodowania/dekodowania buforÛw
  private static Charset charset  = Charset.forName("ISO-8859-2");
  private static final int BSIZE = 1024;

  // Bufor bajtowy - do niego sπ wczytywane dane z kana≥u
  private ByteBuffer bbuf = ByteBuffer.allocate(BSIZE);

  // Tu bÍdzie zlecenie do pezetworzenia
  private StringBuffer reqString = new StringBuffer();

  // Obs≥uga (JEDNEGO) zlecania
  private void serviceRequest(SocketChannel sc) {
    if (!sc.isOpen()) return; // jeøeli kana≥ zamkniÍty - nie ma nic do roboty

    // Odczytanie zlecenia
    reqString.setLength(0);
    bbuf.clear();
    try {
      readLoop:                    // Czytanie jest nieblokujπce
      while (true) {               // kontynujemy je dopÛki
        int n = sc.read(bbuf);     // nie natrafimy na koniec wiersza
        if (n > 0) {
          bbuf.flip();
          CharBuffer cbuf = charset.decode(bbuf);
          while(cbuf.hasRemaining()) {
            char c = cbuf.get();
            if (c == '\r' || c == '\n') break readLoop;
            reqString.append(c);
          }
        }
      }
      // Analiza zlecenia (jak poprzednio) i wo≥anie nowej metody
      // writeResp zapisujπcej odpowiedü do kana≥u
      String[] req = reqPatt.split(reqString, 3);
      String cmd = req[0];

      if (cmd.equals("bye")) {             // koniec komunikacji
          writeResp(sc, 0, null);          // - zamkniÍcie kana≥u
          sc.close();                      // i gniazda
          sc.socket().close();
      }
      else if (cmd.equals("get")) {
        if (req.length != 2) writeResp(sc, 1, null);
        else {
          String phNum = (String) pd.getPhoneNumber(req[1]);
          if (phNum == null) writeResp(sc, 2, null);
          else writeResp(sc, 0, phNum);
        }
      }
      else if (cmd.equals("add"))  {
        if (req.length != 3) writeResp(sc, 1, null);
        else {
          boolean added = pd.addPhoneNumber(req[1], req[2]);
          if (added) writeResp(sc, 0, null);
          else writeResp(sc, 3, null);
        }
      }
      else if (cmd.equals("replace"))  {
        if (req.length != 3) writeResp(sc, 1, null);
        else {
          boolean replaced = pd.replacePhoneNumber(req[1], req[2]);
          if (replaced) writeResp(sc, 0, null);
          else writeResp(sc, 4, null);
        }
      }
      else writeResp(sc, 1, null);             // nieznane zlecenie

    } catch (Exception exc) {                  // przerwane polπczenie?
        exc.printStackTrace();
        try { sc.close();
              sc.socket().close();
        } catch (Exception e) {}
    }
  }

  private StringBuffer remsg = new StringBuffer(); // Odpowiedü

  private void writeResp(SocketChannel sc, int rc, String addMsg)
                         throws IOException {
    remsg.setLength(0);
    remsg.append(rc);
    remsg.append(' ');
    remsg.append(msg[rc]);
    remsg.append('\n');
    if (addMsg != null) {
      remsg.append(addMsg);
      remsg.append('\n');
    }
    ByteBuffer buf = charset.encode(CharBuffer.wrap(remsg));
    sc.write(buf);
  }

  public static void main(String[] args) {
    try {
      String phdFileName = args[0];
      String host = args[1];
      int port = Integer.parseInt(args[2]);

      PhoneDirectory pd = new PhoneDirectory(phdFileName);
      new PhoneBookServerNB(pd, host, port);
    } catch(Exception exc) {
        exc.printStackTrace();
        System.exit(1);
    }
  }

}
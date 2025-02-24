import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;
import java.util.regex.*;


class DictGui extends JFrame implements ActionListener {

  public final static int port = 2628;
  private String server;
  private SocketChannel channel;

  private JTextArea ta = new JTextArea(20, 20);
  private JTextField tf = new JTextField(20);
  private JLabel infoLab = new JLabel("Nie by³o szukania");
  private JButton paste = new JButton("Wklej definicjê");
  private Container cp = getContentPane();
  private ReadDef rd;

  public DictGui(String server) {
    this.server = server;

    // Otwarcie o po³¹czenie kana³u
    // metoda connect - zdefiniowana u koñca klasy
    try {
      channel = SocketChannel.open();
      channel.configureBlocking(false);
      connect();
    } catch(UnknownHostException exc) {
        System.err.println("Uknown host " + server);
        System.exit(1);
    } catch(IOException exc) {
        exc.printStackTrace();
        System.exit(2);
    }

    // Konfiguracja GUI
    Font f = new Font("Dialog", Font.BOLD, 14);
    ta.setFont(f);
    tf.setFont(f);
    tf.setBorder(BorderFactory.createLineBorder(Color.orange, 1));
    infoLab.setPreferredSize(new Dimension(200,30));
    JPanel p = new JPanel();
    p.setBorder(BorderFactory.createLineBorder(Color.red, 2));
    p.add(tf);
    p.add(infoLab);
    p.add(paste);
    cp.add(new JScrollPane(ta));
    cp.add(p, "South");

    tf.addActionListener(this);
    paste.addActionListener(this);

    // Przy zamykaniu aplikacji
    // zamykamy kana³ i gniazdo
    addWindowListener( new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        dispose();
        try {
         channel.close();
         channel.socket().close();
        } catch(Exception exc) {}
        System.exit(0);
      }
    });

    pack();
    show();
  }

  // Obs³uga akcji
  public void actionPerformed(ActionEvent e) {
    // Klikniêcie w przycisk "Wklej definicjê"
    // definicjê przechowuje dla nas obiekr klasy ReadDef
    if (e.getSource() == paste && rd != null) {
       ta.insert(rd.getResult(), ta.getCaretPosition());
    }
    else {  // ENTER na polu tekstowym tf - start w¹tku komuniakcji z serwerem
     if (!channel.isConnected()) try {
       connect();
     } catch(Exception exc) {
        exc.printStackTrace();
        return;
     }
     rd = new ReadDef(this, channel, tf.getText());
     rd.start();
    }
  }

  // £¹czenie kana³u z serwerem
  private void connect() throws UnknownHostException, IOException {
    if (!channel.isOpen()) channel = SocketChannel.open();
    channel.connect(new InetSocketAddress(server, port));
    System.out.print("£¹cze siê ...");
    while (!channel.finishConnect()) {
      try { Thread.sleep(200); } catch(Exception exc) { return; }
      System.out.print(".");
    }
    System.out.println("\nPo³¹czony.");
  }

  // Metoda wykorzystywana przez ReadDef
  // do pokazywania postepów komuniakcji z serwerem
  public void setInfo(String s) {
    infoLab.setText(s);
  }
}

class ReadDef extends Thread {

  private static Charset charset  = Charset.forName("ISO-8859-2");
  private static ByteBuffer inBuf = ByteBuffer.allocateDirect(1024);
  private static Matcher matchCode =
                 Pattern.compile("(\n250 ok)|(552 no match)").matcher("");
  private SocketChannel channel;
  private DictGui gui;
  private String word;


  public ReadDef(DictGui gui, SocketChannel ch, String wordToSearch) {
    this.gui = gui;
    channel = ch;
    word = wordToSearch;
  }

  private StringBuffer result;

  public void run() {
    result = new StringBuffer("Wyniki wyszukiwania:\n");
    int count = 0, rcount = 0;
    try {
      CharBuffer cbuf = CharBuffer.wrap("DEFINE * " + word + "\n");
      ByteBuffer outBuf = charset.encode(cbuf);
      channel.write(outBuf);

      while (true) {
        inBuf.clear();
        int readBytes = channel.read(inBuf);
        if (readBytes == 0) {
          gui.setInfo("Czekam ... " + ++count);
          Thread.sleep(200);
          continue;
        }
        else if (readBytes == -1) {
          gui.setInfo("Kana³ zamkniêty");
          channel.close();
          break;
        }
        else {
          inBuf.flip();
          cbuf = charset.decode(inBuf);
          result.append(cbuf);
          matchCode.reset(cbuf);
          if (matchCode.find()) break;
          else gui.setInfo("Czytam ... " + ++rcount);
        }
      }
    } catch(Exception exc) {
         exc.printStackTrace();
         return;
    }
    gui.setInfo("Czeka³em: " + count + " / Czyta³em: " + rcount + ". Gotowe.");
  }

  public String getResult() {
    if (result == null) return "Brak wyników wyszukiwania";
    return result.toString();
  }
}

class Main {
  public static void main(String[] args) {
    String server = "dict.org";
    new DictGui(server);
  }
}

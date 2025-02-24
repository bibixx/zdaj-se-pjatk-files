import javax.swing.*;
import java.io.*;
 
public class Continue {

  /* dla celów rejestracji wejœcia */
  BufferedWriter bw;
 
  public static void main(String[] args) {
    new Continue();
  } 

  public Continue() {
    /* Dla celów rejestracji wejœcia */


    String header = null;
    int i = 0;

    outerLoop:
      while ((header = ask("Nag³ówek?")) != null) {
        i++; 
        if (header.equals("")) continue;
        int j = 0;
        while (true) {
          String txt = ask("Tekst?"); 
          if (txt == null) break outerLoop;
          j++;
          if (txt.equals("")) continue;
          if (txt.equals("nh")) continue outerLoop;
          System.out.println(i + " " + header + " : " + j + " " + txt);
        }
      }

    System.out.println("Koniec");
    try {
      bw.flush();
    } catch(IOException exc) {} 
    System.exit(0); 
  }  

  private String ask(String txt) {
    String s = JOptionPane.showInputDialog(txt);
    if (s != null) log(txt, s);
    return s;
  }

  private void log(String pyt, String odp) {

    try {
      if (bw == null) 
         bw = new BufferedWriter(new FileWriter("log.$$$"));
      bw.write(pyt + " --- " + odp);
      bw.newLine();
    }
    catch (IOException exc) { 
      System.out.println(exc.toString());
      System.exit(1);
    }
  } 

}

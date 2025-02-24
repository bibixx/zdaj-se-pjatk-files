import java.text.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class DefLoc {

  static JFrame f = new JFrame();

  static public void report(JTextArea ta) {
    Locale defLoc = Locale.getDefault();
    ta.append("\nDomyœlna lokalizacja : " + defLoc);
    DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);
    NumberFormat nf = NumberFormat.getInstance();
    ta.append("\n"+df.format(new Date()));
    ta.append("\n"+nf.format(1234567.1));
    f.pack();
    f.show();
  }

  public static void main(String[] args) {
    JTextArea ta = new JTextArea();
    ta.setFont(new Font("Dialog", Font.BOLD, 32));
    f.getContentPane().add(new JScrollPane(ta));
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    report(ta);
    Locale.setDefault(new Locale("en"));
    report(ta);
  }


}
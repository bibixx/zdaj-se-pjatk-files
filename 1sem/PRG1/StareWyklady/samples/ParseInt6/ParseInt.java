import javax.swing.*;
 
public class ParseInt {
 
  public static void main(String[] args) {

    String s1 = JOptionPane.showInputDialog("Podaj pierwsz¹ liczbê");
    String s2 = null;
    if (s1 != null) {
      s2 = JOptionPane.showInputDialog("Podaj drug¹ liczbê");
      if (s2 != null) {
         int l1 = Integer.parseInt(s1);
         int l2 = Integer.parseInt(s2);
         JOptionPane.showMessageDialog(null, "Suma: " + (l1 + l2));
      }
    }

    System.exit(1);
  } 
 
}

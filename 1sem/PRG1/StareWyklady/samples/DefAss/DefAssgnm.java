import javax.swing.*;
 
public class DefAssgnm {
 
  public static void main(String[] args) {
    int len;
    String s = JOptionPane.showInputDialog("Napis?");
    if (s != null) len = s.length();
    else len = -1;
    System.out.println("D³ugosc napisu : " + len);
  } 
 
}

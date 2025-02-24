import javax.swing.*;
 
public class Sumowanie1 {
 
  public static void main(String[] args) {

    final int LIMIT = 200;
    int sum = 0;
    String in;
    while((in = JOptionPane.showInputDialog("Podaj liczbê :")) != null)  {
      int a = Integer.parseInt(in);
      if (sum + a > LIMIT ) break;
      sum += a;
    }
    System.out.println("Suma: " + sum);
    System.exit(0);
  } 
 
}

import javax.swing.*;
 
public class Sumowanie {
 
  public static void main(String[] args) {

    final int LIMIT = 200;
    int sum = 0;

    while(sum < LIMIT)  {
      String data = JOptionPane.showInputDialog("Podaj liczbê ca³kowit¹:");
      if (data == null) break;
      sum += Integer.parseInt(data);
    }
    System.out.println("Suma: " + sum);
    System.exit(0);
  } 
 
}

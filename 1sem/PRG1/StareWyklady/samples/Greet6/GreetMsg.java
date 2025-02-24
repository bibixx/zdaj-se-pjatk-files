import javax.swing.JOptionPane;
 
public class GreetMsg {
 
  public static void main(String[] args) {
   
    String name = JOptionPane.showInputDialog("Podaj swoje imiê");
    if (name == null) name = "";
    JOptionPane.showMessageDialog( null, "Witaj " + name + "!");
    System.exit(0);

  } 
 
}

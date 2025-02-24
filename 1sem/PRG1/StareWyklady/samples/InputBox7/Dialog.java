import javax.swing.JOptionPane;
 
public class Dialog { 
 
  public String ask(String msg) {
    String s = JOptionPane.showInputDialog(msg);
    if (s == null) s =  "";
    return s;
  }

  public void say(String msg) {
    JOptionPane.showMessageDialog(null, msg);
  }  
 
}

class Main {

  public static void main(String[] args) {
    Dialog dlg = new Dialog();
    String lastName = dlg.ask("Podaj nazwisko");
    String firstName = dlg.ask("Podaj imie");
    dlg.say(lastName + " " + firstName);
    System.exit(0);
  }

}

    

 

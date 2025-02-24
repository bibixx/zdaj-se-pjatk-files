import javax.swing.*;
 
public class Napisy {

  final int LIMIT = 10;
 
  public static void main(String[] args) {
     new Napisy();
  } 

  public Napisy() {
    String ozdoba = null,
           txt = null;
    int n = 0;

    for (ozdoba = ask("Co wokó³?");
         n <= LIMIT && ozdoba != null && (txt = ask("Napis?")) != null; 
         n++, System.out.println(n + ": " + ozdoba + txt + ozdoba) ); 

   System.exit(1); 
  }

  private String ask(String txt) {
    return JOptionPane.showInputDialog(txt);
  }

 
}

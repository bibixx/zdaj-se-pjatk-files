import javax.swing.JOptionPane;
 
public class ShowStrings {
 
  public static void main(String[] args) {

    String out = "";
    String inp = null;

    while ( (inp = JOptionPane.showInputDialog( "Wpisz coœ" )) != null) {
      if (inp.equals("")) 
         JOptionPane.showMessageDialog( null,
                                       "Podaj dane lub zamknij dialog"
                                      ); 
      else out = out + '\n' + inp;
    }

    if (out.equals("")) out = "Nie wprowadzono ¿adnych danych";
    JOptionPane.showMessageDialog(null, out); 
    System.exit(0);
 
  } 
 
}

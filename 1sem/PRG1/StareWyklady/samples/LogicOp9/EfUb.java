import javax.swing.*;
 
public class EfUb {

  public static void main(String[] args) {

    String nazwisko;
    String imie = null;
    String txt;
    
    if ((nazwisko = JOptionPane.showInputDialog("Podaj nazwisko")) != null
         && (imie = JOptionPane.showInputDialog("Podaj imie")) != null
       )
       txt = nazwisko + " " + imie;
    else txt = "Niepelna informacja";
    System.out.println(txt);
    System.out.println("Imie :" + imie);
    System.out.println("Nazwisko :" + nazwisko);
    System.exit(0);
  } 
 
}

import javax.swing.*;

class NotValidZipException extends Exception {   // Klasa wyj¹tku

    NotValidZipException() {
      super();
    }

    NotValidZipException(String s)  {                      
      super(s+ "\nPoprawny kod ma postaæ: nn-nnn"); 
    } 
} 

 
public class ZipAsk {

 public ZipAsk() { }

 public String getZip() throws NotValidZipException { 

    final int N = 6,        // d³ugoœæ kodu
              P = 2;        // pozycja na której wystêpuje kreska

    String zip = JOptionPane.showInputDialog("Podaj kod pocztowy:"); 
    if (zip == null) return zip;

    boolean valid = true;   // czy kod poprawny?

    char[] c = zip.toCharArray(); // tablica znaków w podanym kodzie

    // je¿eli struktura wadliwa: nie ta d³ugoœæ, brak kreski
    if (c.length != N || c[P] != '-') valid = false; 

    // czy w kodzie wystêpuj¹ tylko cyfry? 
    for (int i = 0; i<N && valid; i++) { 
         if (i==P) continue; 
         if (!Character.isDigit(c[i])) valid = false; 
         } 
    // w tej chwili wiemy ju¿, czy kod jest poprawny
    // jeœli nie:
    // - tworzymy i zg³aszamy wyj¹tek  
    if (!valid) throw new NotValidZipException("Wadliwy kod: " + zip); 

    // w przeciwnym razie zwracamy kod
    return zip; 
    } 
} 

class ZipAskTest {
 
  public static void main(String[] args) {

    JOptionPane.showMessageDialog(null, "Podaj trzy prawid³owe kody pocztowe");

    ZipAsk zask = new ZipAsk();
    String zip = null;
    int n = 3;

    while (n > 0) {
      try {
        zip = zask.getZip();
        if (zip == null) break;
        n--;
      } catch (NotValidZipException exc) {
          JOptionPane.showMessageDialog(null, exc.getMessage());
          continue;
      }
      System.out.println("Kod " + (3-n) + " : " + zip);
    }
    System.exit(0);
 
  } 
 
}

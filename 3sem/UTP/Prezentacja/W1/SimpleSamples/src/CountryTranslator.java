import java.util.*;
import java.text.*;
import javax.swing.*;

class CountryTranslator {

  public static void main(String[] args) {

    Locale[] loc = Locale.getAvailableLocales();
    Map map = new HashMap();
    String kraj;

    // Dodanie dostêpnych lokalizacji do mapy
    // klucz: nazwa kraju po polsku, wartoœc - lokealizacja
    for (int i=0; i<loc.length; i++) {
      String countryCode = loc[i].getCountry();  // kod kraju
      if (countryCode.equals("")) continue;
      kraj =  loc[i].getDisplayCountry();
      map.put(kraj, loc[i]);
    }

    String msg = "Podaj kraj";
    String in = "";
    while((kraj = JOptionPane.showInputDialog(msg)) != null ) {
      // Pobieramy lokalizacjê dla podanego kraju
      Locale savedLoc = (Locale) map.get(kraj);
      if (savedLoc == null) continue;
      msg = "Podaj kody jêzyków, rozdzielone spacjami";
      while((in = JOptionPane.showInputDialog(null, msg, in)) != null ) {
        StringTokenizer st = new StringTokenizer(in);
        if (st.countTokens() == 0) continue;
        String rep = "Nazwa kraju " + kraj + ":\n";

        // Dla kolejnych kodów jêzyków
        // uzyskujemy nazwê kraju w jêzyku odpowiadaj¹cym
        // lokalizacji zwi¹zanej z kodem jêsyka
        while(st.hasMoreTokens()) {
          Locale lang = new Locale(st.nextToken());
          rep += lang.getDisplayLanguage() + "   =   " +
                 savedLoc.getDisplayCountry(lang) + "\n";
        }
      JOptionPane.showMessageDialog(null,rep);
      }
      msg = "Podaj kraj";
    }
    System.exit(0);
  }
}
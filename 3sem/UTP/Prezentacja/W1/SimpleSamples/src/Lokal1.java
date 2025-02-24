import java.util.*;
//import java.text.*;
import com.ibm.icu.util.*;
import com.ibm.icu.text.*;
import javax.swing.*;
import java.awt.*;


public class Lokal1 {

  public static void main(String[] args) {

    // Tablica dostêpnych lokalizacji

    Locale[] loc = NumberFormat.getAvailableLocales();

    Vector columnNames = new Vector();
    columnNames.add("Kod jêzyka");
    columnNames.add("Kod kraju");
    columnNames.add("Kod wariantu");
    columnNames.add("Jêzyk");
    columnNames.add("Kraj");
    columnNames.add("Wariant");

    Vector rows = new Vector();

    for (int i=0; i<loc.length; i++) {
      String countryCode = loc[i].getCountry();  // kod kraju
      String langCode = loc[i].getLanguage();    // kod jêzyka
      String varCode  = loc[i].getVariant();     // wariant

      // lokalizacja opisana w jêzyku domyœlnej lokalizacji (polskim)
      String kraj =  loc[i].getDisplayCountry();
      String jezyk = loc[i].getDisplayLanguage();
      String wariant = loc[i].getDisplayVariant();
      Vector data = new Vector();
      data.add(langCode);
      data.add(countryCode);
      data.add(varCode);
      data.add(jezyk);
      data.add(kraj);
      data.add(wariant);
      rows.add(data);
     }
     Collections.sort(rows, new Comparator() {
       public int compare(Object o1, Object o2) {
         Vector v1 = (Vector) o1;
         Vector v2 = (Vector) o2;
         return ( ((String) v1.get(0)).compareTo( (String) v2.get(0)) );
       }
     });
     JTable tab = new JTable(rows, columnNames);
     tab.setFont(new Font("Dialog", Font.PLAIN, 18));
     JFrame f = new JFrame();
     f.getContentPane().add(new JScrollPane(tab));
     f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     f.setSize(600, 400);
     f.show();
  }

}
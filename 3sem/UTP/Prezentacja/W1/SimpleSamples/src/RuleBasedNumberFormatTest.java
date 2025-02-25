import com.ibm.icu.text.*;  // podpakiet ICU - dla RuleBasedNumberFormat
import java.util.*;         // Locale
import javax.swing.*;       // JOptionPane


class RuleBasedNumberFormatTest {

  public static void main(String[] args) {

    // Lokalizacje
    Locale[] loc = {
                     new Locale("pl"),
                     new Locale("en"),
                     new Locale("es"),
                     new Locale("de"),
                     new Locale("ru"),
                   };

    // Tablica formator�w typu SPELLOUT - dla ka�dej lokalizacji jeden
    RuleBasedNumberFormat[] rbnfSpell =
                            new RuleBasedNumberFormat[loc.length];

    // Utworzenie formator�w SPELLOUT
    for (int i= 0; i < loc.length; i++) {
      rbnfSpell[i] = new RuleBasedNumberFormat(
                         loc[i], RuleBasedNumberFormat.SPELLOUT
                     );
    }

    // Warto�ci do formatowania
    long[] values = { 5, 9, 10, 12, 20, 23, 111, 1001 };

    // Jako SPELLOUT
    show(values, rbnfSpell);

    // Jako liczby porz�dkowe - tylko angielskie
    show(values,
         new NumberFormat[] { new RuleBasedNumberFormat(
                                 loc[1], RuleBasedNumberFormat.ORDINAL),

                            }
        );



    System.exit(0);

  }

  // Og�lna metoda formatuj�ca i pokazuj�ca wyniki
  // Argumenty: tablica liczb do sformatowania
  //            tablica formator�w

  static void show(long[] val, NumberFormat[] rbnf) {
    String msg = "";
    for (int i=0; i < val.length; i++) {
      msg += "\n" + val[i];
      for (int j=0; j<rbnf.length; j++) {
        msg += " = " + rbnf[j].format(val[i]);
      }
    }
    JOptionPane.showMessageDialog(null, msg);
  }

}
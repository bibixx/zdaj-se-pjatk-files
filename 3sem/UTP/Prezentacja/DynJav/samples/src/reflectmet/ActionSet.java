package reflectmet;

import javax.swing.*;

public class ActionSet {

 public void dodaj() { show("Dodaj"); }
 public void usuñ() { show("Usuñ"); }
 public void zast¹p() { show("Zast¹p"); }
 public void szukaj() { show("Szukaj"); }
 public void otwórz() { show("Otwórz"); }

 private void show(String string) {
   JOptionPane.showMessageDialog(null, string);
 }

}
package reflectmet;

import javax.swing.*;

public class ActionSet {

 public void dodaj() { show("Dodaj"); }
 public void usu�() { show("Usu�"); }
 public void zast�p() { show("Zast�p"); }
 public void szukaj() { show("Szukaj"); }
 public void otw�rz() { show("Otw�rz"); }

 private void show(String string) {
   JOptionPane.showMessageDialog(null, string);
 }

}
import javax.swing.*;  
public class Test {
 
  public static void main(String[] args) {
    String[] dest  = { "Bali", "Cypr", "Ibiza", "Kenia", "Kuba" };
    double[] price = { 5000, 2500, 2800, 4500, 6000 };
    Travel[] t = new Travel[dest.length];
    for (int i = 0; i < t.length; i++) t[i] = new Travel(dest[i], price[i]);
    TravelSearcher ts = new TravelSearcher(t);
    String d;
    while((d=JOptionPane.showInputDialog("Podaj cel podró¿y:")) != null) {
      Travel trav = ts.search(d);
      String msg;
      if (trav == null) msg = "Nie znaleziono takiej podró¿y";
      else msg = trav.getDest() + " - cena: " + trav.getPrice();
      JOptionPane.showMessageDialog(null, msg); 
    }
    System.exit(0);
  }

}
import javax.swing.*; 
public class Town {

  private String[] town = { "Warszawa", "Poznañ", "Kraków", "Gdañsk" }; 
  
  Town() {
    show("Na pocz¹tku");
    exchange(0, town.length -1);
    show("Po przestawienieu pierwszego i ostatniego");
    exchange(1,2);
    show("Po przestawienieu drugiego i trzeciego");
    String s = JOptionPane.showInputDialog("Podaj nazw© miasta");
    if (s != null) {
      String msg = "Nie ma takiego miasta";
      int ind = getIndex(s);
      if (ind != -1) msg = "Miasto " + s + " indeks " + ind;
      System.out.println(msg);
    }
    System.exit(0);
  }

  public void show(String s) {
    System.out.println(s);
    for (int i=0; i<town.length; i++) System.out.println(town[i]);
  }

  public void exchange(int first, int last) {
    String temp = town[last];
    town[last] = town[first];
    town[first] = temp;
  }

  public int getIndex(String s) {
    for (int i=0; i<town.length; i++) if (town[i].equals(s)) return i;
    return -1;
  }


  public static void main(String[] args) {
     new Town();
  } 
 
}

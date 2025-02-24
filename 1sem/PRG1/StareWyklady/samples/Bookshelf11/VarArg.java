 
public class VarArg {

  public VarArg() {
    showMsgs( new String[] { "Warszawa", "Krak¢w" } );
    showMsgs( new String[] { "Ala", "Kot", "Pies" } );
    showIncome( new Publication[] 
                { new Book("P. Pies", "Psy", "WydPP", 2002, "ISBN01", 25, 100),
                  new Book("K. Kot", "Koty", "WydPP", 2002, "ISBN02", 22, 90),
                  new Journal(1, "Kwiaty", "WydAN", 2002, "ISSN03", 10, 200),
                }
              ); 

    showIncome( new Publication[] 
                { new Book("A. Koä", "Konie", "Tur", 2001, "ISBN01", 35, 50),
                  new Journal(1, "Ryby", "W&S", 2002, "ISSN03", 20, 100),
                }
              ); 
  }

  public void showMsgs(String[] s) {
    for (int i=0; i<s.length; i++) System.out.println(s[i]);
  }
 
  public void showIncome(Publication[] p) {
    double d = 0;
    String opis = "";
    for (int i=0; i<p.length; i++) {
      opis += " " + p[i].getIdent(); 
      d += p[i].getPrice() * p[i].getQuantity();
    }
    System.out.println("Doch¢d z pozycji " + opis);
    System.out.println(d);
  }  

 
  public static void main(String[] args) {
    new VarArg();
  } 
 
}

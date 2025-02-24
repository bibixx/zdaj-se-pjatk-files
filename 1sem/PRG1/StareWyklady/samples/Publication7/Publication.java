public class Publication {
  
  private String title;
  private String publisher;
  private int year;
  private String ident;
  private double price;
  private int quantity;

  public Publication(String t, String pb, int y,
                     String i, double pr, int q) 
  {
    title = t;
    publisher = pb;
    year = y;
    ident = i;
    price = pr;
    quantity = q;
  }

  public String getTitle() {
    return title;
  }

  public String getPublisher() {
    return publisher;
  }

  public int getYear() {
    return year;
  }

  public String getIdent() { 
    return ident;
  }
  
  public double getPrice() {
    return price;
  }

  public void setPrice(double p) {
    price = p;
  }

  public int getQuantity() {
    return quantity;
  }

  public void buy(int n) {
    quantity += n;
  }  
  
  public void sell(int n) {
    quantity -= n;
  }

}

class PubTest {
 
  public static void main(String[] args) {

    Publication b = new Publication("Psy", "Dog & Sons", 2002,
                                    "ISBN6789", 21.0, 0);
    int n = 10;
    b.buy(n);
    double koszt = n * b.getPrice();
    System.out.println("Na zakup " + n + " publikacji:");
    System.out.println(b.getTitle());
    System.out.println(b.getPublisher()); 
    System.out.println(b.getYear());
    System.out.println(b.getIdent());    
    System.out.println("---------------\nwydano: " + koszt);
    b.sell(4); 
    System.out.println("---------------");
    System.out.println("Po sprzeda¿y zosta³o " + b.getQuantity() + " pozycji");    
  }
}


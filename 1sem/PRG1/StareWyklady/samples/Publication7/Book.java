public class Book extends Publication {

  private String author;

  public Book(String aut, String tit, String pub, int y, String id, 
              double price, int quant) {
    super(tit, pub, y, id, price, quant);
    author = aut;
  }

 public String getAuthor() {
   return author;
 }

}

class TestBook {
  public static void main(String[] args) {

    Book b = new Book("James Gossling", "Moja Java", "WNT", 2002,
                      "ISBN6893", 51.0, 0);
    int n = 100;
    b.buy(n);
    double koszt = n * b.getPrice();
    System.out.println("Na zakup " + n + " ksi¹¿ek:");
    System.out.println(b.getAuthor());
    System.out.println(b.getTitle());
    System.out.println(b.getPublisher()); 
    System.out.println(b.getYear());
    System.out.println(b.getIdent());    
    System.out.println("---------------\nwydano: " + koszt);
    b.sell(90); 
    System.out.println("---------------");
    System.out.println("Po sprzeda¿y zosta³o " + b.getQuantity() + " pozycji");    
  }
}
 
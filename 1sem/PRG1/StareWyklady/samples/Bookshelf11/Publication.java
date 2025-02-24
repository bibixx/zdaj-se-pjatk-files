public class Publication {

  private String title;
  private String publisher;
  private int year;
  private String ident;
  private double price;
  private int quantity;

  private Bookshelf bs;  // refrencja do pó³ki na której stoj¹ te publikacje

  // ustal pó³kê gdzie stoj¹ (przy wstawianiu)
  public void setBookshelf(Bookshelf b) {
    bs = b;
  }

  // zwraca pó³kê, gdzie stoj¹ te publikacje
  public Bookshelf whereIs() {
    return bs;
  }

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


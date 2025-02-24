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

 
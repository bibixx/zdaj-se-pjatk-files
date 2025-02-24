public class Journal extends Publication {

  private int nr;

  public Journal(int nr, String tit, String pub, int y, String id, 
              double price, int quant) {
    super(tit, pub, y, id, price, quant);
    this.nr = nr;
  }

 public int getNumber() {
   return nr;
 }

}

 
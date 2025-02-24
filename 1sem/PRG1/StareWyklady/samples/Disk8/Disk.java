public class Disk  {

  private static double vat;

  private String model;
  private int capacity;
  private double price;
  
  public Disk(String m, int c, double p) {
    model = m;
    capacity = c;
    price = p;
  }

  public String getDescription() {
    return model + " ," + capacity + " MB";  
  }
  
  public double getBruttoPrice() {
    return price * ( 1 +  vat/100 );
  }

  public static void setVat(double v) {
    vat = v;
  }
 
}

class Test {
   
  public static void main(String[] args) {
    Disk.setVat(22.0);
    Disk d1 = new Disk("IBM598", 6040, 430.0);
    System.out.println(d1.getDescription() + " - cena " 
                       + d1.getBruttoPrice() + " z³");
  }
}
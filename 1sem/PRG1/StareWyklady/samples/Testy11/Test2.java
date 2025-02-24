 
public class Test2 {
 
  public static void main(String[] args) {
    byte[] b1 = {1, 2, 3 };
    byte[] b2 = {1, 2, 3, 5, 5 }; // tablice maj¥ r¢¾ne rozmiary
    byte[] b = b2;
    b2 = b1;
    b2[0] = 77;
    b[0] = 99;
    System.out.print("\nTablica \"b1\":");
    for (int i=0; i < b1.length; i++) System.out.print(" " + b1[i]);
    System.out.print("\nTablica \"b2\":");
    for (int i=0; i < b2.length; i++) System.out.print(" " + b2[i]);
    System.out.print("\nTablica \"b\":");
    for (int i=0; i < b.length; i++) System.out.print(" " + b[i]);
  } 
 
}

 
public class Catch1 {
 
  public static void main(String[] args) {
    int a = 1, b = 0, c = 0;
    String wynik; 
    try {
      c = a/b;
      wynik = "" + c;
    } catch (ArithmeticException exc) {
        wynik = "***";
    }
    System.out.println(wynik);
  } 
 
}

public class Express1 {

  public static void main(String[] args) {

    int a = 1, b = 2, c = 3;
    a = b = c * 1 + 2;
    System.out.println("a=" + a + " b=" + b + " c=" + c);
    a = b = c * (1 + 2);
    System.out.println("a=" + a + " b=" + b + " c=" + c);
    a = b++;
    System.out.println("a=" + a + " b=" + b + " c=" + c);
    c = --b;
    System.out.println("a=" + a + " b=" + b + " c=" + c);
    a++;
    b++;
    c++;
    System.out.println("a=" + a + " b=" + b + " c=" + c);

    a = b++*++c;
    System.out.println("a=" + a + " b=" + b + " c=" + c);

    int DlugaNazwaZmiennej = 20;
    DlugaNazwaZmiennej = DlugaNazwaZmiennej * 10;
    DlugaNazwaZmiennej *= 10; 
    System.out.println( DlugaNazwaZmiennej );

  }
}
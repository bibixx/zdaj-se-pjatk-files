public class Strings1 {

  public static void main(String[] args) {

    String napis = "Waga ";
    double w = 10.234;
    System.out.println(napis + w + " kg");

    String txt = napis + w + 10 + " kg";  
    System.out.println(txt);               // dlaczego 10.23410?
    txt = napis + (w + 10) + " kg"; 
    System.out.println(txt);               // a tu  20.234?
    txt = napis + w * 10 + " kg";          // w*10 bez nawiasów ?..
    System.out.println(txt);               // ... i dobrze - dlaczego?

    String bzdura = "Bzdura";
    bzdura = napis;
    System.out.println(bzdura);  // dlaczego wyprowadzi "Waga " a nie "Bzdura"

    txt = napis;
    txt = txt + "Ali ?";
    System.out.println(txt);      // a tu inaczej:  
    System.out.println(napis);    // dlaczego napis jest "Waga "?
                                  // a nie "Waga Ali ?"  

    System.out.println( napis == bzdura );           // dlaczego true?
    System.out.println( txt == napis );              // dlaczego false?
    System.out.println( txt == "Waga Ali ?");        // dlaczego false? 
    System.out.println( txt.equals("Waga Ali ?") );  // dlaczego true?
    
    // a teraz coœ dziwnego:
    napis = "Ala";
    System.out.println( napis == "Ala");      // true czy false???
    
  }
}    
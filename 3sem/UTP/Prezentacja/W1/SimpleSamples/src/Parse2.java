import java.io.*;
import java.text.*;
import java.util.*;

public class Parse2 {

  public static void main(String[] args) {

    // Format walutowy w domyœlnej lokalizacji
    // czyli w PL np. 12 z³
    NumberFormat format = NumberFormat.getCurrencyInstance();

    // Lista wartoœci wydatków (zapisanych w tekœcie pliku)
    List numList = new ArrayList();

    try {
      BufferedReader br = new BufferedReader(
                            new FileReader(args[0])
                          );

      // czytanie kolejnych wierszu
      String in;
      while ((in = br.readLine()) != null) {

        int p = 0;                    // bie¿¹cy indeks rozbioru
        int last  = in.length() - 1;  // ostatni indeks w wierszu

        // Utworzenie pozycji rozbioru wiersza (od 0)
        ParsePosition ppos = new ParsePosition(0);

        // Dopóki nie dobiegliœmy do koñca wiersza
        while (p <= last) {
           // Próbujemy pobraæ kolejn¹ liczbê w formacie walutowym
           Number num = format.parse(in, ppos);

           if (num == null)              // je¿eli b³¹d,
             p = ppos.getErrorIndex()+1; // indeks na znaku po b³êdzie
           else {                        // je¿eli uda³o siê sczytaæ wartoœæ
             p =  ppos.getIndex();   // indeks na nastêpnym znaku po formacie
             if (!Character.isLetter(in.charAt(p))) // je¿eli to nie litera
                numList.add(num);           // dodajemy wartoœæ do listy
           }
           ppos.setIndex(p);         // ustawiamy nastêpn¹ pozycjê
        }                            // od której kontynuacja rozbioru
      }
      br.close();
    } catch(Exception exc) {
        exc.printStackTrace();
        System.exit(1);
    }

    // Wypisanie i podsumowanie zapisanych w pliku wydatków
    System.out.println("Wydatki w z³:");
    double suma = 0;
    for (Iterator iter = numList.iterator(); iter.hasNext(); ) {
      Number val  = (Number) iter.next();
      System.out.println(val);
      suma += val.doubleValue();
    }
    System.out.println("Wydano w sumie: " + format.format(suma));
  }
}
import java.io.*;
import java.text.*;
import java.util.*;

public class Parse2 {

  public static void main(String[] args) {

    // Format walutowy w domy�lnej lokalizacji
    // czyli w PL np. 12 z�
    NumberFormat format = NumberFormat.getCurrencyInstance();

    // Lista warto�ci wydatk�w (zapisanych w tek�cie pliku)
    List numList = new ArrayList();

    try {
      BufferedReader br = new BufferedReader(
                            new FileReader(args[0])
                          );

      // czytanie kolejnych wierszu
      String in;
      while ((in = br.readLine()) != null) {

        int p = 0;                    // bie��cy indeks rozbioru
        int last  = in.length() - 1;  // ostatni indeks w wierszu

        // Utworzenie pozycji rozbioru wiersza (od 0)
        ParsePosition ppos = new ParsePosition(0);

        // Dop�ki nie dobiegli�my do ko�ca wiersza
        while (p <= last) {
           // Pr�bujemy pobra� kolejn� liczb� w formacie walutowym
           Number num = format.parse(in, ppos);

           if (num == null)              // je�eli b��d,
             p = ppos.getErrorIndex()+1; // indeks na znaku po b��dzie
           else {                        // je�eli uda�o si� sczyta� warto��
             p =  ppos.getIndex();   // indeks na nast�pnym znaku po formacie
             if (!Character.isLetter(in.charAt(p))) // je�eli to nie litera
                numList.add(num);           // dodajemy warto�� do listy
           }
           ppos.setIndex(p);         // ustawiamy nast�pn� pozycj�
        }                            // od kt�rej kontynuacja rozbioru
      }
      br.close();
    } catch(Exception exc) {
        exc.printStackTrace();
        System.exit(1);
    }

    // Wypisanie i podsumowanie zapisanych w pliku wydatk�w
    System.out.println("Wydatki w z�:");
    double suma = 0;
    for (Iterator iter = numList.iterator(); iter.hasNext(); ) {
      Number val  = (Number) iter.next();
      System.out.println(val);
      suma += val.doubleValue();
    }
    System.out.println("Wydano w sumie: " + format.format(suma));
  }
}
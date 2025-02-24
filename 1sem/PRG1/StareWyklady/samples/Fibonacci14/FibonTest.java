import javax.swing.*;
import java.util.*;

class QTimer {

   private final long start;

   public QTimer() {
     start = System.currentTimeMillis();
   }

   public long getElapsed() {
      return System.currentTimeMillis() - start;
   }
}
 
public class FibonTest {


  public FibonTest() {

    String msg = "WprowadŸ n oraz ew. opcjê I - liczenie tylko iteracyjnie: ";
    String in;

    while ((in = JOptionPane.showInputDialog(msg)) != null) {

      long fibRec = -1,
           etRec = 0, etIter = 0;


      StringTokenizer st = new StringTokenizer(in);
      int n = Integer.parseInt(st.nextToken());
      boolean iterOnly = false;

      if (st.hasMoreTokens() && st.nextToken().equals("I")) iterOnly = true;

      QTimer qt = null;

      // Wywolanie wersji rekurencyjnej
      if (!iterOnly) {
        qt = new QTimer();
        fibRec = fibRec(n);
        etRec = qt.getElapsed();
      } 

      // Wywolanie wersji iteracyjnej
      qt = new QTimer();
      double fibIter = fibIter(n);
      etIter = qt.getElapsed();

      // Wyliczenie za pomoc¹ formu³y znanje jako formu³a Bineta
      double binet = binet(n);
       
      JOptionPane.showMessageDialog(null, 
         "F rec (" + n + ") = " + (fibRec == -1 ? "N/A" : String.valueOf(fibRec))  + 
         "  Czas: " + etRec + " ms\n" +
         "F iter(" + n + ") = " + fibIter + "  Czas: " + etIter + " ms\n" +
         "Fbinet(" + n + ") = " + binet);
    }
  } 
   
  // Wersja rekurencyjna
  long fibRec(int n) {
    if (n < 2) return n;
    else return fibRec(n-1) + fibRec(n-2);
  }

  // Wersja iteracyjna

  double fibIter(int n) {

    if (n == 0) return 0;

    double prev = 1,  // F(n-1)
           pprev = 1, // F(n-2)
           fib = 1;   // F(n) 

    while (n-- > 2) {
      fib = prev + pprev;
      pprev = prev;
      prev = fib;
    }

    return fib;  
  }

  // Formu³a Bineta  
  double binet(int n) {
    double Fi = (1 + Math.sqrt(5))/2;
    double minusfi = (1 - Math.sqrt(5))/2;
    return (Math.pow(Fi,n) - Math.pow(minusfi, n))/Math.sqrt(5);
  }
 
  public static void main(String[] args) {
   new FibonTest(); 
   System.exit(0);
  } 
 
}

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
 

public class SelSort1 {

static public void selectionSort(int[] a) {
    for (int toPos=a.length-1; toPos>0; toPos--) {
      int posMax = 0; 
      for (int k=1; k <= toPos; k++) 
 	if (a[posMax] < a[k]) posMax = k;
      int temp = a[posMax];
      a[posMax] = a[toPos];
      a[toPos] = temp;
    }
  }

 
  public static void main(String[] args) {
     int n = Integer.parseInt(args[0]);
     Random rand = new Random();
     int[] a = new int[n];
     int[] b = new int[n];
     for (int i=0; i < n; i++) {
        a[i] = rand.nextInt(n*10);
        b[i] = a[i];
     }
    System.out.println("Liczba elementów tablicy " + n);
    QTimer qt = new QTimer();
    selectionSort(a);
    System.out.println("Czas selection sort: " + qt.getElapsed());
    qt = new QTimer();
    Arrays.sort(b); 
    System.out.println("Czas quicksort: " + qt.getElapsed());
    
    qt = new QTimer();
    selectionSort(a);

  } 

static void show(int[] a) {
    System.out.print('\n');
    for (int i=0; i < a.length; i++) System.out.print(" " + a[i]);
  }

 
}

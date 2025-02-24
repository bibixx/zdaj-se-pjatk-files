import java.util.*;
public class SelSort {

  public SelSort(int n, int m) { 

    Random rand = new Random();

    int[] a = new int[n];
    for (int i=0; i < n; i++) {
      a[i] = rand.nextInt(m+1);
      System.out.print(" " + a[i]);
    }
    selectionSort(a);
    System.out.print('\n');
    for (int i=0; i < n; i++) System.out.print(" " + a[i]);
  } 

  public void selectionSort(int[] a) {

    // toInd - oznacza ostatni indeks nieposortowanej czêœci tablicy
    // na pocz¹tku jest to ostatni indeks tablicy
    // w kolejnych krokach toInd bêdzie zmniejszany o 1
    // bo zmniejszaj¹ siê rozmiary nieposortowanej czêsci tablicy

    // Gdy toInd osi¹gnie wartoœæ 0 - "nieposortowany" bêdzie pierwszy
    // element. Ale nie mamy go ju¿ gdzie przstawiæ, faktycznie znajduje siê
    // on na w³asciwym miejscu. 
    // Zatem nie musimy dokonywaæ ¿adnego przestawienia. 
    // Tablica jest posortowana. Koñczymy pêtle.

    for (int toInd=a.length-1; toInd>0; toInd--) {

      int indMax = 0;   // indeks maksymalnego elementu
                        // w nieposortowanej czêœci tablicy 

      // szukamy tego indeksu, przegl¹daj¹c nieposortowan¹ czêœæ tablicy
      for (int k=1; k <= toInd; k++) 
 	if (a[indMax] < a[k]) indMax = k; 
      
      // Przestawiamy elementy: 
      // maksymalny element idzie na ostatni¹ pozycjê w nieposortowanej
      // czêœci tablicy; a liczba spod tego indeksu jest zapisywana
      // w miejscu okupowanym poprzednio przez max element

      int temp = a[toInd];
      a[toInd] = a[indMax];
      a[indMax] = temp;
    }
  }


  public static void main(String[] args) {
     new SelSort(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
   } 

 
}


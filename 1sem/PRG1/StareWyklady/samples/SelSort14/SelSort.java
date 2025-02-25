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

    // toInd - oznacza ostatni indeks nieposortowanej cz�ci tablicy
    // na pocz�tku jest to ostatni indeks tablicy
    // w kolejnych krokach toInd b�dzie zmniejszany o 1
    // bo zmniejszaj� si� rozmiary nieposortowanej cz�sci tablicy

    // Gdy toInd osi�gnie warto�� 0 - "nieposortowany" b�dzie pierwszy
    // element. Ale nie mamy go ju� gdzie przstawi�, faktycznie znajduje si�
    // on na w�asciwym miejscu. 
    // Zatem nie musimy dokonywa� �adnego przestawienia. 
    // Tablica jest posortowana. Ko�czymy p�tle.

    for (int toInd=a.length-1; toInd>0; toInd--) {

      int indMax = 0;   // indeks maksymalnego elementu
                        // w nieposortowanej cz�ci tablicy 

      // szukamy tego indeksu, przegl�daj�c nieposortowan� cz�� tablicy
      for (int k=1; k <= toInd; k++) 
 	if (a[indMax] < a[k]) indMax = k; 
      
      // Przestawiamy elementy: 
      // maksymalny element idzie na ostatni� pozycj� w nieposortowanej
      // cz�ci tablicy; a liczba spod tego indeksu jest zapisywana
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


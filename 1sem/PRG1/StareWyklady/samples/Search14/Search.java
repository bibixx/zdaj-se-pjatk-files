import java.util.*; 

public class Search {

  public static int linearSearch(int[] tab, int v) {
    for (int i=0; i < tab.length; i++) 
      if (tab[i] == v) return i;
    return -1;
  }

  static public int binarySearch(int[] tab, int v)   {  
    int low = 0;
    int high = tab.length - 1;
    while (low <= high) {
      int mid = (low + high) / 2;
      if (v  < tab[mid]) high = mid - 1;
      else if (v > tab[mid]) low = mid + 1;
           else return mid;
    }
    return -1;
  }

  public static void main(String[] args) {
     int[] a = { 2, 7, 5, 4, 3, 11 };
     int n = 4;
     System.out.println("Liczba " + n + " ma indeks " + linearSearch(a,n));
     n = 9;
     System.out.println("Liczba " + n + " ma indeks " + linearSearch(a,n));
     Arrays.sort(a);
     n = 4;
     System.out.println("Liczba " + n + " ma indeks " + binarySearch(a,n));
     n = 9;
     System.out.println("Liczba " + n + " ma indeks " + binarySearch(a,n));      
  } 
 
}

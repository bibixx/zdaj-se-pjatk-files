public class SortString {

  static public void selectionSort(String[] s) {
    for (int toInd=s.length-1; toInd>0; toInd--) {
      int indMax = 0;   
      for (int k=1; k <= toInd; k++) 
 	if (s[indMax].compareTo(s[k]) < 0) indMax = k; 
      String temp = s[toInd];
      s[toInd] = s[indMax];
      s[indMax] = temp;
    }
  }


  public static void show(String[] s) {
    System.out.print('\n');
    for (int i=0; i < s.length; i++) System.out.print(" " + s[i]);
  }

 
  public static void main(String[] args) {
    String[] s =  {"A", "Z", "C", "B", "1", "3", "2", "A", "C" };
    show(s);
    selectionSort(s);
    show(s);
  } 
 
}

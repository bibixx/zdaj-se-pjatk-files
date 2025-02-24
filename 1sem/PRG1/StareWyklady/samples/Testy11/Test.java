public class Test {

  Test() {
    int[] a = {1, 2, 3, 4 };
    int[] wynik = dblVal(a);
    for (int i=0; i < wynik.length; i++) 
      System.out.print(" " + wynik[i]);
  }

  int[] dblVal(int[] tab) {
    int[] w = new int[tab.length];  // utworzenie tablicy "pod wynik"
                                    // jej rozmiary musz¹ byæ równe
                                    // rozmiarom tablicy-argumentu   
    for (int i=0; i < w.length; i++) w[i] = tab[i]*2;
    return w;
  }   
      
  public static void main(String[] args) {
    new Test();
  } 
 
}

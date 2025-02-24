 
public class MultiArr {

 public static void main(String[] arg) {
   int w[] = { 2, 3, 4 };
   int n = 3;
   int[][] m3 = new int[n][];    // rozmiary wierszy
                              // b©d¥ zmienne dynamicznie
   for(int i = 0; i < m3.length; i++) {
     m3[i] = new int[w[i]];
     for (int j = 0; j < m3[i].length; j++) m3[i][j] = i + j;
   }
 
   for (int i = 0; i < m3.length; i++) {
     System.out.println("Rozmiar " + i + "-go wiersza " + m3[i].length);
     String out = " ";
     for(int j = 0; j < m3[i].length; j++) out += " " + m3[i][j];
     System.out.println(out);
   }
 
 }

}

 

 
public class Break {
 
  public static void main(String[] args) {
    outerLoop: for (int i=1; i < 10; i++) { 
      System.out.println("Petla po i: i = " + i); 
      for (int j=1; j < 10; j++) { 
        if (i + j  > 5) break outerLoop; 
        System.out.println("Pêtla po j: i = " + i + "  j = " + j);
      } 
    } 
  } 

}

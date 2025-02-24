 
public class Nested {
 
  public static void main(String[] args) {
     String out = null;
     char c = 'a';
     while (c <= 'd') {
       for (int i=1; i<=2; i++) {
         out = "Dla " + c + " " + i + " mamy j ="; 
         for (int j = i; j <= i + 3; j++) out += " " + j;
         System.out.println(out);
       }  
       c++;      
     }
   } 
 
}

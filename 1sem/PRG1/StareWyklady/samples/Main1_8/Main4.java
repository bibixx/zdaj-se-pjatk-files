public class Main4 {

 double a = 12, b = 14, c = 4;

 Main4() {
   report();
   increase();
   report();
   increase();
   report();
 }

 double sum() {
   return a + b + c;
 }

 double average() {
   return (a + b + c)/3;
 }

 void increase() {
   a++;   b++;   c++;
 } 

 void report() {
    System.out.println("Suma " + sum());
    System.out.println("Srednia " + average());
  }
   
 
  public static void main(String[] args) {
    new Main4();
  } 
 
}

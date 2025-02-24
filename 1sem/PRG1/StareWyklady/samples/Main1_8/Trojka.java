public class Trojka {

 double a, b, c;

 Trojka(double x, double y, double z) {
   a = x;
   b = y;
   c = z;
 }

 double sum() {
   return a + b + c;
 }

 double average() {
   return (a + b + c)/3;
 }

 void increase() {
   a++;
   b++;
   c++;
 } 

 void report() {
    System.out.println("Suma " + sum());
    System.out.println("Srednia " + average());
  }
   
 
  public static void main(String[] args) {
    Trojka t = new Trojka(12, 14, 4);
    t.report();
    t.increase();
    t.report();
    t.increase();
    t.report();
  } 
 
}

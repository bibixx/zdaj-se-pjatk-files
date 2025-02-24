public class Main2 {

 double sum(double a, double b, double c) {
   return a + b + c;
 }

 double average(double a, double b, double c) {
   return (a + b + c)/3;
 } 

 void report(double a, double b, double c) {
    System.out.println("Suma " + sum(a, b, c));
    System.out.println("Srednia " + average(a, b, c));
  }
   
 
  public static void main(String[] args) {
    double a = 12.0,
           b = 14.0,
           c =  4.0;
    report(a, b, c);
    a++;
    b++;
    c++;
    report(a, b, c);
    a++;
    b++;
    c++;
    report(a, b, c);

  } 
 
}

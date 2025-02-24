public class SimpleCalc {
  
  private double a;
  private double b;

  public SimpleCalc(double x, double y) {
    a = x;
    b = y;
  }

  public double makeOp(char op) {
   
    double r = 0;

    switch(op) {
       case '+' : r = a + b; break;
       case '-' : r = a - b; break;
       case '*' : r = a * b; break;
       case '/' : r = a / b; break;
       default  : System.out.println("Nieznany kod operacji");
    }
    
    return r;
  }

}

class SimplecalcTest {

  public static void main(String[] args) {

    SimpleCalc sc = new SimpleCalc(1.2, 3.7);
    System.out.println( sc.makeOp('+'));
    System.out.println( sc.makeOp('-'));
    System.out.println( sc.makeOp('*'));
    System.out.println( sc.makeOp('/'));

  } 
}
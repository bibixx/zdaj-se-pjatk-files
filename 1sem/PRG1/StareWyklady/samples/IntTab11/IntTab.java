
public class IntTab {

  public int sum(int[] a) {
    int sum = 0;
    for (int i=0; i < a.length; i++) sum += a[i];
    return sum;
  }

  public int max(int[] a) {
    int max = a[0];
    for (int i=0; i < a.length; i++)
      if (a[i] > max) max = a[i];
    return max;
  }

  public int min(int[] a) {
    int min = a[0];
    for (int i=0; i < a.length; i++)
      if (a[i] < min) min = a[i];
    return min;
  }

  public double average(int[] a) {
    double l = a.length;
    return sum(a)/l;
  }

  public void increase(int[] a) {
    for (int i=0; i < a.length; i++) a[i]++;
  }

  public void decrease(int[] a) {
    for (int i=0; i < a.length; i++) a[i]--;
  }

  public int[] add(int[] a, int[] b) {
    if (a.length != b.length) {
       System.out.println("Rozmiary tanlic musz¹ byæ takie same");
       return null;
    }
    int[] suma = new int[a.length];
    for (int i=0; i < a.length; i++) suma[i] = a[i] + b[i];
    return suma;
  }


  public static void main(String[] args) {
    new IntTab();
   }

  public IntTab() {
    int[] x = { 1, 3, 5 };
    int[] y = { 2, 4, 6 };
    showScalar("Suma x", sum(x) ); 
    showScalar("Suma y", sum(y) );
    showScalar("Max x", max(x) ); 
    showScalar("Max y", max(y) );
    showScalar("Min x", min(x) ); 
    showScalar("Min y", min(y) );
    showScalar("Srednia x", average(x) ); 
    showScalar("Srednia y", average(y) );
    increase(x);
    increase(y);
    showArr("x po zwiekszeniu", x);
    showArr("y po zwiekszeniu", y);
    decrease(x);
    decrease(y);
    showArr("x po zmniejszeniu", x);
    showArr("y po zmniejszeniu", y);
    showArr("x + y", add(x,y));
 }

 private void showScalar(String s, int v) {
    System.out.println(s + " = " + v);
 }

 private void showScalar(String s, double v) {
    System.out.println(s + " = " + v);
 }


 private void showArr(String s, int[] a) {
   System.out.println(s + ":");
   for (int i=0; i < a.length; i++) System.out.print(" " + a[i]);
   System.out.print('\n');
 }

}

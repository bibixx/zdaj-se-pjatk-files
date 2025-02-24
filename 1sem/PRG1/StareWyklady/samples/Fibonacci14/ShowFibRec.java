 
public class ShowFibRec {

  int[] calls;

  ShowFibRec(int n) {
    calls = new int[n+1];
    fib(n);
    for(int i=0; i <= n; i++) 
      System.out.println("Liczba wywo³añ fib z argumentem " + i 
                          + " " + calls[i]);
  }  
  

  int fib(int n) {
    calls[n]++;
    if (n < 2) return n;
    else return fib(n-1) + fib(n-2);
  }


 
  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);
    new ShowFibRec(n);
  } 
 
}

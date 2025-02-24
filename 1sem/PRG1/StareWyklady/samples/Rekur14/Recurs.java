public class Recurs {

  public static void show1(int i) {
    System.out.println("show1 " + i);
    if (i > 10) return;
    show1(i+1);
  } 

  public static void show2(int i) {
    if (i > 10) return;
    show2(i+1);
    System.out.println("show2 " + i);
  }
  

  public static int sum(int n) {
    if (n == 1) return 1;  
    else return n + sum(n-1);
  }    
    
 
  public static void main(String[] args) {
    System.out.println(sum(100));
//    show1(1);
//    show2(1);

  } 
 
}

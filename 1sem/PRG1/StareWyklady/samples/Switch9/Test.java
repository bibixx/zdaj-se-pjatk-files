import javax.swing.*;
 
public class Test {
 
  public static void main(String[] args) {
    int i = Integer.parseInt(JOptionPane.showInputDialog("Liczba"));
    switch(i)    {
        case 1 : switch(testNum(i)) {
                       case 1 : System.out.println("same one"); break;
                       case 2 : System.out.println("double one"); break;
                       default: System.out.println("other one"); break;
                    }
                    break;
      case 2 :  System.out.println("two"); break;
      default:  System.out.println("other two"); break;
    }
  } 

 static int testNum(int n) {
   return n*2;
 }  
 
}

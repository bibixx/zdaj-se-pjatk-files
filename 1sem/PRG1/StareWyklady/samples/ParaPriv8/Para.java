import javax.swing.*;

public class Para {
  
  private int a;
  private int b;
  public String nazwa;

  public Para(int x, int y) {
    a = x; 
    b = y;
  }

  private String makeString() {
    return nazwa + " " + a + " i " + b;
  }

  public void show() {
    JOptionPane.showMessageDialog(null, makeString()); // 6
  }
}    
  
  
class Test {
 
  public static void main(String[] args) {
    Para p = new Para(17,20);
    p.nazwa = "Para liczb";               // 1
                                          // 2
    p.a = 1;                              // 3
    System.out.println(p.makeString());   // 4

    p.show();                             // 5
 
  } 
 
}

public class Count {
  
  private int counter;
  
  public void increase() {
    counter++;
  }
  
  public void show() {
    System.out.println(counter);
  }
}

  

class Test {  
 
  public static void main(String[] args) {
    Count c = new Count();
    c.increase();
    c.increase();
    c.increase();
    c.show();
  } 
 
}

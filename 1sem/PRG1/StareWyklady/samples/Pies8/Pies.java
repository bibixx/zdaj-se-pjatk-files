class Pies {

  String s;

  void nowyPies() {
    String pies =  new String("pies g³ówny");
    s = pies; 
  }

  void jakieMamyPsy() {
    System.out.println("Jest " + s);
    String pies2 = pobierzInnegoPsa();
    System.out.println("Jest te¿ " + pies2);
  }

  String pobierzInnegoPsa() {
    return new String("inny pies");
  }

}

class Test {

  public static void main(String[] args) {
    Pies p =  new Pies();
    p.nowyPies();
    p.jakieMamyPsy();
  }
}
   


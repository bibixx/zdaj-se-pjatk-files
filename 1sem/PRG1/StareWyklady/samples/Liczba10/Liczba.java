import javax.swing.*; 

public class Liczba {

  int a;

  public Liczba(int liczba) {  // konstruktor
    a = liczba; 
  }

  public int getValue() {       // zwraca warto�� liczby
    return a;
  }

  public double pow(int n) {   // metoda pot�gowania

    if (n < 0) {        // warunek konieczny: n >=0
       System.out.println("Niedopuszczalna warto�� wyk�adnika");
       return -0.1;
    }

    double wynik = 1;
    while(n-- > 0) wynik *= a;  // p�tla pot�gowania
    return wynik;
  }

  // Do jakiej parzystej nieujemnej pot�gi 
  // nale�y podnie�� liczb�, by uzyska� targetVal
  public int evenExponentFor(int targetVal) {
    int w = 1;
    int n = 0;

    if (targetVal > 1 && a >= -1 && a <= 1) {
       System.out.println("Wadliwe dane");
       return -1;
    }
 
    while (w < targetVal) {
      n+=2;
      w *= a*a;
      System.out.println(n + " " + w);
    }
    return n;
  }


  public double pow2(int n) {  

    if (n < 0) {        // warunek konieczny: n >=0
       System.out.println("Niedopuszczalna warto�� wyk�adnika");
       return -0.1;
    }
    double wynik = 1;
    for (int i = 1; i <= n; i++) wynik *= a;
    return wynik;
  }

  public static void main(String[] args) {

    Liczba a;     // refrencja do obiekt�w - liczb

    String in1;   // napis oznaczaj�cy wprowadzon� liczb�

    JOptionPane.showMessageDialog(null, "Podnoszenie do pot�gi");

    // p�tla wprowadzania danych b�dzie dzia�a� dop�ki nie
    // zamkniemy kt�rego� z okienek  dialogowych

    while ((in1 = JOptionPane.showInputDialog("Podaj a")) != null) {    

      a = new Liczba( Integer.parseInt(in1) );  // tworzymy obiekt
                                                // napis -> int
                                                // int argumentem konstruktora 
      // pobieramy wyk�adnik
      String in2 = JOptionPane.showInputDialog("Podaj n");
   
      if (in2 == null) break;  // je�eli przerwano dialog - wyj�cie z p�tli

      int n = Integer.parseInt(in2); // napis -> int

      // uzyskanie wyniku za pomoc� wywo�anie metody pow
      double wynik = a.pow(n);
      System.out.println(a.getValue() + " do potegi " + n + " = " + wynik);
      wynik = a.pow2(n);
      System.out.println(a.getValue() + " do potegi " + n + " = " + wynik);
    }

    JOptionPane.showMessageDialog(null, "Poszukiwanie parzystego wyk�adnika");
    
    while ((in1 = JOptionPane.showInputDialog("Podaj a")) != null) {    

      a = new Liczba( Integer.parseInt(in1) );  

      // pobieramy warto�� docelow�
      String in2 = JOptionPane.showInputDialog("Podaj cel");
   
      if (in2 == null) break;  

      int cel = Integer.parseInt(in2);

      // uzyskanie wyniku za pomoc� wywo�anie metody evenExponent
      int n = a.evenExponentFor(cel);
      System.out.println("Aby uzyska� co najmniej " + cel 
                         + " liczb� " + a.getValue() 
                         + " trzeba podnie�� do parzystej pot�gi " + n);
      System.out.println(a.getValue() + " do potegi " + n + " = " + a.pow(n));
    }
 
    System.out.println("Koniec programu");
    System.exit(1);
  } 

}

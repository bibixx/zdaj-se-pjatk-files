class Para { 

    int a;    // To s� "dane" (zwane polami klasy). 
              // Okre�laj� one z jakich element�w sk�ada� si�
    int b;    // b�d� obiekty tej klasy. 
              // a = pierwszy sk�adnik pary, b - drugi


 Para(int x, int y) {   // konstruktor: nadaje warto�� parze
   a = x  ;             // na podstawie przekazanych warto�ci x i y   
   b = y;
 }
 
 Para(int x) {          // inny konstruktor: nadaje obu sk�adnikom pary
   a = b = x;                  // (a i b) t� sam� warto�� x
 } 


 void set(Para p)  {    // metoda ustalenia warto�ci pary
   a = p.a;             // na podstawie sk�adnik�w przekazanej pary 
   b = p.b;
 } 


 Para add(Para p) {             // metoda dodawania dw�ch par   
   Para wynik = new Para(a, b);
   wynik.a += p.a;
   wynik.b += p.b;
   return wynik;
 }
 
                               // metoda pokazuj�ca par� 
 void show(String s)  {                 
   System.out.println(s + " ( " + a + " , " + b + " )" );
 }

}

 
class ParaTest {
 
  public static void main(String[] args) {
    Para para1 = new Para(1,5);
    Para para2 = new Para(2,4);
    para1.show("Para 1 =");    
    para2.show("Para 2 =");
    Para sumaPar = para1.add(para2);
    sumaPar.show("Suma par =");
    para1.set(para2);
    para1.show("Teraz para 1 = ");
 
  } 
 
}

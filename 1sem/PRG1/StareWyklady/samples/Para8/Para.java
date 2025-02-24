class Para { 

    int a;    // To s¹ "dane" (zwane polami klasy). 
              // Okreœlaj¹ one z jakich elementów sk³adaæ siê
    int b;    // bêd¹ obiekty tej klasy. 
              // a = pierwszy sk³adnik pary, b - drugi


 Para(int x, int y) {   // konstruktor: nadaje wartoœæ parze
   a = x  ;             // na podstawie przekazanych wartoœci x i y   
   b = y;
 }
 
 Para(int x) {          // inny konstruktor: nadaje obu sk³adnikom pary
   a = b = x;                  // (a i b) tê sam¹ wartoœæ x
 } 


 void set(Para p)  {    // metoda ustalenia wartoœci pary
   a = p.a;             // na podstawie sk³adników przekazanej pary 
   b = p.b;
 } 


 Para add(Para p) {             // metoda dodawania dwóch par   
   Para wynik = new Para(a, b);
   wynik.a += p.a;
   wynik.b += p.b;
   return wynik;
 }
 
                               // metoda pokazuj¹ca parê 
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

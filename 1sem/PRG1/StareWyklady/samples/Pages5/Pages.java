
public class Pages {
 
  public static void main(String[] args) {

    int posNum = 17;                       // liczba pozycji do wyprowadzenia
    int posPP = 5;                         // liczba pozycji na stronie

    int pageNum = posNum/posPP;            // liczba pelnych stron
    if (posNum % posPP != 0) pageNum++;    // i ew. dodatkowa
    
    int pageNr = 1;                        // bie¿¹cy numer strony 
    int pos = 1;                           // bie¿¹cy numer pozycji

    while (pos <= posNum) {

      System.out.println(pos);      

      if (pos % posPP == 0) {              // zaczyna siê nowa strona
         pageNr++;
         System.out.println('\f' + "Strona " + pageNr + " z " + pageNum);
      }
      pos++;
    }
 
  } 
 
}

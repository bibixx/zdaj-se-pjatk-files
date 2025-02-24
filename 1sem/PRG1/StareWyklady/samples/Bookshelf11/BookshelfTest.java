public class BookshelfTest {
 
  public static void main(String[] args) {

    final int BSNUM = 5;     // liczba pólek, których mo¿na u¿yæ
    final int BSIZE = 100;   // rozmiar pó³ki

    Bookshelf[] bs = new Bookshelf[BSNUM];  // tablica pó³ek;
                                            // samych pólek jeszcze nie ma

    Book[] bk = { new Book("P. Pies", "Psy", "WydPP", 2002, "ISBN01", 25, 100),
                  new Book("K. Kot", "Koty", "WydPP", 2002, "ISBN02", 22, 90),
                  new Book("A. Koñ", "Konie", "Tur", 2001, "ISBN01", 35, 50),
                };

    Journal[] jr = { new Journal(1, "Kwiaty", "WydAN", 2002, "ISSN03", 10, 20),
                    new Journal(1, "Ryby", "W&S", 2002, "ISSN03", 20, 100),
                  };

    bs[0] = new Bookshelf(1, BSIZE);  // pierwsza pó³ka

    int k = 0;  // bie¿¹cy indeks tablicy pó³ek - 
                // daje nam tê pó³kê na któr¹ mamy wstawiaæ publikacje


    for (int i=0; i<bk.length; i++) // wstawiamy po kolei wszystkie ksi¹¿ki

       // je¿eli nie uda³o siê na pó³kê k - to bierzemy now¹ po³kê (k+1)
       while (!bs[k].put(bk[i]) && k < BSNUM ) {
         k++;
         bs[k] = new Bookshelf(k+1, BSIZE);
       }

    for (int i=0; i<jr.length; i++) // wstawiamy po kolei wszystkie czasopisma

       // je¿eli nie uda³o siê na pó³kê k - to bierzemy now¹ po³kê (k+1)
       while (!bs[k].put(jr[i]) && k < BSNUM ) {
         k++;
         bs[k] = new Bookshelf(k+1, BSIZE);
       }
    
    
    // gdzie s¹ ksi¹¿ki ?
    for (int i=0; i<bk.length; i++) {
       Bookshelf b = bk[i].whereIs();
       String s = b == null ? " nie stoi na pó³ce" 
                            : " jest na pó³ce "+ b.getNr();
       System.out.println( bk[i].getTitle() + s);
    }
     
    // gdzie s¹ czasopisma ?
    for (int i=0; i<jr.length; i++) {
       Bookshelf b = jr[i].whereIs();
       String s = b == null ? " nie stoi na pó³ce" 
                            : " jest na pó³ce "+ b.getNr();

       System.out.println(jr[i].getTitle() + s);
    }

    // co jest na pó³kach (tylko tych co zosta³y u¿yte)
    for (int i=0; i < bs.length; i++) {
      if (bs[i] == null) break;
      Publication[] p = bs[i].getPubs();
      System.out.println("Pó³ka nr " +  bs[i].getNr());
      for (int j=0; j < p.length; j++)  System.out.println(p[j].getTitle());
    } 

 
  } 
 
}

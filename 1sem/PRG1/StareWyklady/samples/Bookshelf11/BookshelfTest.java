public class BookshelfTest {
 
  public static void main(String[] args) {

    final int BSNUM = 5;     // liczba p�lek, kt�rych mo�na u�y�
    final int BSIZE = 100;   // rozmiar p�ki

    Bookshelf[] bs = new Bookshelf[BSNUM];  // tablica p�ek;
                                            // samych p�lek jeszcze nie ma

    Book[] bk = { new Book("P. Pies", "Psy", "WydPP", 2002, "ISBN01", 25, 100),
                  new Book("K. Kot", "Koty", "WydPP", 2002, "ISBN02", 22, 90),
                  new Book("A. Ko�", "Konie", "Tur", 2001, "ISBN01", 35, 50),
                };

    Journal[] jr = { new Journal(1, "Kwiaty", "WydAN", 2002, "ISSN03", 10, 20),
                    new Journal(1, "Ryby", "W&S", 2002, "ISSN03", 20, 100),
                  };

    bs[0] = new Bookshelf(1, BSIZE);  // pierwsza p�ka

    int k = 0;  // bie��cy indeks tablicy p�ek - 
                // daje nam t� p�k� na kt�r� mamy wstawia� publikacje


    for (int i=0; i<bk.length; i++) // wstawiamy po kolei wszystkie ksi��ki

       // je�eli nie uda�o si� na p�k� k - to bierzemy now� po�k� (k+1)
       while (!bs[k].put(bk[i]) && k < BSNUM ) {
         k++;
         bs[k] = new Bookshelf(k+1, BSIZE);
       }

    for (int i=0; i<jr.length; i++) // wstawiamy po kolei wszystkie czasopisma

       // je�eli nie uda�o si� na p�k� k - to bierzemy now� po�k� (k+1)
       while (!bs[k].put(jr[i]) && k < BSNUM ) {
         k++;
         bs[k] = new Bookshelf(k+1, BSIZE);
       }
    
    
    // gdzie s� ksi��ki ?
    for (int i=0; i<bk.length; i++) {
       Bookshelf b = bk[i].whereIs();
       String s = b == null ? " nie stoi na p�ce" 
                            : " jest na p�ce "+ b.getNr();
       System.out.println( bk[i].getTitle() + s);
    }
     
    // gdzie s� czasopisma ?
    for (int i=0; i<jr.length; i++) {
       Bookshelf b = jr[i].whereIs();
       String s = b == null ? " nie stoi na p�ce" 
                            : " jest na p�ce "+ b.getNr();

       System.out.println(jr[i].getTitle() + s);
    }

    // co jest na p�kach (tylko tych co zosta�y u�yte)
    for (int i=0; i < bs.length; i++) {
      if (bs[i] == null) break;
      Publication[] p = bs[i].getPubs();
      System.out.println("P�ka nr " +  bs[i].getNr());
      for (int j=0; j < p.length; j++)  System.out.println(p[j].getTitle());
    } 

 
  } 
 
}

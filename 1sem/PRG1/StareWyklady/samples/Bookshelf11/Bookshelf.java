public class Bookshelf {

  private int bsnr;             // numer p�i
  private Publication[] pubs;   // tablica publikacji
  private int freeSpace;        // wolne miejcce

  private int currIndex;        // bie��cy indeks tablicy publikacji,
                                // pod tym indeksem wpiszemy referencj�
                                // do publikacji umiesczanej w tablicy


  Bookshelf(int nr, int size) {
    pubs = new Publication[size];
    freeSpace = size;
    bsnr = nr; 
  }

  // Zwraca numer p�ki
  public int getNr() {
    return bsnr;
  }

  // Umieszcza egzemplarze przekazanej jako argument publikacji
  // na p�lce. Zwraca true, je�li to si� powiod�o - w przeciwnym razie false

  public boolean put(Publication p) {

    // je�eli ju� na p�lce - nie robimy nic
    if (p.whereIs() != null) {
      System.out.println("Publikacje ju� s� na p�ce");
      return false;
    } 

    int n = p.getQuantity();  // ile egzemplarzy danej pozycji wyd.

    if ( n > freeSpace ) {    // gdy brak wolnego miejsca na p�ce
      System.out.println("Brak miejsca na p�ce");
      return false;
    } 

    freeSpace -= n;        // zmniejszenie dost�pnego miejsca na p�ce

    // referencja do obiektu-publikacji jest wpisywana do tablicy publikacji
    // indeks przesuwamy o 1 a nie o n, gdy� nie ma sensu powiela� 
    // informacji o publikacji dla wszsytkich jej egzemplarzy
   
    pubs[currIndex++] = p;

    // w obiekcie-publikacji zapisujemy referencj� do p�lki na kt�rej
    // znalaz�y si� egzemplarze tej publikacji
 
    p.setBookshelf(this);
    return true;
  }

  // zwraca tablic� publikacji na p�ce
  // tablica ta ma currIndex element�w
  // gdy� nie powielamy informacji dla > 1 egzemplarzy
  // tej samej publikacji, wobec czego pozosta�e elementy
  // tablicy publikacji pubs s� null i nie uwzgl�dniamy ich 

  public Publication[] getPubs() { 
    Publication[] p = new Publication[currIndex];
    for (int i=0; i < currIndex; i++) p[i] = pubs[i];
    return p;
  }
}
 
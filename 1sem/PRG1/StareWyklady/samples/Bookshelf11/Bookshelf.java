public class Bookshelf {

  private int bsnr;             // numer pó³i
  private Publication[] pubs;   // tablica publikacji
  private int freeSpace;        // wolne miejcce

  private int currIndex;        // bie¿¹cy indeks tablicy publikacji,
                                // pod tym indeksem wpiszemy referencjê
                                // do publikacji umiesczanej w tablicy


  Bookshelf(int nr, int size) {
    pubs = new Publication[size];
    freeSpace = size;
    bsnr = nr; 
  }

  // Zwraca numer pó³ki
  public int getNr() {
    return bsnr;
  }

  // Umieszcza egzemplarze przekazanej jako argument publikacji
  // na pólce. Zwraca true, jeœli to siê powiod³o - w przeciwnym razie false

  public boolean put(Publication p) {

    // je¿eli ju¿ na pólce - nie robimy nic
    if (p.whereIs() != null) {
      System.out.println("Publikacje ju¿ s¹ na pó³ce");
      return false;
    } 

    int n = p.getQuantity();  // ile egzemplarzy danej pozycji wyd.

    if ( n > freeSpace ) {    // gdy brak wolnego miejsca na pó³ce
      System.out.println("Brak miejsca na pó³ce");
      return false;
    } 

    freeSpace -= n;        // zmniejszenie dostêpnego miejsca na pó³ce

    // referencja do obiektu-publikacji jest wpisywana do tablicy publikacji
    // indeks przesuwamy o 1 a nie o n, gdy¿ nie ma sensu powielaæ 
    // informacji o publikacji dla wszsytkich jej egzemplarzy
   
    pubs[currIndex++] = p;

    // w obiekcie-publikacji zapisujemy referencjê do pólki na której
    // znalaz³y siê egzemplarze tej publikacji
 
    p.setBookshelf(this);
    return true;
  }

  // zwraca tablicê publikacji na pó³ce
  // tablica ta ma currIndex elementów
  // gdy¿ nie powielamy informacji dla > 1 egzemplarzy
  // tej samej publikacji, wobec czego pozosta³e elementy
  // tablicy publikacji pubs s¹ null i nie uwzglêdniamy ich 

  public Publication[] getPubs() { 
    Publication[] p = new Publication[currIndex];
    for (int i=0; i < currIndex; i++) p[i] = pubs[i];
    return p;
  }
}
 
public class CompPrice {

  public static void main(String[] args) {

    int cProc = 700;   // cena procesora
    int cPly  = 500;   // ... p³yty
    int cPam = 300;    // innych sk³adnikow ...
    int cDysk = 400;
    int cInn = 500;

    final double VAT = 1.22;  // narzut podatku VAT

    // Liczymy cenê komputera bez monitora
    double cKomp = (cProc + cPly + cPam + cDysk + cInn) * VAT;

    System.out.println("Cena komputera bez monitora wynosi :");
    System.out.println(cKomp);

        
    int cMon = 1100;             // cena monitora netto
    cKomp = cKomp + cMon * VAT;  // i nowa cena komoutera z monitorem

    System.out.println("Cena komputera z monitorem wynosi :");
    System.out.println(cKomp);

 }
}

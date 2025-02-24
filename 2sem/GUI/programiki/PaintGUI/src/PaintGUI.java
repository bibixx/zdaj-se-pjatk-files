import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

public class PaintGUI extends Applet implements ActionListener, AdjustmentListener, MouseListener, MouseMotionListener
{

 private final int  OLOWEK_STALA        = 1;
 private final int  PROSTA_STALA        = 2;
 private final int  GUMKA_STALA         = 3;
 private final int  WYCZYSC_STALA       = 4;
 private final int  PROSTOKAT_STALA     = 5;
 private final int  KOLO_STALA          = 6;
 private final int  PROSTAKAT2_STALA    = 7;
 private final int  KOLO2_STALA       	= 8;
 private final int  KSZTALT_STALA		= 10;

 // Obecna pozycja myszki 
 private int mousex = 0;
 private int mousey = 0;

 // Poprzednia pozcja myszki
 private int mousepx = 0;
 private int mousepy = 0;


 private boolean inicjalizacjaOlowek   = true;
 private boolean inicjalizacjaProsta   = true;
 private boolean inicjalizacjaGumka    = true;
 private boolean inicjalizacjaRect     = true;
 private boolean inicjalizacjaKolo     = true;
 private boolean inicjalizacjaFRect    = true;
 private boolean inicjalizacjaFKolo    = true;
 private boolean inicjalizacjaPolygon  = true;

 /* G£OWNE ZMIENNE MYSZKI DLA x I y */
 private int  Orx                    = 0;
 private int  Ory                    = 0;
 private int  OrSzerokosc            = 0;
 private int  OrWysokosc             = 0;
 private int  rysujX                 = 0;
 private int  rysujY                 = 0;
 private int  wielkoscGumki          = 5;
 private int  CzerwonyWartoscUsera   = 255;
 private int  ZielonyWartoscUsera    = 255;
 private int  NiebieskiWartoscUsera  = 255;

 /* DOMYSLNE WARTOSCI */
 private int    DomyslneNarzedzie  = OLOWEK_STALA;
 private int    domyslnyKolor      = 1;
 private Color  glownyKolor        = new Color(0,0,0);
 private Color  xorKolor           = new Color(255,255,255);
 private Color  KolorStatusBara    = new Color(166,166,255);
 private Color  KolorUzytkownika   = new Color(CzerwonyWartoscUsera,ZielonyWartoscUsera,NiebieskiWartoscUsera);

 /* DEFINICJE PRZYCISKOW */
 private Button OlowekP       = new Button("Olowek");
 private Button LiniaP        = new Button("Prosta");
 private Button gumkaP        = new Button("Gumka");
 private Button wyczyscP      = new Button("Wyczysc");
 private Button prostokatP    = new Button("Prostokat");
 private Button koloP         = new Button("Kolo");
 private Button prostokat2P   = new Button("Tlo Prostokat");
 private Button kolo2P        = new Button("Tlo Kolo");
 private Button polygonP      = new Button("Polygon");


 private Button czarnyP      		= new Button("Czarny");
 private Button niebieskiP          = new Button("Niebieski");
 private Button czerwonyP           = new Button("Czerwony");
 private Button zielonyP        	= new Button("Zielony");
 private Button fioletP       		= new Button("Fiolet");
 private Button pomaranczP          = new Button("Pomarancz");
 private Button rozowyP         	= new Button("Rozowy");
 private Button szaryP        	    = new Button("Szary");
 private Button zoltyP       	    = new Button("Zolty");
 private Button koloruzytkownikaP   = new Button("Uzytkownika");


 private Scrollbar suwakCzerwony    = new Scrollbar(Scrollbar.HORIZONTAL, 0, 1, 0, 255);
 private Scrollbar suwakNiebieski   = new Scrollbar(Scrollbar.HORIZONTAL, 0, 1, 0, 255);
 private Scrollbar suwakZielony     = new Scrollbar(Scrollbar.HORIZONTAL, 0, 1, 0, 255);

 /* wielkosci pol tekstowych */
 private TextField poleKoloru  		= new TextField(20);
 private TextField poleNarzedzia    = new TextField(20);
 private TextField poleMyszki  		= new TextField(10);
 private TextField czerwonyWartosc  = new TextField(3);
 private TextField zielonyWartosc   = new TextField(3);
 private TextField niebieskiWartosc = new TextField(3);

 /* Nazwy pol w gornym pasku */
 private Label etykietaNarzedzie      = new Label("   Narzêdzie:");
 private Label etykietaKolor          = new Label("   Kolor:");
 private Label etykietaPozycjaMyszki  = new Label("   Pozycja:");

 /* podpanele w glownym aplecie */
 private Panel panelIkonek        			= new Panel(new GridLayout(22,1,0,0));
 private Panel obszarRoboczy       			= new Panel();
 private Panel gornyPasek          			= new Panel();
 private Panel pasekKolorowUsera   			= new Panel(new GridLayout(3,0));
 private Panel panelWybranegoKoloruUsera	= new Panel();


 public void init()
 {
	 
    setLayout(new BorderLayout());

    /* przypisanie przyciskow do panelu */
    panelIkonek.add(czarnyP);
    panelIkonek.add(niebieskiP);
    panelIkonek.add(czerwonyP);
    panelIkonek.add(zielonyP);
    panelIkonek.add(fioletP);
    panelIkonek.add(pomaranczP);
    panelIkonek.add(rozowyP);
    panelIkonek.add(szaryP);
    panelIkonek.add(zoltyP);
    panelIkonek.add(koloruzytkownikaP);
    
    /* kolory t³a przycisków */
    niebieskiP.setBackground(Color.blue);
    czerwonyP.setBackground(Color.red);
    zielonyP.setBackground(Color.green);
    fioletP.setBackground(Color.magenta);
    pomaranczP.setBackground(Color.orange);
    rozowyP.setBackground(Color.pink);
    szaryP.setBackground(Color.gray);
    zoltyP.setBackground(Color.yellow);
    koloruzytkownikaP.setBackground(KolorUzytkownika);

    /* przypisanie narzedzi do panelu */	
    panelIkonek.add(OlowekP);
    panelIkonek.add(LiniaP);
    panelIkonek.add(gumkaP);
    panelIkonek.add(wyczyscP);
    panelIkonek.add(prostokatP);
    panelIkonek.add(koloP);
    panelIkonek.add(prostokat2P);
    panelIkonek.add(kolo2P);
    panelIkonek.add(polygonP);

    panelIkonek.setBounds(0,0,100,300);
    panelIkonek.add(pasekKolorowUsera);
    panelIkonek.add(panelWybranegoKoloruUsera);

    /* przypisanie suwakow do panelu */
    pasekKolorowUsera.add(czerwonyWartosc);
    pasekKolorowUsera.add(suwakCzerwony);

    pasekKolorowUsera.add(zielonyWartosc);
    pasekKolorowUsera.add(suwakZielony);

    pasekKolorowUsera.add(niebieskiWartosc);
    pasekKolorowUsera.add(suwakNiebieski);


    /* GORNY PASEK */
    gornyPasek.add(etykietaKolor);
    gornyPasek.add(poleKoloru);
    gornyPasek.add(etykietaNarzedzie);
    gornyPasek.add(poleNarzedzia);
    gornyPasek.add(etykietaPozycjaMyszki);
    gornyPasek.add(poleMyszki);

    /* Zabezpieczamy przed edycja */
    poleKoloru.setEditable(false);
    poleNarzedzia.setEditable(false);
    poleMyszki.setEditable(false);

    gornyPasek.setBackground(KolorStatusBara);
    panelIkonek.setBackground(Color.white);
    obszarRoboczy.setBackground(Color.white);
    add(gornyPasek, "North");
    add(panelIkonek, "West");
    add(obszarRoboczy, "Center");

    /* action listener */
    OlowekP.addActionListener(this);
    LiniaP.addActionListener(this);
    gumkaP.addActionListener(this);
    wyczyscP.addActionListener(this);
    prostokatP.addActionListener(this);
    koloP.addActionListener(this);
    prostokat2P.addActionListener(this);
    kolo2P.addActionListener(this);
    polygonP.addActionListener(this);

    czarnyP.addActionListener(this);
    niebieskiP.addActionListener(this);
    czerwonyP.addActionListener(this);
    zielonyP.addActionListener(this);
    fioletP.addActionListener(this);
    pomaranczP.addActionListener(this);
    rozowyP.addActionListener(this);
    szaryP.addActionListener(this);
    zoltyP.addActionListener(this);
    koloruzytkownikaP.addActionListener(this);

    suwakCzerwony.addAdjustmentListener(this);
    suwakNiebieski.addAdjustmentListener(this);
    suwakZielony.addAdjustmentListener(this);

    /* dodanie komponentow do glownego panelu */
    obszarRoboczy.addMouseMotionListener(this);
    obszarRoboczy.addMouseListener(this);
    this.addMouseListener(this);
    this.addMouseMotionListener(this);

    odswierzWartosciRGB();

    poleNarzedzia.setText("Olowek");
    poleKoloru.setText("Czarny");
 }


 // dodanie akcji
 public void actionPerformed(ActionEvent e)
 {
    // i przypisanie wartosci

    if (e.getActionCommand() == "Olowek")
       DomyslneNarzedzie = OLOWEK_STALA;

    if (e.getActionCommand() == "Prosta")
       DomyslneNarzedzie = PROSTA_STALA;

    if (e.getActionCommand() == "Gumka")
       DomyslneNarzedzie = GUMKA_STALA;

    if (e.getActionCommand() == "Wyczysc")
       DomyslneNarzedzie = WYCZYSC_STALA;

    if (e.getActionCommand() == "Prostokat")
       DomyslneNarzedzie = PROSTOKAT_STALA;

    if (e.getActionCommand() == "Kolo")
       DomyslneNarzedzie = KOLO_STALA;

    if (e.getActionCommand() == "Tlo Prostokat")
       DomyslneNarzedzie = PROSTAKAT2_STALA;

    if (e.getActionCommand() == "Tlo Kolo")
       DomyslneNarzedzie = KOLO2_STALA;

    if (e.getActionCommand() == "Polygon")
       DomyslneNarzedzie = KSZTALT_STALA;


    if (e.getActionCommand() == "Czarny")
       domyslnyKolor = 1;

    if (e.getActionCommand() == "Niebieski")
       domyslnyKolor = 2;

    if (e.getActionCommand() == "Zielony")
       domyslnyKolor = 3;

    if (e.getActionCommand() == "Czerwony")
       domyslnyKolor = 4;

    if (e.getActionCommand() == "Fiolet")
       domyslnyKolor = 5;

    if (e.getActionCommand() == "Pomarancz")
       domyslnyKolor = 6;

    if (e.getActionCommand() == "Rozowy")
       domyslnyKolor = 7;

    if (e.getActionCommand() == "Szary")
       domyslnyKolor = 8;

    if (e.getActionCommand() == "Zolty")
       domyslnyKolor = 9;

    if (e.getActionCommand() == "Uzytkownika")
       domyslnyKolor = 10;

    inicjalizacjaPolygon = true;

   // Aktualizacja stanow na gornym pasku narzedzi 
    switch (DomyslneNarzedzie)
    {
       case OLOWEK_STALA   : poleNarzedzia.setText("O³ówek");
                       break;

       case PROSTA_STALA  : poleNarzedzia.setText("Linia");
                       break;

       case GUMKA_STALA: poleNarzedzia.setText("Gumka");
                       break;

       case WYCZYSC_STALA : czyscObszarRoboczy(obszarRoboczy);
                       break;

       case PROSTOKAT_STALA  : poleNarzedzia.setText("Prostok¹t");
                       break;

       case KOLO_STALA  : poleNarzedzia.setText("Ko³o");
                       break;

       case PROSTAKAT2_STALA : poleNarzedzia.setText("WYP Prostok¹t");
                       break;

       case KOLO2_STALA : poleNarzedzia.setText("WYP Ko³o");
                       break;

       case KSZTALT_STALA : poleNarzedzia.setText("Kszta³t");
                       break;

    }

    switch (domyslnyKolor)
    {
       case  1: poleKoloru.setText("Czarny");
                break;

       case  2:  poleKoloru.setText("Niebieski");
                 break;

       case  3:  poleKoloru.setText("Zielony");
                 break;

       case  4:  poleKoloru.setText("Czerwony");
                 break;

       case  5:  poleKoloru.setText("Fiolet");
                 break;

       case  6:  poleKoloru.setText("Pomarañcz");
                 break;

       case  7:  poleKoloru.setText("Ró¿owy");
                 break;

       case  8:  poleKoloru.setText("Szary");
                 break;

       case  9: poleKoloru.setText("¯ó³ty");
                break;

       case 10: poleKoloru.setText("Wybór u¿ytkownika");
                break;
    }

    //Ustawianie g³ównego koloru
    ustawKolorGlowny();
    odswierzWartosciRGB();
 }

 // odswierzanie
 public void adjustmentValueChanged(AdjustmentEvent e)
 {
    odswierzWartosciRGB();
 }


 //czyszczenie ekranu roboczego
 public void czyscObszarRoboczy(Panel p)
 {
    poleNarzedzia.setText("Wyczysc");
    Graphics g = p.getGraphics();
    g.setColor(p.getBackground());
    g.fillRect(0,0,p.getBounds().width,p.getBounds().height);
  }


 /*
Metoda bêdzie naœladowaæ styl graficzny pióra.
    rysuj¹c liniê od poprzednich wspolrzednych myszy
    do aktualnych wspó³rzêdnych myszy.
 */
 public void rysujOlowkiem(MouseEvent e)
 {
    Graphics g  = obszarRoboczy.getGraphics();
    g.setColor(glownyKolor);

    /*
      Inicjalizacja wspolrzednych myszy do pozycji na ekranie
    */
    if (inicjalizacjaOlowek)
    {
       ustawieniaDomyslne(e);
       inicjalizacjaOlowek = false;
       g.drawLine(mousepx,mousepy,mousex,mousey);
    }

    /*
   Sprawdzamy czy nie jest na poprzedniej pozycji
    */
    if (czyMyszkaSieRuszyla(e))
    {
       /*
        ustawienie myszy na aktualna pozycje
       */
       mousex = e.getX();
       mousey = e.getY();

       /*
          rysujemy linie z poprzedniego pkt do aktualnego
       */
       g.drawLine(mousepx,mousepy,mousex,mousey);

       /*
          ustawia stan myszy z aktualnego na poprzedni dla nastepnego razu
       */
       mousepx = mousex;
       mousepy = mousey;
    }
 }


 /*
	Metoda bêdzie emulowaæ linie.
    Rysuje linie z jednego pkt do drugiego, dopuki nie bedzie zwolniony Drag na myszce
 */
 public void rysujLinie(MouseEvent e)
 {
    Graphics g  = obszarRoboczy.getGraphics();
    g.setColor(glownyKolor);

    /*
      ustawienie myszy na aktualna pozycje
    */
    if (inicjalizacjaProsta)
    {
       ustawieniaDomyslne(e);
       g.setXORMode(xorKolor);
       g.drawLine(Orx,Ory,mousex,mousey);
       inicjalizacjaProsta=false;
    }

    /*
      sprawdzamy czy myszka sie ruszy³a
    */
    if (czyMyszkaSieRuszyla(e))
    {
       // Kasuje wyswietlanie lini z poprzedniej pozycji poprzez XOR      
       g.setXORMode(xorKolor);
       g.drawLine(Orx,Ory,mousex,mousey);

       // Aktualizacja myszki
       mousex = e.getX();
       mousey = e.getY();

       // rysujemy linie
       g.drawLine(Orx,Ory,mousex,mousey);
    }
 }

 /*
	Metoda bêdzie emulowaæ prostokat.
    Rysuje linie z jednego pkt do drugiego, dopuki nie bedzie zwolniony Drag na myszce
 */
 public void rysujProstokat(MouseEvent e)
 {
    Graphics g  = obszarRoboczy.getGraphics();
    g.setColor(glownyKolor);


    if (inicjalizacjaRect)
    {
       ustawieniaDomyslne(e);
       inicjalizacjaRect = false;
    }

   
    if (czyMyszkaSieRuszyla(e))
    {
 
       g.setXORMode(obszarRoboczy.getBackground());
       g.drawRect(rysujX,rysujY,OrSzerokosc,OrWysokosc);

     
       mousex = e.getX();
       mousey = e.getY();

       // sprawdzamy czy nic sie nie wychaczylo
       ustawGranice();

       // rysujemy prostokat
       g.drawRect(rysujX,rysujY,OrSzerokosc,OrWysokosc);

    }

 }


// rysujemy kolo
 public void rysujKolo(MouseEvent e)
 {
    Graphics g  = obszarRoboczy.getGraphics();
    g.setColor(glownyKolor);

    if (inicjalizacjaKolo)
    {
       ustawieniaDomyslne(e);
       inicjalizacjaKolo=false;
    }

  
    if (czyMyszkaSieRuszyla(e))
    {
     
       g.setXORMode(xorKolor);
       g.drawOval(rysujX,rysujY,OrSzerokosc,OrWysokosc);

       mousex = e.getX();
       mousey = e.getY();

       ustawGranice();

       g.drawOval(rysujX,rysujY,OrSzerokosc,OrWysokosc);
    }
 }


// wypelniony prostokat
 public void rysujProstokat2(MouseEvent e)
 {

    Graphics g  = obszarRoboczy.getGraphics();
    g.setColor(glownyKolor);

    
    if (inicjalizacjaFRect)
    {
       ustawieniaDomyslne(e);
       inicjalizacjaFRect=false;
    }

    if (czyMyszkaSieRuszyla(e))
    {
      
       g.setXORMode(xorKolor);
       g.drawRect(rysujX,rysujY,OrSzerokosc-1,OrWysokosc-1);

       mousex = e.getX();
       mousey = e.getY();

       ustawGranice();

       g.drawRect(rysujX,rysujY,OrSzerokosc-1,OrWysokosc-1);

    }

 }


// wypelnione kolo
 public void rysujKolo2(MouseEvent e)
 {
    Graphics g  = obszarRoboczy.getGraphics();
    g.setColor(glownyKolor);

    if (inicjalizacjaFKolo)
    {
       ustawieniaDomyslne(e);
       inicjalizacjaFKolo = false;
    }

   
    if (czyMyszkaSieRuszyla(e))
    {
       
       g.setXORMode(xorKolor);
       g.drawOval(rysujX,rysujY,OrSzerokosc,OrWysokosc);

       mousex = e.getX();
       mousey = e.getY();

       ustawGranice();

     
       g.drawOval(rysujX,rysujY,OrSzerokosc,OrWysokosc);
    }

 }

// gumka - prostokat w kolorze bialy. wycentrowany
 public void rysujGumke(MouseEvent e)
 {
    Graphics g  = obszarRoboczy.getGraphics();

    if (inicjalizacjaGumka)
    {
       ustawieniaDomyslne(e);
       inicjalizacjaGumka = false;
       g.setColor(glownyKolor.white);
       g.fillRect(mousex-wielkoscGumki, mousey-wielkoscGumki,wielkoscGumki*2,wielkoscGumki*2);
       g.setColor(Color.black);
       g.drawRect(mousex-wielkoscGumki,mousey-wielkoscGumki,wielkoscGumki*2,wielkoscGumki*2);
       mousepx = mousex;
       mousepy = mousey;
    }


    if (czyMyszkaSieRuszyla(e))
    {
       g.setColor(glownyKolor.white);
       g.drawRect(mousepx-wielkoscGumki, mousepy-wielkoscGumki,wielkoscGumki*2,wielkoscGumki*2);


       mousex = e.getX();
       mousey = e.getY();

       //rysuje kursor myszki
       g.setColor(glownyKolor.white);
       g.fillRect(mousex-wielkoscGumki, mousey-wielkoscGumki,wielkoscGumki*2,wielkoscGumki*2);
       g.setColor(Color.black);
       g.drawRect(mousex-wielkoscGumki,mousey-wielkoscGumki,wielkoscGumki*2,wielkoscGumki*2);
       mousepx = mousex;
       mousepy = mousey;
    }
 }

//rysuemy polygon
 public void rysujPolygon(MouseEvent e)
 {
    if (inicjalizacjaPolygon)
    {
       mousepx = e.getX();
       mousepy = e.getY();
       inicjalizacjaPolygon = false;
    }
    else
    {
       mousex = e.getX();
       mousey = e.getY();
       Graphics g = obszarRoboczy.getGraphics();
       g.setColor(glownyKolor);
       g.drawLine(mousepx,mousepy,mousex,mousey);
       mousepx = mousex;
       mousepy = mousey;
    }
 }

 /*
 Metoda okreœla czy mysz zosta³a przeniesionaod ostatniej pozycji.
    Jeœli myszy odesz³a od poprzedniej pozycji,zwrócona wartoœæ bêdzie prawdziwa, 
	w przeciwnym razie bedzie false
 */
 public boolean czyMyszkaSieRuszyla(MouseEvent e)
 {
    return (mousex != e.getX() || mousey != e.getY());
 }


 /*
Metoda jest kluczowym ogniwem w dzia³aniach, w których jest wiêcej ni¿ 2 zmienne odchylenia do
    obszaru graficznego pracy. Ta metoda oblicza nowe wartoœci globlnych zmiennych rysujX i rysujY wed³ug
    nowych pozycji kursora myszy.   
 */
 public void ustawGranice()
 {

    //Je¿eli którykolwiek z bie¿¹cego po³o¿enia myszy s¹ mniejsze ni¿ wspó³rzêdne x wyst¹pi³ z prawej i / lub y- wyst¹pi³ od do³u do góry.

       if (mousex < Orx || mousey < Ory)
       {
          /* Jezeli wspol myszy x jest mniejsz niz oryginalne wspol x, niech to wyrowna
          */
          if (mousex < Orx)
          {
             OrSzerokosc = Orx - mousex;
             rysujX = Orx - OrSzerokosc;
          }
          else
          {
             rysujX = Orx;
             OrSzerokosc = mousex - Orx;

          }
          // to samo dla y
          if (mousey < Ory)
          {
             OrWysokosc = Ory - mousey;
             rysujY = Ory - OrWysokosc;
          }
          else
          {
             rysujY = Ory;
             OrWysokosc = mousey - Ory;
          }
       }
       // po przeciagnieciu z lewej do prawej niech bedzie tez na gorze i na dole
       else
       {
          rysujX    	= Orx;
          rysujY    	= Ory;
          OrSzerokosc  	= mousex - Orx;
          OrWysokosc 	= mousey - Ory;
       }
 }

 // ustawienia domyslne
 public void ustawieniaDomyslne(MouseEvent e)
 {
    mousex   	= e.getX();
    mousey   	= e.getY();
    mousepx    	= e.getX();
    mousepy    	= e.getY();
    Orx      	= e.getX();
    Ory      	= e.getY();
    rysujX    	= e.getX();
    rysujY    	= e.getY();
    OrSzerokosc = 0;
    OrWysokosc 	= 0;
 }


  // przeciaganie
  
 public void mouseDragged(MouseEvent e)
 {
    aktWspolMyszy(e);

    switch (DomyslneNarzedzie)
    {
       case OLOWEK_STALA : rysujOlowkiem(e);
       break;

       case PROSTA_STALA : rysujLinie(e);
       break;

       case PROSTOKAT_STALA : rysujProstokat(e);
       break;

       case KOLO_STALA : rysujKolo(e);
       break;

       case PROSTAKAT2_STALA : rysujProstokat2(e);
       break;

       case KOLO2_STALA : rysujKolo2(e);
       break;

       case GUMKA_STALA : rysujGumke(e);
       break;
    }
 }



//Metoda zostanie aktywowany gdy mysz zosta³a zwolnienie z przycisku . Na tym etapie metoda wywo³a procedury finalizacji dla bie¿¹cej operacji.
 public void mouseReleased(MouseEvent e)
 {
    // Aktualizacja wspolrzednych myszy
    aktWspolMyszy(e);

    switch (DomyslneNarzedzie)
    {
       case OLOWEK_STALA : inicjalizacjaOlowka();
       break;

       case PROSTA_STALA : inicjalizacjaProstej();
       break;

       case PROSTOKAT_STALA : inicjalizacjaProstokatu();
       break;

       case KOLO_STALA : inicjalizacjaKola();
       break;

       case PROSTAKAT2_STALA : inicjalizacjaProstokatu2();
       break;

       case KOLO2_STALA : inicjalizacjaKola2();
       break;

       case GUMKA_STALA : inicjalizacjaGumki();
        break;
    }
 }


// aktualizacja wspolrzednych myszki i pozycji na ekranie
 public void mouseEntered(MouseEvent e)
 {
    aktWspolMyszy(e);
 }


// ustawianie glownego koloru
 public void ustawKolorGlowny()
 {
    switch (domyslnyKolor)
    {
       case 1 : glownyKolor = Color.black;
                break;

       case 2:  glownyKolor = Color.blue;
                break;

       case 3:  glownyKolor = Color.green;
                break;

       case 4:  glownyKolor = Color.red;
                break;

       case 5:  glownyKolor = Color.magenta;
                break;

       case 6:  glownyKolor = Color.orange;
                break;

       case 7:  glownyKolor = Color.pink;
                break;

       case 8:  glownyKolor = Color.gray;
                break;

       case 9:  glownyKolor = Color.yellow;
                break;

       case 10: glownyKolor = KolorUzytkownika;
                break;
    }
 }


// inicjalizacja narzedzi
 public void inicjalizacjaOlowka()
 {
    inicjalizacjaOlowek = true;
 }

 public void inicjalizacjaProstej()
 {
    if ((Math.abs(Orx - mousex) + Math.abs(Ory - mousey)) != 0)
    {
       System.out.println("Prosta zwolniona...uff..");
       inicjalizacjaProsta = true;
       Graphics g  = obszarRoboczy.getGraphics();
       g.setColor(glownyKolor);
       g.drawLine(Orx,Ory,mousex,mousey);
    }
 }


 public void inicjalizacjaGumki()
 {
    inicjalizacjaGumka = true;
    Graphics g  = obszarRoboczy.getGraphics();
    g.setColor(glownyKolor.white);
    g.drawRect(mousex-wielkoscGumki,mousey-wielkoscGumki,wielkoscGumki*2,wielkoscGumki*2);
 }


 public void inicjalizacjaProstokatu()
 {
    inicjalizacjaRect = true;
    Graphics g  = obszarRoboczy.getGraphics();
    g.setColor(glownyKolor);
    g.drawRect(rysujX,rysujY,OrSzerokosc,OrWysokosc);
 }


 public void inicjalizacjaKola()
 {
    inicjalizacjaKolo = true;
    Graphics g  = obszarRoboczy.getGraphics();
    g.setColor(glownyKolor);
    g.drawOval(rysujX,rysujY,OrSzerokosc,OrWysokosc);
 }


 public void inicjalizacjaProstokatu2()
 {
    inicjalizacjaFRect = true;
    Graphics g  = obszarRoboczy.getGraphics();
    g.setColor(glownyKolor);
    g.fillRect(rysujX,rysujY,OrSzerokosc,OrWysokosc);
 }


 public void inicjalizacjaKola2()
 {
    inicjalizacjaFKolo = true;
    Graphics g  = obszarRoboczy.getGraphics();
    g.setColor(glownyKolor);
    g.fillOval(rysujX - 1,rysujY - 1,OrSzerokosc + 2,OrWysokosc + 2);
 }


 // aktualizacja wspol myszy na gornym basku
 public void aktWspolMyszy(MouseEvent e)
 {
    String WspX ="";
    String WspY ="";

    if (e.getX() < 0) WspX = "0";
    else
    {
       WspX = String.valueOf(e.getX());
    }

    if (e.getY() < 0) WspX = "0";
    else
    {
       WspY = String.valueOf(e.getY());
    }
    poleMyszki.setText("x:" + WspX + "   y:" + WspY);
 }


// aktualizacja ekranu z kolorem wybranym przez usera
 public void odswierzWartosciRGB()
 {
    CzerwonyWartoscUsera = suwakCzerwony.getValue();
    ZielonyWartoscUsera = suwakZielony.getValue();
    NiebieskiWartoscUsera = suwakNiebieski.getValue();
    if (CzerwonyWartoscUsera > 255)
       CzerwonyWartoscUsera = 255;

    if (CzerwonyWartoscUsera < 0 )
       CzerwonyWartoscUsera =0;

    if (ZielonyWartoscUsera > 255)
       ZielonyWartoscUsera = 255;

    if (ZielonyWartoscUsera < 0 )
       ZielonyWartoscUsera =0;

    if (NiebieskiWartoscUsera > 255)
       NiebieskiWartoscUsera = 255;

    if (NiebieskiWartoscUsera < 0 )
       NiebieskiWartoscUsera =0;

    czerwonyWartosc.setText(String.valueOf(CzerwonyWartoscUsera));
    zielonyWartosc.setText(String.valueOf(ZielonyWartoscUsera));
    niebieskiWartosc.setText(String.valueOf(NiebieskiWartoscUsera));

    KolorUzytkownika = new Color(CzerwonyWartoscUsera,ZielonyWartoscUsera,NiebieskiWartoscUsera);
    koloruzytkownikaP.setBackground(KolorUzytkownika);

    Graphics g = panelWybranegoKoloruUsera.getGraphics();
    g.setColor(KolorUzytkownika);
    g.fillRect(0,0,800,800);
 }


// aktualizacja wspolrzednych myszy po kliknieciu
 public void mouseClicked(MouseEvent e)
 {
    aktWspolMyszy(e);
    switch (DomyslneNarzedzie)
    {

       case 10 : rysujPolygon(e);
                 break;
    }
}
 //aktualizacja jak myszka wyjdzie z apletu
 public void mouseExited(MouseEvent e)
 {
    aktWspolMyszy(e);
 }
//aktualizacja przy ruchu myszy
 public void mouseMoved(MouseEvent e)
 {
    aktWspolMyszy(e);
 }
// aktualizacja kiedy przycisk myszy jest wcisniety
 public void mousePressed(MouseEvent e)
 {
    aktWspolMyszy(e);
 }

}


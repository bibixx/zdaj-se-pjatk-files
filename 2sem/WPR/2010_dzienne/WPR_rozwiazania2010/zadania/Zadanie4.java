
public class Zadanie4 {

	public static void main(String[] args) {
		
		     Figura fig[] = new Figura[2];
		    
		     fig[0] = new Kolo(200, 200, 50);
		          // polimorficzne wywo�anie metod z klasy Kolo, a nie z klasy Figura 
		     fig[0].pokaz();                 
		     fig[0].pozycja(210, 200);     
		    
		     fig[1] = new Prostokat(200, 200, 50, 50);
		          // polimorficzne wywo�anie metod z klasy Prostokat, a nie z klasy Figura
		     fig[1].pokaz();
		     fig[1].pozycja(210, 300);
	}
}


abstract class Figura  {
	protected int x, y;                                 	// okre�laj� po�o�enie figury
    protected String fig = "Jeszcze nie wiadomo";       	// okre�la nazw� figury z warto�ci� pocz�tkow� 
    public Figura(int x, int y) {this.x = x; this.y = y;}   // konstruuje figur� na podstawie podanego po�o�enia 
    public void pokaz() {System.out.println(this.fig);}     // wypisuje nazw� figury i po�o�enie 
    public abstract void pozycja(int x, int y); 			// wypisuje komunikat, czy punkt (x, y) le�y wewn�trz figury
}

class Kolo extends Figura {
	private int promien; 
	public Kolo(int x, int y, int r) {super(x, y); this.promien=r; this.fig = "Kolo";}             	// konstruuje ko�o o �rodku w punkcie (x, y) i  promieniu r 
	public void pozycja(int x, int y) 
	{
		if(promien<Math.sqrt(this.x*this.x-x*x + this.y*this.y*this.y*y)==true) 
		System.out.println("Punkt (" + this.x + "," + y + ") lezy wewnatrz kola");
		else System.out.println("Punkt (" + this.x + "," + y + ") lezy na zewnatrz kola");
	}
	public void pokaz() {super.pokaz(); System.out.println("Srodek - (" + x+ ", "+ y+")" + "\nPromien - " + promien);}        						// wypisuje dodatkowo promie� ko�a

}

class Prostokat extends Figura{
	private int szer, wys;                          	// szeroko�� i wysoko�� prostok�ta 
	public Prostokat(int x, int y, int s, int w) {
		super(x, y);
		this.szer=s;
		this.wys=w;
		this.fig = "Prostokat";
		} 	// konstruuje prostok�t (o bokach r�wnoleg�ych do osi uk�adu wsp�rz�dnych)
			// z podanym po�o�eniem lewego g�rnego wierzcho�ka (x, y), szeroko�ci� s, wysoko�ci� w 
	public void pozycja(int x, int y) 
	{
		if(this.x-x<0 && this.x-szer<x && this.y+wys>y && this.y-y>0) 
			System.out.println("Punkt (" + this.x + "," + y + ") lezy wewnatrz prostokata");
		else System.out.println("Punkt (" + this.x + "," + y + ") lezy na zewnatrz prostokata");
	}
	public void pokaz() {
		fig = "\nProstokat"; 
		super.pokaz();
		System.out.println("Lewy gorny: (" + x + ", " + y + ")" );
		System.out.println("Szerokosc: " + szer + ", Wysokosc: " + wys);
		}    // wypisuje dodatkowo parametry szer, wys
}

/*
Wynik dzia�ania programu powinien wygl�da� nast�puj�co:

Kolo
Srodek - (200, 200)
Promien - 50
Punkt (200, 200) lezy wewnatrz kola

Prostokat
Lewy gorny - (200, 200)
Szerokosc: 50, Wysokosc: 50
Punkt (210, 300) lezy na zewnatrz prostokata*/
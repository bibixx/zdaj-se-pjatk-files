
public class Zadanie4 {

	public static void main(String[] args) {
		
		     Figura fig[] = new Figura[2];
		    
		     fig[0] = new Kolo(200, 200, 50);
		          // polimorficzne wywo³anie metod z klasy Kolo, a nie z klasy Figura 
		     fig[0].pokaz();                 
		     fig[0].pozycja(210, 200);     
		    
		     fig[1] = new Prostokat(200, 200, 50, 50);
		          // polimorficzne wywo³anie metod z klasy Prostokat, a nie z klasy Figura
		     fig[1].pokaz();
		     fig[1].pozycja(210, 300);
	}
}


abstract class Figura  {
	protected int x, y;                                 	// okreœlaj¹ po³o¿enie figury
    protected String fig = "Jeszcze nie wiadomo";       	// okreœla nazwê figury z wartoœci¹ pocz¹tkow¹ 
    public Figura(int x, int y) {this.x = x; this.y = y;}   // konstruuje figurê na podstawie podanego po³o¿enia 
    public void pokaz() {System.out.println(this.fig);}     // wypisuje nazwê figury i po³o¿enie 
    public abstract void pozycja(int x, int y); 			// wypisuje komunikat, czy punkt (x, y) le¿y wewn¹trz figury
}

class Kolo extends Figura {
	private int promien; 
	public Kolo(int x, int y, int r) {super(x, y); this.promien=r; this.fig = "Kolo";}             	// konstruuje ko³o o œrodku w punkcie (x, y) i  promieniu r 
	public void pozycja(int x, int y) 
	{
		if(promien<Math.sqrt(this.x*this.x-x*x + this.y*this.y*this.y*y)==true) 
		System.out.println("Punkt (" + this.x + "," + y + ") lezy wewnatrz kola");
		else System.out.println("Punkt (" + this.x + "," + y + ") lezy na zewnatrz kola");
	}
	public void pokaz() {super.pokaz(); System.out.println("Srodek - (" + x+ ", "+ y+")" + "\nPromien - " + promien);}        						// wypisuje dodatkowo promieñ ko³a

}

class Prostokat extends Figura{
	private int szer, wys;                          	// szerokoœæ i wysokoœæ prostok¹ta 
	public Prostokat(int x, int y, int s, int w) {
		super(x, y);
		this.szer=s;
		this.wys=w;
		this.fig = "Prostokat";
		} 	// konstruuje prostok¹t (o bokach równoleg³ych do osi uk³adu wspó³rzêdnych)
			// z podanym po³o¿eniem lewego górnego wierzcho³ka (x, y), szerokoœci¹ s, wysokoœci¹ w 
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
Wynik dzia³ania programu powinien wygl¹daæ nastêpuj¹co:

Kolo
Srodek - (200, 200)
Promien - 50
Punkt (200, 200) lezy wewnatrz kola

Prostokat
Lewy gorny - (200, 200)
Szerokosc: 50, Wysokosc: 50
Punkt (210, 300) lezy na zewnatrz prostokata*/
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
	public double pole()  {	return szer*wys;	}
	public double obwod() {	return (szer+wys)*2;}
	
	
	public String toString(){
		return "Prostokat - Lewy gorny: (" + x + ", " + y + ") Szerokosc: " + szer + ", Wysokosc: " + wys;
	}
}

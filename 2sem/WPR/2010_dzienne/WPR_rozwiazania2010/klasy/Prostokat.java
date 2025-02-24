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
	public double pole()  {	return szer*wys;	}
	public double obwod() {	return (szer+wys)*2;}
	
	
	public String toString(){
		return "Prostokat - Lewy gorny: (" + x + ", " + y + ") Szerokosc: " + szer + ", Wysokosc: " + wys;
	}
}

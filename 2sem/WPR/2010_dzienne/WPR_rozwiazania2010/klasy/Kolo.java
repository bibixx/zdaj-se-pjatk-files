class Kolo extends Figura {
	private int promien; 
	public Kolo(int x, int y, int r) {super(x, y); this.promien=r; this.fig = "Kolo";}   // konstruuje ko³o o œrodku w punkcie (x, y) i  promieniu r 
	public void pozycja(int x, int y) 
	{
		if(promien<=Math.sqrt(this.x*this.x-x*x + this.y*this.y*this.y*y)==true) 
		System.out.println("Punkt (" + this.x + "," + y + ") lezy wewnatrz kola");
		else System.out.println("Punkt (" + this.x + "," + y + ") lezy na zewnatrz kola");
	}
	public double pole()   {return promien*promien*3.14;  }
	public double obwod()  {return promien*3.14*2;}
	
	public void pokaz() {super.pokaz(); System.out.println("Srodek - (" + x+ ", "+ y+")" + "\nPromien - " + promien);}       // wypisuje dodatkowo promieñ ko³a
	
	public String toString(){
		return "Kolo - srodek (" + x + "," + y + "), " + "promien "  + promien;
		}

}
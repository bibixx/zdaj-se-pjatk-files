package old;
public class Zadanie1 {

	public static void main(String[] args) 
	{
		Odcinek odcinek1 = new Odcinek(1,1,3,3);
		Odcinek od = new Odcinek (1,1,1,1);
		System.out.println(odcinek1.dlugosc());
		System.out.println(odcinek1.odleglosc(1,1));
		System.out.println(od.dluzszy(odcinek1));
		System.out.println(odcinek1.rzutX());
		System.out.println(odcinek1.rzutY());
		System.out.println(odcinek1.toString());
	}
	
}
	class Odcinek
	{
		static int ile;
		public int id = 1;
		private int x1,y1,x2,y2;
				
		public Odcinek(int x1, int y1, int x2, int y2) 
		{
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
			ile++;
			id=ile;
		}
		public double dlugosc()
		{
			return Math.sqrt( (this.x2-this.x1)*(this.x2-this.x1) + (this.y2-this.y1)*(this.y2-this.y1) );
			
		}

		public double odleglosc(int x, int y) 
		{
			double srX = (( x1 + x2)/2); // this.x1
			double srY = (( y1 + y2)/2); // this.y1
			return Math.sqrt( (x-srX)*(x-srX) + (y-srY)*(y-srY) );

			
		}
		public boolean dluzszy(Odcinek od) 
		{
			if (od.dlugosc() > this.dlugosc()) return true;
			else return false;
		}
		public Odcinek rzutX()
		{
			return new Odcinek(x1,0,x2,0);
		}
		public Odcinek rzutY()
		{
			return new Odcinek(0,y1,0,y2);
		}
		public String toString() 
		{
			return ("x1:" + this.x1 + " y1:" + this.y1 +" x2:" + this.x2 +" y2:" + this.y2 );
		}
		
	}
/*
Zadanie 1 (3p)

Zaprojektowa� i testowa� klas� Odcinek (odcinki na p�aszczy�nie) zawieraj�c� m. in. 
nast�puj�ce metody:

double dlugosc(): oblicza d�ugo�� naszego odcinka,
double odleglosc(int x, int y): oblicza odleg�o�� od �rodka naszego odcinka do punktu (x,y),
boolean dluzszy(Odcinek od): sprawdza, czy nasz odcinek jest d�u�szy ni� odcinek od,
Odcinek rzutX(), Odcinek rzutY(): zwracaj� rzuty naszego odcinka na os. X, Y,
public String toString(): wyprowadza informacje o odcinku (przedefiniowanie metody 
toString() z klasy Object).

Ka�dy odcinek powinien mie� sw�j unikalny numer (zaczynaj�c od 1), nadany 
automatycznie przy tworzeniu.
*/
	
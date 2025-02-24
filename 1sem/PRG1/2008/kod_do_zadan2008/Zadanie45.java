public class Zadanie45 {
	
		public static void main(String[] args) 
		{
			Wektor3W Wektor1 = new Wektor3W(1,2,3);    
			Wektor3W Wektor2 = new Wektor3W(1,2,3);

			System.out.println("Czy Wektor1 jest dluzszy od Wektor2: " + Wektor1.dluzszy(Wektor2)); // dluzszy - true, krotszy/rowny - false.
			Wektor1.wyswietl();
			System.out.println("Czy sa rowne: " +Wektor1.rowne(Wektor2));
			Wektor1.set(6,6,6);
			Wektor1.dodaj(1,2,5);
			Wektor1.odejmij(0, 0, 8);
			Wektor1.mnoz(2);
			System.out.print("Iloczyn skalarny: "); Wektor1.skalarny(Wektor2);
			Wektor3W wektory[] = new Wektor3W [10];
			for (int i=0; i<wektory.length; i++) 
			{
				wektory[i] = new Wektor3W((int)(Math.random()*30),(int)(Math.random()*50),(int)(Math.random()*70));
			}
			Wektor3W najdluzszy = wektory[0];    
			Wektor3W najkrotszy = wektory[0];    
			
			for (int i=0; i<wektory.length; i++)
			{
				if (wektory[i].dluzszy(najdluzszy)==true) {najdluzszy = wektory[i];}
				if (wektory[i].dluzszy(najkrotszy)==false) {najkrotszy = wektory[i];}
			}
			System.out.print("Najdluzszy: " ); najdluzszy.wyswietl();
			System.out.print("Najkrotszy: " ); najkrotszy.wyswietl();
		}
	}
		
		class Wektor3W 
		{
			int x,y,z;
			public Wektor3W(int a, int b, int c)	{x = a;	y = b; z =c;}
			public void wyswietl() {System.out.println("x:" + x +" y :" + y + " z:" + z);} 	
			public void set(int a, int b, int c) {x=a; y=b; z=c;}
			public void dodaj(int a, int b, int c) {x=x+a; y=y+b; z=z+c;}
			public void odejmij(int a, int b, int c) {x=x-a; y=y-b; z=z-c;}
			public void mnoz(int a) {x=x*a; y=y*a; z=z*a;}
			public boolean rowne(Wektor3W WektorX) 
			{
				boolean p=true;
				if (WektorX.x != x || WektorX.y != y || WektorX.z != z) {return false;}
				return p;
			}
			public void skalarny(Wektor3W WektorX) {System.out.println(WektorX.x*x+WektorX.y*y+WektorX.z*z);}
			public boolean dluzszy(Wektor3W WektorX) {return (Math.sqrt(x * x + y * y + z * z) > Math.sqrt(WektorX.x * WektorX.x + WektorX.y * WektorX.y + WektorX.z * WektorX.z));}
		} 
/*Zadanie 45 (4p)

Zaprojektowaæ i testowaæ klasê Wektor3W reprezentuj¹c¹ wektory trójwymiarowe i podstawowe operacje na nich: 
+ wyœwielanie sk³adowych wektora, 
+ ustawienie wartoœci sk³adowych, 
+ dodawanie dwóch wektorów, 
+ odejmowanie dwóch wektorów, 
+ mno¿enie wektora przez liczbê, 
+ sprawdzanie czy dwa wektory s¹ równe, 
+ sprawdzanie czy dany wektor jest d³u¿szy ni¿ drugi. 
+ iloczyn skalarny 2 wektorów, 

Ka¿dy wektor powinien mieæ swój unikalny numer (np. zaczynaj¹c od 1).

Dodatkowo, stworzyæ tablicê 10 wektorów klasy Wektor3W, zainicjowaæ ich wspó³rzêdne losowymi 
liczbami oraz podaæ najd³u¿szy i najkrótszy wektor.*/
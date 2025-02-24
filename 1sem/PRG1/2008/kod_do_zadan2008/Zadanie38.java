
public class Zadanie38 {

	public static void main(String[] args) 
	{
		int n=5;
		System.out.println(silna(n));
	}
	
	public static long silna(int n) 
	{
		int silna=1;
		for (int x=1; x<=n; x++) {silna = silna * x;}
		return silna;
	}
	
}

/*
Zadanie 38 (2p) 

Napisaæ i testowaæ (w g³ównym programie) w³asn¹ metodê: 
long silna(int n), która oblicza silniê n! podanej liczby n, 
wprowadzonej przez u¿ytkownika.
*/
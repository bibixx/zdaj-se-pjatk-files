
public class Zadanie40 {

	public static void main(String[] args) 
	{
		System.out.println(pierwsze(29));
	}
	
	public static boolean pierwsze(int n)
	{
		boolean wynik=true;
		if (n==0 || n==1){wynik=false;}
		else {
			int x=n-1;
			while (x!=1) 
			{
				if (n%x==0) {wynik=false;}
				x--;
			}
		}
		return wynik;
	}
}

/*Zadanie 40 (3p) 

Napisaæ i testowaæ (w g³ównym programie) w³asn¹ metodê boolean 
pierwsze(int n), która zwraca true jeœli n jest liczb¹ pierwsz¹, 
false w przeciwnym przypadku.*/
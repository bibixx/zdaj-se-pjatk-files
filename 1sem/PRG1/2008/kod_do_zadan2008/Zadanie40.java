
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

Napisa� i testowa� (w g��wnym programie) w�asn� metod� boolean 
pierwsze(int n), kt�ra zwraca true je�li n jest liczb� pierwsz�, 
false w przeciwnym przypadku.*/
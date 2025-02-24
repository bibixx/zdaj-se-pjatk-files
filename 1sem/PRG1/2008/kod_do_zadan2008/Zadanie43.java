
public class Zadanie43 {

	public static void main(String[] args) {
		//int[] tab = {1,2,3,4,5,6,7,8,9,10};
		int[] tab = {1,2,3,11,5,13,7,17,9,10};
		System.out.print("Parzyste: "); parzyste(tab); System.out.println("");
		System.out.print("Nieparzyste: "); nieParzyste(tab); System.out.println("");
		System.out.print("Suma nieparzystych: " + sumaNieparzystych(tab));
	}
	public static void parzyste(int[] tab) 
	{
		int i=0;
		while (i!=10) 
		{
			if (tab[i]%2==0) {System.out.print(tab[i]+" ");}
			i++;
		}
	}
	
	public static void nieParzyste(int[] tab) 
	{
		int i=0;
		while (i!=10) 
		{
			if (tab[i]%2==1) {System.out.print(tab[i]+" ");}
			i++;
		}
	}
	
	public static int sumaNieparzystych(int[] tab) 
	{
		int i=0,j=0;
		while (i!=10) 
		{
			if (tab[i]%2==1) j++;
		i++;
		}
		return j;
	}
	
}
/*Zadanie 43 (3p)
W g³ównym programie zainicjowaæ tablicê 10 elementow¹ liczbami ca³kowitymi. Napisaæ oraz testowaæ nastêpuj¹ce metody:

 *     void parzyste(int[] tab), która zwraca liczby parzyste z podanej tablicy tab, 
 *     void nieParzyste(int[] tab), która zwraca liczby nieparzyste z podanej tablicy tab, 
 *     int sumaNieparzystych(int[] tab), która zwraca sumê liczb o indeksach nieparzystych z tablicy tab.*/
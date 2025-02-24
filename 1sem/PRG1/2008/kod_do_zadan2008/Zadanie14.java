package CW_17_10_08;

public class Zadanie14 {
	public static void main(String[] args) 
	{   	
/*Napisaæ program, który pobiera 3-cyfrow¹ 
 liczbê naturaln¹ i wyprowadza cyfry tej liczby 
 w odwrotnej kolejnoœci (np. 123->321). 
 Dane wejœciowe podaæ w inicjacji odpowiedniej z
 miennej (typu int) w programie.*/		
		int n=123;
		int a=(n%10);
		int b=(n-a)/10%10;
		int c=n/100;
		System.out.println(""+a+""+b+""+c);
   	} 
}

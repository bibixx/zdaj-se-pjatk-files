package CW_17_10_08;

public class Zadanie14 {
	public static void main(String[] args) 
	{   	
/*Napisa� program, kt�ry pobiera 3-cyfrow� 
 liczb� naturaln� i wyprowadza cyfry tej liczby 
 w odwrotnej kolejno�ci (np. 123->321). 
 Dane wej�ciowe poda� w inicjacji odpowiedniej z
 miennej (typu int) w programie.*/		
		int n=123;
		int a=(n%10);
		int b=(n-a)/10%10;
		int c=n/100;
		System.out.println(""+a+""+b+""+c);
   	} 
}

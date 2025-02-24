import java.util.StringTokenizer;


public class Zadanie52 {

	public static void main(String[] args) 
	{
		System.out.println(digits("12345")); // true - same cyfry
	}
	public static boolean digits(String s) 
	{
		StringTokenizer b;
		b = new StringTokenizer(s, "0123456789");
		int c=b.countTokens();
		return (c==0);
	}
	
}

/*Zadanie 52 (2p) 

Napisać i przetestować w programie metodę 
boolean digits(String s) sprawdzającą, czy w danym 
łańcuchu tekstowym s występują wyłącznie cyfry. 

*/
public class Zadanie53 {

	public static void main(String[] args) 
	{
		System.out.println(letters("litery"));
	}
	
	public static boolean letters(String s) 
	{
		int a = s.length();
		int dl=0;
		for (int b=0; b!=a; b++) if ((s.charAt(b)<='z' && s.charAt(b)>='a') 
				|| (s.charAt(b)<='Z' && s.charAt(b)>='A')) dl++;
		return (dl==s.length());
	}
}

/*Zadanie 53 (2p) 

Napisać i przetestować w programie metodę boolean letters(String s) 
sprawdzającą, czy w danym łańcuchu tekstowym s występują wyłącznie litery. 
*/

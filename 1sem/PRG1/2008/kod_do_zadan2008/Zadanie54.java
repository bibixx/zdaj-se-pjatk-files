
public class Zadanie54 {

	public static void main(String[] args) 
	{
		System.out.println(reverse("abcde"));
	}
	
	public static String reverse(String s) 
	{
		int dl = s.length();
		String s1="";
		
		while (dl!=0) 
		{
			dl--;
			s1=s1+s.charAt(dl);
		}
		
		return s1;
	}
	
	
}

/*Zadanie 54 (2p) 

Napisać i testować w programie metodę String reverse(String s), 
która zwraca łańcuch odwrócony łańcucha s. 

*/
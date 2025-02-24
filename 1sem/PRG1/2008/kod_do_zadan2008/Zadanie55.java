
public class Zadanie55 {

	public static void main(String[] args)
	{
		System.out.println(palindrome("kajak kajak"));
		
	} 
	
	
	public static boolean palindrome(String s)
	{
		int dl = s.length();
		String s1="";
		
		while (dl!=0) 
		{
			dl--;
			s1=s1+s.charAt(dl);
		}
		
		return (s1.equals(s));
	}
	
}


/*Zadanie 55 (2p) 

Napisaæ i testowaæ w programie metodê boolean palindrome(String s) s
prawdzaj¹c¹, czy dany ³añcuch s jest palindromem.


*/
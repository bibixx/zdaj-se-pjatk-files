
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

Napisa� i testowa� w programie metod� boolean palindrome(String s) s
prawdzaj�c�, czy dany �a�cuch s jest palindromem.


*/
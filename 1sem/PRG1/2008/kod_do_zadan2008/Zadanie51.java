public class Zadanie51 {

	public static void main(String[] args) {

		System.out.println(ocurr("aabaac",'a'));
		
		
	}
	
	public static int ocurr(String s ,char c) 
	{
		int powt=0, b=0;
		String temp1 = Character.toString(c);

		while (powt!=-1)
		{
			powt = s.indexOf(temp1);
			s=s.substring(powt+temp1.length());
			b++;
		}
		b=b-1;
		return b;
	}
}

/*Zadanie 51 (2p) 

Napisać i przetestować w programie metodę int 
ocurr(String s ,char c) obliczającą ile razy w 
łańcuchu s występuje znak c. Wypisać na konsolę 
łańcuch s po usunięciu wszystkich wystąpień znaku c. 
*/
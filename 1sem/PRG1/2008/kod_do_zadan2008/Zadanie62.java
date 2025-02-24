
public class Zadanie62 {

	public static void main(String[] args) 
	{
	
		System.out.println(binStringToInt("101"));

	}
	
	public static String binStringToInt(String b)
	{
		if (b.charAt(0)=='1' && b.length()==32)	b = "Nie licze liczb ujemnych!";
		
		
		else
		{
			int x=0;
			double c=0;
			
			for (int i=0; i<b.length(); i++) 
				{
					if (b.charAt(x)=='1') c = c + Math.pow(2, b.length()-x);
					x++;
				}
			b= Double.toString(c/2);
		}
		return b;
	}

}

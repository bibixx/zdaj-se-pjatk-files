public class zadanie2 {


    public static void main(String[] args)
    {	
    	System.out.print("³¹cznikiem jest + :");
        System.out.println(lacz("+", "2", "2")); 
        System.out.print("³¹cznikiem jest spacja :");
        System.out.println(lacz(" ", "ala", "ma", "kota"));
        System.out.print("³¹cznikiem jest 1 :");
        System.out.println(lacz("1", "jeden", "dwa", "trzy", "cztery", "piec"));
        System.out.print("brak :");
        System.out.println(lacz("aa"));
    }
public static String lacz( String ... dane)
{
    if(dane.length > 1)
    {
    String suma = "";
    String lacznik = dane[0];
    for ( int i = 1; i < dane.length ; i++ )
    {
        suma+=dane[i];
        if(i < dane.length-1) suma+=lacznik;
       
    }
    return suma;
    }
    else
    return null;
   
}
} 
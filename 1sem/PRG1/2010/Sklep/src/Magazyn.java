import java.io.*;
import java.util.*;

public class Magazyn {

	public static void main(String[]args){
		
		odpalamy();
	}
	
	public static void odpalamy()
	{
		try{
	
		opcja();
		}
		catch( Exception e){
			System.out.println( "Huston mamy Problem, " + e + "." ); // Komunikat b³êdu
		}		
	}
	// -------------------CZYTANIE ZNAKÓW OD USERA-----------------------------	
	public static String basicReadStringFromUser() throws Exception
	{
	java.io.InputStreamReader inputReader = new java.io.InputStreamReader( System.in );
	java.io.BufferedReader buffInputReader = new java.io.BufferedReader( inputReader );
	return buffInputReader.readLine();
	
	}

	public static String readStringFromUser() throws Exception
	{
	String stringFromUser = "";
		while( stringFromUser.length() == 0 )
		{
			
			stringFromUser = basicReadStringFromUser();
			
			
		}
	return stringFromUser;
	}


	public static int readIntFromUser()  throws Exception
	{
	    String s = readStringFromUser();
	    return Integer.parseInt( s);
	}
// ------------------ WYBOR OPCJI -------------------------------------	
	public static void opcja() throws Exception
	{
		while(true) // while po to ¿eby mo¿na by³o wykonywaæ dzia³anie po dzia³aniu. dopiero przy opcji 1 program koñczy dzia³anie
        { 
			System.out.println("___________________________________");
			System.out.println("|                                 |");
			System.out.println("|  WYBIERZ DZIA£ANIE:             |");
			System.out.println("|  1. Koniec programu             |");
			System.out.println("|  2. Pokaz magazyn               |");
			System.out.println("|                                 |");
			System.out.println("-----------------------------------");
			System.out.println();
			System.out.print("Podaj numer opcji: ");
			Integer opcja = readIntFromUser();
			System.out.println("Wybra³eœ opcjê nr: " + opcja);
			if(opcja==1) // jezeli dzia³anie nr 7, koniec programu
			{ 
				System.out.println("___________________________________"); 
				System.out.println("|                                 |");
				System.out.println("|        KONIEC PROGRAMU          |");
				System.out.println("|                                 |");
				System.out.println("-----------------------------------");
				break;
	        }
			else if(opcja >1 && opcja < 7) 
				wybieramyOpcje(opcja); // jezeli jest liczba z zakresu 1-6 to w³¹czamy klase wyboru opcji
	        else 
	        	System.out.println("wprowadz numer z zakresu 1-7"); 
	        }
			
		}
		public static void wybieramyOpcje(int opcja) throws Exception // kiedy wybierzemy opcje switch wykona odpowiedni¹ metode
		{
			switch(opcja)
			{			
			case 2:
				pokazMagazyn();
				break;			
			}	
		}

		//---------------------- MAGAZYN ---------------------
		public static void pokazMagazyn()  throws Exception
		{
		try {
	        BufferedReader in = new BufferedReader(new FileReader("magazyn.txt"));
	        String str;
	        while ((str = in.readLine()) != null) {
	     
	            StringTokenizer st = new StringTokenizer(str,";");
	            String id = st.nextToken();
	            String nazwa = st.nextToken();
	            String waga = st.nextToken();
	            String wysokosc= st.nextToken();
	            String szerokosc= st.nextToken();
	            String dlugosc= st.nextToken();
	            String cena= st.nextToken();
	            String stanMagazynowy = st.nextToken();
	            String stanSprzedazowy = st.nextToken();
	            System.out.println("ID: "+id+", Nazwa: "+nazwa+ ", Waga: "+waga+ ", W/S/D: " +wysokosc+"/"+szerokosc+"/"+dlugosc+", Cena: "+cena+", Stan na mag: " +stanMagazynowy+", Dostêpnoœæ: " +stanSprzedazowy );
	        	}
	        in.close();
	    } 
		catch (IOException e) {}

	} 
        
}
		



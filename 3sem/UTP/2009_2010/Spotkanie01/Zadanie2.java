package Spotkanie01;
import java.text.Collator;
import java.util.Locale;
import java.util.Arrays;

public class Zadanie2 {

	public static void main(String[] args) {
		String tablica[] = {"bela", "Ala", "�", "�", "�", "ala" , "Be", "Ala",
	              "alabama", "be", "Be", "1", "�", "my", "My", "Myk", "myk"};
		
		Arrays.sort(tablica, Collator.getInstance());
		for(int i=0; i<tablica.length; i++) System.out.println("PL: " + tablica[i]);

		Arrays.sort(tablica, Collator.getInstance(new Locale("en")));
		for(int i=0; i<tablica.length; i++) System.out.println("ENG: " + tablica[i]);
		
	}

}

/*Zadanie 2
Stworz tablic� tekstow� sk�adaj�c� si� z wyraz�w oraz samych liter. Tablic� t� posortuj wed�ug
domy�lnego porz�dku (zgodnego z lokalizacj� Twojego komputera) i wypisz j� na ekran. Nastepnie
rozwi� sw�j program tak, aby oprocz tej lokalizacji wypisywa� posortowan� tablice dla jeszcze
jednej, innej lokalizacji.
Od czego zacz�c ?
Zajrzyj w API do java.text.Collator, java.util.Locale oraz java.util.Arrays :)
Zadanie 3
*/
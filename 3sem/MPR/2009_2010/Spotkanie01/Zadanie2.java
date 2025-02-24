package Spotkanie01;
import java.text.Collator;
import java.util.Locale;
import java.util.Arrays;

public class Zadanie2 {

	public static void main(String[] args) {
		String tablica[] = {"bela", "Ala", "¹", "¥", "¹", "ala" , "Be", "Ala",
	              "alabama", "be", "Be", "1", "æ", "my", "My", "Myk", "myk"};
		
		Arrays.sort(tablica, Collator.getInstance());
		for(int i=0; i<tablica.length; i++) System.out.println("PL: " + tablica[i]);

		Arrays.sort(tablica, Collator.getInstance(new Locale("en")));
		for(int i=0; i<tablica.length; i++) System.out.println("ENG: " + tablica[i]);
		
	}

}

/*Zadanie 2
Stworz tablicê tekstow¹ sk³adaj¹c¹ siê z wyrazów oraz samych liter. Tablicê t¹ posortuj wed³ug
domyœlnego porz¹dku (zgodnego z lokalizacj¹ Twojego komputera) i wypisz j¹ na ekran. Nastepnie
rozwiñ swój program tak, aby oprocz tej lokalizacji wypisywa³ posortowan¹ tablice dla jeszcze
jednej, innej lokalizacji.
Od czego zacz¹c ?
Zajrzyj w API do java.text.Collator, java.util.Locale oraz java.util.Arrays :)
Zadanie 3
*/
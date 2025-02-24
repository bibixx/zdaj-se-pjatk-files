package Spotkanie02;
import java.util.*;

public class Zadanie2 {
	public static void main(String[] args) {
		Locale defLoc = Locale.getDefault();
		String nazwaKraju, flagaKraju;
		
		ResourceBundle info = ResourceBundle.getBundle("CountryInfo_en", defLoc);
		nazwaKraju = info.getString("name");
		flagaKraju = info.getString("flag");
		System.out.println(nazwaKraju + " " + flagaKraju);
		
		info = ResourceBundle.getBundle("CountryInfo_pl", defLoc);
		nazwaKraju = info.getString("name");
		flagaKraju = info.getString("flag");
		System.out.println(nazwaKraju + " " + flagaKraju);
		
	}
}

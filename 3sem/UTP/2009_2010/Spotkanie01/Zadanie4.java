package Spotkanie01;
import java.text.DateFormatSymbols;
import java.util.*;

public class Zadanie4 {

	  public static void main(String[] args) {
		 
		  DateFormatSymbols dfs = new DateFormatSymbols(new Locale("eng"));
		  String[] miesiace = dfs.getMonths();
		  for (int i=-0; i<miesiace.length-1; i++) System.out.print(miesiace[i]+", "); 
		  		  
	  }
}


/*Zadanie 4
Wykorzystuj¹c klasê java.util.DateFormatSymbols dla kilku znanych ró¿nych krajów wypisz pe³ne
nazwy miesiêcy w ich jêzyku.
*/
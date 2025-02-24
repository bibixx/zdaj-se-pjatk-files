package Spotkanie01;

import java.text.DecimalFormat;

public class Zadanie1 {

	public static void main(String[] args) {
	
		double liczba1 = 5.987654321;	
		
		DecimalFormat dform1 = new DecimalFormat(".#");
		System.out.println(dform1.format(liczba1));
		
		DecimalFormat dform2 = new DecimalFormat(".#%");
		System.out.println(dform2.format(liczba1));
		
		DecimalFormat dform3 = new DecimalFormat(".#¤¤");
		System.out.println(dform3.format(liczba1));
		
		DecimalFormat dform4 = new DecimalFormat(".#¤");
		System.out.println(dform4.format(liczba1));
	}
}

/*Zadanie 1
Zainicjuj liczbê double posiadaj¹c¹ kilka znaków po przecinku. U¿ywaj¹c klasy
java.text.DecimalFormat (dziedzicz¹cej z java.text.NumberFormat) wypisz t¹ liczbê posiadaj¹c¹ :
– tylko jedno miejsce po przecinku
– znak % na koñcu
– na koñcu narodowy i miêdzynarodowy symbol walut
*/
package Spotkanie01;

import java.text.DecimalFormat;

public class Zadanie1 {

	public static void main(String[] args) {
	
		double liczba1 = 5.987654321;	
		
		DecimalFormat dform1 = new DecimalFormat(".#");
		System.out.println(dform1.format(liczba1));
		
		DecimalFormat dform2 = new DecimalFormat(".#%");
		System.out.println(dform2.format(liczba1));
		
		DecimalFormat dform3 = new DecimalFormat(".#��");
		System.out.println(dform3.format(liczba1));
		
		DecimalFormat dform4 = new DecimalFormat(".#�");
		System.out.println(dform4.format(liczba1));
	}
}

/*Zadanie 1
Zainicjuj liczb� double posiadaj�c� kilka znak�w po przecinku. U�ywaj�c klasy
java.text.DecimalFormat (dziedzicz�cej z java.text.NumberFormat) wypisz t� liczb� posiadaj�c� :
� tylko jedno miejsce po przecinku
� znak % na ko�cu
� na ko�cu narodowy i mi�dzynarodowy symbol walut
*/
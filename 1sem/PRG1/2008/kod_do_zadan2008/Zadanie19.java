package CW_20_10_08;

import javax.swing.JOptionPane;
public class Zadanie19 {

	
	public static void main(String[] args) {

		// Wczytywanie danych.
		String dane1 = JOptionPane.showInputDialog("Podaj argument A1 (int) ");
		int a1x = Integer.parseInt(dane1);
		String dane2 = JOptionPane.showInputDialog("Podaj wartosc funkcji A1: (int) ");
		int a1y = Integer.parseInt(dane2);
		
		String dane3 = JOptionPane.showInputDialog("Podaj argument B1 (int) ");
		int a2x = Integer.parseInt(dane3);
		String dane4 = JOptionPane.showInputDialog("Podaj wartosc funkcji B1: (int) ");
		int a2y = Integer.parseInt(dane4);
		
		String dane5 = JOptionPane.showInputDialog("Podaj argument A2 (int) ");
		int b1x = Integer.parseInt(dane5);
		String dane6 = JOptionPane.showInputDialog("Podaj wartosc funkcji A2: (int) ");
		int b1y = Integer.parseInt(dane6);
		
		String dane7 = JOptionPane.showInputDialog("Podaj argument B2 (int) ");
		int b2x = Integer.parseInt(dane7);
		String dane8 = JOptionPane.showInputDialog("Podaj wartosc funkcji B2: (int) ");
		int b2y = Integer.parseInt(dane8);
		
		JOptionPane.showMessageDialog(null, "Podales punkty: A1("+a1x+","+a1y+") B1("+b1x+","+b1y+") A2("+a2x+","+a2y+") B2("+b2x+","+b2y+")"	);
		
		 
		// Obliczanie wartosci bezwzglednej (dodatnia liczba).
		
		int width = Math.abs(a2x-a1x); 
		int high = Math.abs(b1y-b2y);
		

		// Obliczanie punktow srodkowych (aby sprawdzic czy kwadraty na siebie nachodza.)
		double xA = a2x-((a2x-a1x)/2.);
		double yA=a2y-((a2y-a1y)/2.);
		double xB=b1x-((b1x-b2x)/2.);
		double yB=b1y-((b1y-b2y)/2.);
		double szer_srod = Math.abs( xA - xB );
		double wys_srod = Math.abs( yA - yB );
			if( szer_srod>=width/2.+high/2. || wys_srod>= width/2.+high/2.) JOptionPane.showMessageDialog(null, "Kwadraty sie nie przecinaja.");
			else { 
				// Ustawianie w kolejnosci liczb do ustalenia szerokosci wspolnej czesci.
				int kw1,kw2,kw3,kw4,temp;
				kw1=a2x;
				kw2=a1x;
				kw3=b1x;
				kw4=b2x;
				if( kw1>kw2 ) {
					temp=kw1;
					kw1=kw2;
					kw2=temp;}
				if( kw2>kw3 ) {
					temp=kw2;
					kw2=kw3;
					kw3=temp;	}
				if( kw3>kw4 ) {
					temp=kw3;
					kw3=kw4;
					kw4=temp;	}
				if( kw1>kw2 ) {
					temp=kw1;
					kw1=kw2;
					kw2=temp;	}
				if( kw2>kw3 ) {
					temp=kw2;
					kw2=kw3;
					kw3=temp;	}
				if( kw1>kw2 ) {
					temp=kw1;
					kw1=kw2;
					kw2=temp;	}
				int szerokosc=Math.abs(kw2-kw3);
				// Ustawianie w kolejnosci liczb do ustalenia wspolnej wysokosci.
				kw1=a2y;
				kw2=a1y;
				kw3=b1y;
				kw4=b2y;
				// Ustawianie w kolejnosci liczb do ustalenia wspolnej wysokosci.
				if( kw1>kw2 ){
					temp=kw1;
					kw1=kw2;
					kw2=temp;}
				if( kw2>kw3 ){
					temp=kw2;
					kw2=kw3;
					kw3=temp;}
				if( kw3>kw4 ){
					temp=kw3;
					kw3=kw4;
					kw4=temp;}
				if( kw1>kw2 ){
					temp=kw1;
					kw1=kw2;
					kw2=temp;}
				if( kw2>kw3 ){
					temp=kw2;
					kw2=kw3;
					kw3=temp;}
				if( kw1>kw2 ){
					temp=kw1;
					kw1=kw2;
					kw2=temp;}
				int wysokosc=Math.abs(kw2-kw3);
		// Pole wspolnego prostokata (szerokosc*wysokosc)
		JOptionPane.showMessageDialog(null, "Pole czesci wspolnej prostokatow: "+(szerokosc*wysokosc));
		}
	}
}

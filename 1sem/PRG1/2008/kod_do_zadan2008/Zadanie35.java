import javax.swing.JOptionPane;

public class Zadanie35 {
	
	public static void main(String[] args) {
	
		int suma1=0, suma2=0, nowa=0, i=0;
		for (int x=1; x<=10; x++) 
		{
			String zm1 = JOptionPane.showInputDialog("Podaj "+x+" liczbe: ");
			int liczba = (Integer.parseInt(zm1));
			if (liczba>2) {suma1=suma1+liczba;}
			
			if (i==0){
				suma2=suma2+liczba;
				if (liczba==0) 
				{
					nowa = suma2;
					i=1;
				}
			}
	}
		if (nowa==0) {nowa=suma2;}
		JOptionPane.showInputDialog("Suma to: "+suma1);
		JOptionPane.showInputDialog("Do pierwszej 10 lub do ostatniej liczby: "+nowa);}
}
/*
Zadanie 5 (2p) 

Opracowaæ algorytm, który oblicza sumê liczb wiêkszych od 2 spoœród 
10 liczb wprowadzonych przez u¿ytkownika. Przedstawiæ go w postaci schematu 
blokowego oraz w pseudo-kodzie.


Zadanie 7 (3p) 

Opracowaæ algorytm, który wczytuje po kolei 10 liczb oraz oblicza sumê od pierwszej 
liczby do pierwszego zera (lub do ostatniej liczby). Przedstawiæ go w postaci schematu 
blokowego oraz w pseudo-kodzie. 
*/

import javax.swing.JOptionPane;

public class Zadanie48 {

	public static void main(String[] args) {
	
		String i;
		i = JOptionPane.showInputDialog("Slowo i: ");
		int dl;
		dl = i.length();
		
		System.out.println("D�ugo�c �ancucha: " + dl);
		System.out.println("Pierwszy znak: " + i.charAt(0) + "\nOstatni znak: " + i.charAt(dl-1));
		
		int x=3;
		System.out.print("Od 3 do konca: "); 
		while (x!=dl) {System.out.print(i.charAt(x));	x++; }
		
		x=3;
		System.out.print("\nOd 3 do przedostatniego: "); 
		while (x!=(dl-1)) {System.out.print(i.charAt(x));	x++; }
		
		
	}
}

/*Zadanie 48 (2p) 

Napisa� program, kt�ry pobiera z okienka dialogowego �a�cuch znakowy i : 


    *    podaje na konsoli d�ugo�� �a�cucha, 
    *    wyprowadza pierwszy i ostatni znak, 
    *    wyprowadza pod�a�cuch od 3 znaku do ostatniego znaku, 
    *    wyprowadza pod�a�cuch od 3 znaku do przedostatniego znaku.
*/

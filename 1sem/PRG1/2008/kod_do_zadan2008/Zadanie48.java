import javax.swing.JOptionPane;

public class Zadanie48 {

	public static void main(String[] args) {
	
		String i;
		i = JOptionPane.showInputDialog("Slowo i: ");
		int dl;
		dl = i.length();
		
		System.out.println("D³ugoœc ³ancucha: " + dl);
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

Napisaæ program, który pobiera z okienka dialogowego ³añcuch znakowy i : 


    *    podaje na konsoli d³ugoœæ ³añcucha, 
    *    wyprowadza pierwszy i ostatni znak, 
    *    wyprowadza pod³añcuch od 3 znaku do ostatniego znaku, 
    *    wyprowadza pod³añcuch od 3 znaku do przedostatniego znaku.
*/

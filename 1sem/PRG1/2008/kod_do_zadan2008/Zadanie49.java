import javax.swing.JOptionPane;
import java.util.*;

public class Zadanie49 {

	public static void main(String[] args) {
		
		String s;
		s = JOptionPane.showInputDialog("Lancuch s: ");
		
		StringTokenizer b;
		b = new StringTokenizer(s, " ,.;");
		System.out.println("Ilosc slow: " + b.countTokens());
	}
	
}

/*Zadanie 49 (2p)


Napisaæ program, który pobiera od u¿ytkownika ³añcuch znakowy s i wyprowadza 
informacje o tym, ile s³ów zawiera s (s³owa = zestawy znaków rozdzielone spacjami, 
przecinkami, kropkami i œrednikami).*/

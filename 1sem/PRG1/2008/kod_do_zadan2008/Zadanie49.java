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


Napisa� program, kt�ry pobiera od u�ytkownika �a�cuch znakowy s i wyprowadza 
informacje o tym, ile s��w zawiera s (s�owa = zestawy znak�w rozdzielone spacjami, 
przecinkami, kropkami i �rednikami).*/

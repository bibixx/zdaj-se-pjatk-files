import javax.swing.JOptionPane;

public class Zadanie32 {
	public static void main (String[] args) {
	String x = JOptionPane.showInputDialog("Wprowadz znak: ");
	char znak = x.charAt(0);
	

	if (znak>='0' && znak<='9') {System.out.println(znak);} 
	else 
		if (znak>='a' && znak<='f') {System.out.println(znak-'a'+10);} 
		else 
			if (znak>='A' && znak<='F') {System.out.println(znak-'A'+10);}
			else System.out.println("-1");
		}
	}

/*
Zadanie 32 (2p)

Napisaæ program, który pobiera 1 znak i wyprowadza wartoœæ liczbow¹ odpowiadaj¹c¹ 
cyfrze szesnastkowej podanej w postaci tego znaku lub -1 jeœli dany znak nie odpowiada
 ¿adnej cyfrze szesnastkowej. Np: '0' -> 0, 'A' -> 10, 'a'-> 10, 'x' -> -1.
*/
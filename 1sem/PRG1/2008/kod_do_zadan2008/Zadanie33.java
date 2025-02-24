import javax.swing.JOptionPane;


public class Zadanie33 {
	public static void main (String[] args) {
		String zm1 = JOptionPane.showInputDialog("Wprowadz liczbe1 (double): ");
		String s1 = JOptionPane.showInputDialog("Wprowadz znak: ");
		String zm2 = JOptionPane.showInputDialog("Wprowadz liczbe2 (double): ");
		
		double x1 = (Double.parseDouble(zm1));
		double x2 = (Double.parseDouble(zm2));
		char znak = s1.charAt(0);
		
		switch (znak) {
		case '+': {System.out.println(x1+x2);break;}
		case '-': {System.out.println(x1-x2);break;}
		case '*': {System.out.println(x1*x2);break;}
		case '/': {System.out.println(x1/x2);break;}
		default: System.out.println("Bledny znak!");
		}
		 

	}

}

/* Zadanie 33 (2p) 

Napisaæ program symuluj¹cy dzia³anie czterodzia³aniowego kalkulatora: 
u¿ytkownik podaje (w okienku dialogowym) rodzaj operacji (+, -,*,/)  
i  dwie liczby typu double, program wyœwietla w okienku dialogowym wynik.

*/

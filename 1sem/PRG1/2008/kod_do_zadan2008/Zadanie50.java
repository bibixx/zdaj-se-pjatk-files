import javax.swing.JOptionPane;


public class Zadanie50 {

	public static void main(String[] args) {

		String temp1,temp2;
		temp1 = JOptionPane.showInputDialog("Slowo s1: ");
		temp2 = JOptionPane.showInputDialog("Slowo s2: ");

		int powt=0, b=0;
		
		while (powt!=-1)
		{
			powt = temp1.indexOf(temp2);
			temp1=temp1.substring(powt+temp2.length());
			b++;
		}
		System.out.println("Ilosc powtorzen: " + (b-1));
	}

}

/*Zadanie 50 (3p)


Napisać program, który pobiera od użytkownika 2 łańcuchy znakowe 
s1, s2 i wyprowadza informacje o tym, ile razy w łańcuchu s1 występuje łańcuch s2.
*/





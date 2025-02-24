import javax.swing.JOptionPane;

public class Zadanie37 {

	public static void main(String[] args) {
		
		String temp;
		temp = JOptionPane.showInputDialog("Pierwsza liczba:");
		int a = (Integer.parseInt(temp));
		temp = JOptionPane.showInputDialog("Druga liczba:");
		int b = (Integer.parseInt(temp));
		System.out.println(NWD(a,b));
		}
	
	public static int NWD(int i, int j) 
	{
		int c=0;
		while (j!=0)
		{
			c=i%j;
			i = j;
			j = c;
		}
		return i;
	}

	
}

/*
Zadanie 37 (2p) 

Napisaæ i testowaæ (w g³ównym programie) w³asn¹ metodê: int nwd(int a ,int b), 
która oblicza najwiêkszy wspólny dzielnik (NWD) 2 liczb naturalnych wprowadzonych 
przez u¿ytkownika.

*/
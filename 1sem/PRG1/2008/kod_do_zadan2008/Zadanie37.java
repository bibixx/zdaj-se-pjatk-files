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

Napisa� i testowa� (w g��wnym programie) w�asn� metod�: int nwd(int a ,int b), 
kt�ra oblicza najwi�kszy wsp�lny dzielnik (NWD) 2 liczb naturalnych wprowadzonych 
przez u�ytkownika.

*/
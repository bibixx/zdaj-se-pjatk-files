import javax.swing.JOptionPane;


public class Zadanie56 {
	public static void main(String[] args)
	{

		while (true) 
		{
		int a=0;
			String temp1;
		temp1 = JOptionPane.showInputDialog("Slowo s1: ");
		if (temp1==null || temp1.equals("Koniec")) {System.exit(0);}
		
		for (int x=0; x<temp1.length(); x++) 
		{
			if (temp1.indexOf(temp1.charAt(x))==temp1.lastIndexOf(temp1.charAt(x))) {a++;}
		}
		
		System.out.println(a);
	}
	}
}
		
		
		


/*Zadanie 56 (3p) 

Napisa� i testowa� program, kt�ry liczy ilo�� znak�w, wyst�puj�cych 
tylko raz w podanym �a�chu tekstowym, wprowadzonym przez u�ytkownika. 
Dane wej�ciowe i wyj�ciowe poda� w oknach dialogowych. Program dzia�a 
"w p�tl�" i ko�czy dzia�anie w momencie wybrania Cancel lub wprowadzenia 
�a�cucha "Koniec". 



*/
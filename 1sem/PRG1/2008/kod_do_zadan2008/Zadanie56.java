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

Napisaæ i testowaæ program, który liczy iloœæ znaków, wystêpuj¹cych 
tylko raz w podanym ³añchu tekstowym, wprowadzonym przez u¿ytkownika. 
Dane wejœciowe i wyjœciowe podaæ w oknach dialogowych. Program dzia³a 
"w pêtlê" i koñczy dzia³anie w momencie wybrania Cancel lub wprowadzenia 
³añcucha "Koniec". 



*/
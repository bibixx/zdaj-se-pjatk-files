import javax.swing.JOptionPane;


public class Zadanie34 {

	public static void main(String[] args) {
		String zm1 = JOptionPane.showInputDialog("Podaj P (boolean): ");
		String zm2 = JOptionPane.showInputDialog("Podaj Q (boolean):\n1. negacja\n2. i\n3. lub\n4. implikacja\n5. rownowaznosc");
		String operator = JOptionPane.showInputDialog("Podaj operacje (n): ");
		
		int p = (Integer.parseInt(zm1));
		int q = (Integer.parseInt(zm2));
		int op = (Integer.parseInt(operator));
		boolean w;
		
		switch (op) {
		case 1: {if (p==1) p=0; else p=1; if (q==1) q=0; else q=1; System.out.println("Negacja P: " + p + " Negacja Q: " + q );break;}
		case 2: {if (p==1 && q==1) w=true; else w=false; System.out.println(w); break;}
		case 3: {if (p==1 || q==1) w=true; else w=false; System.out.println(w);break;}
		case 4: {if (q==1) w=true; else w=false; System.out.println(w); break;}
		case 5: {if ((p==1&&q==1)||(p==0&&q==0)) w=true; else w=false; System.out.println(w); break;}
		default: System.out.println("Bledny znak!");
		}
	}

}

/*
Zadanie 34 (3p)

Napisaæ program symuluj¹cy dzia³anie podstawowych operacji logicznych na 
dwóch argumentach: u¿ytkownik podaje rodzaj operacji (negacja, i, lub, 
implikacja, równowa¿noœæ) i wartoœci logiczne argumentów, program wyœwietla wynik.
*/

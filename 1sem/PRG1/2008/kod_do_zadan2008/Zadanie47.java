import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;

public class Zadanie47 {

	public static void main(String[] args) 
	{
		String temp = null;
		ArrayList<String> tab = new ArrayList<String>();

		int i=0;
		while (true) 
		{
			temp = JOptionPane.showInputDialog("Zmienna: ");
			if (temp == null) {break;}
			tab.add(temp);
			i++; 
		}
		Collections.sort(tab);
	
		
		ArrayList<String> nowa = new ArrayList<String>();
		ArrayList<Integer> powt = new ArrayList<Integer>();
		
		int pow=1, sj=0;
		for (int j=0; j<i-1; j++) 
		{
			if ((tab.get(j)).equals(tab.get(j+1))) pow++;
			else 
			{
				powt.add(pow);
				nowa.add(tab.get(j));
				pow=1;
				sj=j+1;
			}
		}
		powt.add(pow);
		nowa.add(tab.get(sj));
		
		int m=0;
		while (m!=nowa.size()) 
		{
			System.out.print(nowa.get(m)+": "+ powt.get(m)+", ");
			m++;
		}
	}
}

/*Zadanie 47 (4p) 

Napisa� program, kt�ry wczytuje napisy podawane przez u�ytkownika (dop�ki nie zrezygnuje 
on z ich wprowadzania), a nast�pnie podaje liczb� wyst�pie� wszystkich wprowadzonych napis�w. 
Np. po wprowadzeniu napis�w Aaa, Bbb, Aaa, Ccc, Aaa, Bbb powinni�my otrzyma� raport: Aaa 3, Bbb 2, Ccc 1.
*/
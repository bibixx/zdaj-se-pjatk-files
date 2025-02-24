import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;


public class Zadanie46 {

	public static void main(String[] args) 
	{
		String temp;
		ArrayList<String> tab = new ArrayList<String>();
		int i=0;
		while (true) 
		{
			temp = JOptionPane.showInputDialog("Zmienna:");
			if (temp == null) {System.exit(0);}

			tab.add(temp);
			i++;
			Collections.sort(tab);
			for (int j=0; j<i; j++) {System.out.print(tab.get(j)+", ");}
			System.out.print("\n");
		
		}
	}
}

/*Zadanie 46 (4p)
('Dynamiczne" sortowanie kolekcji argumentów tekstowych)


Kolejny argument tekstowy dostarcza siê do kolekcji za poœrednictwem okienka dialogowego. 
Po dodawaniu kolejnego argumentu do kolekcji, program wyprowadza jej zawartoœæ, posortowan¹ 
wed³ug porz¹dku leksykograficznego, na konsolê. Wyjœcie z programu nastêpuje po naciœniêciu 
przycisku "Cancel". Dzia³anie programu mog³oby wygl¹daæ nastêpuj¹co:

aaa 
[aaa] 
aca 
[aaa,aca] 
abc 
[aaa,abc,aca] 
c 
[aaa,abc,aca,c] 
b 
[aaa,abc,aca,b,c] 
...

*/
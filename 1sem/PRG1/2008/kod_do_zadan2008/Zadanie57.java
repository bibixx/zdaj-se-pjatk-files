import java.io.*;
import java.util.*;

public class Zadanie57 {

	public static void main(String[] args) {
		try {
			FileReader fr = new FileReader("in.txt");
			FileWriter fw = new FileWriter("out.txt",true);
			BufferedReader bfr = new BufferedReader(fr);
			BufferedWriter bfw = new BufferedWriter(fw);
			String s;
			ArrayList<String> tab = new ArrayList<String>();
			while ((s=bfr.readLine())!=null)
			{
				tab.add(s);
			}
			bfr.close();
			
			
			for (int x=tab.size()-1; x>=0; x--)
			{
					bfw.write(tab.get(x));
					bfw.newLine();
			}
			bfw.close();
		}

		catch (IOException e) {System.out.println("ERROR?");}
	}

}

/*Zadanie 57 (2p) 

Otwórz plik tekstowy tak, aby móc go odczytywaæ wiersz po wierszu. 
Odczytuj wszystkie wiersze i umieszczaj je w pliku wyjœciowym w odwrotnym porz¹dku. 
*/
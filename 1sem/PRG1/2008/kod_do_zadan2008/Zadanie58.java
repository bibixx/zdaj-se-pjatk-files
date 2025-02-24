import java.io.*;
import java.util.*;

public class Zadanie58 {

	public static void main(String[] args) {
		try {
			FileWriter fw1 = new FileWriter("output1.txt",false);
			FileWriter fw2 = new FileWriter("output2.txt",false);
			BufferedReader bfr = new BufferedReader(new FileReader("input.txt"));
			BufferedWriter bfw1 = new BufferedWriter(fw1);
			BufferedWriter bfw2 = new BufferedWriter(fw2);
			
			String s, temp2=null;
			StringTokenizer token1 = new StringTokenizer(" ");
			StringTokenizer token2 = new StringTokenizer(" ");
			
			while ((s=bfr.readLine())!=null)
			{
				System.out.println(temp2);
				while (token1.hasMoreTokens()) 
				{
					try {
						Integer.parseInt(temp2);
						bfw1.write(temp2);
						System.out.println();
						
						
					}
					
					catch (NumberFormatException e) 
					{
						while (token2.hasMoreTokens())
						{
							
						}
					}
				}
			}
			
			bfr.close();
			bfw1.close();
			bfw2.close();
		}

		catch (IOException e) {System.out.println("ERROR");}
	}

}

/*
Zadanie 58 (4p)


Leksemy to ci¹gi znaków rozdzielone znakami spacji.


Napisz program, który kopiuje z pliku input.txt do output1.txt wszystkie 
leksemy bêd¹ce liczbami ca³kowitymi (typu int) i podaje ich sumê na konsolê. 
Nastêpnie program zapisuje pozosta³e leksemy wraz z liczb¹ ich wyst¹pieñ do 
pliku output2.txt. 
*/
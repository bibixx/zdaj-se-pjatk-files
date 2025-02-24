import java.io.*;
import java.util.*;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.CompareGenerator;


public class Zadanie11 {
	public static void main(String[] args) {
		
		String linia = null;
		String nazwisko, pensja, nrd;
		try 
		{
			FileReader fr;
			fr = new FileReader("plik.txt");
			BufferedReader bfr = new BufferedReader(fr);
			int nrlini=0;
			ArrayList lista = new ArrayList();
			
			while ((linia=bfr.readLine())!=null){
			
			StringTokenizer str = new StringTokenizer(linia, "   %    ");	
			if (str.countTokens()==3)
			{
				lista.add(new elementy(str.nextToken().trim(),str.nextToken().trim(),str.nextToken().trim()));
			}
			System.out.println(lista.size());
			
			//lista.
			
			try {
				FileWriter fw = new FileWriter("out.txt", true);
				BufferedWriter bfw = new BufferedWriter(fw);
				//bfw.write(ele.put());
				bfw.newLine();
				bfw.close();
				}
				catch (IOException e) {}
			}
		} 
		catch (FileNotFoundException e) {System.out.println("Plik nie znaleziony!");} 
		catch (IOException e) {System.out.println("IOException! ;<");	e.printStackTrace();}
		
	}
}

class elementy implements Comparable<elementy>
{
	String nazwisko, pensja, nrd;
	public elementy(String nazwisko, String pensja, String nrd) {
		this.nazwisko = nazwisko;
		this.pensja =  pensja;
		this.nrd = nrd;
	}
	public String get_nazwisko() {return nazwisko;}
	public String get_pensja() {return this.pensja;}
	public String get_nrd() {return this.nrd;}
	public void put() 
	{
		
	}
	public int compareTo(elementy ele) 
	{
	//	if ( ele.get_nrd() > this.nrd) {}
		return 0;
	}
}

/*Zadanie 11 (4p)

Napisaæ program, który kopiuje dane pracowników z pliku wejœciowego do pliku wyjœciowego o innej strukturze. 

Ka¿dy poprawny wiersz pliku wejœciowego (przyk³ad) jest postaci: 
nazwisko   %    pensja    %    numerDzia³u, 
gdzie numer dzia³u jest liczb¹ ca³kowit¹. 
Plik wyœciowy (przyk³ad) ma zawieraæ informacje o pracownikach, posortowane wed³ug kryteriów uporz¹dkowania: 
najpierw numery dzia³u s¹ porównywane, potem nazwiska, a na koñcu pensje. 
Wiersze pliku wyjœciowego maj¹ byæ zapisane w postaci: numerDzia³u     nazwisko     pensja. 
Wszystkie niepoprawne (sk³adniowo) wiersze pliku wejœciowego s¹ ignorowane przy kopiowaniu, 
jednoczeœnie s¹ one zapisywane do innego pliku wyjœciowego (przyk³ad). */

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

Napisa� program, kt�ry kopiuje dane pracownik�w z pliku wej�ciowego do pliku wyj�ciowego o innej strukturze. 

Ka�dy poprawny wiersz pliku wej�ciowego (przyk�ad) jest postaci: 
nazwisko   %    pensja    %    numerDzia�u, 
gdzie numer dzia�u jest liczb� ca�kowit�. 
Plik wy�ciowy (przyk�ad) ma zawiera� informacje o pracownikach, posortowane wed�ug kryteri�w uporz�dkowania: 
najpierw numery dzia�u s� por�wnywane, potem nazwiska, a na ko�cu pensje. 
Wiersze pliku wyj�ciowego maj� by� zapisane w postaci: numerDzia�u     nazwisko     pensja. 
Wszystkie niepoprawne (sk�adniowo) wiersze pliku wej�ciowego s� ignorowane przy kopiowaniu, 
jednocze�nie s� one zapisywane do innego pliku wyj�ciowego (przyk�ad). */

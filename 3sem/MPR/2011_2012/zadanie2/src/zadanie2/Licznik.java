package zadanie2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Licznik
{
	private static final String kryt = "ipsum"; // moje kryterium
	private static final Pattern wzor = Pattern.compile(kryt); // wzor 
	
	public static boolean checkAddress(String s)
	{
		Matcher m = Pattern.compile("^[a-zA-Z][:](\\\\[^\\\\/?*:|\"<>]+)+$").matcher(s); // czy zgadza sie ze wzorem
		return m.matches();
	}

	public static int count(File f) throws FileNotFoundException // licznik z pliku
	{
		int counter = 0;
		Scanner sc = new Scanner(f);
		
		while (sc.findWithinHorizon(kryt, 0) != null)
		{
			counter++;
		}
		sc.close();
		return counter;
	}

	

	public static int count(String s)
	{
		Matcher m =wzor.matcher(s);
		int counter = 0;
		
		while (m.find())
		{
			counter++;
		}
		return counter;
	}
}

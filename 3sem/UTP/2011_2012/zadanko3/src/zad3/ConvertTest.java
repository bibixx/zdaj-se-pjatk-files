package zad3;

import static org.junit.Assert.*;
import java.io.File;
import org.junit.Test;

public class ConvertTest {

	@Test
	public void test0() {
		//	wejœcie - plik istnieje
		File in = new File("c:\\wejscie.txt");
		//	wyjœcie - plik istnieje
		File out = new File("c:\\wyjscie.txt");
		//	dekodowanie
		String decode = "UTF-8";
		//	kodowanie
		String encode = "UTF-8";
		//	sprawdŸ czy prawdziwe - musi byæ prawdziwe,
		//	poniewa¿ in i out istniej¹ oraz kodowania s¹ prawid³owe
		assertTrue(ConvertControl.Convert(in, out, decode, encode));
	}

	@Test
	public void test1() {	//	tak samo jak wy¿ej - z innym dekodowaniem i kodowaniem
		File in = new File("c:\\wejscie.txt");
		File out = new File("c:\\wyjscie.txt");
		String decode = "windows-1250";
		String encode = "windows-1250";
		assertTrue(ConvertControl.Convert(in, out, decode, encode));
	}

	@Test
	public void test2() {	//	tak samo jak pierwsze
		File in = new File("Z:\\input.txt");
		//	plik out nie istnieje - nie ma dysku X
		File out = new File("X:\\output.txt");
		String decode = "UTF-8";
		String encode = "UTF-8";
		//	sprawdŸ czy fa³szywe - nie uda siê,
		//	poniewa¿ plik wyjœciowy nie istnieje
		assertFalse(ConvertControl.Convert(in, out, decode, encode));
	}

	@Test
	public void test3() {	//	tak samo jak pierwsze
		File in = new File("e:\\input.txt");
		File out = new File("e:\\output.txt");
		//	dekodowanie jest z³e - nie ma czegoœ takiego jak "USA-24"
		String decode = "USA-24";
		String encode = "UTF-8";
		//	sprawdŸ czy fa³szywe - nie uda siê,
		//	poniewa¿ plik podane dekodowanie nie istnieje
		assertFalse(ConvertControl.Convert(in, out, decode, encode));
	}

	@Test
	public void test4() {
		//	z³e wejœcie
		File in = new File("X:\\input.txt");
		//	z³e wyjœcie
		File out = new File("Z:\\output.txt");
		//	z³e dekodowanie
		String decode = "USA-24";
		//	z³e kodowanie
		String encode = "XXX---24";
		//	sprawdŸ czy fa³szywe - nie uda siê,
		//	poniewa¿ wszystkie dane s¹ z³e
		assertFalse(ConvertControl.Convert(in, out, decode, encode));
	}
	
}
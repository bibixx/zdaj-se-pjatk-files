package zad3;

import static org.junit.Assert.*;
import java.io.File;
import org.junit.Test;

public class ConvertTest {

	@Test
	public void test0() {
		//	wej�cie - plik istnieje
		File in = new File("c:\\wejscie.txt");
		//	wyj�cie - plik istnieje
		File out = new File("c:\\wyjscie.txt");
		//	dekodowanie
		String decode = "UTF-8";
		//	kodowanie
		String encode = "UTF-8";
		//	sprawd� czy prawdziwe - musi by� prawdziwe,
		//	poniewa� in i out istniej� oraz kodowania s� prawid�owe
		assertTrue(ConvertControl.Convert(in, out, decode, encode));
	}

	@Test
	public void test1() {	//	tak samo jak wy�ej - z innym dekodowaniem i kodowaniem
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
		//	sprawd� czy fa�szywe - nie uda si�,
		//	poniewa� plik wyj�ciowy nie istnieje
		assertFalse(ConvertControl.Convert(in, out, decode, encode));
	}

	@Test
	public void test3() {	//	tak samo jak pierwsze
		File in = new File("e:\\input.txt");
		File out = new File("e:\\output.txt");
		//	dekodowanie jest z�e - nie ma czego� takiego jak "USA-24"
		String decode = "USA-24";
		String encode = "UTF-8";
		//	sprawd� czy fa�szywe - nie uda si�,
		//	poniewa� plik podane dekodowanie nie istnieje
		assertFalse(ConvertControl.Convert(in, out, decode, encode));
	}

	@Test
	public void test4() {
		//	z�e wej�cie
		File in = new File("X:\\input.txt");
		//	z�e wyj�cie
		File out = new File("Z:\\output.txt");
		//	z�e dekodowanie
		String decode = "USA-24";
		//	z�e kodowanie
		String encode = "XXX---24";
		//	sprawd� czy fa�szywe - nie uda si�,
		//	poniewa� wszystkie dane s� z�e
		assertFalse(ConvertControl.Convert(in, out, decode, encode));
	}
	
}
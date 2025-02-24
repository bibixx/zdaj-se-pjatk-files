package zadanie2;


import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LicznikTest
{

	@Before
	public void setUp() throws Exception
	{
	}

	@After
	public void tearDown() throws Exception
	{
	}

	
	@Test
	public void test()
	{
		ArrayList<String> list = new ArrayList<String>();
		list.add("C:\\test.txt");
		list.add("C:\\input.txt");
		for(int i=0; i< list.size(); i++)
		{
			assertTrue(Licznik.checkAddress(list.get(i))); //czy sciezki s¹ prawid³owe
		}
	}
	
	@Test
	public void test1()
	{
		ArrayList<String> list = new ArrayList<String>();
		assertTrue(Licznik.checkAddress("C:\\Windows")); // poprawna sciezka
		assertFalse(Licznik.checkAddress("C:\\Windows\\")); // niepoprawna sciezka
		list.add("C:\\Windows\\"); // dodaje do listy swoj¹ sciezke
		for(int i=0; i< list.size(); i++)
		{
			assertFalse(Licznik.checkAddress(list.get(i)));
		}
	}
	
	@Test
	public void test2()
	{
		ArrayList<String> list = new ArrayList<String>();
		list.add("C:\\test1.txt");
		for(int i=0; i< list.size(); i++)
		{
			try // Sprawdza czy jest plik i czy w nim jest 10 razy wpisane kryterium
			{
				assertEquals(10,Licznik.count(new File(list.get(i))));
			}
			catch (FileNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void test3() // sprawdz stringa
	{
		ArrayList<String> list = new ArrayList<String>();
		list.add("ipsum	ipsum ipsum ipsum"); // dodajemy do listy te 4 slowa
		for(int i=0; i< list.size(); i++)
		{
			assertEquals(4,Licznik.count(list.get(i)));
		}
	}
	
	@Test
	public void test4() // sprawdza to samo dla blednych danych
	{
		ArrayList<String> list = new ArrayList<String>();
		list.add("ala ma kota kot ma");
		for(int i=0; i< list.size(); i++)
		{
			assertEquals(0,Licznik.count(list.get(i)));
		}
	}
}

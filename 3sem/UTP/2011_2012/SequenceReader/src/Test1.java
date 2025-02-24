import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class Test1 {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test1() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("D:\\in.txt");
		for(int i=0; i< list.size(); i++){
			assertTrue(Counter.checkAddress(list.get(i)));
		}
	}
	@Test
	public void test2() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("D:\\in.txt");
		for(int i=0; i< list.size(); i++){
			try {
				assertEquals(5,Counter.count(new File(list.get(i))));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	@Test
	public void test3() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("asd asdasdasd");
		for(int i=0; i< list.size(); i++){
			assertEquals(4,Counter.count(list.get(i)));
		}
	}
	@Test
	public void test4() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("gfhfhfgdrgada");
		for(int i=0; i< list.size(); i++){
			assertEquals(0,Counter.count(list.get(i)));
		}
	}
}

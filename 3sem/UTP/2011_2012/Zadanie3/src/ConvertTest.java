import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ConvertTest {
	static final int BUFFOR_SIZE = 1024;
	char[] buffer = new char[BUFFOR_SIZE];
	char[] buffer2 = new char[BUFFOR_SIZE];
	FileInputStream fis;
	InputStreamReader isf;
	FileInputStream fos;
	InputStreamReader osw;

	@Before
	public void setUp() throws Exception {
		Converter c = new Converter(new File("C:\\Users\\Mates\\Documents\\xxx.txt"), new File("C:\\Users\\Mates\\Documents\\test.txt"), Charset.forName("UTF-8"), Charset.forName("iso-8859-2"));
		c.convertNow();
		fis = new FileInputStream(new File("C:\\Users\\Mates\\Documents\\xxx.txt"));
		isf = new InputStreamReader(fis, Charset.forName("UTF-8"));
		fos = new FileInputStream(new File("C:\\Users\\Mates\\Documents\\test.txt"));
		osw = new InputStreamReader(fos, Charset.forName("iso-8859-2"));
		
	}

	@After
	public void tearDown() throws Exception {
		isf.close();
		fis.close();
		osw.close();
		fos.close();
	}

	@Test
	public void test() {
		try {
			while (isf.read(buffer, 0, BUFFOR_SIZE) != -1 && isf.read(buffer2, 0, BUFFOR_SIZE) != -1) {
				assertArrayEquals(buffer, buffer2);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

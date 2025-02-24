import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

public class Converter {
	File source;
	File target;
	Charset csource;
	Charset ctarget;
	static final public int BUFFOR_SIZE=1024;
	char[] buffer = new char[BUFFOR_SIZE];
	Converter(File s, File t, Charset cs, Charset ct) {
		source = s;
		target = t;
		csource = cs;
		ctarget = ct;
	}

	public void convertNow() throws IOException {
		FileInputStream fis = new FileInputStream(source);
		InputStreamReader isf = new InputStreamReader(fis, csource);
		FileOutputStream fos = new FileOutputStream(target);
		OutputStreamWriter osw = new OutputStreamWriter(fos, ctarget);
		int r;
		while ((r=isf.read(buffer, 0, BUFFOR_SIZE)) != -1) {
			System.out.println(r);
			osw.write(buffer, 0, r);
		}
		isf.close();
		fis.close();
		osw.close();
		fos.close();
	}
}

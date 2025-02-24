import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Counter {
	private static final String LOOKFOR = "asd";
	private static final Pattern LookPatern = Pattern.compile(LOOKFOR);
	public static boolean checkAddress(String s) {
		Matcher m = Pattern.compile(
				"^[a-zA-Z][:](\\\\[^\\\\/?*:|\"<>]+)+$").matcher(s);
		return m.matches();
	}

	public static int count(File f) throws FileNotFoundException {
		int counter = 0;
		Scanner sc = new Scanner(f);
		while (sc.findWithinHorizon(LOOKFOR, 0) != null) {
			counter++;
		}
		sc.close();
		return counter;
	}

	public static int count(InputStream in) {
		int counter = 0;
		Scanner sc = new Scanner(in);
		while (sc.findWithinHorizon(LOOKFOR, 0) != null) {
			counter++;
		}
		sc.close();
		return counter;
	}

	public static int count(String s) {
		Matcher m =LookPatern.matcher(s);
		int counter = 0;
		while (m.find()) {
			counter++;
		}
		return counter;
	}
}

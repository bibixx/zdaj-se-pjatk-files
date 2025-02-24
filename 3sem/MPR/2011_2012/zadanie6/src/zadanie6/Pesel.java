import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Pesel {
	private String Pesel;

	Pesel(String Pesel) {
		this.Pesel = Pesel;
		if (Pesel.length() != 11)
			System.err.println("ERROR(" + Pesel + "): 0x46846");
	}

	private boolean isValid() {
		int Hash = 1 * Integer.parseInt(Character.toString(Pesel.charAt(0)))
				+ 3 * Integer.parseInt(Character.toString(Pesel.charAt(1))) + 7
				* Integer.parseInt(Character.toString(Pesel.charAt(2))) + 9
				* Integer.parseInt(Character.toString(Pesel.charAt(3))) + 1
				* Integer.parseInt(Character.toString(Pesel.charAt(4))) + 3
				* Integer.parseInt(Character.toString(Pesel.charAt(5))) + 7
				* Integer.parseInt(Character.toString(Pesel.charAt(6))) + 9
				* Integer.parseInt(Character.toString(Pesel.charAt(7))) + 1
				* Integer.parseInt(Character.toString(Pesel.charAt(8))) + 3
				* Integer.parseInt(Character.toString(Pesel.charAt(9)));
		int Mod = Hash % 10;
		int Control;
		if (Mod == 0)
			Control = 0;
		else
			Control = 10 - Mod;
		if (Integer.parseInt(Character.toString(Pesel.charAt(10))) == Control)
			return true;
		else
			return false;
	}

	private String getSex() {
		if (Integer.parseInt(Character.toString(Pesel.charAt(9))) % 2 == 0)
			return "man";
			return "woman";
	}

	private Date getDate() throws ParseException {
		DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
		int r = 1900;
		int d = Integer.parseInt(Character.toString(Pesel.charAt(4))) * 10
				+ Integer.parseInt(Character.toString(Pesel.charAt(5)));
		int m = Integer.parseInt(Character.toString(Pesel.charAt(2))) * 10
				+ Integer.parseInt(Character.toString(Pesel.charAt(3)));
		if ((m - 20) >= 1 && (m - 20) <= 12)
			r = 2000;
		if ((m - 40) >= 1 && (m - 40) <= 12)
			r = 2100;
		if ((m - 60) >= 1 && (m - 60) <= 12)
			r = 2200;
		if ((m - 80) >= 1 && (m - 80) <= 12)
			r = 1800;
		if (r == 2000)
			m -= 20;
		if (r == 2100)
			m -= 40;
		if (r == 2200)
			m -= 60;
		if (r == 1800)
			m -= 80;
		r += Integer.parseInt(Character.toString(Pesel.charAt(0))) * 10
				+ Integer.parseInt(Character.toString(Pesel.charAt(1)));
		String s = r + "-" + m + "-" + d;
		Date urodzenie = formatter.parse(s);
		return urodzenie;
	}

	public void set(String Pesel) {
		this.Pesel = Pesel;
	}

	public String get() {
		return Pesel;
	}
}

package CW_20_10_08;

import javax.swing.JOptionPane;

public class Zadanie18 {
 /* Zadanie 18 (3p) 
Napisaæ program, który pobiera 3 argumenty o postaci liczb rzeczywistych i 
wyprowadza je w porz¹dku niemalej¹cym. Dane wejœciowe i wyjœciowe podaæ w oknach dialogowych. 
*/
	public static void main(String[] args)
	{
		double liczba1=0,liczba2=0,liczba3=0;
		
		String dane1 = JOptionPane.showInputDialog("Podaj 1 liczbê typu double");
		try {liczba1 = Double.parseDouble(dane1);}
		catch (NumberFormatException e){JOptionPane.showMessageDialog(null, "Niepoprawna liczba typu double");}
			
		String dane2 = JOptionPane.showInputDialog("Podaj 2 liczbê typu double");
		try {liczba2 = Double.parseDouble(dane2);}
		catch (NumberFormatException e){JOptionPane.showMessageDialog(null, "Niepoprawna liczba typu double");}
		
		String dane3 = JOptionPane.showInputDialog("Podaj 3 liczbê typu double");
		try {liczba3 =Double.parseDouble(dane3);}
		catch (NumberFormatException e){JOptionPane.showMessageDialog(null, "Niepoprawna liczba typu double");}
		
		if (liczba1>liczba2) 
			if (liczba1>liczba3) 
				if (liczba2>liczba3) JOptionPane.showMessageDialog(null, "Kolejnosc to " + liczba3 + " " + liczba2 + " " + liczba1);
				else JOptionPane.showMessageDialog(null, "Kolejnosc to " + liczba2 + " " + liczba3 + " " + liczba1);
			else JOptionPane.showMessageDialog(null, "Kolejnosc to " + liczba2 + " " + liczba1 + " " + liczba3
					);
		else 
			if (liczba2>liczba3) 
				if (liczba3>liczba1) JOptionPane.showMessageDialog(null, "Kolejnosc to " + liczba1 + " " + liczba3 + " " + liczba2);
				else JOptionPane.showMessageDialog(null, "Kolejnosc to " + liczba3 + " " + liczba1 + " " + liczba2);
			else
				if (liczba1>liczba3) JOptionPane.showMessageDialog(null, "Kolejnosc to " + liczba2 + " " + liczba3 + " " + liczba1);
				else JOptionPane.showMessageDialog(null, "Kolejnosc to " + liczba2 + " " + liczba1 + " " + liczba3);
		}
	}

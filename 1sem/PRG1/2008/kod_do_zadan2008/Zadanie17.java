package CW_20_10_08;

import javax.swing.*;

public class Zadanie17 {
	public static void main(String[] args)
	{
		int liczba1=0,liczba2=0,liczba3=0;
		
		String dane1 = JOptionPane.showInputDialog("Podaj 1 liczbê typu int");
		try {liczba1 = Integer.parseInt(dane1);}
		catch (NumberFormatException e){JOptionPane.showMessageDialog(null, "Niepoprawna liczba typu int");}
			
		String dane2 = JOptionPane.showInputDialog("Podaj 2 liczbê typu int");
		try {liczba2 = Integer.parseInt(dane2);}
		catch (NumberFormatException e){JOptionPane.showMessageDialog(null, "Niepoprawna liczba typu int");}
		
		String dane3 = JOptionPane.showInputDialog("Podaj 3 liczbê typu int");
		try {liczba3 =Integer.parseInt(dane3);}
		catch (NumberFormatException e){JOptionPane.showMessageDialog(null, "Niepoprawna liczba typu int");}
		
		if (liczba1>liczba2) {
			if (liczba1>liczba3) JOptionPane.showMessageDialog(null, "Najwieksza liczba to " + liczba1);
			else JOptionPane.showMessageDialog(null, "Najwieksza liczba to " + liczba3);
			}
		else {
			if (liczba2>liczba3) JOptionPane.showMessageDialog(null, "Najwieksza liczba to " + liczba2);
			else JOptionPane.showMessageDialog(null, "Najwieksza liczba to " + liczba3);
			}
		}
	}

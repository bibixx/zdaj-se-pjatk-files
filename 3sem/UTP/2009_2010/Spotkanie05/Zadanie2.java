package Spotkanie05;
import java.awt.Color;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class Zadanie2 extends JFrame{
	
	public static void button_do_xml(String napis, Color kolor_tla, Color kolor_napisow, String nazwa_pliku)
	{
		JButton button = new JButton(napis);
	    button.setBackground(kolor_tla);
	    button.setForeground(kolor_napisow);

	    try 
	    {
	        XMLEncoder enc = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(nazwa_pliku)));
	        enc.writeObject(button);
	        enc.close();
	    } 
	    catch (FileNotFoundException exc) {}
	}

	public Zadanie2(final String nazwa_pliku){
		  SwingUtilities.invokeLater(new Runnable() {
			  public void run() {
				  	createGui(nazwa_pliku);
				  }
		  });
	}

	public void createGui(String nazwa_pliku) {
		
		 try {
		      XMLDecoder dec = new XMLDecoder(new BufferedInputStream(new FileInputStream(nazwa_pliku)));
		      Object obj = dec.readObject();
		      JButton b = (JButton) obj;
		      dec.close();
		      
		  	  b.getText();
		  	  b.getBackground();
		      b.getForeground();
		      
		      add(b);  pack();
			  setTitle("Spotkanie05");
			  setLocationRelativeTo(null);
			  setVisible(true);
			  setDefaultCloseOperation(EXIT_ON_CLOSE);
		 } 
		 catch (FileNotFoundException exc) {System.out.println("Brak pliku: " + nazwa_pliku);}
	}
	    
	
	public static void main(String[] args) 
	{
		button_do_xml("napis", Color.black, Color.green, "guzik.xml");
		new Zadanie2("guzik.xml");
	}
}
package Spotkanie05;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Zadanie1 extends JFrame {
	
	public Zadanie1(){
		  SwingUtilities.invokeLater(new Runnable() {
			  public void run() {
				  	createGui();
			      }
		  });
	}
	
	public void createGui() {
		
		JButton b_pl = new JButton("PL");
		JButton b_en = new JButton("EN");
		JButton b_de = new JButton("DE");
		JButton b_xx = new JButton("??");
		Monitorek mon = new Monitorek();

		Ogranicznik ogr = new Ogranicznik();
		
		
		
		final Language lang = new Language();
		lang.addPropertyChangeListener(mon);
		lang.addVetoableChangeListener(ogr);
		
		
		setLayout(new GridLayout(2,1));
		JPanel p1 = new JPanel(new GridLayout()); 
			JPanel p1b = new JPanel(new GridLayout());	p1.add(p1b);
				p1b.add(b_pl); p1b.add(b_en); p1b.add(b_de); p1b.add(b_xx);
		JPanel p2 = new JPanel();
			p2.add(mon);
			
		add(p1);
		add(p2);
		
		 b_pl.addActionListener(new ActionListener()  {
	    	  public void actionPerformed(ActionEvent e) { try {
				lang.setLang(0);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}}
		 });
		 
		 b_en.addActionListener(new ActionListener() {
	    	  public void actionPerformed(ActionEvent e) { try {
				lang.setLang(1);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}}
		 });
		 
		 b_de.addActionListener(new ActionListener() {
	    	  public void actionPerformed(ActionEvent e) { try {
				lang.setLang(2);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}}
	    });
		
		 b_xx.addActionListener(new ActionListener() {
	    	  public void actionPerformed(ActionEvent e) {
	    		  try {
					lang.setLang(3);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,"FAIL ;(","ouch ;(", JOptionPane.ERROR_MESSAGE);
				}
	    		  //else JOptionPane.showMessageDialog(null,"FAIL ;(","ouch ;(", JOptionPane.ERROR_MESSAGE);
		       }
	    });
		
		pack();
		setTitle("Spotkanie05");
		setLocationRelativeTo(null);
	    setVisible(true);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) 
	{
		new Zadanie1();
	}
}

class Language implements java.io.Serializable{
	private PropertyChangeSupport chg = new PropertyChangeSupport(this);
	private VetoableChangeSupport veto = new VetoableChangeSupport(this);
	int language=0;
	
	public synchronized void setLang(int language) throws PropertyVetoException{
		veto.fireVetoableChange("language", new Integer(this.language), new Integer(language));
		
		chg.firePropertyChange("Zmiana", new Integer(this.language), new Integer(language));
		
		this.language=language;
	}
	public int	getLang() {return language;}
	
    public synchronized void addPropertyChangeListener(PropertyChangeListener listener) {
    	chg.addPropertyChangeListener(listener);
	}

	public synchronized void removePropertyChangeListener(PropertyChangeListener l) {
	    chg.removePropertyChangeListener(l);
	}
	public synchronized void addVetoableChangeListener(VetoableChangeListener listener) {
    	veto.addVetoableChangeListener(listener);
	}

	public synchronized void removeVetoableChangeListener(VetoableChangeListener l) {
	    veto.removeVetoableChangeListener(l);
	}
	
}

class Monitorek extends JLabel implements PropertyChangeListener {

	String[] hello = {"Witam!", "Hello!", "GUTEN CAPS!", "ZOMG HAI!"};
	String[] bye = {"¯egnam!", "Goodbye!", "GERMAN-ish bb", "bai?"};
	Monitorek()  {}//{this("Witam!");}
	 
	public void propertyChange(PropertyChangeEvent e) {
		setText(hello[(Integer) e.getNewValue()] + ", " + bye[(Integer) e.getNewValue()]);  // pokazanie na etykiecie nowego stanu licznika
	}
}

class Ogranicznik implements VetoableChangeListener {

	public void vetoableChange(PropertyChangeEvent e) throws PropertyVetoException 
	{
          if ((Integer) e.getNewValue()==3 && Math.random()>0.5)
	      throw new PropertyVetoException("Niedopuszczalna zmiana wartoœci", e);
	}
}
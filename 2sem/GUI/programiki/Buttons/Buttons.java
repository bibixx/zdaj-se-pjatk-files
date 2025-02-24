import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/* 
 * Potrzebujesz nastepujacych plikow (w katalogu z programem ) aby program dzialal poprawnie
 *   ./L.gif
 *   ./M.gif
 *   ./R.gif
 */

//################################################################
public class Buttons extends JPanel implements ActionListener {
//################################################################
	
	protected JButton b1, b2, b3;
	protected JPanel p1;

//======================================
public static void main(String[] args) {
//======================================
	
	wyswietl(); 
    
}//============================================================== main

//===================
public Buttons() {
//===================
	
        ImageIcon LBI = new ImageIcon("./L.gif"); //pobieramy ikone do lewego przycisku
        ImageIcon MBI = new ImageIcon("./M.gif"); //pobieramy ikone do srodkowego przycisku
        ImageIcon RBI = new ImageIcon("./R.gif"); //pobieramy ikone do prawego przycisku

        b1 = new JButton("Disable middle button", LBI);
        b1.setVerticalTextPosition(AbstractButton.CENTER);	//centralne wyrownanie tekstu
        b1.setHorizontalTextPosition(AbstractButton.LEADING); //"glowne" wyrownanie tekstu
        b1.setMnemonic(KeyEvent.VK_D);
        b1.setActionCommand("disable");

        b2 = new JButton("Middle button", MBI);
        b2.setVerticalTextPosition(AbstractButton.BOTTOM); //dolne wyrownanie tekstu
        b2.setHorizontalTextPosition(AbstractButton.CENTER);
        b2.setMnemonic(KeyEvent.VK_M);

        b3 = new JButton("Enable middle button", RBI);
        b3.setMnemonic(KeyEvent.VK_E);
        b3.setActionCommand("enable");
        b3.setEnabled(false); //przycisk jest wcisniety

        b1.setToolTipText("Wylacz srodkowy przycisk.");
        b2.setToolTipText("Buahahahahahaha.");
        b3.setToolTipText("Wlacz srodkowy przycisk.");
        
        b1.addActionListener(this);
        b3.addActionListener(this);

        //Dodajemy wszystko do kontenera (domyslnie w fomacie FlowLayout)
        add(b1);
        add(b2);
        add(b3);
        
}//============================================================== ButtonDemo

//==========================================
public void actionPerformed(ActionEvent e) {
//==========================================
	
        if ("disable".equals(e.getActionCommand())) {
            b2.setEnabled(false);
            b1.setEnabled(false);
            b3.setEnabled(true);
        } else {
            b2.setEnabled(true);
            b1.setEnabled(true);
            b3.setEnabled(false);
        }

}//========================================================== actionPerformed

//==============================
private static void wyswietl() {
//==============================
	
        JFrame.setDefaultLookAndFeelDecorated(true); // aby wszytko bylo "piekne" :)

        JFrame frame = new JFrame("Main Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Buttons content = new Buttons();
        frame.setContentPane(content);

        frame.pack();
        frame.setVisible(true);
        
}//========================================================= wyswietl
    
}//################################################################ ButtonDemo
import java.awt.*; 	
import javax.swing.*;

public class DrawSwing {

    public static void main(String[] args)
    {
    	new DrawSwing();
    }
    
    public DrawSwing()
    {
    	SwingUtilities.invokeLater(new Runnable(){
    		public void run()
    		{
    			createGUI();
    		}
    	});
    }
    
    protected void createGUI()
    {
    		// utworzenie okna
        JFrame jf = new JFrame();
      
        	// okreœlenie tytu³u okna
        jf.setTitle("Rysowanie");
      		
      		// obs³uga zamkniêcia okna JFrame
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        	// okreœlenie po³o¿enia okna
        jf.setLocation(50,50);
        
        	// uniemo¿liwienie zmiany rozmiarów okna
        jf.setResizable(false);
		
			// utworzenie obszaru rysowania - pulpitu
		MyPanel p = new MyPanel();
		
			// wymiana domyœlnego pulpitu na w³asny
        jf.setContentPane(p);	
        
        	// upakowanie okna
        jf.pack( );
        	
        	// wyœwietlenie okna
        jf.setVisible(true);   
    }
}

class MyPanel
	extends JPanel {

    	// konstruktor     
    MyPanel()
    {
          // ustalenie rozmiarów pocz¹tkowych
        setPreferredSize(new Dimension(400,400));
     
          // ustalenie sposobu buforowania: 
          // true (domyœlnie):rysowanie odbywa siê w buforze 
          // pamiêci, bufor ten jest wyœwietlany na ekranie od razu w ca³oœci
          // dopiero po wyjœciu sterowania z funkcji paintComponent.  
        RepaintManager.currentManager(this).
                setDoubleBufferingEnabled(true);        
    }
    
    public void paintComponent(Graphics g)
    {
          // musi byæ jako pierwsza instrukcja
        super.paintComponent(g);
        
          // pobranie aktualnych rozmiarów
        int width = getWidth();
        int height = getHeight();
        
	        // TU RYSUJEMY!
	        // ........
        g.setColor(Color.red);
        g.setFont(new Font("Dialog", Font.BOLD|Font.ITALIC, 30));
        g.drawString("Tu rysujemy!", width/2, height/2);
    	// ........
    }
}

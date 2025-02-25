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
      
        	// okre�lenie tytu�u okna
        jf.setTitle("Rysowanie");
      		
      		// obs�uga zamkni�cia okna JFrame
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        	// okre�lenie po�o�enia okna
        jf.setLocation(50,50);
        
        	// uniemo�liwienie zmiany rozmiar�w okna
        jf.setResizable(false);
		
			// utworzenie obszaru rysowania - pulpitu
		MyPanel p = new MyPanel();
		
			// wymiana domy�lnego pulpitu na w�asny
        jf.setContentPane(p);	
        
        	// upakowanie okna
        jf.pack( );
        	
        	// wy�wietlenie okna
        jf.setVisible(true);   
    }
}

class MyPanel
	extends JPanel {

    	// konstruktor     
    MyPanel()
    {
          // ustalenie rozmiar�w pocz�tkowych
        setPreferredSize(new Dimension(400,400));
     
          // ustalenie sposobu buforowania: 
          // true (domy�lnie):rysowanie odbywa si� w buforze 
          // pami�ci, bufor ten jest wy�wietlany na ekranie od razu w ca�o�ci
          // dopiero po wyj�ciu sterowania z funkcji paintComponent.  
        RepaintManager.currentManager(this).
                setDoubleBufferingEnabled(true);        
    }
    
    public void paintComponent(Graphics g)
    {
          // musi by� jako pierwsza instrukcja
        super.paintComponent(g);
        
          // pobranie aktualnych rozmiar�w
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

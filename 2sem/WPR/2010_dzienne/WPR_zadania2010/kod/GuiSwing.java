import java.awt.*;
import javax.swing.*;

public class GuiSwing extends JFrame {

	public GuiSwing()
	{
	    // jakie� dzialania inicjacyjne, nie zwi�zane z komponentami Swing
	    // ...
	
	    // budujemy okno;
	    // poniewa� jest to dzia�anie na komponentach wizualnych
	    // - zrobimy to w w�tku obs�ugi zdarze�
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() 
			{
				createGui();
			}
		});
	}

	protected void createGui() 
	{
	    	// ustalenie tytu�u okna
	    setTitle("Okno aplikacji");
	
	    	// ustalenie rozk�adu - je�li trzeba, np:
	    setLayout(new FlowLayout());
	
	    	// tworzenie komponent�w np.
	    JLabel lab = new JLabel("Etykieta");
	    JButton b = new JButton("Przycisk");
	
	    	// Ustalenie w�asciwo�ci komponent�w,
	    	// np:
	    lab.setForeground(Color.red);
	    b.setForeground(Color.blue);
	
	    	// Dodanie komponent�w do okna np.
	    add(lab);
	    add(b);
	
	    	// Ustalenie domy�lnej operacji zamkni�cia okna
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	    	// ustalenie rozmiar�w okna, np.:
	    pack();
	
	    	// ustalenie po�o�enia okna np. wycentrowanie
	
	    setLocationRelativeTo(null);
	
	    	// pokazanie okna
	    setVisible(true);
	}

	public static void main(String[] args) 
	{
		new GuiSwing();
	}
 
}
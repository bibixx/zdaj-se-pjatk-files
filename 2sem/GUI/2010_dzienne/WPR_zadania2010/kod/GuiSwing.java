import java.awt.*;
import javax.swing.*;

public class GuiSwing extends JFrame {

	public GuiSwing()
	{
	    // jakieœ dzialania inicjacyjne, nie zwi¹zane z komponentami Swing
	    // ...
	
	    // budujemy okno;
	    // poniewa¿ jest to dzia³anie na komponentach wizualnych
	    // - zrobimy to w w¹tku obs³ugi zdarzeñ
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
	    	// ustalenie tytu³u okna
	    setTitle("Okno aplikacji");
	
	    	// ustalenie rozk³adu - jeœli trzeba, np:
	    setLayout(new FlowLayout());
	
	    	// tworzenie komponentów np.
	    JLabel lab = new JLabel("Etykieta");
	    JButton b = new JButton("Przycisk");
	
	    	// Ustalenie w³asciwoœci komponentów,
	    	// np:
	    lab.setForeground(Color.red);
	    b.setForeground(Color.blue);
	
	    	// Dodanie komponentów do okna np.
	    add(lab);
	    add(b);
	
	    	// Ustalenie domyœlnej operacji zamkniêcia okna
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	    	// ustalenie rozmiarów okna, np.:
	    pack();
	
	    	// ustalenie po³o¿enia okna np. wycentrowanie
	
	    setLocationRelativeTo(null);
	
	    	// pokazanie okna
	    setVisible(true);
	}

	public static void main(String[] args) 
	{
		new GuiSwing();
	}
 
}
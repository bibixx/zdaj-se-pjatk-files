
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.BasicStroke;


public class paint{
	public static void main(String[] args){
		Icon ikonaNiebieski = new ImageIcon("niebieski.gif");
		Icon ikonaMagenta = new ImageIcon("magenta.gif");
		Icon ikonaCzerwony = new ImageIcon("czerwony.gif");
		Icon ikonaCzarny = new ImageIcon("czarny.gif");
		Icon ikonaZielony = new ImageIcon("zielony.gif");
		Icon ikonaSzary = new ImageIcon("szary.gif");
		Icon ikonaPomaranczowy = new ImageIcon("pomaranczowy.gif");
		Icon ikonaBrazowy = new ImageIcon("brazowy.gif");
		Icon ikonaGumka = new ImageIcon("gumka.gif");
		
		JFrame frame = new JFrame("Prosty Paint");
		
		Container content = frame.getContentPane();
		//tworzymy kontener
		content.setLayout(new BorderLayout());
		//tworzymy layout
		
		final ObszarRoboczy ObszarRoboczy = new ObszarRoboczy();
		//tworzymy panel do rysowania
		
		content.add(ObszarRoboczy, BorderLayout.CENTER);
		//i ustawiamy go na œrodku
		
		JPanel panel = new JPanel();
		//tworzymy Jpanel
		panel.setPreferredSize(new Dimension(32, 32));
		panel.setMinimumSize(new Dimension(32, 32));
		panel.setMaximumSize(new Dimension(32, 32));
		//Definiujemy jego wielkosc
		
		// ----------- PRZYCISKI ------------------------------------------
		
		
		JButton czyscP = new JButton("czysc");
		// tworzymy przycisk z etykieta czysc
		czyscP.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ObszarRoboczy.clear();
			}
		});
		//dodajemy action listener do przycisku i wywolujemy metode clear()
		
		JButton czerwonyP = new JButton(ikonaCzerwony);
		//tworzymy przycisk czerwony i ikonke na nim
		czerwonyP.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ObszarRoboczy.czerwony();
			}

		});
		//jak powyzej tylko ze z metoda czerwony()
		
		JButton czarnyP = new JButton(ikonaCzarny);
		czarnyP.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ObszarRoboczy.czarny();
			}
		});
		
		JButton magentaP = new JButton(ikonaMagenta);
		magentaP.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ObszarRoboczy.magenta();
			}
		});
		
		JButton niebieskiP = new JButton(ikonaNiebieski);
		niebieskiP.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ObszarRoboczy.niebieski();
			}
		});
		
		JButton zielonyP = new JButton(ikonaZielony);
		zielonyP.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ObszarRoboczy.zielony();
			}
		});
		
		JButton pomaranczowyP = new JButton(ikonaPomaranczowy);
		pomaranczowyP.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ObszarRoboczy.pomaranczowy();
			}
		});
		
		JButton szaryP = new JButton(ikonaSzary);
		szaryP.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ObszarRoboczy.szary();
			}
		});
		
		JButton brazowyP = new JButton(ikonaBrazowy);
		brazowyP.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ObszarRoboczy.brazowy();
			}
		});
		
		JButton gumkaP = new JButton(ikonaGumka);
		gumkaP.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ObszarRoboczy.gumka();
			}
		});
		
		 // wielkosc przyciskow kolorow
		czarnyP.setPreferredSize(new Dimension(16, 16));
		magentaP.setPreferredSize(new Dimension(16, 16));
		czerwonyP.setPreferredSize(new Dimension(16, 16));
		niebieskiP.setPreferredSize(new Dimension(16, 16));
		zielonyP.setPreferredSize(new Dimension(16,16));
		pomaranczowyP.setPreferredSize(new Dimension(16,16));
		szaryP.setPreferredSize(new Dimension(16,16));
		brazowyP.setPreferredSize(new Dimension(16,16));
		gumkaP.setPreferredSize(new Dimension(16,16));
		
		//dodajemy przyciski do panelu
		panel.add(zielonyP);
		panel.add(niebieskiP);
		panel.add(magentaP);
		panel.add(czarnyP);
		panel.add(czerwonyP);
		panel.add(pomaranczowyP);
		panel.add(szaryP);
		panel.add(brazowyP);
		panel.add(gumkaP);
		panel.add(czyscP);
		
		
		content.add(panel, BorderLayout.SOUTH);
		//panel na dole
		
		frame.setSize(500, 500);
		//wielkosc aplikacji
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//przycisk zamykania
		frame.setVisible(true);
		//no i zeby byla widoczna
	}
}


class ObszarRoboczy extends JComponent{
	Image image; // to bedzie powstalal nasz obraz
	Graphics2D graphics2D; //jest to co bêdziemy u¿ywaæ do rysowania
	int currentX, currentY, oldX, oldY; // zmienne dla myszki, obecna pozycja i poprzednie

	
	
	//Konstruktory
	public ObszarRoboczy(){
		setDoubleBuffered(false);
		addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				oldX = e.getX();
				oldY = e.getY();
			}
		});
		//Kiedy mycha jest wcisnieta ustawia ich pozycje na oldy
		//koordynaty x i y myszki
		addMouseMotionListener(new MouseMotionAdapter(){
			public void mouseDragged(MouseEvent e){
				currentX = e.getX();
				currentY = e.getY();
				if(graphics2D != null)
				graphics2D.drawLine(oldX, oldY, currentX, currentY);
				repaint();
				oldX = currentX;
				oldY = currentY;
			}

		});
		// ruch myszki powoduje nadanie obecnych stanow XiY a nastepnie rysowanie linii po tych stanach
		//metoda repaint ustawia oldy na currenty
	}

	public void paintComponent(Graphics g){
		if(image == null){
			image = createImage(getSize().width, getSize().height);
			graphics2D = (Graphics2D)image.getGraphics();
			graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			clear();

		}
		g.drawImage(image, 0, 0, null);
	}
	// to jest trochê malarstwo
	// jeœli nie ma to nic na to nastêpnie
	// tworzy obrazek wielkoœci okna
	// ustawia wartoœæ Graphics jako obraz
	// ustawia renderowanie
	// uruchamia clear () metoda
	// nastêpnie rysuje obraz


	public void clear(){
		graphics2D.setPaint(Color.white);
		graphics2D.fillRect(0, 0, getSize().width, getSize().height);
		graphics2D.setPaint(Color.black);
		repaint();
	}
	// metoda czysczenia. ustawia kolor bialy, wypelnie okno bialym i ustawia pedzel na czarny.

	// -------------------- KOLORY
	
	public void czerwony(){
		graphics2D.setPaint(Color.red);
		graphics2D.setStroke(new BasicStroke(1));
		repaint();
	}
	
	public void czarny(){
		graphics2D.setPaint(Color.black);
		graphics2D.setStroke(new BasicStroke(1));
		repaint();
	}
	
	public void magenta(){
		graphics2D.setPaint(Color.magenta);
		graphics2D.setStroke(new BasicStroke(1));
		repaint();
	}
	
	public void niebieski(){
		graphics2D.setPaint(Color.blue);
		graphics2D.setStroke(new BasicStroke(1));
		repaint();
	}
	
	public void zielony(){
		graphics2D.setPaint(Color.green);
		graphics2D.setStroke(new BasicStroke(1));
		repaint();
	}
	
	public void pomaranczowy(){
		graphics2D.setPaint(Color.orange);
		graphics2D.setStroke(new BasicStroke(1));
		repaint();
	}
	
	public void szary(){
		graphics2D.setPaint(Color.gray);
		graphics2D.setStroke(new BasicStroke(1));
		repaint();
	}
	
	public void brazowy(){
		graphics2D.setPaint(Color.yellow);
		graphics2D.setStroke(new BasicStroke(1));
		repaint();
	}
	public void gumka() {
		graphics2D.setPaint(Color.white);
		graphics2D.setStroke(new BasicStroke(15));
		repaint();
		
	}
	

}


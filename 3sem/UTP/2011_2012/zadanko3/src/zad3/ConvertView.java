package zad3;

import javax.swing.*;
import java.awt.event.*;
import java.awt.FlowLayout;

import java.io.File;
import java.nio.charset.Charset;

public class ConvertView extends JFrame {

	private static final long serialVersionUID = 1L;

	//	lista dostepnych kodowan
	private static final Object[] KODOWANIA = Charset.availableCharsets().keySet().toArray();
	//	kododwanie domyœlne
	private static final String KODOWANIE_DOMYSLNE = "windows-1250";

	//	pola: na sciezki: in i out
	protected JTextField JpathIn, JpathOut;

	//	sciezka do pliku
	protected File pathInF;

	//	guziki: do wybierania i resetu
	protected JButton choosePathB, resetB, convertB;

	//	filechooser
	protected JFileChooser choosePathF = new JFileChooser("c:");

	//	combobox'y kodowania: in i out
	protected JComboBox encodeIn, encodeOut;

	public ConvertView() {
		super("Zadanie 3");
		setView();
		//	przy klikniêciu na guzik zamkniêcia zamykaj program
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	private void setView() {
		//	ustaw layout na Flow
		setLayout(new FlowLayout());

		//	guzik do wyboru pliku
		add(choosePathB = new JButton("wska¿ plik lub wpisz œcie¿kê:"));

		//	akcja wyboru
		choosePathB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnVal = choosePathF.showOpenDialog(null); 
				//	otwiera okno
				if (returnVal == 0) {
				//	jesli kliknieto na wybor
					pathInF = choosePathF.getSelectedFile();
					//	sciezka do pliku
					
					if (pathInF.exists() && pathInF.isFile()) {
						//	czy jest plikiem
						JpathIn.setText(pathInF.toString());
						//	okno in
						JpathOut.setText(pathInF.toString());
						//	okno out
						JpathIn.setEnabled(false);
						//	disabled in - poniwa¿ wybrano dobry plikW in
					}
				}
			}
		});

		//	sciezka in
		add(JpathIn = new JTextField(20));
		//	dostepne kodowania in
		add(encodeIn = new JComboBox(KODOWANIA));
		//	ustaw kodowanie domyslne na zdefiniowane
		encodeIn.setSelectedItem(KODOWANIE_DOMYSLNE);

		//	guzik resetuj¹cy wprowadzone dane
		add(resetB = new JButton("resetuj"));
		//	akcja dla guzika resetuj¹cego
		resetB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clean(false);
			}
		});
		
		//	sciezka out
		add(JpathOut = new JTextField(20));
		//	dostepne kodowania in
		add(encodeOut = new JComboBox(KODOWANIA));
		//	ustaw kodowanie domyslne na zdefiniowane
		encodeOut.setSelectedItem(KODOWANIE_DOMYSLNE);

		//	guzik konwertuj
		add(convertB = new JButton("konwertuj"));
		//	akcja dla guzika konwertuj
		convertB.addActionListener(new ConvertControl(this));
	}

	//	funkcja resetuj¹ca
	public void clean(boolean converted) {
		//	boolean - w zale¿noœci od wywo³ania
		if (converted) {
			//	wywo³ywane, jeœli clean by³o po pomyœlnym konwertowaniu
			JOptionPane.showMessageDialog(null, "przekonwertowano pomyœlnie!");
		} else {
			//	wywo³anie, jeœli clean by³o po klikniêciu guzika resetuj¹cego
			JOptionPane.showMessageDialog(null, "b³¹d lub zresetowano");
		}
		//	ustawia œcie¿kê in na pust¹
		JpathIn.setText("");
		//	ustawia œcie¿kê out na pust¹
		JpathOut.setText("");
		//	ustawia kododowanie dla in na domyslnie zdefiniowane
		encodeIn.setSelectedItem(KODOWANIE_DOMYSLNE);
		//	ustawia kododowanie dla in na domyslnie zdefiniowane
		encodeOut.setSelectedItem(KODOWANIE_DOMYSLNE);
		//	odblokowuje œcie¿kê in
		JpathIn.setEnabled(true);
	}
}
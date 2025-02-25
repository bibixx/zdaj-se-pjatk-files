package zad3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class ConvertControl implements ActionListener {

	private static ConvertView cView;
	private static boolean converted = false;

	public ConvertControl(ConvertView cv) {
		cView = cv;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//	plik in
		File inFT = new File(cView.JpathIn.getText());
		//	plik out
		File outFT = new File(cView.JpathOut.getText());
		//	kodowanie in (dekodowanie)
		String decode = cView.encodeIn.getSelectedItem().toString();
		//	kodowanie out
		String encode = cView.encodeOut.getSelectedItem().toString();
		
		//	pr�buje przekonwertowa�
		if (Convert(inFT, outFT, decode, encode)) {
			// 	je�li przekonwertowano czy�ci okno z komunikatem, �e ok
			cView.clean(true);
		} else {
			//	je�li by� b��d czy�ci okno i wy�wietla, �e �le
			cView.clean(false);
		}
	}

	//	funkcja konwertuj�ca
	public static boolean Convert(File in, File out, String decode, String encode) {
		//	czyta
		Reader rIn = null;
		//	zapisuje
		Writer wOut = null;
		converted = false;
		//	pr�buje zapisa�
		try {
			//	�r�d�o in, dekodowanie
			rIn = new InputStreamReader(new FileInputStream(in), decode);
			//	�cie�ka out, kodowowanie
			wOut = new OutputStreamWriter(new FileOutputStream(out), encode);
			//	zmienna do przepisywania pliku
			int c;
			//	przepisuje plik znak, po znaku
			while ((c = rIn.read()) != -1) {
				wOut.write(c);
			}
			//	zamknij �r�d�o
			rIn.close();
			//	zamknij wyj�cie
			wOut.close();
			//	przekonwertowano pomy�lnie
			converted = true;
		} catch (IOException e1) {
		} finally {	//	je�li si� nie uda
			try {
				//	zamknij �r�d�o
				rIn.close();
				//	zamknij wyj�cie
				wOut.close();
			} catch (IOException e2) {
			} catch (NullPointerException e3) {
			}
		}
		return converted;
	}
}

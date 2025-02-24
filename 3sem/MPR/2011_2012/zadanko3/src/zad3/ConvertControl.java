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
		
		//	próbuje przekonwertowaæ
		if (Convert(inFT, outFT, decode, encode)) {
			// 	jeœli przekonwertowano czyœci okno z komunikatem, ¿e ok
			cView.clean(true);
		} else {
			//	jeœli by³ b³¹d czyœci okno i wyœwietla, ¿e Ÿle
			cView.clean(false);
		}
	}

	//	funkcja konwertuj¹ca
	public static boolean Convert(File in, File out, String decode, String encode) {
		//	czyta
		Reader rIn = null;
		//	zapisuje
		Writer wOut = null;
		converted = false;
		//	próbuje zapisaæ
		try {
			//	Ÿród³o in, dekodowanie
			rIn = new InputStreamReader(new FileInputStream(in), decode);
			//	œcie¿ka out, kodowowanie
			wOut = new OutputStreamWriter(new FileOutputStream(out), encode);
			//	zmienna do przepisywania pliku
			int c;
			//	przepisuje plik znak, po znaku
			while ((c = rIn.read()) != -1) {
				wOut.write(c);
			}
			//	zamknij Ÿród³o
			rIn.close();
			//	zamknij wyjœcie
			wOut.close();
			//	przekonwertowano pomyœlnie
			converted = true;
		} catch (IOException e1) {
		} finally {	//	jeœli siê nie uda
			try {
				//	zamknij Ÿród³o
				rIn.close();
				//	zamknij wyjœcie
				wOut.close();
			} catch (IOException e2) {
			} catch (NullPointerException e3) {
			}
		}
		return converted;
	}
}

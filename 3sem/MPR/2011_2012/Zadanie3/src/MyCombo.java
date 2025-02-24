import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;


public class MyCombo extends DefaultComboBoxModel<Charset> {

	ArrayList<Charset> lista;
	private static final long serialVersionUID = 1L;
	MyCombo(){
		Map<String, Charset> m = Charset.availableCharsets();
		m.values();
		 lista = new ArrayList<Charset>(m.values());
		 for(int i=0; i<lista.size(); i++){
			 addElement(lista.get(i));
		 }
	}
}

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.charset.Charset;

import javax.swing.JComboBox;

public class ActionConvert implements ActionListener {
	private PanelChooser source;
	private PanelChooser target;
	private JComboBox<Charset> jcb1;
	private JComboBox<Charset> jcb2;
	ActionConvert(PanelChooser s, PanelChooser t, JComboBox<Charset> j1,JComboBox<Charset> j2){
		source=s;
		target=t;
		jcb1=j1;
		jcb2=j2;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (target.getFile().isFile() && !target.getFile().exists()) {
			try {
				target.getFile().createNewFile();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
		if (source.getFile().isFile() && source.getFile().exists()
				&& target.getFile().isFile()) {
			Converter c = new Converter(source.getFile(), target.getFile(),
					(Charset) jcb1.getSelectedItem(),
					(Charset) jcb2.getSelectedItem());
			try {
				c.convertNow();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}

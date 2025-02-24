import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JTextField;

public class ActionChooser implements ActionListener {
	Component parent;
	JTextField text;

	ActionChooser(Component c, JTextField jtf) {
		parent = c;
		text = jtf;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JFileChooser chooser;
		if (!text.getText().isEmpty()) {
			File crruent = new File(text.getText());
			if (crruent.isFile() && crruent.exists())
				chooser = new JFileChooser(crruent);
			else
				chooser = new JFileChooser();
		} else
			chooser = new JFileChooser();
		String[] list = { "txt" };
		SimpleFileFilter filter = new SimpleFileFilter(list, ".txt");
		chooser.setFileFilter(filter);
		chooser.setAcceptAllFileFilterUsed(false);
		int returnVal = chooser.showSaveDialog(parent);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File fileToSave = chooser.getSelectedFile();
			text.setText(fileToSave.getAbsolutePath());
		}
	}

}

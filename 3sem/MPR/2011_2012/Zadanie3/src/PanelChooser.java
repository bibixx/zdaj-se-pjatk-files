import java.awt.GridLayout;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PanelChooser extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton jb = new JButton("Wybierz");
	JTextField jtf = new JTextField();

	PanelChooser(String text) {
		setLayout(new GridLayout(1, 3));

		JLabel jl = new JLabel(text);
		jl.setHorizontalAlignment(SwingConstants.RIGHT);
		add(jl);
		add(jtf);
		jb.addActionListener(new ActionChooser(this, jtf));
		add(jb);
	}

	public File getFile() {
		return new File(jtf.getText());
	}
}

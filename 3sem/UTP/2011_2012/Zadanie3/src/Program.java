import java.awt.GridLayout;

import java.awt.Dimension;
import java.nio.charset.Charset;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Program extends JFrame {

	private static final long serialVersionUID = 1L;
	JComboBox<Charset> jcb2;
	JComboBox<Charset> jcb1;
	PanelChooser source = new PanelChooser("èrÛd≥o: ");
	PanelChooser target = new PanelChooser("Cel: ");

	public Program() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setView();
		pack();
		setVisible(true);
	}

	private void setView() {
		setLayout(new GridLayout(4, 1));
		JPanel jp = new JPanel(new GridLayout(1, 2));
		jp.add(source);
		jp.add(target);
		Box b = Box.createVerticalBox();
		jp.setMaximumSize(new Dimension(600, 20));
		b.add(Box.createGlue());
		b.add(jp);
		jcb1 = new JComboBox<Charset>();
		jcb1.setModel(new MyCombo());
		jcb2 = new JComboBox<Charset>();
		jcb2.setModel(new MyCombo());
		add(b);
		add(jcb1);
		add(jcb2);
		JButton jb2 = new JButton("Konwertuj");
		jb2.addActionListener(new ActionConvert(source, target, jcb1, jcb2));
		add(jb2);

	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Program();
			}
		});
	}

}

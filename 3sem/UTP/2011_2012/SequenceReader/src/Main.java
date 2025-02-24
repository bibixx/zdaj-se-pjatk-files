import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private static JTextArea jta = new JTextArea();
	private static JTextArea jtaErrors = new JTextArea();
	private static ArrayList<Thread> threads = new ArrayList<Thread>();

	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setView();
		pack();
		setVisible(true);
	}

	private void setView() {
		setLayout(new GridLayout(2, 2));
		JScrollPane jsp = new JScrollPane(jta);
		jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jsp.setAlignmentX(Component.CENTER_ALIGNMENT);
		jsp.getViewport().setViewPosition(new java.awt.Point(0, 0));
		add(jsp);
		JScrollPane jspError = new JScrollPane(jtaErrors);
		jspError.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		jspError.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jspError.setAlignmentX(Component.CENTER_ALIGNMENT);
		jspError.getViewport().setViewPosition(new java.awt.Point(0, 0));
		jtaErrors.setEditable(false);
		add(jspError);
		JButton jb = new JButton("Count");
		jb.addActionListener(new Actions(jta,jtaErrors));
		add(jb);

		setMinimumSize(new Dimension(600, 300));
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Main();
			}
		});
	}

}

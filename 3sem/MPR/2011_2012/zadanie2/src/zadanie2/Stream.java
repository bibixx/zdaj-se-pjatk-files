package zadanie2;

import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

public class Stream extends JFrame
{

	private static final long serialVersionUID = 1L;
	private static JTextArea area1 = new JTextArea();
	private static JTextArea area2 = new JTextArea();

	public Stream()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setView();
		pack();
		setVisible(true);
	}

	private void setView()
	{
		setLayout(new GridLayout(2, 2));		
		JScrollPane pane1 = new JScrollPane(area1);
		pane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		pane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		pane1.setAlignmentX(Component.CENTER_ALIGNMENT);
		area1.setBackground(Color.BLUE);
				
		JScrollPane pane2 = new JScrollPane(area2);
		pane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		pane2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		pane2.setAlignmentX(Component.CENTER_ALIGNMENT);
		area2.setBackground(Color.RED);		
		
		ImageIcon ico = new ImageIcon("smile.gif");
		JButton b = new JButton("Skanuj", ico);
		b.setBackground(Color.WHITE);
		b.addActionListener(new ActionListenerPlik(area1,area2));
		
		add(pane1);
		add(pane2);
		add(b);
		
		area2.setEditable(false);
		setLocation(0,0);
		setMinimumSize(new Dimension(500, 400));
	}

	public static void main(String[] args)
	{

		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				new Stream();
			}
		});
	}

}


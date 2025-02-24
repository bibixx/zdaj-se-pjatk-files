import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class Program extends JFrame {

	private static final long serialVersionUID = 1L;
	public static MessageList ml=new MessageList();
	public static QueueListTableModel qlm=new QueueListTableModel(ml);
	JTable lista = new JTable(qlm);
	
	public Program() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setView();
		pack();
		setVisible(true);
		new Thread(new Klient(new Message(10,20, 1))).start();
		new Thread(new Klient(new Message(10,20, 5))).start();
		new Thread(new Klient(new Message(10,20, 4))).start();
		new Thread(new Klient(new Message(10,20, 3))).start();
		new Thread(new Klient(new Message(10,20, 1))).start();
		new Thread(new Klient(new Message(10,20, 3))).start();
		new Thread(new Klient(new Message(10,20, 2))).start();
		new Thread(new Klient(new Message(10,20, 1))).start();
		new Thread(new Klient(new Message(10,20, 1))).start();
		new Thread(new Klient(new Message(10,20, 5))).start();
		new Thread(new Klient(new Message(10,20, 4))).start();
		new Thread(new Klient(new Message(10,20, 3))).start();
		new Thread(new Klient(new Message(10,20, 1))).start();
		new Thread(new Klient(new Message(10,20, 3))).start();
		new Thread(new Klient(new Message(10,20, 2))).start();
		new Thread(new Klient(new Message(10,20, 1))).start();
		new Thread(new Producent()).start();
		new Thread(new Producent()).start();
		new Thread(new Producent()).start();
	}

	private void setView() {
		setLayout(new GridLayout(1, 1));
		setPreferredSize(new Dimension(400, 400));
		add(lista);

	}
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Program();
			}
		});
	}

}
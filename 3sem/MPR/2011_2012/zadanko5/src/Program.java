import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.logging.ConsoleHandler;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class Program extends JFrame {

	private static final long serialVersionUID = 1L;
	public static MessageList ml=new MessageList();
	public static QueueListTableModel qlm=new QueueListTableModel(ml);
	JTable list = new JTable(qlm);
	
	public Program() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setView();
		pack();
		setVisible(true);
		//tworzy klientow i producentow
		new Thread(new Client(new Message(1,2, 1))).start();
		new Thread(new Client(new Message(5,7, 1))).start();
		new Thread(new Client(new Message(1,5, 2))).start();
		new Thread(new Client(new Message(11,8, 2))).start();
		new Thread(new Client(new Message(6,0, 3))).start();
		new Thread(new Client(new Message(44,0, 3))).start();
		new Thread(new Client(new Message(16,0, 4))).start();
		new Thread(new Client(new Message(3,20, 4))).start();
		new Thread(new Client(new Message(12,30, 5))).start();
		new Thread(new Client(new Message(8,22, 5))).start();
		new Thread(new Client(new Message(1,23, 1))).start();
		new Thread(new Client(new Message(1,2, 5))).start();
		new Thread(new Client(new Message(4,27, 3))).start();
		new Thread(new Client(new Message(17,24, 2))).start();
		new Thread(new Client(new Message(1,23, 2))).start();
		new Thread(new Client(new Message(10,1, 1))).start();
		
		new Thread(new Producent()).start();
		new Thread(new Producent()).start();
		new Thread(new Producent()).start();
		new Thread(new Producent()).start();
	}

	private void setView() {
		setTitle("Client<>Producer");
		setLayout(new GridLayout(1, 1));
		setPreferredSize(new Dimension(400, 400));
		add(list);

	}
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Program();
			}
		});
	}

}
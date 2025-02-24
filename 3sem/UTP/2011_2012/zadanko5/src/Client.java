import java.util.Random;

import javax.swing.event.TableModelEvent;

public class Client implements Runnable {

	Message msg;
	private static int idClient=0;
	private int id;
	
	private static Random rand = new Random();
	//klient robiony z messaga
	Client(Message m) {
		msg = m;
		idClient++;
		id=idClient;
	}

	@Override
	public void run() {
		System.out.println("Client Start");
		Message ms;
		
		boolean end =true;
	
		while (end) {
			try {
				Thread.sleep(rand.nextInt(1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (msg.done == 0) {
				Program.ml.add(msg);
				msg.done = 2;
				
				System.out.println("Client "+id+" : Send " + msg.id);
				Program.qlm.fireTableChanged(new TableModelEvent(new QueueListTableModel(new MessageList())));
			} else {
				ms = Program.ml.peek();
				if (ms!=null && ms.id == msg.id && ms.done == 1) {
					System.out.println("Client "+id+" : Recieve "
							+ msg.id);
					Program.ml.remove(ms);
					msg.done = 1;
					Program.qlm.fireTableChanged(new TableModelEvent(new QueueListTableModel(new MessageList())));
					
					end=false;
					
				}
			}
			
		}
	}

}

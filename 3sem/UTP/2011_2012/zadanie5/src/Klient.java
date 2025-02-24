import java.util.Random;

import javax.swing.event.TableModelEvent;

public class Klient implements Runnable {

	Message msg;
	private static int nrKlienta=0;
	private int nr;
	
	private static Random rand = new Random();
	Klient(Message m) {
		msg = m;
		nrKlienta++;
		nr=nrKlienta;
	}

	@Override
	public void run() {
		System.out.println("start Klient");
		Message ms;
		
		boolean end =true;
		while (end) {
			try {
				Thread.sleep(rand.nextInt(1000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (msg.done == 0) {
				Program.ml.add(msg);
				msg.done = 2;
				
				System.out.println("Klient"+nr+":wys³a³em wiadomosc" + msg.id);
				Program.qlm.fireTableChanged(new TableModelEvent(new QueueListTableModel(new MessageList())));
			} else {
				ms = Program.ml.peek();
				if (ms!=null && ms.id == msg.id && ms.done == 1) {
					System.out.println("Klient"+nr+":otrzyma³em spowrotem wiadomosc"
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

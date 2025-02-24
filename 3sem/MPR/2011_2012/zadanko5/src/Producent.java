import java.util.Random;

import javax.swing.event.TableModelEvent;

public class Producent implements Runnable {
	private static Random rand = new Random();
	private static int idProducer=0;
	private int id;
	Producent() {
		idProducer++;
		id=idProducer;
	}

	@Override
	public void run() {
		System.out.println("Producer Start "+id);
		Message ms;
		//producent dodaje dane klienta 
		while (true) {
			try {
				Thread.sleep(rand.nextInt(1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			ms = Program.ml.peek();
			if (ms != null && ms.done==2) {
				System.out.println("Producer "+id+" : Recieve : " + ms.id
						+ " result:" + (ms.field1 + ms.field2)); //tutej pokazuje wartosc zsumowana to co klient wyslal
				Program.ml.remove(ms);
				ms.done = 1;
				System.out.println("Producer "+id+" : Replay : " + ms.id);
				Program.ml.add(ms);
				Program.qlm.fireTableChanged(new TableModelEvent(new QueueListTableModel(new MessageList())));
				
			}
		}
	}

}

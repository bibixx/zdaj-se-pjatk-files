import java.util.Random;

import javax.swing.event.TableModelEvent;

public class Producent implements Runnable {
	private static Random rand = new Random();
	private static int nrProducenta=0;
	private int nr;
	Producent() {
		nrProducenta++;
		nr=nrProducenta;
	}

	@Override
	public void run() {
		System.out.println("start Producent"+nr);
		Message ms;
		while (true) {
			try {
				Thread.sleep(rand.nextInt(1000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ms = Program.ml.peek();
			if (ms != null && ms.done==2) {
				System.out.println("Producent"+nr+":przetowrzy³em wiadomosc: " + ms.id
						+ " wynik:" + (ms.pole1 + ms.pole2));
				Program.ml.remove(ms);
				ms.done = 1;
				System.out.println("Producent"+nr+":odes³alem wiadomoœæ: " + ms.id);
				Program.ml.add(ms);
				Program.qlm.fireTableChanged(new TableModelEvent(new QueueListTableModel(new MessageList())));
				
			}
		}
	}

}

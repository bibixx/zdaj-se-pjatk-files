import java.util.Comparator;
import java.util.PriorityQueue;
//tu jest import PriorityQueue
public class MessageList extends PriorityQueue<Message> {
	private static final long serialVersionUID = 1L;
	private static final ComparatorMessage c=new ComparatorMessage();
	//kolejkowanie
	@Override
	public synchronized boolean add(Message e) {
		boolean t=super.add(e);
		return t;
	}

	@Override
	public synchronized Message peek() {
		Message t=super.peek();
		return t;
	}

	@Override
	public synchronized Message poll() {
		Message t=super.poll();
		return t;
	}

	@Override
	public synchronized boolean remove(Object o) {
		boolean t= super.remove(o);
		return t;
	}

	@Override
	public Comparator<? super Message> comparator() {
		return c;
	}

}

import java.util.Comparator;
import java.util.PriorityQueue;

public class MessageList extends PriorityQueue<Message> {
	private static final long serialVersionUID = 1L;
	private static final ComparatorMessage c=new ComparatorMessage();
	@Override
	public synchronized boolean add(Message e) {
		// TODO Auto-generated method stub
		boolean t=super.add(e);
		return t;
	}

	@Override
	public synchronized Message peek() {
		// TODO Auto-generated method stub
		Message t=super.peek();
		return t;
	}

	@Override
	public synchronized Message poll() {
		// TODO Auto-generated method stub 
		Message t=super.poll();
		return t;
	}

	@Override
	public synchronized boolean remove(Object o) {
		// TODO Auto-generated method stub
		boolean t= super.remove(o);
		return t;
	}

	@Override
	public Comparator<? super Message> comparator() {
		// TODO Auto-generated method stub
		return c;
	}

}

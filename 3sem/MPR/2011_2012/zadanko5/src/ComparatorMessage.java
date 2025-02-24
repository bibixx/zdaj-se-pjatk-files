import java.util.Comparator;


public class ComparatorMessage implements Comparator<Message>{
//priorytetowanie
	@Override
	public int compare(Message arg0, Message arg1) {
		if(arg0.Priority>arg1.Priority)
			return -1;
		if(arg0.Priority<arg1.Priority)
			return 1;
		if(arg0.id<arg1.id)
			return -1;
		if(arg0.id>arg1.id)
			return 1;
		return 0;
	}
	
}

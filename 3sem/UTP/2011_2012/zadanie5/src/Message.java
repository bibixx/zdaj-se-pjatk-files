public class Message implements Comparable<Message> {
	static int lastId=0;
	int Priority;
	int pole1;
	int pole2;
	int id;
	int done;
	private static ComparatorMessage c =new ComparatorMessage();
	Message(int p1, int p2, int p){
		pole1=p1;
		pole2=p2;
		lastId++;
		id=lastId;
		Priority = p;
		done=0;
	}
	@Override
	public int compareTo(Message o) {
		return c.compare(this,o);
	}
}

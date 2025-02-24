public class Message implements Comparable<Message> {
	//dane klienta
	static int lastId=0;
	int Priority;
	int field1;
	int field2;
	int id;
	int done;
	private static ComparatorMessage c =new ComparatorMessage();
	Message(int f1, int f2, int p){
		field1=f1;
		field2=f2;
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

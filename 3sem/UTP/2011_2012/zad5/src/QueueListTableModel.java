import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;

//tworzenie widocznej listy
public class QueueListTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private MessageList ml;
	public QueueListTableModel(MessageList ms){
		ml=ms;
	}
	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public int getRowCount() {
		return ml.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		if(arg0<ml.size()){
		Message m = (Message)(ml.toArray())[arg0];

		if(arg1==0)
			return "Client: " + m.id + " ; " + m.field1 + " + " + m.field2;
		else
			return "Priority: " + m.Priority;
		}else 
			return null;
	}

	@Override
	synchronized public void fireTableChanged(TableModelEvent arg0) { //te FireTable to odswiezania tabeli, to jest w paru miejscach jeszce w kodzie
		super.fireTableChanged(arg0);
	}

}

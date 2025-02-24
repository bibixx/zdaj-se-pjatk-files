import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;


public class QueueListTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private MessageList ml;
	public QueueListTableModel(MessageList ms){
		ml=ms;
	}
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return ml.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		if(arg0<ml.size()){
		Message m = (Message)(ml.toArray())[arg0];

		if(arg1==0)
			return m.id;
		else
			return m.Priority;
		}else 
			return null;
	}

	@Override
	synchronized public void fireTableChanged(TableModelEvent arg0) {
		// TODO Auto-generated method stub
		super.fireTableChanged(arg0);
	}

}

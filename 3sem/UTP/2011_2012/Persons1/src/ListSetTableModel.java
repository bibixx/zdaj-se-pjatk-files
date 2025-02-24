import java.io.File;
import java.io.IOException;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class ListSetTableModel extends AbstractTableModel {
	private String [] colNames = {"NAME","SURNAME","BIRTH DATE"};
	
	private static ListsSet sortedLists=new ListsSet();
	private static int sortType=Person.BY_NAME;

	public ListSetTableModel() {
		super();
	}

	public static void setSortType(int _sortType) {
		sortType = _sortType;
		if(sortType==Person.BY_CHOSEN_DATE)
			sortedLists.makeChosenDateList(GUI.getDate(), GUI.getSortType());
	}

	public static void update() {
		File file=new File(GUI.getFilePath());
		if (file.exists())
		{
			sortedLists=new ListsSet();
			try
			{
				sortedLists.read(file);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public int getColumnCount() {
			return 3;
	}
	@Override
	public boolean isCellEditable(int row, int col) {
		return false;
	}
	 @Override
	public String getColumnName(int column) {
		 return colNames[column];
	}
	@Override
	  public Class<?> getColumnClass(int c) {
		    return getValueAt(0, c).getClass();
		  }
	@Override
	public int getRowCount() {
		return sortedLists.getListAt(sortType).size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		return sortedLists.getListAt(sortType).get(arg0).getElementAt(arg1);
	}
	

}

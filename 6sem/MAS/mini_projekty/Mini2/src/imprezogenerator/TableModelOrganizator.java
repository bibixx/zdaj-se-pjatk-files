package imprezogenerator;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TableModelOrganizator extends AbstractTableModel 
{
	private static final long serialVersionUID = 1L;
	private Hashtable<Object,Object> dotabeli;
	private final int wiersze;
	private final int kolumny;
	private final String naglowki[] = {"ID Organizatora", "Specjalizacja Organizatora"};
	private List<?> anArrayList;
	
	public TableModelOrganizator(List<?> arrayList){
		anArrayList = arrayList;
		this.wiersze = anArrayList.size();
		this.kolumny = naglowki.length;
		dotabeli = new Hashtable<Object, Object>();

		for(int i=0; i<wiersze; i++)
		{
			Organizator organizator = (Organizator)anArrayList.get(i);
			dotabeli.put(new Point(i, 0), organizator.getID_ORGANIZATOR());
			dotabeli.put(new Point(i, 1), organizator.getSPECJALIZACJA());
		}
	}
	public int getColumnCount() 
	{
		return kolumny;
	}
	public int getRowCount() 
	{
		return wiersze;
	}  
	public String getColumnName(int column) 
	{
	    return naglowki[column];
	}
	public Object getValueAt(int rowIndex, int columnIndex) 
	{
		return dotabeli.get(new Point(rowIndex, columnIndex));
	}
}

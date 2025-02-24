package imprezogenerator;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TableModelImpreza extends AbstractTableModel 
{
	private static final long serialVersionUID = 1L;
	private Hashtable<Object,Object> dotabeli;
	private final int wiersze;
	private final int kolumny;
	private final String naglowki[] = {"ID Imprezy", "Nazwa Imprezy","ID Organizatora"};
	private List<?> anArrayList;
	
	public TableModelImpreza(List<?> arrayList)
	{
		anArrayList = arrayList;                      //lista rozmiarow tabeli do przechowywania danych
		this.wiersze = anArrayList.size();
		this.kolumny = naglowki.length;
		dotabeli = new Hashtable<Object, Object>();

		for(int i=0; i<wiersze; i++)
		{
			Impreza impreza = (Impreza)anArrayList.get(i);
			
			dotabeli.put(new Point(i, 0), impreza.getID_IMPREZA());            //wydobywanie ID imprezy
			dotabeli.put(new Point(i, 1), impreza.getNUMER_IMPREZY());         // wydobywanie NUMERU imprezy
			
			if(impreza.getID_ORGANIZATOR() != null)
				dotabeli.put(new Point(i, 2), impreza.getID_ORGANIZATOR());
			else
				dotabeli.put(new Point(i, 2), "");
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


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RepaintAndUpdateAction implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(ListsSet.isDateValid(GUI.getDate()))
			ListSetTableModel.setSortType(Person.BY_CHOSEN_DATE);
		else ListSetTableModel.setSortType(GUI.getSortType());
		GUI.repaintAndUpdate();
	}
}

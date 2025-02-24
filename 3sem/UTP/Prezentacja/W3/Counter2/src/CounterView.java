import javax.swing.*;
import java.awt.*;
import java.beans.*;
import java.io.*;

public class CounterView extends JLabel implements PropertyChangeListener, Serializable {

  CounterView()  {
     this("0");
  }	
  CounterView(String lab) {
     super(lab);
     setOpaque(true);
     setBorder(BorderFactory.createLineBorder(Color.black));
     setPreferredSize(new Dimension(75, 40));
     setHorizontalAlignment(CENTER);
  }   

  public void propertyChange(PropertyChangeEvent e)  {
    Integer oldVal = (Integer) e.getOldValue(),
           newVal = (Integer) e.getNewValue();
    System.out.println("Value changed from " + oldVal + " to " + newVal); 
    setText("" + newVal + "");
   }
   

}
	
	
//Klasa CounterView
//Widok licznika przedstawiamy w postaci etykiety

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;


public class CounterView extends JLabel implements PropertyChangeListener {


  // Konstruktor domyœlny: inicjalizuje etykietê tekstem "0"
  CounterView()  {
     this("0");
  }

  // Konstruktor inicjalizuj¹cy etykietê podanym tekstem
  CounterView(String lab) {
     super(lab);
     setOpaque(true);   // etykieta nie przezroczysta
       // ramka
     setBorder(BorderFactory.createLineBorder(Color.black));
       // rozmiary i wyrównanie tekstu
     setPreferredSize(new Dimension(75, 40));
     setHorizontalAlignment(CENTER);
  }

  // ob³uga zdarzenia PropertyChange
  public void propertyChange(PropertyChangeEvent e)  {
    Integer oldVal = (Integer) e.getOldValue(),
           newVal = (Integer) e.getNewValue();
    System.out.println("Value changed from " + oldVal + " to " + newVal);
    setText("" + newVal + "");  // pokazanie na etykiecie nowego stanu licznika
   }


}
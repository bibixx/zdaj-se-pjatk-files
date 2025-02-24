// Klasa Counter

import java.awt.event.*;
import java.beans.*;
import java.io.*;

public class Counter implements Serializable {

  private int count = 0;    // w³aœciwoœæ count

  // Pomocniczy obiekt do prowadzenia listy s³uchaczy zmian w³aœciwoœci oraz
  // propagowania zmian  wœród zarejestrowanych z³uchaczy
   private PropertyChangeSupport propertyChange = new PropertyChangeSupport(this);


  // Konstruktory

  public Counter() {
    this(0);
  }

  public Counter(int aCount) {
    setCount( aCount );
  }


  // Metody przy³¹czania i od³¹czania s³uchaczy zmian w³aœciwoœci

  public synchronized void addPropertyChangeListener(PropertyChangeListener listener) {
    propertyChange.addPropertyChangeListener(listener);
  }

  public synchronized void removePropertyChangeListener(PropertyChangeListener l) {
    propertyChange.removePropertyChangeListener(l);
  }

  // Proste metody zwiêkszania i zmniejszania licznika

  public void increment() {
    setCount(getCount()+1);
  }

  public void decrement() {
    setCount(getCount()-1);
  }


  // Getter w³aœciwoœci "count"
  public int getCount() {
    return count;
  }

  // Setter w³aœciowœci "count"
  public synchronized void setCount(int aCount) {
    int oldValue = count;
    count = aCount;

    // wywo³anie metody firePropertChange z klasy PropertyChangeSupport
    // powoduje wygenerowanie zdarzenia PropertyChangeEvent i rozpropagowanie
    // go wœród wszystkich przy³¹czonych s³uchaczy, ale tylko wtedy, gdy nowa
    // wartoœæ w³aœciwoœci ró¿ni siê od starej wartoœci

    propertyChange.firePropertyChange("count", new Integer(oldValue),
                                            new Integer(aCount));
  }

}
import java.awt.event.*;
import java.beans.*;
import java.io.*;

public class Counter implements Serializable {

  private int count = 0;
  private PropertyChangeSupport propertyChange = new PropertyChangeSupport(this);
  private VetoableChangeSupport vetos = new VetoableChangeSupport(this);

public Counter() throws PropertyVetoException {
  this(0);
}

public Counter(int aCount) throws PropertyVetoException {
  setCount( aCount );
}


public synchronized void addPropertyChangeListener(PropertyChangeListener listener) {
  propertyChange.addPropertyChangeListener(listener);
}

public synchronized void removePropertyChangeListener(PropertyChangeListener listener) {
  propertyChange.removePropertyChangeListener(listener);
}

public synchronized void addVetoableChangeListener(VetoableChangeListener l) {
  vetos.addVetoableChangeListener(l);
}

public synchronized void removeVetoableChangeListener(VetoableChangeListener l) {
  vetos.removeVetoableChangeListener(l);
}


public void increment() throws PropertyVetoException {
  setCount(getCount()+1);
}

public void decrement() throws PropertyVetoException {
  setCount(getCount()-1);
}

public int getCount() {
  return count;
}


public synchronized void setCount(int aCount)
                         throws PropertyVetoException {
  int oldValue = count;
  vetos.fireVetoableChange("count", new Integer(oldValue), new Integer(aCount));
  count = aCount;
  propertyChange.firePropertyChange("count", new Integer(oldValue), new Integer(aCount));
}
}
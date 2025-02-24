import java.beans.*;
public class Main {

 public static void main(String[] args) throws PropertyVetoException {
   Counter counter = new Counter();
   CounterView counterView = new CounterView(""+counter.getCount());
   counter.addPropertyChangeListener(counterView);
   CounterLimitator clim = new CounterLimitator(-5, 10);
   counter.addVetoableChangeListener(clim);
   CounterControlGui gui = new CounterControlGui(counter, counterView);
   gui.pack();
   gui.show();
  }
  
}
import java.beans.*;
public class Main {

 Counter counter;
 CounterView counterView;
 CounterLimitator clim;
 CounterControlGui gui;

 public Main() throws PropertyVetoException {
   installCounter(new Counter());
 }

 public void installCounter(Counter c) {
   if (counter != null) {
    counter.removePropertyChangeListener(counterView);
    counter.removeVetoableChangeListener(clim);
   }
   counter = c;
   if (counterView == null)
    counterView = new CounterView(""+counter.getCount());
   else counterView.setLabel(counter.getCount() + "" );
   counter.addPropertyChangeListener(counterView);
   if (clim == null) clim = new CounterLimitator(-5, 10);
   counter.addVetoableChangeListener(clim);
   if (gui == null) {
     gui = new CounterControlGui(counter, counterView);
   }
   else gui.setCounter(counter);
 }


 public static void main(String[] args) throws PropertyVetoException {
   new Main();
 }

}
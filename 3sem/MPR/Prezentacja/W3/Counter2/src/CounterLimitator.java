import java.beans.*;

public class CounterLimitator implements VetoableChangeListener {

private int min, max;

CounterLimitator(int minLim, int maxLim)  {
  min = minLim;
  max = maxLim;
}

public void vetoableChange(PropertyChangeEvent e)  
            throws PropertyVetoException {
   Integer newVal = (Integer) e.getNewValue();
   int val = newVal.intValue();
   if (val < min || val > max)  
      throw new PropertyVetoException("Niedopuszczalna zmiana wartoœci", e);
   }
}


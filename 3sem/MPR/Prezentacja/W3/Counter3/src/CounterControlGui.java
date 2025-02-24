import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.io.*;

public class CounterControlGui extends JFrame implements ActionListener {

private Counter counter;
private CounterView counterView;
private CounterLimitator clim;
private JButton binc = new JButton("Increment");
private JButton bdec = new JButton("Decrement");
private JTextField txt = new JTextField(10);
private JButton save = new JButton("Save");
private JButton load = new JButton("Load");

private static final String fname = "counter.xml";


CounterControlGui(Counter c) {
  installCounter(c);
  Container cp = getContentPane();
  cp.setLayout(new FlowLayout());
  binc.addActionListener(this);
  cp.add(binc);
  cp.add(counterView);
  bdec.addActionListener(this);
  cp.add(bdec);
  txt.addActionListener(this);
  cp.add(txt);
  save.addActionListener(this);
  cp.add(save);
  load.addActionListener(this);
  cp.add(load);
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  pack();
  show();
}


public void actionPerformed(ActionEvent e)  {
  try  {
    if (e.getSource() == txt)  {
       int n = 0;
       try  {
          n = Integer.parseInt(txt.getText());
       } catch (NumberFormatException exc)  { return; }
       counter.setCount(n);
       return;
    }
    String cmd = e.getActionCommand();
    if (cmd.equals("Increment")) counter.increment();
    else if (cmd.equals("Decrement")) counter.decrement();
    else if (cmd.equals("Save")) {
      try {
        XMLEncoder enc = new XMLEncoder(
                           new BufferedOutputStream(
                              new FileOutputStream(fname)
                              )
                        );
        enc.writeObject(counter);
        enc.close();
      } catch (Exception exc) {
          exc.printStackTrace();
      }
    }
    else if (cmd.equals("Load")) {
      try {
        XMLDecoder dec = new XMLDecoder(
                           new BufferedInputStream(
                              new FileInputStream(fname)
                              )
                        );
        Counter c = (Counter) dec.readObject();
        dec.close();
        installCounter(c);
      } catch (Exception exc) {
          exc.printStackTrace();
      }
    }
    else System.out.println("Unrecognized command");
  } catch (PropertyVetoException exc)  {
       System.out.println(""+ exc);
  }
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
 }


 public static void main(String[] args) throws PropertyVetoException {
   new CounterControlGui(new Counter());
 }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.io.*;

public class CounterControlGui extends JFrame implements ActionListener {

Counter counter;
JButton binc = new JButton("Increment");
JButton bdec = new JButton("Decrement");
JTextField txt = new JTextField(10);


CounterControlGui(Counter c, CounterView clab)  {
  counter = c;
  Container cp = getContentPane();
  cp.setLayout(new FlowLayout());
  binc.addActionListener(this);
  cp.add(binc);
  cp.add(clab);
  bdec.addActionListener(this); 
  cp.add(bdec);
  txt.addActionListener(this);
  cp.add(txt);
  setDefaultCloseOperation(3);
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
    else System.out.println("Unrecognized command");
  } catch (PropertyVetoException exc)  {
       System.out.println(""+ exc);
  }
}

}


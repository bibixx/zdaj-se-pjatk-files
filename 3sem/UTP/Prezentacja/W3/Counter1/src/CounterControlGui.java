Klasa CounterControlGui dostarcza dwóch przycisków (zwiêksz, zmniejsz licznik)
oraz pole tekstowe, w którym mo¿na wpisaæ wartoœæ licznika (ENTER)
Zarówno klikniêcie w przyciski jak i ENETR na polu tekstowym powoduje
powstanie zdarzenia Action, które tu (w tej klasie) obs³ugujemy
ustalaj¹c za pomoc¹ metod incremet() decrement() i setCount(...) z kalsy
 Counter wartoœci licznika

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

  // Konstruktor otrzymuje jako argumenty obiekty typu Counter i CounterView
  // Pierwszy jest nam potrzebny do komunikacji z licznikiem, drugi - widok
  // wbudujemy w to GUI.

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


  // Obs³uga akcji
  public void actionPerformed(ActionEvent e)  {
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
  }

}
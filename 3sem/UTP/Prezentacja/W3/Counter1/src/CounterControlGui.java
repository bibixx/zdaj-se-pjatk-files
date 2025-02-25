Klasa CounterControlGui dostarcza dw�ch przycisk�w (zwi�ksz, zmniejsz licznik)
oraz pole tekstowe, w kt�rym mo�na wpisa� warto�� licznika (ENTER)
Zar�wno klikni�cie w przyciski jak i ENETR na polu tekstowym powoduje
powstanie zdarzenia Action, kt�re tu (w tej klasie) obs�ugujemy
ustalaj�c za pomoc� metod incremet() decrement() i setCount(...) z kalsy
 Counter warto�ci licznika

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


  // Obs�uga akcji
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
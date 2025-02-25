
import java.lang.reflect.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ReflectionTest extends JFrame implements ActionListener {

  Method currAction = null; // bie��ca metoda obs�ugi
  Class actionClass = null; // klasa obs�ugi
  Object actionObject = null; // obiekt obs�ugi
  JPopupMenu popUp = null; // menu kontekstowe z wyborem obs�ugi
  JButton b;

  public ReflectionTest() {
    super("Test refleksji");
    try {
      actionClass = Class.forName("ActionSet");
      actionObject = actionClass.newInstance();
    } catch (Exception exc) {
      throw new RuntimeException("Wadliwa klasa obs�ugi");
    }

    popUp = new JPopupMenu();
    createMenuItems();

    b = new JButton("U�yj prawego klawisza myszki, by ustali� akcj�");
    b.setFont(new Font("Dialog", Font.BOLD, 24));
    b.addActionListener(this);
    b.setComponentPopupMenu(popUp); // u�atwienie z popup!!!

    setLayout(new FlowLayout());
    add(b);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }

  void createMenuItems() {
    Method mets[] = null;
    try {
      mets = actionClass.getMethods();
    } catch (Exception exc) {
      throw new RuntimeException("Niedost�pna info o metodach klasy obs�ugi");
    }

    for (Method m : mets) {
      if (m.getDeclaringClass() == actionClass) {
        String name = m.getName();
        JMenuItem mi = new JMenuItem(name);
        mi.addActionListener(this);
        popUp.add(mi);
      }
    }
  }

  void setCurrentAction(String action) {
    try {
      currAction = actionClass.getMethod(action); // zm. liczba arg.!!!
      b.setText("Teraz akcj� jest: " + currAction.getName() + " - kliknij!");
    } catch (Exception exc) {
      throw new RuntimeException("Nieznana metoda obs�ugi");
    }
  }

  public void actionPerformed(ActionEvent e) {
    Object src = e.getSource();
    if (src instanceof JMenuItem)
      setCurrentAction(((JMenuItem) src).getText());
    else {
      try {
        currAction.invoke(actionObject); // zmienna liczba arg. !!!
      } catch (Exception exc) {
        JOptionPane.showMessageDialog(null, "Akcja na przycisku nieustalona!");
      }
    }
  }

  public static void main(String args[]) {
    new ReflectionTest();
  }

}

package dynload;
import javax.swing.*;

class Main extends JFrame {

  static void exit(String s) {
    System.out.println(s);
    System.exit(1);
  }


  Main(String actionClassName) {
    Class actionClass = null;
    Action act = null;
    try {
      actionClass =  Class.forName(actionClassName);
      act = (Action) actionClass.newInstance();
    } catch (Exception exc) {
        exit("Obiekt klasy akcji nie mo¿e byæ utworzony");
    }

    JButton b = new JButton();
    b.setAction(act);
    add(b);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    pack();
    setLocationRelativeTo(null);
    setVisible(true);  }
  
  public static void main(String args[]) {
    final String in = JOptionPane.showInputDialog("Podaj nazwê klasy:");
    if (in != null) {
      SwingUtilities.invokeLater(new Runnable() {
        public void run() {
          new Main(in);
        }
      });  
     
    }
  }

}

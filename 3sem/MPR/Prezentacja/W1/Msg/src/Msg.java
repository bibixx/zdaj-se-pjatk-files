import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.*;
import java.util.*;
import java.beans.*;

public class Msg extends JFrame  {

  private JComponent cp = (JComponent) getContentPane();
  private JPanel panel = new JPanel();
  private String parseErrorPattern;
  private String reportPattern;
  private String exceedPattern;
  private double limit = 1000;
  private double sum;

  public Msg() {
    panel.add(new JLabel());
    final JTextField tf = new JTextField(10);
    panel.add(tf);
    JButton b = new JButton();
    b.setActionCommand("pl_PL");
    b.addActionListener(locChanger);
    panel.add(b);
    b = new JButton();
    b.setActionCommand("en_GB");
    b.addActionListener(locChanger);
    panel.add(b);
    cp.add(panel, "North");

    localize(Locale.getDefault());

    final JTextArea log = new JTextArea(20, 60);
    cp.add(log);

    tf.addActionListener( new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            ParsePosition p = new ParsePosition(0);
            String txtNum = tf.getText().trim();
            Number val = NumberFormat.getInstance().parse(txtNum, p);
            String out;
            if (p.getIndex() != txtNum.length())
              out = MessageFormat.format(parseErrorPattern,
                                         new String[] { txtNum }
                                         );
            else if (sum + val.doubleValue() > limit)
              out = MessageFormat.format(exceedPattern,
                                         new Double[] { new Double(limit) }
                                         );
            else {
              sum += val.doubleValue();
              Date date = Calendar.getInstance().getTime();
              out = MessageFormat.format(reportPattern,
                               new Object[] { date, val });
            }
            log.append(out+'\n');
          }
       });


    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
    show();
  }

  private void localize(Locale loc) {
    Locale.setDefault(loc);
    ResourceBundle info =
                   ResourceBundle.getBundle("Info");
    String[] txt = {
                       info.getString("enterData"),
                       "",
                       info.getString("polish"),
                       info.getString("english"),
                   };
    Component[] c = panel.getComponents();
    for (int i=0; i<c.length; i++) {
      Statement stmt = new Statement(c[i],
                                     "setText",
                                     new String[] { txt[i]
                                     });
      try {
        stmt.execute();
      } catch(Exception exc) { exc.printStackTrace(); }

    }
    panel.invalidate();
    parseErrorPattern = info.getString("parseError");
    reportPattern = info.getString("report");
    exceedPattern = info.getString("exceedError");
  }


  ActionListener locChanger =  new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      String symloc = e.getActionCommand();
      String[] locArg = symloc.split("_");
      localize(new Locale(locArg[0], locArg[1]));
    }
  };

  public static void main(String[] args) {
     new Msg();
  }


}
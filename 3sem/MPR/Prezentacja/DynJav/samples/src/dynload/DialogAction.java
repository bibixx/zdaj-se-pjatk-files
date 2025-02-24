package dynload;
import javax.swing.*;
import java.awt.event.*;

public class DialogAction extends AbstractAction {

  final static String ACTION_NAME = "Show msg";

   public DialogAction() {
     super(ACTION_NAME);
   }

   public void actionPerformed(ActionEvent e) {
     JOptionPane.showMessageDialog(null, ACTION_NAME);
   }

}
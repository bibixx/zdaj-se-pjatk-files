package dynload;
import javax.swing.*;
import java.awt.event.*;

public class PrintAction extends AbstractAction {

  final static String ACTION_NAME = "Print";

   public PrintAction() {
     super(ACTION_NAME);
   }

   public void actionPerformed(ActionEvent e) {
     System.out.println("Wykonan akcja: " + ACTION_NAME);
   }

}
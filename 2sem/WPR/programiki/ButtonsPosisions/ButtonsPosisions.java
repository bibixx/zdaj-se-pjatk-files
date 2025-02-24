import javax.swing.*;
import java.awt.*;

//#####################################
class ButtonsPosisions extends JFrame {
//#####################################

//=======================================
public static void main (String[] args) {
//=======================================
	
      String localStyle = UIManager.getSystemLookAndFeelClassName();
      try { UIManager.setLookAndFeel(localStyle); }
      catch (Exception e) { }

      ButtonsPosisions frame = new ButtonsPosisions();
      frame.display();
      
}//================================================ main

//==============
void display() {
//==============
	
      setTitle("Tilte of the frame");
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      Container content = getContentPane();
      
      JButton button_C = new JButton("C");
      JButton button_N = new JButton("N");
      JButton button_S = new JButton("S");
      JButton button_E = new JButton("E");
      JButton button_W = new JButton("W");
      
      content.add( button_C );
      content.add( button_N, BorderLayout.NORTH );
      content.add( button_S, BorderLayout.SOUTH );
      content.add( button_E, BorderLayout.EAST  );
      content.add( button_W, BorderLayout.WEST  );
      pack();
      show();

}//constructor
}//class

// Requirements:
// 1.jpg
// 2.jpg

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

//####################
public class FirstGui{
//####################	
	
	static boolean check = false;
	
//=====================================
public static void main (String[]args){
//=====================================
	
	new FirstGui();

}//============================= main

//++++++++++
FirstGui() {
//++++++++++
	
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Container cont = frame.getContentPane();

        int frameWidth = 800;
        int frameHeight = 600;
        frame.setSize(frameWidth, frameHeight);
        
        JRadioButton rb1 = new JRadioButton("Radio 1", true);
        JRadioButton rb2 = new JRadioButton("Radio 2");

        JButton b2 = new JButton("Button 2");
        JButton b3 = new JButton("Button 3");
        JButton b4 = new JButton("Button 4");

        final JLabel label = new JLabel(new ImageIcon("./1.jpg"));

        JButton b1 = new JButton("Button 1");
          b1.setPreferredSize(new Dimension(100, 100));
          b1.addActionListener( new ActionListener() {
                    public void actionPerformed(ActionEvent e)   {
                    	if (check == false) {
                    		label.setIcon(new ImageIcon("./2.jpg"));
                    		check = true;		
                    	}else{
                    		label.setIcon(new ImageIcon("./1.jpg"));
                    		check = false;		
                    	}
                }
          });// addActionListener

	    JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();

        p1.setBackground(Color.gray);
        p1.setLayout(new FlowLayout());
        p1.setSize(frameHeight/2,frameWidth);
        p2.setBackground(Color.black);
        p2.setLayout(new FlowLayout());

        cont.setLayout( new GridLayout(1,2));
        cont.add(p1);
        cont.add(p2);
        
        p1.add(b1);
        p1.add(b2);
        p1.add(b3);
        p1.add(b4);
        p1.add(label);
        
        p2.add(rb1);
        p2.add(rb2);
        
        frame.show();
        
}//constructor
}//FirstGui

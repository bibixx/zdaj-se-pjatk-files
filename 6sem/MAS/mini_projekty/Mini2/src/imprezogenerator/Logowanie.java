package imprezogenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
 
class Login extends JFrame implements ActionListener
{
 JButton SUBMIT;
 JPanel panel;
 JLabel label1,label2;
 final JTextField  text1,text2;
 
	Login()
	{
	    label1 = new JLabel();
		label1.setText("Login:");
		text1 = new JTextField(15);

		label2 = new JLabel();
		label2.setText("Has³o:");
	    text2 = new JPasswordField(15);
 
		SUBMIT=new JButton("Loguj");
		
        panel=new JPanel(new GridLayout(3,1));
		panel.add(label1);
		panel.add(text1);
		panel.add(label2);
		panel.add(text2);
		panel.add(SUBMIT);
	    add(panel,BorderLayout.CENTER);
        SUBMIT.addActionListener(this);
        setTitle("Logowanie do systemu");
	}
	
   public void actionPerformed(ActionEvent ae)
	{
		String loginText=text1.getText();
		String hasloText=text2.getText();
		
		Uzytkownik uzytkownik = Baza.getUzytkownik(loginText, hasloText);
		
        if (uzytkownik != null) 
        {
			GlowneOkno page=new GlowneOkno();
			page.createAndShowGUI();
			Login.this.setVisible(false);
        }
		else
		{
			System.out.println("Wprowadz poprawne dane!");
			JOptionPane.showMessageDialog(this,"Z³y login lub Has³o","Popraw!",JOptionPane.ERROR_MESSAGE);
		}
}
}
 class Logowanie
 {
	public static void main(String arg[])
	{
		try
		{
			Login frame=new Login();
			frame.setSize(300,100);
			frame.setVisible(true);
		}
	catch(Exception e)
		{JOptionPane.showMessageDialog(null, e.getMessage());}
	}
 }
 
 

 

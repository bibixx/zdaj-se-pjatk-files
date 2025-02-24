package zadanie2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import javax.swing.JTextArea;


public class ActionListenerPlik implements ActionListener
{
	JTextArea area1;
	JTextArea area2;
	
	public ActionListenerPlik(JTextArea area1, JTextArea area2)
	{
		this.area1=area1;
		this.area2=area2;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		if (e.getActionCommand() == "Skanuj")
		{
			area2.setText("");
			StringReader sr = new StringReader(area1.getText());
			BufferedReader br = new BufferedReader(sr);
			String line = "";
			try
			{
				while ((line = br.readLine()) != null)
				{
					if(Licznik.checkAddress(line))
					{
						File f= new File(line);
						if(f.exists() && f.isFile())
						{
							try
							{
								area2.setText(area2.getText()+Licznik.count(f)+'\n');
							}
							catch (FileNotFoundException e1)
							{
								area2.setText(area2.getText()+Licznik.count(line)+'\n');
							}
						}
						else
						{
							area2.setText(area2.getText()+Licznik.count(line)+'\n');
						}
					}
					else
					{
						area2.setText(area2.getText()+Licznik.count(line)+'\n');
					}
				}
			}
			catch (IOException e1)
			{
				e1.printStackTrace();
			} 
		}
	}
}
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import javax.swing.JTextArea;


public class Actions implements ActionListener{
	JTextArea jtaErrors;
	JTextArea jta;
	public Actions(JTextArea jta, JTextArea jtaErrors) {
		this.jta=jta;
		this.jtaErrors=jtaErrors;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand() == "Count") {
			jtaErrors.setText("");
			StringReader sr = new StringReader(jta.getText());
			BufferedReader br = new BufferedReader(sr);
			String line = "";
			try {
				while ((line = br.readLine()) != null){
					if(Counter.checkAddress(line)){
						File f= new File(line);
						if(f.exists() && f.isFile()){
							try {
								jtaErrors.setText(jtaErrors.getText()+Counter.count(f)+'\n');
							} catch (FileNotFoundException e1) {
								jtaErrors.setText(jtaErrors.getText()+Counter.count(line)+'\n');
							}
						}else{
							jtaErrors.setText(jtaErrors.getText()+Counter.count(line)+'\n');
						}
					}else{
						jtaErrors.setText(jtaErrors.getText()+Counter.count(line)+'\n');
					}
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			} 
		}
	}

}

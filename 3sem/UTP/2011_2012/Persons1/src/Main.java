
public class Main {
	public static void main(String[] args) {
		
		//Test.test0();
	
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new GUI();
			}
		});
	}
}

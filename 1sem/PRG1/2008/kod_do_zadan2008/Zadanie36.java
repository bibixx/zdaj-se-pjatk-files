import javax.swing.JOptionPane;


public class Zadanie36 {

	public static void main(String[] args) {
		
		boolean dzialaj=true; while (dzialaj=true)
		{
			String zm1 = JOptionPane.showInputDialog("Do:");
			if (zm1 == null) {System.exit(0);} else
			{
				int x = (Integer.parseInt(zm1));
				int i=1, iloczyn=1;
				while (iloczyn<x) {System.out.println(i); iloczyn=iloczyn*i; i=i+2; }
			}
		}
	}
		
	

}

/*Zadanie 36 (2p) 
Napisa� i testowa� program, kt�ry wyprowadza na konsol� po kolei wszystkie 
liczby nieparzyste, dop�ki ich iloczyn nie przekroczy podanej liczby. 
Dane wej�ciowe poda� w oknach dialogowych. Program dzia�a "w p�tl�" i 
ko�czy dzia�anie w momencie wybrania Cancel*/

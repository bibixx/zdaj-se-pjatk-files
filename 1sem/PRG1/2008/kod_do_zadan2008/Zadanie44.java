import javax.swing.JOptionPane;


public class Zadanie44 {

	public static void main(String[] args) {

		int tab[][] = {{-1,2,2},{-2,3,1},{-1,5,1}};
		
		String temp;
		int x=0,y=0;
		
		for (int p=0; p<9; p++) 
		{
			temp = JOptionPane.showInputDialog("Podaj ["+x+"]["+y+"]");
			tab[x][y]  = (Integer.parseInt(temp));
			y++;
			if (y==3) {y=0; x++;}
		}
		
		int suma=0, iloczyn=1, najm=tab[0][0], najw=tab[0][0];
		for (int i=0; i<3; i++) 
		{
			for (int j=0; j<3; j++)
			{
				suma=suma+tab[i][j];
				iloczyn=iloczyn*tab[i][j];
				if (tab[i][j]<najm) najm=tab[i][j];
				if (tab[i][j]>najw) najw=tab[i][j];
			}
		}
		
		int a=0,b=0,suma2=0;
		while (a<3) 
		{
			if (tab[a][b]>0) 
			{
				suma2=suma2+tab[a][b];
				b++;
				if (b==3) {b=0; a++;}
			}
			else {a++; b=0;}
		}
		System.out.println("Suma: " + suma);
		System.out.println("Iloczyn: "+ iloczyn);
		System.out.println("Element Najmniejszy: " +najm);
		System.out.println("Element Najwiekszy: "+ najw);
		System.out.println("Suma do 1 niedodatniego elementu: " + suma2);
	}
}

/*Zadanie 44 (3p) 

Napisaæ program, który wprowadza z okienek dialogowych elementy tablicy dwuwymiarowej 
3x3 typu int  i wypisuje na konsolê: 
    *    sumê, iloczyn, element najmniejszy i element najwiêkszy tej tablicy, 
    *    sumê tych pocz¹tkowych elementów wszystkich wierszy tablicy, które 
         poprzedzaj¹ element nie-dodatni. */
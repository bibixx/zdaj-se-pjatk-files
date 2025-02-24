/* Napisaæ program, który rozwi±zuje uk³ad równañ postaci:

            a*x + b*y =c
            d*x + e*y =f
Dane wej¶ciowe podaæ w inicjacji odpowiednich zmiennych (typu double) w programie.
*/

package CW_17_10_08;

public class Zadanie16 {
	
	public static void main(String[] args) {
		double a=2,b=4,c=22;
		double d=3,e=5,f=29;
		double w,x,y;
		w=a*e-b*d;
		x=(c*e-b*f);
		y=(a*f-d*c);
		if(w!=0) 
		{
			System.out.println("Uklad posiada jedno rozwiazanie.\nX = " + x/w + "\nY = " + y/w);
		}
		else 
			{
			if (x==0&&y==0) System.out.println("Uk³ad posiada nieskonczenie wiele rozwi±zañ");
			else  System.out.println("Uklad nie posiada rozwiazan");
			}
		}
}

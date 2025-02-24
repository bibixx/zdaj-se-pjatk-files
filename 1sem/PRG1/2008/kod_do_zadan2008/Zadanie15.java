/*Napisaæ program, który rozwi¹zuje równanie kwadratowego: a*x*x + b*x + c = 0. 
  Wspó³czynniki rzeczywiste podaæ w inicjacji odpowiednich zmiennych (typu double) w programie.*/

package CW_17_10_08;

public class Zadanie15 {
	public static void main(String[] args) 
	{   
	double a=1,b=3,c=2;
	double x1,x2;
		if (a!=0) 
			{
				double delta =b*b- 4*a*c;
				if (delta>0)
				{
					double p = Math.sqrt(delta);
					x1=((-b+p)/(2*a));
					x2=((-b-p)/(2*a));
					System.out.println("x1 = " + x1);
					System.out.println("x2 = " + x2);
			
				}
				if (delta==0)
				{
					x1=-b/(2*a);
					System.out.println("x = " + x1);
				}
				if (delta<0)
				{
					System.out.println("Brak rozwi±zañ");
				}
						
			}
		else System.out.println("Jest to rownanie liniowe, a nie kwadratowe!");
	}
}	
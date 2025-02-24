
public class Zadanie39 {

	public static void main(String[] args) 
	{
		int liczba1 = 100;
		int liczba2 = 3;
		int calkowita = 0;
		
	
		while (liczba1>=liczba2)
		{
			liczba1=liczba1-liczba2; calkowita++;
		}
		System.out.println("Calkowita: " + calkowita + " Reszta: " + liczba1);
	}
	
}

/*Zadanie 39 (3p) 

Napisaæ program liczenia czêœci ca³kowitej i reszty z op. dzielenia 
dwóch liczb naturalnych korzystaj¹c TYLKO z operacji dodawania i odejmowania. 
*/

public class Zadanie41 {

	public static void main(String[] args) {
		double tab[] = new double[args.length];
		if (tab.length!=10) System.out.println("Argumenty != 10");
		 
		for (int a=0; a<tab.length; a++) 
		{
			double b = Double.parseDouble(args[a]);
			tab[a]=b;
		}

		for (int i=0; i<tab.length; i++) if (tab[i]>0) System.out.println(tab[i]);
		 
		}
	}


/*Zadanie 41 (1p) 

Napisa� program, kt�ry pobiera z linii wywo�ania 10 argument�w o 
postaci liczb rzeczywistych i wyprowadza na konsol� ilo�� argument�w 
wi�kszych ni� 0. Program wypisuje odpowiedni komunikat, je�li liczba 
argument�w jest inna ni� 10.  
*/
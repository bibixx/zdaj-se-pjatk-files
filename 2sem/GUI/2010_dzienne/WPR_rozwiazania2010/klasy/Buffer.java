
public class Buffer {
	int aktualny = 0;
	int[] buffor = new int[5];
	
	synchronized void get() 
	{
		if (aktualny>0)
		{
			aktualny--; 
			System.out.println(buffor[aktualny]);
		}
		else System.out.println("Buffor pusty!");
	}
	synchronized void put(int i) 
	{
		if (aktualny<5) {buffor[aktualny] = i; aktualny++;}
		else System.out.println("Buffor pelny!");
	}
	int size() {return aktualny;}

}

/*
Bufor jest obiektem klasy Buffer zawieraj¹cej miêdzy innymi metody 
get() - pobiera liczbê z bufora i 
put(int n) - wstawia liczbê do bufora. 
Bufor (np. w postaci tablicy) ma ograniczon¹ pojemnoœæ. 
W danej chwili dostêp do bufora mo¿e mieæ albo producent, albo konsument.*/
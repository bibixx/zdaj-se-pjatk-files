
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
Bufor jest obiektem klasy Buffer zawieraj�cej mi�dzy innymi metody 
get() - pobiera liczb� z bufora i 
put(int n) - wstawia liczb� do bufora. 
Bufor (np. w postaci tablicy) ma ograniczon� pojemno��. 
W danej chwili dost�p do bufora mo�e mie� albo producent, albo konsument.*/
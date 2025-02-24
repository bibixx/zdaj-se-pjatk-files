
public class Consumer extends Thread {
	Buffer buf = new Buffer();
	Consumer(Buffer b)
	{
		buf = b;
	}
	public void run()
	{
		while(true) 
		{
			System.out.print("-:");
			buf.get();
			try {java.lang.Thread.sleep(1000);} 
			catch (InterruptedException e) {return;}
		}
	}

}

/*
Konsument jest obiektem klasy Consumer (np. rozszerzaj¹cej Thread). 
Konstruktor Consumer(Buffer b) tworzy nowy obiekt-konsument, który pobiera 
produkty z bufora b i konsumuje je. Przedefiniowana metoda run() zawiera 
nieskoñczon¹ pêtlê: 1. Pobiera jedn¹ liczbê z bufora za pomoc¹ metody 
int get(); 2. Konsumuje liczbê, czyli wyprowadza j¹ na konsolê; 
3. Zasypia na okreœlony sta³y czas 1s.
*/
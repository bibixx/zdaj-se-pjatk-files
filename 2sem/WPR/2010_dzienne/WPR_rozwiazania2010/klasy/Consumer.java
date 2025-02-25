
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
Konsument jest obiektem klasy Consumer (np. rozszerzaj�cej Thread). 
Konstruktor Consumer(Buffer b) tworzy nowy obiekt-konsument, kt�ry pobiera 
produkty z bufora b i konsumuje je. Przedefiniowana metoda run() zawiera 
niesko�czon� p�tl�: 1. Pobiera jedn� liczb� z bufora za pomoc� metody 
int get(); 2. Konsumuje liczb�, czyli wyprowadza j� na konsol�; 
3. Zasypia na okre�lony sta�y czas 1s.
*/
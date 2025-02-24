
public class Producer extends Thread{
	
	Buffer buf = new Buffer();
	Producer(Buffer b) 
	{
		buf = b;
	}
	
	public void run()
	{
		while (true)
		{
			int x = (int)(Math.random()*100);
			System.out.println("+:" +x);
			buf.put(x);
			try {java.lang.Thread.sleep((long) (Math.random()*2000));} 
			catch (InterruptedException e) {return;}
		}
	}
}

/*Producent jest obiektem klasy Producer (np. rozszerzaj¹cej Thread). 
 * Konstruktor Producer(Buffer b) tworzy nowy obiekt-producent, który
 *  produkuje i umieszcza produkty w buforze b. Przedefiniowana 
 *  metoda run() zawiera nieskoñczon¹ pêtlê: 1. Generuje losowo jedn¹ liczbê 
 *  ca³kowit¹; 2. Umieszcza j¹ w buforze za pomoc¹ metody put(int); 3. 
 *  Zasypia na losowy wybrany czas z przedzia³u od 0 do 2s.
 */
 

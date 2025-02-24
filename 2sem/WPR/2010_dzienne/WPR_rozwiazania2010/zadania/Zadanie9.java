
public class Zadanie9 {
	public static void main(String[] args) {
		
		Player p1	= new Player("Gracz1");		
		Player p2	= new Player("Gracz2"); 
		Referee w = new Referee(3000);
		
		p1.start(); 	p2.start(); 	w.start();
		
		try { w.join();	} catch (InterruptedException e) {e.printStackTrace();}
		
        p1.interrupt();	p2.interrupt();
		
		if (p1.suma != p2.suma) 
		{
			if (p1.suma > p2.suma) {System.out.println("Wygral: " + p1.nazwa_gracza);}
			else System.out.println("Wygral: " + p2.nazwa_gracza);
		}
		else System.out.println("Remis!");
		System.out.println(p1.suma + " - " + p2.suma);
	}
}



/*
Zadanie 9 (4p)

Napisaæ program symuluj¹cy grê w losowanie liczb miêdzy dwoma graczami. Jeden ruch ka¿dego gracza polega na 
wylosowaniu jednej liczby ca³kowitej z przedzia³u [1, 100]. Miêdzy kolejnymi ruchami gracza nale¿y stosowaæ 
opóŸnienia czasowe (równie¿ losowe). Arbiter daje sygna³ rozpoczêcia gry i mierzy czas. Wygrywa ten, który po 
up³ywie okreœlonego czasu otrzyma wiêkszy rezultat bêd¹cy sum¹ wszystkich swoich wylosowanych liczb.

Arbiter jest obiektem klasy Referee (np. rozszerzaj¹cej Thread), przy tworzeniu obiektu okreœlony jest czas gry. 
Gracze s¹ obiektami klasy Player (np. rozszerzaj¹cej Thread) i posiadaj¹ identyfikatory-nazwy. W metodzie main(...) 
klasy testuj¹cej nale¿y uruchomiæ w¹tki arbitra i graczy.
*/
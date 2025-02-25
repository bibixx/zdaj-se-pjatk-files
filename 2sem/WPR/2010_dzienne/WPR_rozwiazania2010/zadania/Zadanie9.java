
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

Napisa� program symuluj�cy gr� w losowanie liczb mi�dzy dwoma graczami. Jeden ruch ka�dego gracza polega na 
wylosowaniu jednej liczby ca�kowitej z przedzia�u [1, 100]. Mi�dzy kolejnymi ruchami gracza nale�y stosowa� 
op�nienia czasowe (r�wnie� losowe). Arbiter daje sygna� rozpocz�cia gry i mierzy czas. Wygrywa ten, kt�ry po 
up�ywie okre�lonego czasu otrzyma wi�kszy rezultat b�d�cy sum� wszystkich swoich wylosowanych liczb.

Arbiter jest obiektem klasy Referee (np. rozszerzaj�cej Thread), przy tworzeniu obiektu okre�lony jest czas gry. 
Gracze s� obiektami klasy Player (np. rozszerzaj�cej Thread) i posiadaj� identyfikatory-nazwy. W metodzie main(...) 
klasy testuj�cej nale�y uruchomi� w�tki arbitra i graczy.
*/
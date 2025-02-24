public class KolkoKrzyzyk {
	private KolkoKrzyzykPlayer ImieX;
	private KolkoKrzyzykPlayer ImieO;
	private int czyjRuch = 1;
	private int[][] plansza;
	private int wygrany;
	private int rozmiar;// okreslamy wielkosc planszy
		       
	public KolkoKrzyzyk(KolkoKrzyzykPlayer ImieX, KolkoKrzyzykPlayer ImieO,
			int rozmiar)// mozemy dodac jeszcze wielkosc planszy , ale po co?
	{
		this.ImieX = ImieX;
		this.ImieO = ImieO;
		this.rozmiar=rozmiar;
		plansza = new int[rozmiar][rozmiar];
	}/*
public void setWymiar(int rozmiar) {
if (rozmiar > 2) {
this.rozmiar = rozmiar;
plansza = new int[rozmiar][rozmiar];
}else
throw new zlyWymiar();
}
*/
	public void wstawOX() {// ustawienie ruchu
		java.awt.Point point = czyjaKolej().getPoint();
		int x = point.x;
		int y = point.y;
		if (rozmiar > x && rozmiar > y) {// sprawdzamy czy wspolrzedne ruchu nie
											// wykraczaja po za wymiar
			if (plansza[x][y] == 0) {// sprawdzamy czy pole nie jest zajete
				plansza[x][y] = czyjRuch;// wykonujemy ruch
				zmienRuch();// zmieniamy wykonujacego ruch
			} //else
				//throw new fullArrayException();
		} else
			throw new indexOutofArrayException(x, y, rozmiar, rozmiar);
		Rysuj();
		czyKoniec();
	}

	public void zmienRuch() {
		if (czyjRuch == 1)
			czyjRuch = 2;
		else
			czyjRuch = 1;
	}

	public KolkoKrzyzykPlayer czyjaKolej() {
		if (czyjRuch == 1)
			return ImieX;
		else
			return ImieO;
	}

	// metoda rysujaca plansze - odpowiednia -,X,O
	public void Rysuj() {
		for (int i = 0; i < plansza.length; i++) {
			for (int j = 0; j < plansza.length; j++) {
				if (plansza[i][j] == 0)// spradzamy jaka wartosc ma pole 0-
										// puste 1- ImieX 2- Imie0, chyba ze na
										// poczatku dalismy wartosc false, wtedy
										// odwrotnie
					System.out.print("- ");
				else if (plansza[i][j] == 1)
					System.out.print("X ");
				else if (plansza[i][j] == 2)
					System.out.print("O ");

			}
			System.out.println();// nastepny wiersz
		}
		System.out.println("Waiting...Nastepny ruch...");
	}

	// metoda badajaca czy gra sie zakonczy³a. 2x(3x pionowo, 3x poziomo, 2x na
	// skos)
	// spradzamy jaka wartosc ma pole 0- puste 1- ImieX 2- Imie0, chyba ze na
	// poczatku dalismy wartosc false, wtedy odwrotnie
	public boolean czyKoniec() {
		boolean wygrywa1 = true;
		boolean wygrywa2 = true;
		boolean rys = true;

		for (int i = 0; i < plansza.length; i++) {
			for (int j = 0; j < plansza.length; j++) {
				if (plansza[i][j] != 1) {
					wygrywa1 = false;
				}
				if (plansza[i][j] != 2) {
					wygrywa2 = false;
				}
				if (plansza[i][j] == 0) {
					rys = false;
				}
			}
			if (wygrywa1 || wygrywa2) {
				if (wygrywa1)
					wygrany = 1;
				else
					wygrany = 2;
				throw new gameOverException();
			} else {
				wygrywa1 = true;
				wygrywa2 = true;
			}
		}
		if (rys) {
			wygrany = 0;
			throw new fullArrayException();
		}
		for (int i = 0; i < plansza.length; i++) {
			for (int j = 0; j < plansza.length; j++) {
				if (plansza[j][i] != 1) {
					wygrywa1 = false;
				}
				if (plansza[j][i] != 2) {
					wygrywa2 = false;
				}
			}
			if (wygrywa1 || wygrywa2) {
				if (wygrywa1)
					wygrany = 1;
				else
					wygrany = 2;
				throw new gameOverException();
			} else {
				wygrywa1 = true;
				wygrywa2 = true;
			}
		}
		for (int j = 0; j < plansza.length; j++) {
			if (plansza[j][j] != 1) {
				wygrywa1 = false;
			}
			if (plansza[j][j] != 2) {
				wygrywa2 = false;
			}
		}
		if (wygrywa1 || wygrywa2) {
			if (wygrywa1)
				wygrany = 1;
			else
				wygrany = 2;
			throw new gameOverException();
		} else {
			wygrywa1 = true;
			wygrywa2 = true;
		}
		int i = 0;
		for (int j = plansza.length - 1; j >= 0; j--) {
			if (plansza[i][j] != 1) {
				wygrywa1 = false;
			}
			if (plansza[i][j] != 2) {
				wygrywa2 = false;
			}
			i++;
		}
		if (wygrywa1 || wygrywa2) {
			if (wygrywa1)
				wygrany = 1;
			else
				wygrany = 2;
			throw new gameOverException();
		} else {
			wygrywa1 = true;
			wygrywa2 = true;
		}

		return false;

	}


	// wyswietla wygranego albo niezstrzygnieto
	public String Wynik() {
		if (wygrany == 1)
            return ImieX.getName();
        else if (wygrany == 2)
            return ImieO.getName();
		 return "REMIS";
}
	
}
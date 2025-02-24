import static javax.swing.JOptionPane.*;

public class Program {

	public static void main(String[] args) {
		String ImieX = " ";
		String ImieO = " ";
		int rozmiar=3;
		while (true) {
			ImieX = pobierzImie("Podaj imie pierwszego gracza: ");
			if (ImieX.isEmpty()) {
				showMessageDialog(null,
						"Nie podales zadnej wartosci lub podana jest niepoprawna.");
			} else
				break;
		}
		while (true) {
			ImieO = pobierzImie("Podaj imie drugiego gracza: ");
			if (ImieO.isEmpty()) {
				showMessageDialog(null,
						"Nie podales zadnej wartosci lub podana jest niepoprawna.");
			} else
				break;
		}
		
			rozmiar = Integer.parseInt(pobierzRozmiar("Podaj rozmiar planszy: "));
			
		KolkoKrzyzyk kk = new KolkoKrzyzyk(new RandomKolkoKrzyzykPlayer(ImieX,
							rozmiar), new RandomKolkoKrzyzykPlayer(ImieO,
							rozmiar), rozmiar);
				kk.Rysuj();

				while (true) {
					try {
						kk.wstawOX();
					} catch (fullArrayException e) {
						System.out.println("plansza jest pelna koniec gry");
						break;
					} catch (indexOutofArrayException e) {
						System.out.println("punkt " + e.getX() + "," + e.getY()
								+ " jest poza plansza");
					} catch (Exceptions e) {
						System.out.println("pole " + e.getX() + "," + e.getY()
								+ " jest zajete");
					} catch (gameOverException e) {
						showMessageDialog(null, kk.Wynik() + "! Wygrales!!!");
						break;
					}
				}
		}
	

	public static String pobierzImie(String komunikat) {
		return showInputDialog(komunikat);
	}
	public static String pobierzRozmiar(String komunikat) {
		return showInputDialog(komunikat);
	}

}
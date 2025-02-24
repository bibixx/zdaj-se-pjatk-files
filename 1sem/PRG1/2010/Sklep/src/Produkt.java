
public class Produkt {

	private int id;
	private String nazwa;
	private int waga;
	private int wysokosc;
	private int szerokosc;
	private int dlugosc;
	private int cena;
	private int stanMagazynowy;
	private String stanSprzedazowy;
	
	Produkt(int id, String nazwa, int waga, int wysokosc, int szerokosc, int dlugosc, int cena, int stanMagazynowy, String stanSprzedazowy) {
		this.id=id;
		this.nazwa=nazwa;
		this.waga=waga;
		this.wysokosc=wysokosc;
		this.szerokosc=szerokosc;
		this.dlugosc=dlugosc;
		this.cena=cena;
		this.stanMagazynowy = stanMagazynowy;
		this.stanSprzedazowy = stanSprzedazowy;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public int getWaga() {
		return waga;
	}

	public void setWaga(int waga) {
		this.waga = waga;
	}

	public int getWysokosc() {
		return wysokosc;
	}

	public void setWysokosc(int wysokosc) {
		this.wysokosc = wysokosc;
	}

	public int getSzerokosc() {
		return szerokosc;
	}

	public void setSzerokosc(int szerokosc) {
		this.szerokosc = szerokosc;
	}

	public int getDlugosc() {
		return dlugosc;
	}

	public void setDlugosc(int dlugosc) {
		this.dlugosc = dlugosc;
	}

	public int getCena() {
		return cena;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}

	public int getStanMagazynowy() {
		return stanMagazynowy;
	}

	public void setStanMagazynowy(int stanMagazynowy) {
		this.stanMagazynowy = stanMagazynowy;
	}

	public String getStanSprzedazowy() {
		return stanSprzedazowy;
	}

	public void setStanSprzedazowy(String stanSprzedazowy) {
		this.stanSprzedazowy = stanSprzedazowy;
	}


	
}

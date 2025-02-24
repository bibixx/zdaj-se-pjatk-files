class Osoba implements Comparable<Osoba>{
	String nazwisko;
	int wiek;
	public Osoba(String nazwisko, int wiek) {
		this.nazwisko = nazwisko;
		this.wiek = wiek;
	} 
	public String getImie() {return nazwisko;}
	public int getWiek() {return wiek;}
	public void setImie(String imie) {this.nazwisko = imie;}
	public void setWiek(int wiek) {this.wiek = wiek;}
	
	public int compareTo(Osoba osoba){
		int i = nazwisko.compareTo(osoba.nazwisko);
		if (i == 0){
			if (wiek > osoba.wiek) 		{i = 1;}
			else if (wiek == osoba.wiek){i = 0;}
				else i = -1;
		}
		return i;
	}
	
	public String toString() {
		return (nazwisko + ", "+ wiek + " lat");
	}
}
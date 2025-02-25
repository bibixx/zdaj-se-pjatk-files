package old;

public class Zadanie3 {

	public static void main(String[] args) {
		
		Osoba mykhi = new Osoba("Mikolaj", 20);
		System.out.println(mykhi.getImie());
		mykhi.setWiek(99);
		System.out.println(mykhi.getWiek());
		System.out.println(mykhi.toString());
		Student student = new Student(mykhi,6020);

		System.out.println(student.toString());
		
	}
	
}
class Osoba{
	String imie;
	int wiek;
	public Osoba(String imie, int wiek) {
		this.imie = imie;
		this.wiek = wiek;
	} 
	public String getImie() {return imie;}
	public int getWiek() {return wiek;}
	public void setImie(String imie) {this.imie = imie;}
	public void setWiek(int wiek) {this.wiek = wiek;}
	
	
	public String toString() {
		return (imie + ", "+ wiek + " lat");
	}
}
	
	class Student extends Osoba{

		static int ilosc;
		int id;
		int grupa;
		public Student(Osoba osoba, int grupa) {
			super(osoba.imie, osoba.wiek);
			this.grupa = grupa; 
			this.ilosc++;
			id = ilosc;
		
		}
	
		public void setGr(int grupa) {grupa = this.grupa;}
		public String getImie() {return super.imie;}
		public int getWiek() {return super.wiek;}
		public void setImie(String imie) {this.imie = super.imie;}
		public void setWiek(int wiek) {this.wiek = super.wiek;}
		
		public String toString() {
			return (super.toString() + " gr:" + this.grupa);
		}
		
	}





/*Zadanie 3 (3p)

Zaprojektowa� klas� Osoba z polami opisuj�cymi nazwisko i wiek. Klasa powinna by� 
wyposa�ona w konstruktory, metody ustalaj�ce/pobieraj�ce dane osoby oraz metod� toString() 
zwracaj�c� informacje o danej osobie. Nast�pnie, zdefiniowa� klas� Student rozszerzaj�c� 
klas� Osoba z dodatkowym polem opisuj�cym numer grupy, do kt�rej nale�y student. 
Klas� Student wyposa�y� w potrzebne konstruktory i metody, kt�re korzystaj�, tam gdzie jest 
to mo�liwe, z konstruktor�w oraz metod nadklasy. Ka�dy student powinien mie� sw�j unikalny 
numer (zaczynaj�c od 1), nadany automatycznie przy jego tworzeniu.*/

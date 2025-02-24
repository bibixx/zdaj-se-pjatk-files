import java.util.ArrayList;
import java.util.HashMap;

public class Zadanie7 {

	public static void main(String[] args) {	//Dziekanat: G³ówna klasa testuj¹ca.
	
		// Tworzenie obiektow
		Student stud1 = new Student("Mykhi",  "99999999");	
		Student stud2 = new Student("Obibok", "88888888");
		
		Wykladowca wykl1 = new Wykladowca("Wykladowca1", "01234567890"); 
		Wykladowca wykl2 = new Wykladowca("Wykladowca2", "13579246802");
		
		Asystent asys1 = new Asystent("Asystent1", "0987654321");
				
		Przedmiot PRG1 = new Przedmiot("Programowanie1");
		Przedmiot WPR = new Przedmiot("Warsztaty Programistyczne");
		
		// Dodawanie przedmiotow wykladowcom i asystentom
		wykl1.dodaj(PRG1); asys1.dodaj(PRG1);
		wykl1.dodaj(WPR); asys1.dodaj(WPR);
		wykl2.dodaj(PRG1);
		
		// Dodawanie przedmiotow studentom
		stud1.dodaj(PRG1); stud1.dodaj(WPR);
		stud2.dodaj(PRG1);
		
		// Dodawanie ocen
		wykl1.ocen(PRG1, stud1, 5);
		asys1.ocen(PRG1, stud1, 5);
		
		wykl1.ocen(WPR, stud1, 3);
		asys1.ocen(WPR, stud1, 4);
		
		// Testowanie w konsoli
		System.out.println("--- TESTOWANIE KLASY ---");
		System.out.println("Srednia: " + stud1.srednia());
		System.out.println("---Lista studentow PRG1:---");	PRG1.listStud();
		System.out.println("---Lista wykladowcow:---"); PRG1.listWykl();
		System.out.println("---Lista asystentow:---"); PRG1.listAsys();
	}

}

class Osoba {
	String nazwisko, pesel;
	public Osoba(String nazwisko, String pesel) {this.nazwisko = nazwisko; this.pesel = pesel;}
	public void setNazwisko(String nazwisko)	{this.nazwisko = nazwisko;}
	public void setPesel(String pesel)			{this.pesel = pesel;}
}

class Student extends Osoba
{
	int nr_idx;	
	static int ilosc;
	HashMap<Przedmiot, Oceny> dziennik = new HashMap<Przedmiot, Oceny> ();
	
	public Student(String nazwisko, String pesel) 
	{
		super(nazwisko, pesel);
		ilosc++;
		nr_idx = ilosc;
	}
	public void dodaj(Przedmiot przedmiot)	
	{ 
		dziennik.put(przedmiot, new Oceny(0,0));
		przedmiot.dodajStudenta(this);
	}
	public void usun(Przedmiot przedmiot)	
	{ 
		dziennik.remove(przedmiot);
		przedmiot.usunStudenta(this);
	}

	public double srednia()
	{
		double suma_ocen = 0;
		for (Przedmiot key : dziennik.keySet()) 
		{
			suma_ocen = suma_ocen + dziennik.get(key).getOcenaAsys();
			suma_ocen = suma_ocen + dziennik.get(key).getOcenaWyk();
		}
		suma_ocen = suma_ocen / (dziennik.size()*2);
		return suma_ocen;
	}
}

class Wykladowca extends Osoba{

	ArrayList<Przedmiot> wykladowcy_przed = new ArrayList<Przedmiot>();
	
	public Wykladowca(String nazwisko, String pesel){ super(nazwisko, pesel);		}
	public void dodaj(Przedmiot przed) 			
	{ 
		wykladowcy_przed.add(przed);
		przed.wykladowcy.add(this);
	}
	public void usun(Przedmiot przed) 			
	{
		wykladowcy_przed.remove(przed);
		przed.wykladowcy.remove(this);
	}
	public void ocen(Przedmiot przedmiot,Student stud, double i)
	{
		stud.dziennik.put(przedmiot, new Oceny(i, stud.dziennik.get(przedmiot).getOcenaAsys()));
	}
}

class Asystent extends Osoba{
	ArrayList<Przedmiot> asystenci = new ArrayList<Przedmiot>();
	
	public Asystent(String nazwisko, String pesel) 	{super(nazwisko, pesel);}
	public void dodaj(Przedmiot przed)				
	{
		asystenci.add(przed);
		przed.dodajAsystenta(this);
	}
	
	public void usun(Przedmiot przed)				
	{
		asystenci.remove(przed);
		przed.usunAsystenta(this);
	}
		
	public void ocen(Przedmiot przedmiot,Student stud, double i)
	{
		stud.dziennik.put(przedmiot, new Oceny(stud.dziennik.get(przedmiot).getOcenaWyk(), i));
	}
}

class Przedmiot {
	int nr_przed;
	static int ilosc;
	String nazwa;
		
	ArrayList<Wykladowca> wykladowcy = new ArrayList<Wykladowca>();
	ArrayList<Asystent> asystenci = new ArrayList<Asystent>();
	ArrayList<Student> studenci = new ArrayList<Student>();

	
	public Przedmiot(String nazwa)
	{
			ilosc++;
			nr_przed = ilosc;
			this.nazwa = nazwa;
	}
	
	public void dodajWykladowce(Wykladowca wykl){ wykladowcy.add(wykl);}
	public void usunWykladowce(Wykladowca wykl){wykladowcy.remove(wykl);}
	public void dodajAsystenta(Asystent asys){asystenci.add(asys);}
	public void usunAsystenta(Asystent asys){asystenci.remove(asys);}
	public void dodajStudenta(Student stud){studenci.add(stud);}
	public void usunStudenta(Student stud){studenci.remove(stud);}
	public void listStud() 
	{
		for (int i=0; i!=studenci.size(); i++){
		System.out.println("ID:" + studenci.get(i).nr_idx + " - " + studenci.get(i).nazwisko);
		}
	}
	public void listWykl() 
	{
		System.out.print(this.nazwa + ": ");
		for (int i=0; i!=wykladowcy.size(); i++)
		{
			System.out.print(wykladowcy.get(i).nazwisko + ", ");
			 
		}
		System.out.println();
	}
	public void listAsys() 
	{
		System.out.print(this.nazwa + ": ");
		for (int i=0; i!=asystenci.size(); i++)
		{
			System.out.print(asystenci.get(i).nazwisko + ", ");
			 
		}
		System.out.println();
	}
}

class Oceny
{
	double wykl,asys;
	public Oceny(double wykl, double asys) {this.wykl = wykl; this.asys = asys;}
	public double getOcenaWyk() {return wykl;}
	public double getOcenaAsys() {return asys;}
}
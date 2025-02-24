package old;
import java.util.ArrayList;

public class Zadanie6 {
	public static void main(String[] args) 
	{
		Bank BPH = new Bank("BPH", 2.1);
		Bank BOS = new Bank("BOS", 2.0);
        Bank mBank = new Bank("mBank", 1.9);
        Bank PKO = new Bank("PKO", 3.0);

        Bankomat bankomat1 = new Bankomat(0, 0, 200, 2000, 20000);
        Bankomat bankomat2 = new Bankomat(20, 20, 200, 2000, 20000);
        
        bankomat1.uzupelnij_bankomat(50, 0, 0, 0, 0); // uzupelnamy bankomat
        
        Person klient1 = new Person("prg1"), klient2 = new Person("wpr");
        Account  acc1 = new Account(klient1, BPH), acc2 = new Account(klient2, mBank);
        
        BPH.acc_list();
        
        acc1.deposit(1000);	// deponujemy 1000 pln na koncie
        
        bankomat1.add_bank(BPH); // dodajemy obsluge bankow
        bankomat1.add_bank(BOS); // dodajemy obsluge bankow
        bankomat1.remove_bank(PKO); // probujemy usunac bank PKO
        bankomat1.add_bank(PKO); // dodajemy obsluge bankow
        bankomat1.show_allowed(); // wyswietlamy dostepne banki w bankomacie
        bankomat1.withdraw(acc1, 760); // wyciagamy 760 pln

        
        bankomat2.withdraw(acc1, 60); // w tym bankomacie ten bank nie jest obslugiwalny
        bankomat2.add_bank(BPH); // dodajemy obsluge banku
        bankomat2.withdraw(acc1, 300); // probujemy wyciagnac 300 pln (brak debetu)
        
        acc2.setDebet(100); // ustawiamy debet
        bankomat1.add_bank(mBank); // dodajemy obsluge bankow
        bankomat1.withdraw(acc2, 100); // wyciagamy 100 pln (z debetem)
        mBank.acc_list();
 	}
}

class Account{
	
	double srodki, debet;
	String nazwa_konta;
	Bank bank;
	static int ilosc;
	int id;
	
	public Account(Person klient, Bank bank) 
	{
		srodki = 0;
		debet = 0;
		nazwa_konta = klient.dajNazwe();
		this.bank = bank;
		Account.ilosc++;
		id = ilosc;
		bank.acc_add(this);
	}

	public void setDebet(double i) {debet = i;}

	public void addInterest() {srodki = (srodki * ((Bank.intrest / 100)+1));}

	public void transfer(Account acc, double i) {
		if (srodki-i>=debet) {srodki=srodki-i; acc.deposit(i);}
		else System.out.println("Operacja niedozwolona: przelew!");
		}

	public void withdraw(double i) 
	{
		if (srodki - i >= debet) srodki = srodki - i;
		else System.out.println("Brak srodkow na koncie!");
	}

	public void deposit(double i) {srodki = srodki + i;}
		
	public String toString() {return (  nazwa_konta + ", stan konta: " + srodki);}
}

class Person{
	String nazwa_konta;
	public Person(String string) {nazwa_konta = string;	}
	public String dajNazwe(){	return nazwa_konta;	}
}

class Bank{
	static int ilosc;
	int id;
	static double intrest;
	
	private ArrayList<Account> tab = new ArrayList<Account>();
	
	public static void setInterest(double i) {intrest = i;}
	
	public Bank(String nazwa, double stopa){
		Bank.ilosc++;
		id = ilosc;
		Bank.intrest = stopa;
	}

	public void acc_add(Account a) {tab.add(a);}
		
	public void acc_list()
	{
		for (int i=1; i<=tab.size();i++ ) {	System.out.println(i +". " + tab.get(i-1));	}
	}

}
class Bankomat{
	
	int b200, b100, b50, b20, b10;
	
	private ArrayList<Integer> allowed = new ArrayList<Integer>();

	public Bankomat(int b200, int b100, int b50, int b20, int b10)
	{
		this.b200 = b200;
		this.b100 = b100;
		this.b50 = b50;
		this.b20 = b20;
		this.b10 = b10;
	}
	
	public void uzupelnij_bankomat(int b200, int b100, int b50, int b20, int b10) 
	{
		this.b200 = this.b200 + b200;
		this.b100 = this.b100 + b100;
		this.b50 = this.b50 + b50;
		this.b20 = this.b20 + b20;
		this.b10 = this.b10 + b10;
	}
	
	public void add_bank(Bank bank)
	{
		if (allowed.contains(bank.id) == false ) allowed.add(bank.id);
		else System.out.println("Bank juz istnieje na liscie!");
	}
	
	public void remove_bank(Bank bank)
	{
		if (allowed.contains(bank.id) == true) allowed.remove(allowed.indexOf(bank.id));
		else System.out.println("Bank nie istnieje na liscie!");
	}
	
	public void show_allowed()
	{
		System.out.print("ID obslugiwanych bankow: ");
		for (int i=1; i<=allowed.size();i++ ) 
		{
			System.out.print(allowed.get(i-1) + ", ");
		}
		System.out.println();
	}
		
	
	
	public void withdraw(Account acc, double i) 
	{
		if (allowed.contains(acc.bank.id) == true)
			if (acc.srodki - i >= 0 - acc.debet) 
				{
					double operacja = i;
					int b200=0,b100=0,b50=0,b20=0,b10=0;
					if (i % 10 == 0) 
					{
						while (i>=200 && this.b200>0) {	b200++;	this.b200--;i = i - 200;}
						while (i>=100 && this.b100>0) {b100++; this.b100--; i = i - 100; }
						while (i>=50 && this.b50>0) {b50++; this.b50--; i = i - 50; }
						while (i>=20 && this.b20>0) {b20++; this.b20--; i = i - 20; }
						while (i>=10 && this.b10>0) {b10++; this.b10--; i = i - 10; }
						
						if (i==0) 
						{
							acc.srodki = acc.srodki - operacja;
							System.out.println(acc.nazwa_konta +"-> wyplacono! " + b200 + "x200, "+ b100 + "x100, " +  b50 + "x50, " +  b20 + "x20, " + b10 + "x10 = " +  operacja + " zostalo: " + acc.srodki);
						}
						else 
						{
							System.out.println("Brak wystarczajacych srodkow w bankomacie!");
							this.b200 = this.b200 + b200;
							this.b100 = this.b100 + b100;
							this.b50 = this.b50 + b50;
							this.b20 = this.b20 + b20;
							this.b10 = this.b20 + b10;
						}
					}
					else System.out.println("Bankomat nie wyplaca monet!");
				}
			else System.out.println("Brak srodkow na koncie!");
		else System.out.println("Bank nie jest obslugiwalny!");
	}
}
/*Zadanie 6 (7p)

Rozbudowaæ zadanie 2 o dodatkowe klasy obs³uguj¹ce banki oraz bankomaty:

Ka¿dy bank ma m. in. nazwê, unikalny identyfikator, listê kont oraz aktualn¹ stopê procentow¹ - wspóln¹ dla wszystkich kont.
Ka¿de konto ma m. in. w³aœciciela, unikalny idenyfikator, stan konta, limit debetu oraz bank, do którego nale¿y konto. 
Dostêpne s¹ trzy operacje na koncie: wp³ata, wyp³ata oraz obliczenie stanu konta po dodaniu odsetek (w skali rocznej).
Ka¿da transakcja bankowa (przelew) posiada m. in. informacjê o kontach w niej uczestnicz¹cych oraz kwotê.   

Bankomat obs³uguje konta niektórych banków. U¿ytkownik powinien mieæ mo¿liwoœæ zmodyfikowania listy obs³ugiwanych banków 
przez dany bankomat. Bankomat wydaje pieni¹dze z konta, o ile stan konta na to pozwala, w banknotach o okreœlonych nomina³ach. 
U¿ytkownik powinien mieæ mo¿liwoœæ zmodyfikowania stanu bankomatu, dotyczy to iloœci pieniêdzy dostêpnych w bankomacie, 
w jakich nomina³ach oraz dla ka¿dego nomina³u w jakiej iloœci.

Dla uproszczenia mo¿na za³o¿yæ, ¿e ka¿da w/w operacja, o ile mo¿liwe jest jej wykonanie w danym momencie, powinna mieæ 
skutek natychmiastowy. Dotyczy to aktualnych stanów wszystkich "podmiotów" uczestnicz¹cych w operacji.*/

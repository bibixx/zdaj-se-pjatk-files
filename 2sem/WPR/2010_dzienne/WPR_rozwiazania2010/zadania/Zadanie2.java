
public class Zadanie2 {
	public static void main(String[] args) 
	{
        Person klient1 = new Person("prg1"), klient2 = new Person("wpr");
        Account  acc1 = new Account(klient1), acc2 = new Account(klient2);

        acc1.setDebit(0); 
        acc2.setDebit(-100);
    
        acc1.deposit(500);
        acc2.deposit(900);
     
        acc2.withdraw(300);
        acc1.transfer(acc2, 200);
    
        System.out.println(acc1);
        System.out.println(acc2);
    
        acc2.transfer(acc1, 950);
     
        Account.setInterest(2);
        acc1.addInterest();
        acc2.addInterest();
     
        System.out.println(acc1);
        System.out.println(acc2);
 	}
}

class Account{
	
	double srodki, debet;
	String nazwa_konta;
	static double intrest;
	
	public Account(Person klient) {
		srodki = 0;
		debet = 0;
		nazwa_konta = klient.dajNazwe();
	}

	public void setDebit(double i) {debet = i;}

	public void addInterest() {srodki = (srodki * ((intrest / 100)+1));}

	public static void setInterest(double i) {intrest = i;}

	public void transfer(Account acc, double i) {
		if (srodki-i>=debet) {srodki=srodki-i; acc.deposit(i);}
		else System.out.println("Operacja niedozwolona: przelew!");
		}

	public void withdraw(double i) {
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
/*
Zadanie 2 (6p)

Napisaæ program symuluj¹cy operacje na prostych kontach bankowych.
Ka¿de konto (obiekt klasy Account) ma w³aœciciela (obiekt klasy Person), stan konta, limit debetu. Dostêpne s¹ cztery operacje na koncie: wp³ata, wyp³ata, przelew oraz obliczenie stanu konta po dodaniu odsetek, w skali rocznej. Aktualna stopa procentowa - wspólna dla wszystkich kont jest ustalona statycznie w klasie Account. 

Stworzyæ klasy Person, Account w taki sposób, aby nastêpuj¹cy program:

public class Test {

    public static void main(String[] args)
    {
            Person klient1 = new Person("prg1"), klient2 = new Person("wpr");
            Account  acc1 = new Account(klient1), acc2 = new Account(klient2);

            acc1.setDebit(0); 
            acc2.setDebit(-100);
        
            acc1.deposit(500);
            acc2.deposit(900);
         
            acc2.withdraw(300);
            acc1.transfer(acc2, 200);
        
            System.out.println(acc1);
            System.out.println(acc2);
        
            acc2.transfer(acc1, 950);
         
            Account.setInterest(2);
            acc1.addInterest();
            acc2.addInterest();
         
            System.out.println(acc1);
            System.out.println(acc2);
    }
}
wyprowadzi³ na konsolê poni¿sze wyniki:
prg1, stan konta: 300.0
wpr, stan konta: 800.0
Operacja niedozwolona: przelew!
prg1, stan konta: 306.0
wpr, stan konta: 816.0
*/
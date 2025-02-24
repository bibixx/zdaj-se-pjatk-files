/*
Zadanie 9.

Uzupelnic podane klasy i funkcje, tak
aby zadzialala podana funkcja main:

////////////////////////////////////////////////////////////////////////

#include <iostream>
#include <cstring>
using namespace std;

class Osoba {
char* imie;
public:
friend class Para;
friend ostream& operator<<(ostream& str, const Osoba& os);

explicit Osoba(const char* n);
Osoba(const Osoba& os);
Osoba& operator=(const Osoba& os);
~Osoba();
};

// implementacja konstruktorow,
// destruktora i metod klasy Osoba

class Para {
Osoba *maz, *zona;
public:
friend ostream& operator<<(ostream& str, const Para& p);

Para(const Osoba& m , const Osoba& z);
Para(const Para& p);
Para& operator=(const Para& p);
~Para();
};

// implementacja konstruktorow,
// destruktora i metod klasy Para

int main(void) {
Osoba jan("Jan"), jasia("Jasia");
Para para1(jan,jasia);
Para *p2 = new Para(Osoba("Karol"),Osoba("Karolcia"));
Para para3(Osoba("Feliks"),Osoba("Fela"));
para3 = para3; // tak!
*p2 = para3;
cout << "Jan  : " << jan   << endl;
cout << "Jasia: " << jasia << endl;
cout << *p2 << endl;
delete p2;
}
////////////////////////////////////////////////////////////////////////

Program powinien wydrukowac cos w rodzaju

Jan  : Jan
Jasia: Jasia
Para: on Feliks, ona Fela

*/

#include <iostream>
#include <cstring>

using namespace std;

class Osoba {
	char* imie;
public:
	friend class Para;

	friend ostream& operator<<(ostream& str, const Osoba& os)
	{
		str << os.imie;
		return str;
	}

	explicit Osoba(const char* n) : imie(strcpy(new char[strlen(n)+1],n))
	{
	}

	Osoba(const Osoba& os) : imie(strcpy(new char[strlen(os.imie)+1],os.imie))
	{
	}

	Osoba& operator=(const Osoba& os)
	{
		if (this == &os) return *this;
		delete [] imie;
		imie = strcpy(new char[strlen(os.imie)+1],os.imie);
		return *this;
	}

	~Osoba()
	{
		delete []imie;
	}
};

class Para {
	Osoba *maz, *zona;
public:

	friend ostream& operator<<(ostream& str, const Para& p)
	{
		str << "Para: on " << *p.maz << ", ona " << *p.zona;
		return str;
	}

	Para(const Osoba& m , const Osoba& z)
	{
		maz = new Osoba(m.imie);
		zona = new Osoba(z.imie);
	}

	Para::Para(const Para& p) {
		maz  = p.maz;
		zona = p.zona;
	}

	Para& operator=(const Para& p)
	{
		if (this == &p) return *this;

		delete maz;
		delete zona;

		maz = p.maz;
		zona = p.zona;

		maz = new Osoba(p.maz->imie);
		zona = new Osoba(p.zona->imie);

		return *this;
	}

	~Para()
	{
		delete maz;
		delete zona;
		//maz = NULL;
		//zona = NULL;
	}
};


int main()
{
	Osoba jan("Jan"), jasia("Jasia");
	Para para1(jan,jasia);
	Para *p2 = new Para(Osoba("Karol"),Osoba("Karolcia"));
	Para para3(Osoba("Feliks"),Osoba("Fela"));
	para3 = para3; // tak!
	*p2 = para3;
	cout << "Jan  : " << jan   << endl;
	cout << "Jasia: " << jasia << endl;
	cout << *p2 << endl;
	delete p2;
	system("PAUSE");
}

/*
Zadanie 8.

N i e  uzywajac klasy 'string' z Biblioteki Standardowej (STL),
zdefiniowac wlasna klase String, ktorej obiekty przechowuja napisy
(tablice znakow) o dowolnej dlugosci. Mozna korzystac z funkcji
udostepnianych przez naglowek <cstring> (czyli, w starej nomenklaturze
naglowek <string.h>).

Zdefiniowac dla tej klasy

* konstruktor
String(const char*);

* konstruktor kopiujacy
String(const String&);

* metode
size_t length();
dostarczajaca dlugosc napisu

* przeciazenie operatora przypisania
(metoda zwraca przez odniesienie "ten" obiekt)
String& operator=(const String&);

* przeciazenie operatora +, czyli konkatenacji, czyli dodawanie
napisow (metoda ma zwracac  p r z e z  w a r t o s c  nowy
obiekt klasy)
String operator+(const String&)

* przeciazenie operatora +=, czyli "zwiekszania" napisow
(metoda ma zwracac  p r z e z  w a r t o s c  nowy
obiekt klasy)
String& operator+=(const String&);

* przeciazenie operatora ==, czyli porownywania napisow
bool operator==(const String&);

Ponadto przciazony powinien zostac operator '<<' tak,
zeby mozliwe bylo normalne wypisywanie obiektow klasy,
np. 'cout << s;' dla s klasy String.

Na przyklad

int main(void) {
String s1("Ala ma ");
String s2("kota");
String s3 = s1 + s2;
s3  +=  ".";
s3 = s3; // sic!
if (s3 == "Ala ma kota.")
cout << s3 << " ma " << s3.length()
<< " znakow" << endl;
}

powinno sie skompilowac i wydrukowac 'Ala ma kota. ma 12 znakow'
*/

#include <iostream>
#include <string>
#include <assert.h>

using namespace std;

class MyString
{
public:

	int max_rozm;
	size_t dlugosc;
	int pojemnosc;
	char *napis;

	MyString()
	{
		max_rozm = 1024;
		dlugosc = 0;
		pojemnosc = 1;
		napis = new char[pojemnosc];
		napis[0] = '\0';
	}

	~MyString()
	{
		delete []napis;
	}

	MyString(const char *n)
	{
		assert(n!=0);
		dlugosc = strlen(n);
		pojemnosc = dlugosc + 1;
		napis = new char[pojemnosc];
		strcpy(napis, n);
	}

	MyString(const MyString &str)
	{
		dlugosc = str.dlugosc;
		pojemnosc = dlugosc + 1;
		napis = new char[pojemnosc];
		strcpy(napis, str.napis);
	}

	size_t length() const 
	{
		return dlugosc;
	}

	MyString& operator=(const MyString &str)
	{
		if (this != &str)                        
		{
			if (pojemnosc < str.length() + 1)    
			{ 
				delete[] napis;                  
				pojemnosc = str.length() + 1;       
				napis = new char[pojemnosc];
			}

			dlugosc = str.length();
			strcpy(napis,str.napis);
		}

		return *this;  
	}


	MyString& operator+=(const MyString &str)
	{
		MyString copystring(str);

		int newLength = length() + str.length(); 
		int lastLocation = length();

		if (newLength >= pojemnosc)
		{
			pojemnosc = newLength + 1;
			char * newBuffer = new char[pojemnosc];
			strcpy(newBuffer,napis);
			delete [] napis;
			napis = newBuffer;
		}

		strcpy(napis+lastLocation,copystring.c_str() );
		dlugosc = newLength;

		return *this;
	}

	MyString operator+(const MyString &str)
	{
		return (*this+=str);
	}

	const char* c_str() const
	{
		return napis;
	}

};

ostream& operator << (ostream & os, const MyString &str)
{
	os << str.napis;
	return os;
}

bool operator==(const MyString& lewy, const MyString& prawy)
{
	return strcmp(lewy.c_str(), prawy.c_str()) == 0;
}

int main()
{
	MyString s1("Ala ma ");
	MyString  s2("kota");
	s2  +=  ".";
	MyString  s3 = s1 + s2;
	//s3 = s3; // sic!
	if (s3 == "Ala ma kota.")
		cout << s3 << " | ma " << s3.length()
		<< " znakow" << endl;

	system("PAUSE");
}

/*
Napisac funkcje o naglowku

char* clean(const char* napis);

przyjmujaca poprzez argument napis (C-string). Zadaniem
funkcji jest utworzenie (dynamicznie, a wiec przez uzycie
operatora 'new') nowego napisu (tablicy znakow) i zwrocenie
go. Do zwracanego napisu funkcja powinna przepisac zawartosc
oryginalnego, otrzymanego przez argument napisu, ale tak,
aby kazda sekwencja kolejnych bialych znakow (dowolnie dluga)
zastapiona zostala dokladnie jedna spacja. Biale znaki to

spacja,
tabulator ('\t'),
znak przesuniecia karetki ('\r'),
znak nowej linii ('\n').

Biale znaki na poczatku i koncu napisu powinny zostac
w ogole usuniete (tak jak robi to String.strip() z Javy).


Mozna uzyc pomocniczej funkcji

bool isWhite(char c) {
return c == '\t' || c == '\n' || c == '\r' || c == ' ';
}

ktora sprawdza, czy otrzymany znak jest bialym znakiem.


N I E  mozna uzywac zadnych funkcji bibliotecznych z naglowka
<cstring> (lub <string.h>), takich jak strlen, strcpy itd.


Na przyklad, jesli oryginalnym napisem byl napis

"\n Litwo,  \n \t  ojczyzno\nmoja "

to zwrocony napis powinien byc

"Litwo, ojczyzno moja"

Zaalokowana na zwracany napis pamiec powinna byc dokladnie
taka, jaka jest potrzebna, to znaczy miec tyle bajtow ile
jest znakow w zwroconym napisie, plus jeden bajt na konczacy
znak konca napisu ('\0'). Wewnatrz funkcji mozna tworzyc
dodatkowe, pomocnicze tablice znakow (oczywiscie usuwajac
je po uzyciu!).


Na przyklad ponizszy program (uzupelniony o definicje
funkcji 'clean') */

#include <iostream>
#include <stdio.h>

using namespace std;

bool isWhite(char c) {
	return c == '\t' || c == '\n' || c == '\r' || c == ' ';
}

int charCounter(const char* c)
{
	int ile = 0;
	while(c[ile++]!='\0'){}
	//liczy tez ze znakiem konczacym string
	return ile;
}

void charCopier(char* dest, const char* source, int j)
{
	for (int i=0;i<j;i++)
	{
		dest[i] = source[i];
	}
}

char* clean(const char* napis)
{
	char* tempChar = new char[charCounter(napis)];
	bool tryb = false;
	bool znaki = false;

	int j = 0;
	for(int i=0;i<charCounter(napis);i++)
	{
		if (!isWhite(napis[i]))
		{
			tempChar[j] = napis[i];
			j++;
			tryb = true;

		}else
		{
			if(tryb)
			{
				tempChar[j] = ' ';
				j++;
				tryb = false;
			} 
		}
	}

	char* retChar = new char[charCounter(tempChar)];
	charCopier(retChar,tempChar,charCounter(tempChar));
	delete tempChar;

	return retChar;
}

int main()
{
	char *napis, *res;

	napis = "  \n Jeden, \r\t   dwa  trzy. ";
	res = clean(napis);
	cout << ">>" << res << "<<" << endl;
	delete [] res;

	napis = "Jeden,\r\tdwa\ttrzy. ";
	res = clean(napis);
	cout << ">>" << res << "<<" << endl;
	delete [] res;

	napis = "\r\t\t ";
	res = clean(napis);
	cout << ">>" << res << "<<" << endl;
	delete [] res;

	napis = "";
	res = clean(napis);
	cout << ">>" << res << "<<" << endl;
	delete [] res;

	system("PAUSE");
}
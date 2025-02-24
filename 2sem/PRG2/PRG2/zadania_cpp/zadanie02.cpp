/*Zadanie 2.

Prosze napisac i przetestowaæ opisana nizej funkcje

void szukaj_litery(char* napis, char litera, char** pfirst, char** plast);

ktora pobiera napis 'napis' (zakonczony, jak zwykle,
znakiem '\0'), adresy dwoch wskaznikow typu char*
oraz znak do znalezienia ('litera'). Zadaniem funkcji
jest wpisanie do wskaznikow, ktorych adresy pobrala,
adresow pierwszego i ostatniego wystapienia znaku
'litera' w napisie. Jesli 'litera' nie wystepuje
w napisie, to oba te wskazniki powinny zostac
ustawione na NULL (0), jesli jest tylko jedno
wystapienie, to jego adres powinien zostac wpisany
do wskaznika wskazywanego przez 'pfirst', a wskaznik
wskazywany przez 'plast' powinien byc 0 (NULL).*/


#include <iostream>
using namespace std;

void szukaj_litery(char* napis, char litera, char** first, char** last) 
{
	*first = *last = 0;
	while (*napis) {
		if (*napis == litera)
			if (! *first) *first = napis;
			else          *last  = napis;
			++napis;
		}
}

int main() {
	char* napis = "Jakis napis z roznymi literami";
	char *pocz, *konc;
	char litera;

	cout << "Podaj jedna litere: ";
	cin  >> litera;

	szukaj_litery(napis, litera, &pocz, &konc);

	if (pocz != 0) {
		cout << "Pierwsze wystapienie \'" << litera
			<< "\': " << pocz << endl;
		if (konc != 0)
			cout << "Ostatnie wystapienie \'" << litera
			<< "\': " << konc << endl;
		else
			cout << "Litera \'" << litera
			<< "\' wystepuje tylko raz"  << endl;
	} else
		cout << "Litera \'" << litera
		<< "\' nie wystepuje" << endl;

	system("PAUSE");
}

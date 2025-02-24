/*
Zadanie 6.

Struktura opisujaca wezel listy ma postac

struct Wezel
{
int    dana;
Wezel* next;
};

Napisac i przetestowac funkcje o naglowku

Wezel* przestaw(const Wezel *head);

pobierajaca wskaznik do pierwszego elementu istniejacej listy ('head'),
a zwracajacej wskaznik do pierwszego elementu nowej listy, ktora
jest  k o p i a  listy wejsciowej, ale z elementami przestawionymi
w ten sposob, ze kazda kolejna para elementow jest przestawiona
miejscami. Jesli ilosc elementow jest nieparzysta, to ostatni element
jest taki sam w obu listach. Na przyklad jesli w liscie pierwotnej
elementy (wezly) byly w kolejnosci

2 4 3 6 7 1 9

to w liscie wyjsciowej kolejnosc ta jest

4 2 6 3 1 7 9

Do testow nalezy dopisac tez funkcje drukujaca zawartosc listy
a pobierajaca wskaznik do pierwszego wezla (head)

void printlist(const Wezel* head);

oraz funkcje tworzaca nowy element (wezel) i wstawiajaca go
na  k o n i e c  listy

void wstaw(Wezel*& head, int nowa_dana);

Jesli head jest NULL, to znaczy, ze lista jest pusta i wstawiamy
pierwszy element: w tym przypadku po wyjsciu z funkcji 'head' powinno
wskazywac na ten pierwszy i jedyny element listy - dlatego parametr
przekazywany jest przez referencje (zeby wskaznik head mogl sie
zmienic i zeby bylo to widoczne w funkcji wywolujacej).
Funkcja 'wstaw' powinna uzyc operatora new aby utworzyc nowy
wezel i wstawic tam wartosc nowa_dana jako wartosc skladowej 'dana'
oraz ustawic odpowiednia wartosc skladowej 'next'.

Dodatkowo nalezy napisac funkcje

void usun(Wezel*& head);

zwalniajaca pamiec na wszystkie wezly z listy i ustawiajacej
'head' na NULL.

program powinien wydrukowac np.:

1 3 2 4 5 6 7
3 1 4 2 6 5 7

*/

#include <iostream>
using namespace std;

struct Wezel {
	int    dana;
	Wezel *next;
};

void wstaw(Wezel*& head, int nowa_dana) 
{
	Wezel *n  = new Wezel;
	n->dana = nowa_dana;
	n->next = 0;
	if (!head)
		head = n;
	else {
		Wezel *tmp = head;
		while (tmp->next) tmp = tmp->next;
		tmp->next = n;
		}
}

Wezel* przestaw(const Wezel *head) 
{
	Wezel *newhead = 0;
	while (head) 
	{
		if (!head->next) 
		{
			wstaw(newhead,head->dana);
			break;
		}
		wstaw(newhead,head->next->dana);
		wstaw(newhead,head->dana);
		head = head->next->next;
	}
	return newhead;
}

void printlist(const Wezel* head) {

	while (true)
	{
		cout << head->dana << " ";
		if (head->next==NULL){break;}
		head = head->next;
	}
	cout << "" << endl;
}

void usun(Wezel*& head) 
{
	while (head) 
	{
		Wezel *n = head;
		head = head->next;
		delete n;
	}
}

int main() {
	Wezel *head_old = 0;
	wstaw(head_old,1);
	wstaw(head_old,3);
	wstaw(head_old,2);
	wstaw(head_old,4);
	wstaw(head_old,5);
	wstaw(head_old,6);
	wstaw(head_old,7);
	printlist(head_old);
	Wezel *head_new = przestaw(head_old);
	printlist(head_new);
	usun(head_old);
	usun(head_new);
	system("PAUSE");
}
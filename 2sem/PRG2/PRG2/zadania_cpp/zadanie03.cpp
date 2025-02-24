/*
Zadanie 3.

Prosze napisac i przetestowac opisana nizej funkcje o naglowku:

int fun(int* tab, int size, int& mn, int& mx);

ktora pobiera tablice int'ow o wymiarze size i:

1. porzadkuje elementy tablicy tak, ze wszystkie elementy
ujemne znajduja sie przed elementami nieujemnymi (w ramach
grup "ujemne" i "nieujemne" kolejnosc jest dowolna).
UWAGA: funkcja powinna to zrobic w jednym przebiegu petli
przez tablice (nie powinno byc zadnych petli w petli);
w przeciwnym przypadku bardziej oplacaloby sie
posortowac tablice!

2. "ustawia" referencje mn i mx na, odpowiednio, najmniejszy
i najwiêkszy element tablicy

3. zwraca indeks pierwszego elementu nieujemnego - moze to
byc 0 jesli w tablicy nie bylo liczb ujemnych, lub indeks
rowny wymiarowi tablicy jesli w tablicy byly wylacznie
liczby ujemne.

Na przyk³ad po

int minim,maxim;
int t[] = {1,-1,-2,3,-7};
int ind = fun(t,5,minim,maxim);

wartosci zmiennych w, minim i maxim powinny wynosiæ 3,-7 i 3,
a elementy o wartosciach 1 i 3 powinny byæ przestawione na
koniec tablicy.
*/


#include <iostream>
using namespace std;

void PrintTable(int *tab, int size)
{
	cout << "[ ";
	for (int i = 0; i < size; i++)
	{
		cout << tab[i] << " " ;
		
	}
	cout << "]" << endl;
}

int fun(int* tab, int size, int& mn, int& mx)
{
	int pos = 0;
	mn = mx = tab[0];
	for(int i = 0; i < size; i++) 
	{
		if( tab[i] < mn ) mn=tab[i];
		if( tab[i] > mx ) mx=tab[i];

		if(tab[i] < 0) {
			int tmp  = tab[pos];
			tab[pos] = tab[i];
			tab[i]   = tmp;
			pos++;
		}
	}

	cout << "minimum : " << mn << endl;
	cout << "maximum : " << mx << endl;
	
	cout << "PO : ";
	cout << "[ ";
	for (int i = 0; i < size; i++)
	{
		cout << tab[i] << " " ;
		
	}
	cout << "]" << endl;
	

	return pos;
}

int main() {
	
	int minim,maxim;
	int t[] = {1,-1,-2,3,-7};
	cout << "PRZED : ";
	PrintTable(t,5);
	int ind = fun(t,5,minim,maxim);
	system("PAUSE");
}

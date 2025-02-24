/*
Zadanie 7 (klasy - rozdzialy 14 i 15)

Zdefiniowac klase Tab

class Tab {
int size;
int   *t;
public:
// ...
};

w ktorej skladowa 't' to wskaznik do alokowanej dynamicznie
w konstruktorach tablicy int'ow. Zdefiniowac dla tej klasy

1. Konstruktor 'Tab(const int* tt, int size);'
konstruujacy obiekt klasy na podstawie przeslanej
do konstruktora tablicy int'ow 'tt' i jej wymiaru 'size',
o ktorym zakladamy, ze zawsze bedzie wiekszy od zera.

2. Konstruktor kopiujacy 'Tab(const Tab& tab);'

3. Destruktor.

4. Metode 'void add(const Tab& tab);' ktora tablice wskazywana
przez skladowa 't' obiektu na rzecz ktorego zostala wywolana
powieksza tak, aby na jej koniec dodac elementy tablicy
wskazywanej przez skladowa 't' obiektu 'tab'.

5. Metode 'void resize(int new_size);', ktora tablice wskazywana
przez skladowa 't' w obiekcie na rzecz ktorego zostala wywolana
'obcina' do wymiaru 'new_size' jesli new_size < size,
a powieksza do wymiaru new_size (i wypelnia dodatkowe elementy
zerami) jesli new_size >= size.

6. Metode 'void print() const;' drukujaca zawartosc tablicy
wskazywanej przez skladowa 't' i jej wymiar.


Program powinien wydrukowaæ:

size=13:  1 2 3 4 5 0 0 11 12 13 14 15 0
size=10:  11 12 13 14 15 0 0 0 0 0
*/

#include <iostream>
#include <cstring>
using namespace std;

class Tab {
	int size;
	int   *t;

public:
	Tab(const int* tt, int size)
		: size(size),
		t((int*)memcpy(new int[size],
		tt,size*sizeof(int)))
	{}

	Tab(const Tab& tab)
		: size(tab.size),
		t((int*)memcpy(new int[size],
		tab.t,size*sizeof(int)))
	{}

	~Tab() {
		delete [] t;
	}

	void add(const Tab& tab) {
		int *n = (int*)memcpy(new int[size+tab.size],
			t,size*sizeof(int));
		memcpy(n+size,tab.t,tab.size*sizeof(int));
		delete [] t;
		size = size + tab.size;
		t = n;
	}

	void resize(int new_size) {
		if (new_size <= size) {
			size = new_size;
			return;
		}

		int *n = (int*)memcpy(new int[new_size],
			t,size*sizeof(int));
		(void)memset(n+size,0,(new_size-size)*sizeof(int));
		delete [] t;
		size = new_size;
		t = n;
	}

	void print() const {
		cout << "size=" << size << ": ";
		for (int i = 0; i < size; ++i) cout << " " << t[i];
		cout << endl;
	}
};

int main() {
	int t1[] = {1,2,3,4,5},
		t2[] = {11,12,13,14,15};

	Tab tab1(t1,5);
	tab1.resize(7);

	Tab tab2(t2,5);
	tab2.resize(10);

	tab1.add(tab2);
	tab1.resize(13);

	tab1.print();
	tab2.print();

	system("PAUSE");
}
// zadanie 3
// Ocenione 10/10

#include <iostream>
using namespace std;

int fun(int* tab, int size, int* pmn, int& mx) {
	mx = tab[0];
	*pmn = tab[0];
	int parz_counter = 0;
	int last = size - 1;
	for (int i = 0; i < size; i++) {
		if (tab[i] > mx) {
			mx = tab[i];
		}
		if (tab[i] < *pmn) {
			*pmn = tab[i];
		}
		if (tab[i] % 2 != 0) {
			int np = tab[i];
			for (int j = last; j > -1; j--) {
				if (tab[j] % 2 == 0 && last > parz_counter) {
					int pa = tab[j];
					tab[i] = pa;
					tab[j] = np;
					last--;
					parz_counter++;
					break;
				}
			}
		} else {
			parz_counter++;
		}
	}
	return parz_counter;
}

int main() {
	int minim, maxim;
	int tab[] = {3,1,6,4,7};
	int s = sizeof(tab) / sizeof(int);

	cout << "tablica startowa: ";
	for (int i = 0; i < s; i++) {
		cout << tab[i] << " ";
	}
	cout << endl;
	
	int w = fun(tab,s,&minim,maxim);

	cout << "tablica po przestawieniach: ";
	for (int i = 0; i < s; i++) {
		cout << tab[i] << " ";
	}
	cout << endl;
	cout << "maxim: " << maxim << endl;
	cout << "minim: " << minim << endl;
	cout << "indeks pierwszego elementu nieparzystego: " << w << endl;
	return 0;
}
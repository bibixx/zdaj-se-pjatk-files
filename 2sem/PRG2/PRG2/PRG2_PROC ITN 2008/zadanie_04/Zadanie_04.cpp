// zadanie 04
// Ocenione 10/10

#include <iostream>

using namespace std;

int cols2int(int col1[]) {
	unsigned int k = col1[0]<<24 | col1[1]<<16 | col1[2]<<8 | col1[3];
	return k;
}

void int2cols(int k, int col2[]) {
	int left = 0;
	for (int i = 0; i < 4; i++) {
		col2[i] = (k << left) >> 24;
		if (col2[i] < 0) { // k jest signed
			col2[i] += 256;
		}
		left += 8;
	}
}

int main()
{
	int col1[4];
	int col2[4];
	int i = 0;
	int sum = 0;

	while (1) {
		int p;
		while (1)	
		{
			cout << "Podaj element tablicy col1[] o indeksie " << i << ": ";
			cin >> p;
			if (cin.fail()) {
				cin.clear();
				cin.ignore(1000, '\n');
				cout << "Nie wprowadziles liczby, sprobuj jeszcze raz" << endl;
				continue;
			}
			cin.ignore(1000, '\n');
			if (cin.gcount() > 1) { 
				cout << "Niepoprawna liczba, sprobuj jeszcze raz" << endl;
				continue;
			}
			if (!(p > -1 && p < 256)) {
				cout << "Niepoprawna liczba, wprowadz liczbe z zakresu 0-255" << endl;
				continue;
			}
			break;
		}

		cout << "OK, wprowadziles " << p << endl;
		col1[i] = p;

		if (i == 3) {
			cout << "Tablica col1[]: ";
			for (int j = 0; j < 4; j++) {
				sum += col1[j];
				cout << col1[j] << " ";
			}
			cout << endl;
			
			if (sum == 0) {
				cout << "Same zera, KONIEC" << endl;
				exit(0);
			}
			i = -1;
			sum = 0;

			// tu mamy pewnosc ze liczby sa poprawne i mozemy wolac dla nich funkcje
			int k = cols2int(col1);
			cout << "liczba k = " << k << endl;
			int2cols(k, col2);
			cout << "Tablica col2[]: ";
			for (int i = 0; i < 4; i++) {
				cout << col2[i] << " ";
			}
			cout << endl << endl;
		} 
		i++;
	}
	cin.get();
	return 0;
}
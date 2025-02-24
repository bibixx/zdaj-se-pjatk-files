//   Zadanie_09
// Ocenione 10/10

#include <iostream>
#include <fstream>
#include <cstring>
using namespace std;

class Osoba {
	char* imie;
	int rok;
	Osoba* next;
	
	Osoba();
	Osoba(const Osoba&);
	Osoba& operator=(const Osoba&);
public:
	friend void showList(const Osoba*);
	friend void writeList(const Osoba*,const char*);
	friend void deleteList(Osoba*&);
	friend Osoba* recreateList(const char*);
	
	Osoba(const char* imie, int rok, Osoba* next) {
		this->imie = new char[strlen(imie)+1];
		strcpy(this->imie, imie);
		this->rok = rok;
		this->next = next;
	}

	void writeOsoba(ostream& s) const {
		int imie_size = strlen(this->imie); 
		s.write((char *)&imie_size, sizeof(imie_size)); // dlugosc imienia
		s.write(this->imie, imie_size); // imie
		s.write((char *)&rok, sizeof(this->rok)); // rok
	}

	~Osoba() {
		cout << "Del " << imie << "; ";
		delete [] imie;
	}
};

void showList(const Osoba* head) {
	if (! head) {
		cout << "Lista jest pusta" << endl;
		return;
	}
	while (head) {
		cout << head->imie << "(" << head->rok << ") ";
		head = head->next;
	}
	cout << endl;
}

void deleteList(Osoba*& head) {
	if (! head) {
		cout << "Lista jest pusta" << endl;
		return;
	}
	while (head) {
		Osoba* temp = head;
		head = head->next;
		delete temp;
	}
	cout << endl;	
}

void writeList(const Osoba* head, const char* fil) {	
	ofstream plik(fil, ios::binary);
	while(head) {
		head->writeOsoba(plik);
		head = head->next;
	}
	plik.close();
}

Osoba* recreateList(const char* fil) {
	ifstream file(fil);
	file.seekg(0, ios::end);
	streamsize len = file.tellg();
	file.seekg(ios::beg);
	
	int rok, imie_size;
	Osoba *head = NULL;
	while (file.tellg() < len) {
		file.read((char *)&imie_size, 4);
		char* im = new char[imie_size + 1];
		file.get(im, imie_size + 1);
		file.read((char *)&rok, 4);
		if (!head) 
			head = new Osoba(im, rok, NULL);
		else {
			Osoba *temp = head;
			while(temp->next != NULL)
				temp = temp->next;
			temp->next = new Osoba(im, rok, NULL);
		}
		delete [] im;
	}
	file.close();
	return head;
}


int main(void) {
	cout << "Creating list:" << endl;
	Osoba* list = new Osoba("Dorota",1981,0);
	list = new Osoba("Cecylia",1982,list);
	list = new Osoba("Basia",1983,list);
	list = new Osoba("Ania",1984,list);
	showList(list);

	// piszemy liste na dysk
	writeList(list, "z_09.dat");
	cout << "\nDeleting list:" << endl;
	deleteList(list);
	list = 0; // na wszelki wypadek!

	cout << "\nRecreating list:" << endl;
	list = recreateList("z_09.dat");
	showList(list);
	cout << "\nDeleting list:" << endl;
	deleteList(list);
}
// zadanie_07
// Ocenione 9/10
// Trzeba poprawiæ - problem opisany ponizej:
/* Niektórzy z Panstwa w funkcji arrayToList tworzyli tablice wskazników:
Wezel* head = new Wezel[size];
Nie jest to w³asciwe, bo samej tablicy Panstwo nie zwalniali (a jak jest new, to
gdzies powinno byc delete). Powstawa³ przy tym trudny do wykrycia b³ad, bo
jesli pierwszym elementem listy by³ element nieparzysty, to pózniejsze (w funkcji
removeOdd) wywo³anie delete head usuwa³o ca³a tablice (bo w head by³
adres pierwszego elementu tablicy) i w dodatku nieprawid³owo, bo bez nawiasów
kwadratowych. Tymczasem adresy w sk³adowych next odnosi³y sie w³asnie
do elementów z tej, nieistniejacej juz, tablicy! Zachowanie programu stawa³o
sie wtedy nieokreslone.
*/

#include "iostream"

using namespace std;

struct Wezel {
	int dana;
	Wezel* next;
};

void showList(const Wezel* head) {
	if (! head) {
		cout << "Lista jest pusta" << endl;
		return;
	}
	while (head) {
		cout << head->dana << " ";
		head = head->next;
	}
	cout << endl;
}

Wezel* arrayToList(const int tab[], int size) {
	Wezel* head = new Wezel[size];
	for (int i = 0; i < size; i++) {
		head[i].dana = tab[i];
		(i != size - 1) ? head[i].next = &(head[i+1]) : head[i].next = NULL;
	}
	return head;
}

Wezel* removeOdd(Wezel* head) {
  if (!head)
    return 0;
  
  Wezel* startPrev = 0;
  Wezel* prev = 0;
  while(head){
    if (head->dana % 2 != 0.0){
      cout << "usuwamy: " << head->dana << endl;
      if (prev)
        prev->next = head->next;
      
      Wezel* deleteMe = head;
      head = head->next;
      
      delete deleteMe; 
    } else {       
      prev = head;
      if (!startPrev)
        startPrev = prev;
      head = head->next;
    }
  }
  
  return startPrev;
}

int main() {
	int tab[] = {7,5,2,3,4,8,1,7,2};
	int size = sizeof(tab)/sizeof(tab[0]);
	Wezel *head = arrayToList(tab, size);
	showList(head);
	head = removeOdd(head);
	showList(head);
}
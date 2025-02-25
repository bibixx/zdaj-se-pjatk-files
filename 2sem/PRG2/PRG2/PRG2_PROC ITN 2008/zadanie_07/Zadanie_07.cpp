// zadanie_07
// Ocenione 9/10
// Trzeba poprawi� - problem opisany ponizej:
/* Niekt�rzy z Panstwa w funkcji arrayToList tworzyli tablice wskaznik�w:
Wezel* head = new Wezel[size];
Nie jest to w�asciwe, bo samej tablicy Panstwo nie zwalniali (a jak jest new, to
gdzies powinno byc delete). Powstawa� przy tym trudny do wykrycia b�ad, bo
jesli pierwszym elementem listy by� element nieparzysty, to p�zniejsze (w funkcji
removeOdd) wywo�anie delete head usuwa�o ca�a tablice (bo w head by�
adres pierwszego elementu tablicy) i w dodatku nieprawid�owo, bo bez nawias�w
kwadratowych. Tymczasem adresy w sk�adowych next odnosi�y sie w�asnie
do element�w z tej, nieistniejacej juz, tablicy! Zachowanie programu stawa�o
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
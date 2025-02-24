#include <iostream>
#include <stdio.h>

using namespace std;

struct Wezel {
	int    dana;
	Wezel *next;
};

void wstaw(Wezel*& head, int nowa_dana) 
{
	Wezel *kopiaHead = head;
	Wezel *temp = new Wezel();

	if(head)
	{
		temp->dana = nowa_dana;
		temp->next = 0;
		while(kopiaHead->next)
		{
			kopiaHead = kopiaHead->next;
		}
		kopiaHead->next = temp;
	}
	else
	{
		head = temp;
		temp->dana = nowa_dana;
		temp->next = 0;
	}
}

Wezel* przestaw(const Wezel *head) 
{
	Wezel *kopia = head;
	head = head->next;
	head->next = kopia;
	

}

void printlist(const Wezel* head) 
{
		while(head->next)
		{
			head = head->next;
			cout << head->dana << " ";
		}
}

void usun(Wezel*& head) {

}

int main() 
{

	//Wezel w1;
	//Wezel w2;
	//Wezel *wskW = &w2;
	//w1.dana = 1;
	//w1.next = &w2;

	//cout << "Wartosc wezla 1: " << w1.dana << endl;
	//cout << "wskW : " << wskW << endl;
	//cout << "w1next : " << w1.next << endl;

	Wezel *head_old = 0;
	wstaw(head_old,1);
	wstaw(head_old,3);
	wstaw(head_old,2);
	wstaw(head_old,4);
	wstaw(head_old,5);
	wstaw(head_old,6);
	wstaw(head_old,7);
	printlist(head_old);
	//Wezel *head_new = przestaw(head_old);
	//printlist(head_new);
	//usun(head_old);
	//usun(head_new);

	system("PAUSE");
}
#include <iostream>
#include <stdio.h>

using namespace std;

struct Wezel
{
	int    dana;
	Wezel* next;
};

void wstaw(Wezel*& head, int nowa_dana) 
{
	Wezel* temp = new Wezel();

	if (head)
	{
		Wezel* kopiaHead = head;

		temp->dana = nowa_dana;

		while (kopiaHead->next)
		{
			kopiaHead = kopiaHead->next;
		}
		kopiaHead->next = temp;

		//delete kopiaHead;
	} 
	else
	{
		head = temp;
		head->dana = nowa_dana;
		head->next = 0;
	}
}



void printlist(const Wezel* head) {

	while (true)
	{
		cout << head->dana << " "; 
		if (head->next==NULL){break;}
		head = head->next;
	}
}

bool parzysta(const Wezel *head)
{
	int counter = 0;
	while (true)
	{
		counter++;
		if (head->next==NULL){break;}
		head = head->next;
	}
	if (counter%2){return false;}
	else{return true;}
}

Wezel* przestaw(const Wezel *head)
{
	Wezel *nowy = new Wezel();

	Wezel *kopiaNowy = nowy;


	if(parzysta(head))
	{
		while(head->next)
		{
			wstaw(kopiaNowy,head->next->dana);
			wstaw(kopiaNowy,head->dana);
			head = head->next;
			if(head->next)head = head->next;
		}
	}else
	{
		while(head->next)
		{
			wstaw(kopiaNowy,head->next->dana);
			wstaw(kopiaNowy,head->dana);
			head = head->next;
			if(head->next)
				head = head->next;
			if(!head->next)
			{
				wstaw(kopiaNowy,head->dana);
			}
		}
	}


	nowy = nowy->next;
	return nowy;
}

void usunOstatni(Wezel*& head)
{
	cout << "Jestem w head i dana=:"<< head->dana<<endl;
	if (!head->next)
	{
		cout << "Usuwam wezel nr:" << head->dana << endl;
		head = NULL;
		delete head;

	}else{
		head = head->next;
		cout << "Przechodze dalej rekurencyjnie do wezla nr: " << head->dana << endl;
		usunOstatni(head);
	}

	// szczerze mowiac to nie mam pomyslu jak zrobic zeby poprawienie kasowaly sie odpowiednie wezly, jeszcze ostatnio jako tako, ale pierwszy to nie wiem.

}

int main()
{
	Wezel *head_old =
		0;

	wstaw(head_old,1);
	wstaw(head_old,2);
	wstaw(head_old,3);
	wstaw(head_old,4);
	wstaw(head_old,5);
	wstaw(head_old,6);
	wstaw(head_old,7);
	wstaw(head_old,8);
	printlist(head_old);
	Wezel *head_new = przestaw(head_old);
	cout << " "<< endl;
	cout << "Po przestawieniu: " << endl;
	printlist(head_new);
	//usun(head_old);
	//usun(head_new);

	cout << endl;
	system("PAUSE");
}

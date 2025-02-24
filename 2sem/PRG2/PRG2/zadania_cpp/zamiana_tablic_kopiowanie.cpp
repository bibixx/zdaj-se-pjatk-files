// zamiana tablic, kopiowanie jednej tablicy do drugiej

#include <iostream>
#include <string>

using namespace std;

int* fun(int *tab1, int *tab2, int size)
{
	int x,y;
	for (int i = 0; i < size; ++i)
	{
		x = tab1[i];
		y = tab2[i];
		tab1[i] = y;
		tab2[i] = x;
	}
	return 0;
}

void printTable(int *tab, int size)
{
	int i;
	for (i = 0; i < size; ++i) cout << tab[i] << " ";
	cout << endl;
}

int main(void)
{
	int tab1[] = {1,2,3}, tab2[] = {4,5,6}, *tab3;

	cout << "tab1 przed: "; printTable(tab1,3);
	cout << "tab2 przed: "; printTable(tab2,3);
	tab3 = fun(tab1,tab2,3);
	cout << "tab1    po: "; printTable(tab1,3);
	cout << "tab2    po: "; printTable(tab2,3);
	
	system("PAUSE");
}
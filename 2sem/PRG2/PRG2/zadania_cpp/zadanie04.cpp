/*
Zadanie 4.

Prosze napisac i przetestowac funkcje typu void pobierajaca

   1. tablice wskaznikow do funkcji typu double -> double
   2. wymiar tej tablicy
   3. liczbe rzeczywista 'a'

i porzadkujaca (sortujaca) funkcje z tablicy wg. kryterium:

                     f1  <  f2

               wtedy i tylko wtedy gdy

             modul(f1(a)) < modul(f2(a))

czyli funkacja f1 jest "mniejsza" of funkcji f2 wtedy
i tylko wtedy gdy jej wartosc w punkcie 'a' jest co do
modulu mniejsza od wartosci w tym samym punkcie funkcji f2.
Dla sprawdzenia nalezy w programie glownym wypisac wartosci
kolejnych funkcji z tablicy w punkcie 'a' przed i po
wywolaniu naszej funkcji.


Jesli, na przyklad, w tablicy sa wskazniki do funkcji

      f0(x) = 5x-1,  f1(x) = -5x-1,  f2(x) = 3x+1

i wartosc 'a' wynosi 2, to wartosci tych funkcji w tym
punkcie sa 9, -11, 7, czyli ich moduly wynosza 9, 11, 7,
a wiec po uporzadkowaniu ich kolejnosc powinna byc

                    f2, f0, f1.
*/

#include <iostream>
#include <stdio.h>
#include <cmath>

using namespace std;

double f0(double x){return 5*x-1;};
double f1(double x){return -5*x-1;};
double f2(double x){return 3*x+1;};
double f3(double x){return 3*x+2;};
double f4(double x){return 3*x-3;};

double (*(tablicaWskDoFunk[]))(double) = {*f0,*f1,*f2,*f3,*f4};

void sprawdzacz(double (*(tablicaWskDoFunk[]))(double),int rozmiar, int a);

int main()
{
	int b = 10;
	sprawdzacz(tablicaWskDoFunk,5,2);
	cin >> b;
}
void wypiszAdresy(double (*(tablicaWskDoFunk[]))(double),int rozmiar, int a)
{
	for (int i=0;i<rozmiar;i++)
	{
		cout << i << ") adres: " << (tablicaWskDoFunk[i]) << " wartosc: " << tablicaWskDoFunk[i](a)<< endl;
	}
}

double (*znajdzMinimum( double (*(tablicaWskDoFunk[]))(double),int rozmiar, int zacznijOd, int a))(double)
{
	double (*funkMin)(double x) = (tablicaWskDoFunk[zacznijOd]);

	for(int i=zacznijOd;i<rozmiar-1;i++)
	{
		cout <<"Porównuje: "<<fabs((*funkMin)(a))<<" z "<<fabs((*tablicaWskDoFunk[i+1])(a)) <<endl;

		if (fabs((*funkMin)(a)) > fabs((*tablicaWskDoFunk[i+1])(a)))
		{	
			cout << "Stan tablicy PRZED zamiana:" << endl;
			wypiszAdresy(tablicaWskDoFunk,rozmiar,a);
			cout << "UWAGA ZAMIANA MIEJSCAMI!!!!" << endl;
			cout << "Zamieniam miejscami funkcje zwracaj¹ce" << (*funkMin)(a)<<" i "<<(*tablicaWskDoFunk[i+1])(a) << endl;

			double (*temp)(double x) = funkMin;
			funkMin = (tablicaWskDoFunk[i+1]);

			(tablicaWskDoFunk[i+1]) = temp;

			cout << "Stan tablicy PO zamianie:" << endl;
			wypiszAdresy(tablicaWskDoFunk,rozmiar,a);
		} 
	}
	cout << "Najmniejsza wartosc: " << ((*funkMin)(a))<< endl;
	return funkMin;
};

void sprawdzacz(double (*(tablicaWskDoFunk[]))(double),int rozmiar, int a)
{
	double (*funkMin)(double x) = (tablicaWskDoFunk[0]);

	cout << "Przed sortowaniem:" << endl;
	for (int i=0;i<rozmiar;i++)
	{
		cout << tablicaWskDoFunk[i](a)<< endl;
	}

	int indeks=0;
	for (int i=0;i<rozmiar;i++)
	{
		cout << "============INDEKS:"<< indeks << endl;
		(tablicaWskDoFunk[indeks]) = znajdzMinimum(tablicaWskDoFunk,rozmiar,i,a);
		indeks++;
	}

	cout << "================================================" << endl;
	cout << "================================================" << endl;
	cout << "Po sortowaniu :" << endl;
	for (int i=0;i<rozmiar;i++)
	{
		cout << fabs(tablicaWskDoFunk[i](a))<< endl;
	}
}

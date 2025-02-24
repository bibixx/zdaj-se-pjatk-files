#include <iostream>
#include <stdio.h>
#include <math.h>

using namespace std;

double f0(double x){return 5*x-1;};
double f1(double x){return -5*x-1;};
double f2(double x){return 3*x+1;};
double f3(double x){return 3*x+2;};
double f4(double x){return 3*x-3;};

double (*(tablicaWskDoFunk[]))(double) = {*f0,*f1,*f2,*f3,*f4};

void sprawdzacz(double (*(tablicaWskDoFunk[]))(double),int rozmiar, int a);


//double (*wskDof0)(double x) = *f0;
//double (*wskDof2)(double x) = *f2;
//double (*tablicaWskDoFunk[])(double x) = {*wskDof0,*wskDof1,*wskDof2};

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
	//cout << "Zaczynam od: "<< zacznijOd << endl;
	//cout << "Wskaznik pokazuje na: "<< (funkMin) << ",sprawdzenie "<<(tablicaWskDoFunk[zacznijOd])<<endl;
	//cout << "CZYLI WARTOSCI:" << endl;
	//cout << "Wskaznik pokazuje na: "<< ((*funkMin)(a))<< ",sprawdzenie "<<tablicaWskDoFunk[zacznijOd](a)<<endl;
	//cout <<"Rozmiar: "<< rozmiar <<endl;

	//for (int i=zacznijOd;i<rozmiar;i++)
	for(int i=zacznijOd;i<rozmiar-1;i++)
	{
		cout <<"Porównuje: "<<abs((*funkMin)(a))<<" z "<<abs((*tablicaWskDoFunk[i+1])(a)) <<endl;

		if (abs((*funkMin)(a))>abs((*tablicaWskDoFunk[i+1])(a)))
		{	
			cout << "Stan tablicy PRZED zamiana:" << endl;
			wypiszAdresy(tablicaWskDoFunk,rozmiar,a);
			cout << "UWAGA ZAMIANA MIEJSCAMI!!!!" << endl;
			cout << "Zamieniam miejscami funkcje zwracaj¹ce" << (*funkMin)(a)<<" i "<<(*tablicaWskDoFunk[i+1])(a) << endl;

			double (*temp)(double x) = funkMin;
			funkMin = (tablicaWskDoFunk[i+1]);
			/*		
			cout << "Stan tablicy W TRAKCIE zamiany:" << endl;
			wypiszAdresy(tablicaWskDoFunk,rozmiar,a);	*/

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
	//double (*(temp[3]))(double); 

	cout << "Przed sortowaniem:" << endl;
	for (int i=0;i<rozmiar;i++)
	{
		cout << tablicaWskDoFunk[i](a)<< endl;
	}


	int indeks=0;
	for (int i=0;i<rozmiar;i++)
	{
		//if (abs((*tablicaWskDoFunk[i])(a))>abs((*tablicaWskDoFunk[i+1])(a)))
		//{
		//funkMin = (tablicaWskDoFunk[i]);
		//(tablicaWskDoFunk[indeks]) = (tablicaWskDoFunk[i+1]);
		//indeks++;
		cout << "============INDEKS:"<< indeks << endl;
		(tablicaWskDoFunk[indeks]) = znajdzMinimum(tablicaWskDoFunk,rozmiar,i,a);
		indeks++;

		//} 
	}

	//cout << endl;
	//cout << "Najwieksza wartosc: " << ((*funkMin)(a)) << endl;
	//cout << endl;
	cout << "================================================" << endl;
	cout << "================================================" << endl;
	cout << "Po sortowaniu :" << endl;
	for (int i=0;i<rozmiar;i++)
	{
		cout << abs(tablicaWskDoFunk[i](a))<< endl;
	}
}





/*#include <iostream>
#include <stdio.h>
#include <math.h>

using namespace std;

double f0(double x){return 5*x-1;};
double f1(double x){return -5*x-1;};
double f2(double x){return 3*x+1;};

double (*(tablicaWskDoFunk[]))(double) = {*f0,*f1,*f2};

void sprawdzacz(double (*(tablicaWskDoFunk[]))(double),int rozmiar, int a);


//double (*wskDof0)(double x) = *f0;

//double (*wskDof2)(double x) = *f2;
//double (*tablicaWskDoFunk[])(double x) = {*wskDof0,*wskDof1,*wskDof2};

int main()
{
	int b = 10;
	
	sprawdzacz(tablicaWskDoFunk,3,2);

	cin >> b;
}

void sprawdzacz(double (*(tablicaWskDoFunk[]))(double),int rozmiar, int a)
{
	double (*funkMin)(double x) = (tablicaWskDoFunk[0]);
	double (*(temp[3]))(double); 
	
	cout << "Przed sortowaniem:" << endl;
	for (int i=0;i<rozmiar;i++)
	{
		cout << tablicaWskDoFunk[i](a)<< endl;
	}

	for (int i=1;i<rozmiar;i++)
	{
		if (abs((*funkMin)(a))>abs((*tablicaWskDoFunk[i])(a)))
		{
			funkMin = (tablicaWskDoFunk[i]);
		} 
	}
	
	cout << endl;
	cout << "Najwieksza wartosc: " << ((*funkMin)(a)) << endl;
	cout << endl;
	
	cout << "Po sortowaniu:" << endl;
	for (int i=0;i<rozmiar;i++)
	{
		cout << tablicaWskDoFunk[i](a)<< endl;
	}
}

*/
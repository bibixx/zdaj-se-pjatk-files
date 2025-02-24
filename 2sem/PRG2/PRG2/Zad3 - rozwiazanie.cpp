// This is the main project file for VC++ application project 
// generated using an Application Wizard.

#include "stdafx.h"

#using <mscorlib.dll>

using namespace System;

int fun(int *tab, int size, int& mn, int& mx) {
	int *pom;
	pom = (int*)malloc(size * sizeof(int));
	int krok = 0;
	int ile_minus = 0;
	int ile_plus = 0;
	int max = tab[0];
	int min = tab[0];
	while(krok < size) {
		int x;
		x = tab[krok];
		if(x < 0) {
			if(min > x)
				min = x;
			pom[ile_minus] = x;
			ile_minus++;
		}
		else {
			if(max < x)
				max = x;
			pom[size - ile_plus - 1] = x;
			ile_plus++;
		}
		krok++;
	}
	mn = min;
	mx = max;
	for(int i = 0; i < size; i++) {
	tab[i] = pom[i];
	}
	
	return ile_minus;
}

int _tmain()
{
	int size = 5;
	int t[] = {1, -1, -2, 3, -7};
	int max, min;
	int &rmax = max, &rmin = min;
	int indeks = 0;
	

	indeks = fun(t, size, rmin, rmax);
	
	for(int i = 0; i < size; i++) {
		printf("%d ", t[i]);
		printf("\n");
	}
	printf("min: %d\n", min);
	printf("max: %d\n", max);
	printf("indeks: %d", indeks);
	printf("\n");
	return 0;
}
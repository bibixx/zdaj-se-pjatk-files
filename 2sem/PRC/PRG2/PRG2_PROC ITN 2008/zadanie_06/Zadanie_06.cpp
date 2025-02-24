// Zadanie_06
// Ocenione 10/10

#include <iostream>
#include <cstdlib>

using namespace std;

enum Banki {PKO, BGZ, BRE, BPH};

struct Konto {
  Banki bank;
  int stan;
};

struct Osoba {
  char imie[20];
  Konto konto;
};

struct Para {
  Osoba pan;
  Osoba pani;
};

inline int ComputeStan(Para* para){
  return para->pan.konto.stan + para->pani.konto.stan;
}

inline bool CheckBank(Para* para, Banki bank){
  return (para->pan.konto.bank == bank || 
          para->pani.konto.bank == bank);
}

Para* bestClient(Para* ppary, int size, Banki bank){
  Para* maxPara = 0;
  for (int i=0; i < size; i++){
    Para* currentPara = &ppary[i];
	if (!CheckBank(currentPara, bank)){
      continue;
	}
	if (!maxPara){
      maxPara = currentPara;
	} else {
		if (ComputeStan(maxPara) <= ComputeStan(currentPara)){
			maxPara = currentPara;
		}
	}
  }
  return maxPara;
}

int main(){
  Para pary[] = { {{"Jan",   {PKO, 1200}}, {"Maria", {BGZ, 1100}}},
				  {{"Karol", {BGZ, 1400}}, {"Basia", {BRE, 1300}}},
				  {{"Wacek", {PKO, 1600}}, {"Kasia", {BPH, 1500}}},
				  {{"Zenek", {BPH, 1800}}, {"Lusia", {BRE, 1700}}},
				};
					
  Para* p = bestClient(pary,4,BGZ);
  
  if (p != NULL)
	cout << p->pan.imie << " i " << p->pani.imie << ": " << ComputeStan(p) << endl;
  cin.get();
}
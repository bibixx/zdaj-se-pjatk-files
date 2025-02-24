// Zadanie 02
// Ocenione 10/10

#include <iostream>
using namespace std;

// ++++++++++++
// + Sposob 1 +
// ++++++++++++

inline bool checkIfRightChar(char n) {
  if ((n > 64 && n < 91) || (n > 96 && n < 123)) {
    return true;
  } else {
    return false;
  }
}

void pierwsza(const char* nap1, const char* nap2){
  const char* pNap1 = nap1;
  
  while(*pNap1 != 0x00){
    //sprawdzamy czy litera poprawna
    if (checkIfRightChar(*pNap1)){
      
      //sprawdzamy czy jest w nap2
      const char* pNap2 = nap2;
      while(*pNap2 != 0x00){
                   
        if (*pNap2 == *pNap1){
          //sprawdzamy czy juz nie wystapila
          const char* pNap3 = nap1;
          bool exists = false;
        
          while (*pNap3 != 0x00 && ((int)pNap3 < (int)pNap1)){
            if (*pNap3 == *pNap1){
              exists = true;
              break;
            }
            pNap3++;
          }
          // nie wystapila - wypisujemy
          if (!exists)
            cout << *pNap1;
	  
          break;
        }
        
        pNap2++; 
      }    
    }
    
    pNap1++;
  }
}

void druga(const char* nap1, const char* nap2){
  const char* pNap1 = nap1;
  
  while(*pNap1 != 0x00){
    //sprawdzamy czy litera poprawna
    if (checkIfRightChar(*pNap1)){
      
      //sprawdzamy czy jest w nap2
      const char* pNap2 = nap2;
      bool exists = false;
      while(*pNap2 != 0x00){
        if (*pNap2 == *pNap1){
          exists = true;
          break;   
        }  
        pNap2++;          
      }
      
      if (!exists){ 
        //sprawdzamy czy juz nie wystapila
        pNap2 = nap1;
        exists = false;
        
        while (*pNap2 != 0x00 && ((int)pNap2 < (int)pNap1)){
          if (*pNap2 == *pNap1){
            exists = true;
            break;
          }
          pNap2++;
        }
	//nie wystapila - wypisujemy
        if (!exists)
          cout << *pNap1;      
      }
    }
    
    pNap1++;
  }
    
}

void wypisz_wyniki1(const char* nap1, const char* nap2){
  cout << "Line 1: ";
  pierwsza(nap1, nap2);
  cout << endl;
  cout << "Line 2: ";
  druga(nap1, nap2);
  cout << endl;
}

// ++++++++++++
// + Sposob 2 +
// ++++++++++++

void wypisz_wyniki2(const char* n1, const char* n2){
  unsigned short int i,j = 0;
  short int f1,f2 = 0;
  // pierwsza linia
  cout << "Line 1: ";
  for (i = 65;i < 123; i++){
    j = f1 = f2 = 0;

    // omijamy niepotrzebne znaki
    if (i == 91) i = 97;

    // sprawdzamy dla znaku z nap1 lub nap2
    while ((*(n1 + j)) || (*(n2 + j))){
      // zaznaczamy jesli nastapil koniec nap1
      if ((!f1) && (*(n1 + j) == '\0')) f1 = -1;
      // zaznaczamy jesli nastapil koniec nap2
      if ((!f2) && (*(n2 + j) == '\0')) f2 = -1;

      // sprawdzamy czy litera wystepuje w nap1
      if ((f1 == 0) && (*(n1 + j)== i)) f1 = 1;

      // sprawdzamy czy litera wystepuje w nap2
      if ((f2 == 0) && (*(n2 + j) == i)) f2 = 1;

      // wypisujemy jesli litera wystepuje w obydwu napisach
      if ((f1 == 1) && (f2 == 1)){
	cout << (char) i;
	break;
      }

      j++;
    }
  }

  cout << endl;

  // druga linia
  cout << "Line 2: ";
  for (i = 65;i < 123; i++){
    j = f1 = f2 = 0;
    
    // omijamy niepotrzebne znaki
    if (i == 91) i = 97;
    
    // sprawdzamy dla znaku z nap1 lub nap2
    while ((*(n1 + j)) || (*(n2 + j))){
      // zaznaczamy jesli nastapil koniec nap1
      if ((!f1) && (*(n1 + j) == '\0')) f1 = -1;
      // zaznaczamy jesli nastapil koniec nap2
      if ((!f2) && (*(n2 + j) == '\0')) f2 = -1;
      
      // sprawdzamy czy litera wystepuje w nap1
      if ((f1 == 0) && (*(n1 + j)== i)) f1 = 1;
      
      // sprawdzamy czy litera wystepuje w nap2
      if ((f2 == 0) && (*(n2 + j) == i)) f2 = 1;

      // konczymy jesli znak wystepuje w obydwu napisach
      if ((f1 == 1) && (f2 == 1)) break;

      j++;
    }
    
    // wypisujemy jesli litera wystepuje w nap1
    // i nie wystepuje w nap2
    if ((f1 == 1) && (f2 != 1)) cout << (char) i;
  }
  
  cout << endl;
}


int main() {
  char nap1[] = "abAAc#$%^&* &deAfASD";
  char nap2[] = "aAAA SDA";
  
  cout << endl << "Sposob pierwszy:" << endl;
  wypisz_wyniki1(nap1, nap2);
  cout << endl << "Sposob drugi:" << endl;
  wypisz_wyniki2(nap1, nap2);
}

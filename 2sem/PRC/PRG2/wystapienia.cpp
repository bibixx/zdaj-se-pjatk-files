#include <iostream>
using namespace std;

void szukaj_litery(char* napis, char litera,
                   char** first, char** last) {
    
    first = NULL;
    last = NULL;
    cout << napis << endl;
    for(int licznik=0; licznik < sizeof(licznik); licznik++)
    {
            if(napis[licznik]==litera)
            {
                 if(first==NULL)
                 {
             
                 first = &napis; 
           
                 }
                 else
                 {
  
                 last = &napis;
                 }                     
            }
            
    }
        
    
}

int main() {
    char* napis = "Jakis napis z roznymi literami";
    char *pocz, *konc;
    char litera;

    cout << "Podaj jedna litere: ";
    cin  >> litera;

    szukaj_litery(napis, litera, &pocz, &konc);
    cout << "Pocz: " << pocz << endl;
    cin >> litera ;
    
    if (pocz != 0) {
        cout << "Pierwsze wystapienie \'" << litera
             << "\': " << pocz << endl;
        if (konc != 0)
            cout << "Ostatnie wystapienie \'" << litera
                 << "\': " << konc << endl;
        else
            cout << "Litera \'" << litera
                 << "\' wystepuje tylko raz"  << endl;
    } else
        cout << "Litera \'" << litera
             << "\' nie wystepuje" << endl;

}

#include <iostream>
#include <conio.h>
#include <stdlib.h>

using namespace std;


int wieksza(int a, int b)
{
if(a>b) return a;
else return b;
}

int mniejsza(int a, int b)
{
if(a<b) return a;
else return b;
}

void wypisz(int tab[], int rozmiar)
{
if(rozmiar){
cout << "Wynik: ";
for(int i = 0; i<rozmiar ; i++)
{
 cout << " " << tab[i];
}
cout << endl <<"To tyle :D" << endl;
}else
cout << "Nie ma nic !!! " << endl;
}

int main(void)
{
   int x,y;
   int dziel_x = 2;
   int dziel_y = 2;
   int temp_tab = 0;
   int tab_wynikow[20];
   bool enable = true;

   while(enable){
   cout << "Podaj pierwsza liczbe : " ;
   cin >> x;
   cout << "Podaj druga liczbe : " ;
   cin >> y;
   
   if( x == 0 || y == 0)
   {

       cout << "jedna z liczb to 0 ! koncze program..."<< endl; ;
       system("EXIT");
   }
   else {

      if(wieksza(x,y)%mniejsza(x,y)==0)
      {
       cout << "dziela sie bez reszty" <<endl;
       tab_wynikow[temp_tab] = mniejsza(x,y);
       temp_tab++;
      }

      while(x != 1 && y != 1) {


               if (x%dziel_x) dziel_x++;
               else x = x/dziel_x ;
               if (y%dziel_y) dziel_y++ ;
               else y = y/dziel_y ;
               if (dziel_x == dziel_y)
               {
                 tab_wynikow[temp_tab] =  dziel_x;
                 temp_tab++;
               }
      }

      wypisz(tab_wynikow,temp_tab);

   }
   cout << "Juz koniec czy jeszcze chcesz cos sprawdzic ?\n(1 - tak fajna zabawa :D, 0 - nie mam dosyc)" << endl;
   cin >> enable;
   }

   system("PAUSE");
   return 0;
     
}



// dostalem 7/10 pkt za to

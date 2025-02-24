#include <iostream>

using namespace std;

int fun(int*, int , int&, int&);

int main(){

   int koniec;
   int minim,maxim;
   int t[] = {1,-1,2,3,-7};
   int ind = fun(t,5,minim,maxim);

   cout << "Return: " << ind <<  endl;
   cout << "Minimum: " << minim <<  endl;
   cout << "Maximum: " << maxim <<  endl;
   cin >> koniec;

return 0;

}

int fun(int* tab, int size, int& mn, int& mx)
{
int min;
int max;

int min_index =0;
int max_index = size - 1;
int tablica[5];

   for(int i=0; i<size; i++)
   {
       if(i==0)
       {
       min = tab[i];
       max = tab[i];
       }
       else
       {
           if(tab[i]<min)
               min = tab[i];

           if(tab[i]>max)
               max = tab[i];

                  }

       if(tab[i]<0)
       {
           tablica[min_index] = tab[i];
           min_index++;

       }
       else
       {
           tablica[max_index] = tab[i];
           max_index--;

       }

   }

   for(int j=0; j<size; j++)
       cout << "tablica[" << j << "]  " << tablica[j] << endl;

   mn = min;
   mx = max;

   if(min_index == 0)
   {
       return 0;
   }
   else
   {
       if((max_index+1) == size)
           return size;
       else
           return tablica[max_index+1];
   }

         
   }

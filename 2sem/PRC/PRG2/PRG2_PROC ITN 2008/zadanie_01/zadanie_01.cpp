// Zadanie 01
// Ocenione 10/10

#if defined(STAR) && defined(EQU)
   #error Define only one symbol to compile
#elif !(defined(STAR) || defined(EQU))
   #error Define ONE symbol (STAR or EQU)
#endif

#if defined(STAR)
#define znak '*'
#elif defined(EQU)
#define znak '='
#endif

#include <iostream>
using namespace std;

int main()
{
  int a,b,c,max;
  cout << "Podaj 3 liczby naturalne ";
  cin  >> a >> b >> c;
  if (a >= b && a >= c) { max=a; }
  else if (b >= a && b >= c) { max=b; }
  else { max=c; }

  while (max > 0) {
    if (a >= max) {
      cout << znak << " ";
    } else {
      cout << "  ";
    }
    if (b >= max) {
      cout << znak << " ";
    } else {
      cout << "  ";
    }
    if (c >= max) {
      cout << znak << " " << endl;
    } else {
      cout << "  " << endl;
    }
    max--;
  }
}

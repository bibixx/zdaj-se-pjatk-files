// Zadanie_05
// Ocenione 10/10

#include <iostream>
#include <cmath>

using namespace std;

typedef double (*FUN)(double);
typedef FUN FUNS[];
double fmed(FUNS funs, int size, double a, double b);

double fmed(FUNS funs, int size, double a, double b) {
  double min = funs[0](a);
  double max = funs[0](b);
  int min_idx = 0;
  int max_idx = 0;
  for (int i = 1; i < size; i++) {
    if (double t_min = funs[i](a) < min) {
      min = t_min;
      min_idx = i;
    }
    if (double t_max = funs[i](b) > max) {
      max = t_max;
      max_idx = i;
    }
  }
  //cout << "funs[min_idx](a): " << funs[min_idx](a) << " min_idx: " << min_idx << endl;
  //cout << "funs[max_idx](b): " << funs[max_idx](b) << " max_idx: " << max_idx << endl;
  double c_point = (a + b) / 2;
  return (funs[min_idx](c_point) + funs[max_idx](c_point)) / 2;
}

int main() {
  FUNS funs = {sin,cos,erf};
  double w = fmed(funs,3,-0.5,0.5);
  cout << w << endl;
}

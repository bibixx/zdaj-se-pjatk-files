#include <iostream>

using namespace std;

int main(void)
{
	char **v;
	char *t[] = {"abcd", "efghi", "jklmno"};

	v = t;

	cout << "v = " << v << endl;
	cout << "v+2 = " << v+2 << endl;
	cout << "v[2] = " << v[2] << endl;
	cout << "*(v+2) = " << *(v+2) << endl;

	cout << "*(*(t+1)+2) = " << *(*(t+1)+2) << endl;
	
	system("PAUSE");
	
	return 0;
}
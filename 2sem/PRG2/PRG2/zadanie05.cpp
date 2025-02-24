#include <iostream>
#include <stdio.h>

using namespace std;

bool isWhite(char c) {
	return c == '\t' || c == '\n' || c == '\r' || c == ' ';
}

int charCounter(const char* c)
{
	int ile = 0;
	while(c[ile++]!='\0'){}
	//liczy tez ze znakiem konczacym string
	return ile;
}

void charCopier(char* dest, const char* source, int j)
{
	for (int i=0;i<j;i++)
	{
		dest[i] = source[i];
	}
}

char* clean(const char* napis)
{
	char* tempChar = new char[charCounter(napis)];
	bool tryb = false;
	bool znaki = false;

	int j = 0;
	for(int i=0;i<charCounter(napis);i++)
	{
		if (!isWhite(napis[i]))
		{
			tempChar[j] = napis[i];
			j++;
			tryb = true;

		}else
		{
			if(tryb)
			{
				tempChar[j] = ' ';
				j++;
				tryb = false;
			} 
		}
	}

	char* retChar = new char[charCounter(tempChar)];
	charCopier(retChar,tempChar,charCounter(tempChar));
	delete tempChar;

	return retChar;
}

int main()
{
	char *napis, *res;

	napis = "  \n Jeden, \r\t   dwa  trzy. ";
	res = clean(napis);
	cout << ">>" << res << "<<" << endl;
	delete [] res;

	napis = "Jeden,\r\tdwa\ttrzy. ";
	res = clean(napis);
	cout << ">>" << res << "<<" << endl;
	delete [] res;

	napis = "\r\t\t ";
	res = clean(napis);
	cout << ">>" << res << "<<" << endl;
	delete [] res;

	napis = "";
	res = clean(napis);
	cout << ">>" << res << "<<" << endl;
	delete [] res;

	system("PAUSE");
}

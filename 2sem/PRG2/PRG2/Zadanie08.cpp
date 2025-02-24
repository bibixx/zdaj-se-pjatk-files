#include <iostream>
#include <string>
#include <assert.h>

using namespace std;

class MyString
{
public:

	int max_rozm;
	size_t dlugosc;
	int pojemnosc;
	char *napis;

	MyString()
	{
		max_rozm = 1024;
		dlugosc = 0;
		pojemnosc = 1;
		napis = new char[pojemnosc];
		napis[0] = '\0';
	}

	~MyString()
	{
		delete []napis;
	}

	MyString(const char *n)
	{
		assert(n!=0);
		dlugosc = strlen(n);
		pojemnosc = dlugosc + 1;
		napis = new char[pojemnosc];
		strcpy(napis, n);
	}

	MyString(const MyString &str)
	{
		dlugosc = str.dlugosc;
		pojemnosc = dlugosc + 1;
		napis = new char[pojemnosc];
		strcpy(napis, str.napis);
	}

	size_t length() const 
	{
		return dlugosc;
	}

	 MyString& operator=(const MyString &str)
	 {
		 if (this != &str)                        
		 {
			 
			 if (pojemnosc < str.length() + 1)    
			 { 
				 delete[] napis;                  
				 pojemnosc = str.length() + 1;       
				 napis = new char[pojemnosc];
			 }

			 dlugosc = str.length();
			 strcpy(napis,str.napis);
		 }

		 return *this;  
	 }



	MyString& operator+=(const MyString &str)
	{
		MyString copystring(str);

		int newLength = length() + str.length(); 
		int lastLocation = length();

		if (newLength >= pojemnosc)
		{
			pojemnosc = newLength + 1;
			char * newBuffer = new char[pojemnosc];
			strcpy(newBuffer,napis);
			delete [] napis;
			napis = newBuffer;
		}
 
		strcpy(napis+lastLocation,copystring.c_str() );
		dlugosc = newLength;

		return *this;
	}


	MyString operator+(const MyString &str)
	{
		return (*this+=str);
	}
	
	const char* c_str() const
	{
		return napis;
	}

};

ostream& operator << (ostream & os, const MyString &str)
{
	os << str.napis;
	return os;
}

bool operator==(const MyString& lewy, const MyString& prawy)
{
	return strcmp(lewy.c_str(), prawy.c_str()) == 0;
}


int main()
{
	MyString s1("Ala ma ");
	MyString  s2("kota");
	s2  +=  ".";
	MyString  s3 = s1 + s2;
	
	//s3 = s3; // sic!
	if (s3 == "Ala ma kota.")
		cout << s3 << " | ma " << s3.length()
		<< " znakow" << endl;

	system("PAUSE");
}

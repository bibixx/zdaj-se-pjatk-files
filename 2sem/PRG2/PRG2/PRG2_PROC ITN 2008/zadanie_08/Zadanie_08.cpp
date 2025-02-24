// Zadanie 08
// Ocenione 10/10

#include <cstdlib>
#include <iostream>
#include <stdexcept>
#include <cstring>
#include <cctype>

using namespace std;

class String{
    char* nap;
  public:
    explicit String(const char* n =  ""); //konstruktor domyslny 
    String(const String& s); //konstruktor kopiujacy
    virtual ~String() throw();
    //przypisanie
    String& operator=(const char* s);
    String& operator=(const String& s);
    //konkatenacja
    String operator+(const String& s) const;
    String operator+(const char* n) const;
    friend String operator+(const char* cstr, const String& mystr);
    //porownywanie
	bool operator==(const char* s) const;
	bool operator!=(const char* s) const;
    bool operator==(const String& s) const;
    bool operator!=(const String& s) const;
    //operacje
    String& toLower();
    String& toUpper();
    //dlugosc
    size_t length() const;
    //strumienie
    friend std::ostream& operator<<(std::ostream&, const String&);
};
  
String operator+(const char* cstr, const String& mystr);
std::ostream& operator<<(std::ostream& stream, const String& str);
  

String::String(const char* n /*= ""*/): nap(0){
  if (!n)
    throw std::invalid_argument("null pointer exception !");
  
  size_t s = strlen(n);
  
  if (s != 0){
    nap = new char[++s];
    memset(nap, 0, s);
    strcpy(nap, n);
  }
}

String::String(const String& s): nap(0){
  *this = s;
}

String::~String() throw() {
  delete []nap;            
}

String& String::operator=(const char* s){
  size_t total = strlen(s)+1;
  // mozliwy wyjatek
  char* newNap = new char[total];
  memset(newNap, 0, total); 
  strcpy(newNap, s);
  delete []nap;
  nap = newNap;
  
  return *this;            
}

String& String::operator=(const String& s){
  if (this == &s)
    return *this;
  
  return operator=(s.nap);      
}

String String::operator+(const String& s) const{
  return operator+(s.nap);    
}

String String::operator+(const char* n) const{
  if (!n)
    throw std::invalid_argument("null pointer exception !");
  
  if (strlen(n) == 0)
    return String(*this);
      
  size_t f = strlen(nap);
  size_t s = strlen(n);
  size_t total = f+s+1;
  
  char* temp = new char[total];    
  memset(temp, 0, total);
  
  strncpy(temp, nap, f);
  strncpy(temp+f, n, s);
  
  String ret(temp);
  delete []temp;
  
  return ret;       
}

bool String::operator==(const char* s) const{
  return (strcmp(nap, s) == 0);     
}

bool String::operator!=(const char* s) const{
  return !operator==(s);     
}
      
bool String::operator==(const String& s) const{
  return (strcmp(nap, s.nap) == 0);     
}

bool String::operator!=(const String& s) const{
  return !operator==(s);     
}

String& String::toLower(){
  for (unsigned int i=0; i < length(); i++)
    nap[i] = tolower(nap[i]);
  
  return *this;          
}

String& String::toUpper(){
	for (unsigned int i=0; i < length(); i++)
    nap[i] = toupper(nap[i]);
  
  return *this;  
}

size_t String::length() const{
  return strlen(nap);
}

String operator+(const char* cstr,const String& mystr){
  String ret(cstr);
  return (ret + mystr);        
}

std::ostream& operator<<(std::ostream& stream, const String& str){
  return stream << str.nap;                  
}

int main(){
  String s = "ALA " + String("ma ") + "kota";
  cout << s << endl;

  if (s == "ALA ma kota")
    cout << s.toUpper() << endl;
  s = s = "KONIEC PROGRAMU"; // <- sic!
  cout << s.length() << " " << s << endl;
  return EXIT_SUCCESS;
}
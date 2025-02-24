#include <iostream>
#include <cstring>
#include <stdio.h>

using namespace std;

class Tab {
	int size;
	const int *t;
public:
		Tab(const int* tt, int s){

			size = s;
			t = tt;

		}

		Tab(const Tab& tab){
			size = tab.size; 
			t = tab.t;
		}

		~Tab(){

			cout << "destruktor wywolany !" << endl;

		}

		void add(const Tab& tab){
			int nowyRozm = this->size + tab.size;
			int *Ntablica = new int [nowyRozm];
			int j = 0;
			for (int i=0;i<nowyRozm;i++)
			{
				if (i<this->size){
					Ntablica[i] = this->t[i];
				}
				if (i>=this->size)
				{
						Ntablica[i] = tab.t[j++];
				}
			}

			this->t = Ntablica;
			this->size = nowyRozm;
		}

		void resize(int new_size){
			int nowyRozm =new_size;
			int *tempTab = new int [nowyRozm];

			if (new_size > this->size)
			{
				

				for (int i=0;i<nowyRozm;i++)
				{
					if (i<this->size)
					{
						tempTab[i] = this->t[i];
						
					}else
						tempTab[i] = 0;
						
				}
				this->t = tempTab;
				this->size = nowyRozm;
			}else this->size = new_size;

		}

		void print() const {
			cout << "size=" << size << ": ";
			for (int i = 0; i < size; ++i) cout << " " << t[i];
			cout << endl;
	}
};

int main() {
    int t1[] = {1,2,3,4,5},
        t2[] = {11,12,13,14,15};

    Tab tab1(t1,5);
    tab1.resize(7);

    Tab tab2(t2,5);
    tab2.resize(10);

    tab1.add(tab2);
    tab1.resize(13);

    tab1.print();
    tab2.print();

	system("PAUSE");
}

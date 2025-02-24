//#include <cstdlib>  //do pause (przydatne w windzie)
#include <iostream>

using namespace std;

typedef int DATA;

class Queue
{
  struct Node
  {
    DATA data;
    Node* next;
    Node(DATA data, Node* next = 0)
    {
      this->data = data;
      this->next = next;
      //...
    }
  };
  Node* head;
  Node* tail;
public:
  Queue()
  {
    head = NULL;
    tail = NULL;
    //...
  }
  
  bool empty() const
  {
  /*
  if(head == NULL)
    return true;
  else
    return false;
  */ //rownowazne temu nizej
    return head == NULL;
  //...czy kolejka jest pusta?
  }
  
  void enque(DATA data)
  {
    Node* p = new Node(data);
    if( !empty() )  //if(head) | if (head!=NULL)
      tail->next = p;
    else
      head = p;
    tail = p;
    //...Dodoaj wezel z danymi 'data'
    //   na koniec kolejki
  }
  
  DATA deque()
  {
    //int del;
    DATA del;
    if( !empty() )//if (head) | if (head!=NULL)
    {
      Node* p = head;
      del = head->data;
      head = head->next;
      delete p;
      if( empty() )//if (!head) | if (head==NULL)
        tail = NULL;
    }
    return del;
    //...Usun pierwszy wezel kolejki
    //   i zwroc dane tego wezla
  }
  
  ~Queue()
  {
    cout << "DELETING";
    Node* tmp;
    while(tmp = head)
    {
      head = head->next;
      cout << " " << tmp->data;    
      delete tmp;
    }
    cout << endl;
    //...destruktor
  }

  //zaprzyjazniony operator<<
  friend ostream& operator<<(ostream& str, const Queue& q)
  {
    Node* node = q.head;
    do
      str << " " << node->data;
    while(node=node->next);
/*  while(node!=NULL) //rownowazne temu z gory
    {
      str << " " << node->data;
      node=node->next;
    }
*/
    delete node;
    return str << endl;
  }
};

int main()
{
  Queue* q = new Queue;
  q->enque(6);
  q->enque(5);
  q->enque(5);
  q->enque(1);
  cout << *q;
  cout << "Zdjete: " << q->deque() << endl;
  cout << *q;
  delete q;

//te 2 linijki nizej przydatne tylko dla windy  
//  system("PAUSE");
//  return EXIT_SUCCESS;
}


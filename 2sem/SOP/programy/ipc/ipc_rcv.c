#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/msg.h>


// struktura kt�ra b�dzie odbierana
typedef struct {
   long type;
   char body[100];
} msg;

int main(){
   msg wiad; // zmienna do kt�rej b�d� odbierane dane
   int id, result;
   strcpy(wiad.body, ""); // wyczyszczenie tre�ci wiadomo�ci
   id = msgget(1642,0); // pobranie identyfikatora kolejki 
   if(id < 0){ // je�li nie uda�o si� otworzy�
      printf("Msggetrcv: %d\n", id); // to wywal komunikat
      exit(1); // i wyjd� z programu
   }
   result = msgrcv(id, &wiad, sizeof(wiad.body), 0, 0); // odczytaj wiadomo��
   // id - id kolejki
   // &wiad - referencja do zniemmej w kt�rej b�d� zapisane odczytane dane
   // sizeof(...) d�ugo�� danych
   // 0, 0 - nie wa�ne
   wiad.body[result] = 0; // dodanie zera na ko�cu odczytanej wiadomo�ci
   // znak o kodzie ASCII 0 oznacza koniec ci�gu tekstowego
   // w result jest ilo�� odczytanych znak�w tak wi�c body[result] wskazuje
   // na znak nast�pny po ko�cu komunikatu
   printf("%d: %s\n", result, wiad.body); // wy�wietlenie ilo�ci znak�w
   // i samego komunikatu
   msgctl(id, IPC_RMID, (struct msqid_ds *)0); // Usuni�cie wiadomo�ci z kolejki
   return 0;
}

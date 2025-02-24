#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/msg.h>


// struktura która bêdzie odbierana
typedef struct {
   long type;
   char body[100];
} msg;

int main(){
   msg wiad; // zmienna do której bêd± odbierane dane
   int id, result;
   strcpy(wiad.body, ""); // wyczyszczenie tre¶ci wiadomo¶ci
   id = msgget(1642,0); // pobranie identyfikatora kolejki 
   if(id < 0){ // je¶li nie uda³o siê otworzyæ
      printf("Msggetrcv: %d\n", id); // to wywal komunikat
      exit(1); // i wyjd¼ z programu
   }
   result = msgrcv(id, &wiad, sizeof(wiad.body), 0, 0); // odczytaj wiadomo¶æ
   // id - id kolejki
   // &wiad - referencja do zniemmej w której bêd± zapisane odczytane dane
   // sizeof(...) d³ugo¶æ danych
   // 0, 0 - nie wa¿ne
   wiad.body[result] = 0; // dodanie zera na koñcu odczytanej wiadomo¶ci
   // znak o kodzie ASCII 0 oznacza koniec ci±gu tekstowego
   // w result jest ilo¶æ odczytanych znaków tak wiêc body[result] wskazuje
   // na znak nastêpny po koñcu komunikatu
   printf("%d: %s\n", result, wiad.body); // wy¶wietlenie ilo¶ci znaków
   // i samego komunikatu
   msgctl(id, IPC_RMID, (struct msqid_ds *)0); // Usuniêcie wiadomo¶ci z kolejki
   return 0;
}

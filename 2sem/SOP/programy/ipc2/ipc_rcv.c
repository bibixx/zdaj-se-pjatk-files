#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/msg.h>


// struktura która bêdzie odbierana
typedef struct {
   long type;
   char body[100];
} msg;

int main(){
   char temp[20];
   msg wiad; // zmienna do której bêd± odbierane dane
   int id, result, msgtyp;
   strcpy(wiad.body, ""); // wyczyszczenie tre¶ci wiadomo¶ci
   id= msgget(1642,0); // pobranie identyfikatora kolejki 
   if(id < 0){ // je¶li nie uda³o siê otworzyæ
      printf("Msggetrcv: %d\n", id); // to wywal komunikat
      exit(1); // i wyjd¼ z programu
   }
   scanf("%s", temp); // odczyt z klawiatury
   msgtyp = atoi(temp); // konwersja na liczbê
   result = msgrcv(id, &wiad, sizeof(wiad.body), msgtyp, IPC_NOWAIT); // odczytaj wiadomo¶æ
   if (result>-1) {
      wiad.body[result] = 0; // dodanie zera na koñcu odczytanej wiadomo¶ci
      printf("%d: %s\n", result, wiad.body); // wy¶wietlenie ilo¶ci znaków
      msgctl(id, IPC_RMID, (struct msqid_ds *)0); // Usuniêcie wiadomo¶ci z kolejki
   } else {
      printf("nie da³o siê odczytaæ z kolejki wiadom. o pod. typie\n");
   }
   return 0;
}

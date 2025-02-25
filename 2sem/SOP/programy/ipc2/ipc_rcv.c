#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/msg.h>


// struktura kt�ra b�dzie odbierana
typedef struct {
   long type;
   char body[100];
} msg;

int main(){
   char temp[20];
   msg wiad; // zmienna do kt�rej b�d� odbierane dane
   int id, result, msgtyp;
   strcpy(wiad.body, ""); // wyczyszczenie tre�ci wiadomo�ci
   id= msgget(1642,0); // pobranie identyfikatora kolejki 
   if(id < 0){ // je�li nie uda�o si� otworzy�
      printf("Msggetrcv: %d\n", id); // to wywal komunikat
      exit(1); // i wyjd� z programu
   }
   scanf("%s", temp); // odczyt z klawiatury
   msgtyp = atoi(temp); // konwersja na liczb�
   result = msgrcv(id, &wiad, sizeof(wiad.body), msgtyp, IPC_NOWAIT); // odczytaj wiadomo��
   if (result>-1) {
      wiad.body[result] = 0; // dodanie zera na ko�cu odczytanej wiadomo�ci
      printf("%d: %s\n", result, wiad.body); // wy�wietlenie ilo�ci znak�w
      msgctl(id, IPC_RMID, (struct msqid_ds *)0); // Usuni�cie wiadomo�ci z kolejki
   } else {
      printf("nie da�o si� odczyta� z kolejki wiadom. o pod. typie\n");
   }
   return 0;
}

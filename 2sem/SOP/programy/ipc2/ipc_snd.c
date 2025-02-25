#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/msg.h>

/* 
 * Stworzenie struktury kt�ra zawiera typ wiadomo�ci i tre��
 */
typedef struct {
   long type;
   char body[100];
} msg;

int main(){
   int id;
   int result;
   msg wiad; // tworzenie zmiennej kt�ra b�dzie wysy�ana
   wiad.type = 100; // Ustawienie typu wiadomo�ci na 100 - ten 
 //   typ programista wymy�la sam - przydaje si� to kiedy mi�dzy programami
 //   jest wysy�anych wiele wiadomo�ci o r�nym przeznaczeniu
   strcpy(wiad.body, "Hello World test"); // Wstawienie napisu do tre�ci 
   // wiadomo�ci
   id = msgget(1642, IPC_CREAT | 0666); // stworzenie kolejki z
   // uprawnieniami odczytu i zapisu dla wszystkich
   if(id < 0){ // je�li kana� nie zosta� utworzony - id <0 - wy�wietl komunikat
      // i zako�cz dzia�anie programu
      printf("Msgget: %d\n", id);
      exit(1);
   }
   result = msgsnd(id, &wiad, strlen(wiad.body), 0); // wysy�anie wiadomo�ci
   wiad.type = 101;
   strcpy(wiad.body, "Inny test 101");
   result = msgsnd(id, &wiad, strlen(wiad.body), 0);
   wiad.type = 102;
   strcpy(wiad.body, "Inny test 102");
   result = msgsnd(id, &wiad, strlen(wiad.body), 0);
   wiad.type = 103;
   strcpy(wiad.body, "Inny test 103");
   result = msgsnd(id, &wiad, strlen(wiad.body), 0);
   printf("%d\n", result); // wy�wietlenie komunikatu o wyniku wys�ania
   return 0;
}

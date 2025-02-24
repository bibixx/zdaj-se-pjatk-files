#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/msg.h>

/* 
 * Stworzenie struktury która zawiera typ wiadomo¶ci i tre¶æ
 */
typedef struct {
   long type;
   char body[100];
} msg;

int main(){
   int id;
   int result;
   msg wiad; // tworzenie zmiennej która bêdzie wysy³ana
   wiad.type = 100; // Ustawienie typu wiadomo¶ci na 100 - ten 
 //   typ programista wymy¶la sam - przydaje siê to kiedy miêdzy programami
 //   jest wysy³anych wiele wiadomo¶ci o ró¿nym przeznaczeniu
   strcpy(wiad.body, "Hello World test"); // Wstawienie napisu do tre¶ci 
   // wiadomo¶ci
   id = msgget(1642, IPC_CREAT | 0666); // stworzenie kolejki z
   // uprawnieniami odczytu i zapisu dla wszystkich
   if(id < 0){ // je¶li kana³ nie zosta³ utworzony - id <0 - wy¶wietl komunikat
      // i zakoñcz dzia³anie programu
      printf("Msgget: %d\n", id);
      exit(1);
   }
   result = msgsnd(id, &wiad, strlen(wiad.body), 0); // wysy³anie wiadomo¶ci
   wiad.type = 101;
   strcpy(wiad.body, "Inny test 101");
   result = msgsnd(id, &wiad, strlen(wiad.body), 0);
   wiad.type = 102;
   strcpy(wiad.body, "Inny test 102");
   result = msgsnd(id, &wiad, strlen(wiad.body), 0);
   wiad.type = 103;
   strcpy(wiad.body, "Inny test 103");
   result = msgsnd(id, &wiad, strlen(wiad.body), 0);
   printf("%d\n", result); // wy¶wietlenie komunikatu o wyniku wys³ania
   return 0;
}

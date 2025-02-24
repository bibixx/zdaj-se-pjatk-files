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
   msg wiad;
   wiad.type = 100;
   strcpy(wiad.body, "Hello World test"); // strcpy( dest, src )
   id = msgget(1642, IPC_CREAT | 0666); // msgget - tworzy kolejke
   // nr kolejki, prawa dostêpu
   // zwracana wartoœæ: <0 - b³¹d; >=0 id kolejki
   if(id < 0){
      printf("Msgget: %d\n", id);
      exit(1);
   }
   result = msgsnd(id, &wiad, strlen(wiad.body), 0); // wysy³anie wiadomo¶ci
   // identyfikator kolejki, * komunikat, d³ugoœæ treœci komunikatu, flaga 
   // okreœlaj¹ca zachowanie w przypadku, jeœli d³ugoœæ jest wiêksza ni¿ ta podana jako
   // drugi parametr
   printf("%d\n", result); // wy¶wietlenie komunikatu o wyniku wys³ania
   return 0;
}

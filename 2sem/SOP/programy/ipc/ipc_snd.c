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
   msg wiad;
   wiad.type = 100;
   strcpy(wiad.body, "Hello World test"); // strcpy( dest, src )
   id = msgget(1642, IPC_CREAT | 0666); // msgget - tworzy kolejke
   // nr kolejki, prawa dost�pu
   // zwracana warto��: <0 - b��d; >=0 id kolejki
   if(id < 0){
      printf("Msgget: %d\n", id);
      exit(1);
   }
   result = msgsnd(id, &wiad, strlen(wiad.body), 0); // wysy�anie wiadomo�ci
   // identyfikator kolejki, * komunikat, d�ugo�� tre�ci komunikatu, flaga 
   // okre�laj�ca zachowanie w przypadku, je�li d�ugo�� jest wi�ksza ni� ta podana jako
   // drugi parametr
   printf("%d\n", result); // wy�wietlenie komunikatu o wyniku wys�ania
   return 0;
}

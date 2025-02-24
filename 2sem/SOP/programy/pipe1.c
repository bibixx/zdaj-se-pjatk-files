#include <stdio.h>

int main()
{
   int pid;
   char * buf;
   char * buf2;
   int filedes[2];
   int size;
   if(pipe(filedes)==-1) // 0 - odczyt, 1 - zapis
      exit(1);
   switch( pid = fork() )
   {
  case 0:
     printf("pid potomka: %d ppid: %d\n", getpid(), getppid());
     close(filedes[0]);
     buf = "a";
     write(filedes[1], buf, strlen(buf)); // deskryptor, char * - tekst do zapisu, d³ugoœæ
     close(filedes[1]);
     exit(0);
  case -1:
     printf("Blad funkcji fork\n");
     exit (1);
  default:
     printf("pid przodka: %d ppid: %d pid potomka: %d \n", 
           getpid(), getppid(), pid);
     close(filedes[1]);
     buf2 = (char *)malloc(100); // malloc zwraca typ void *, wiêc potrzebna jest konwersja
	 // typów na char *
     read(filedes[0], buf2, 100); // deskryptor, char * - gdzie ma zapisaæ, ile bajtów
	 printf("%s", buf2);
     close(filedes[0]);
     wait(NULL); // czekaj na zakoñczenie dzia³ania procesu potomnego
     exit(0);
   }
}


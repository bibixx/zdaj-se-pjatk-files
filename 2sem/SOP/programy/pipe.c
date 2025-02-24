#include <stdio.h>

int main()
{
   int pid;
   char * buf;
   char * buf2;
   int filedes[2];
   int filedes2[2];
   int size;
   if(pipe(filedes)==-1)
      exit(1);
   if(pipe(filedes2)==-1)
      exit(1);
   switch( pid = fork() )
   {
      case 0:
         printf("pid potomka: %d ppid: %d\n", getpid(), getppid());
         close(filedes[0]);
         close(filedes2[1]);
         buf = "a";
         write(filedes[1], buf, strlen(buf));
         close(filedes[1]);
         buf = (char *)malloc(2);
         read(filedes2[0], buf, 1);
         printf("%s\n", buf);
         exit(0);
      case -1:
         printf("Blad funkcji fork\n");
         exit (1);
      default:
         printf("pid przodka: %d ppid: %d pid potomka: %d \n", 
               getpid(), getppid(), pid);
         close(filedes[1]);
         close(filedes2[0]);
         buf2 = (char *)malloc(1);
         read(filedes[0], buf2, 1);
         close(filedes[0]);
         if(!strcmp("a", buf2))
            strcpy(buf2, "A");
         if(!strcmp("b", buf2))
            strcpy(buf2, "B");
         write(filedes2[1], buf2, strlen(buf2));
         close(filedes2[1]);
         wait(NULL);
         exit(0);
   }
}


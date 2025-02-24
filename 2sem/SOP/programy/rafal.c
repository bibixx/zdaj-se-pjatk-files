#include <stdio.h>

int main()
{
   int pid;
   char * buf;
   char * buf2;
   int filedes[2];
   int size;
   if(pipe(filedes)==-1)
      exit(1);
   switch( pid = fork() )
   {
  case 0:
	  switch(fork()){
	  case 0:
		  printf("potomny potomnego");
		  break;
	  default:
		  switch(fork()){
		  }
		  printf("potomny");
	  }
     printf("pid potomka: %d ppid: %d\n", getpid(), getppid());
     close(filedes[0]);
     buf = "a";
     write(filedes[1], buf, strlen(buf));
     close(filedes[1]);
     buf = (char *)malloc(2);
     exit(0);
  case -1:
     printf("Blad funkcji fork\n");
     exit (1);
  default:
     printf("pid przodka: %d ppid: %d pid potomka: %d \n", 
           getpid(), getppid(), pid);
     close(filedes[1]);
     buf2 = (char *)malloc(1);
     read(filedes[0], buf2, 1);
	 printf("%s", buf2);
     close(filedes[0]);
     close(filedes2[1]);
     wait(NULL);
     exit(0);
   }
}


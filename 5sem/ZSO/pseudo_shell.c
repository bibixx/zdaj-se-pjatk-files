#include <stdlib.h>
#include <stdio.h>
#include <string.h> //strtok strcpy strcat

// socket(), bind(), listen(), accept(), connect(), recv(), send()
#include <sys/socket.h>
// inet_ntoa(), inet_addr()
#include <netinet/in.h>
// htons(), ntohs()
#include <arpa/inet.h>
// gethostbyname()
#include <netdb.h>

/*
Tresc zadania z I-go zestawu zadan z ZSO:
" Klient autoryzuje się to serwera (adres podany jawnie) przez user/pass.
Następnie z klawiatury odczytuje polecenia, które wysyła do serwera.
Serwer je wykonuje, po czym odsyła do klienta wyniki. Komendy dostępne dla
klienta: ls - lista plików, cd <kat> - zmień katalog, pwd - wyświetl
bieżący katalog, logout - odłącz się od serwera. Komunikacja za pomocą
protokołu TCP. "

Rozwiazanie zrobione na bazie przykladu z socketami tcp przerabianego na zajeciach. 
Wywolania serwera i klienta pozostaly bez zmian. Dane do zalogowania klienta zostaly
ustalone na stale i sa nastepujace:
login: hhh  haslo: ttt
Uwaga:
Serwer obsluguje tylko 1 klienta jednoczesnie(bo nie bylo zadnego innego wymogu w poleceniu).
Inna drobna wada programu jest niepotrzebnie duzy rozmiar danych odsylach do klienta 
- rozm. ustalony zostal na stale na 1024.
poza tym wszystko ok.
*/
// numer portu serwera
#define SERV_SOCK_PORT	2222
#define SIZE 20
#define LOGIN "hhh"
#define PASS "ttt"

//logowanie -spr danych podanych przez klienta
int zaloguj(char* n,char* m) {
   if(strcmp(n,LOGIN)!=0) return 0;
   if(strcmp(m,PASS)!=0) return 0;
   return 1;
}


char* trimm(char* b)
{
  char* e=strrchr(b, '\0'); /* Find the final null */
  while(b<e && isspace(*b)) /* Scan forward */
    ++b;
  while (e>b && isspace(*(e-1))) /* scan back from end */
    --e; 
  *e='\0'; /* terminate new string */
  return b;
}


int wykonaj(char* tresc, int sock) {
	//system("ls");
	char buf[1024];
	char * token, *path, *command;
	
	tresc = trimm(tresc);
	//printf("wykonuje polecenie: \"%s\"\n",tresc);
	  token = strtok(tresc," ");
	  command = token;
	  
	  while (token != NULL)
	  {
		 path = token;
		 token = strtok (NULL, " \n");
	  }
	 
		if(strcmp(command,"cd")==0){

			chdir(path);
			char komunikat[100] = "Zmieniono katalog na \"";
			strcat(komunikat,path);
			strcat(komunikat,"\".");
			strcpy(buf,komunikat);
	
			if(send(sock, buf, sizeof(char)*1024, 0) != sizeof(char)*1024) {
		   	// wysłano za mało danych, coś jest nie w porządku
		   	perror("błąd send answer w server: wykonaj cd");
		   	return -1;
			}
		}
		else if(strcmp(command,"logout")==0) {
			
			char komunikat[10] = "exit";
			strcpy(buf,komunikat);
			
			if(send(sock, buf, sizeof(char)*1024, 0) != sizeof(char)*1024) {
		   	// wysłano za mało danych, coś jest nie w porządku
		   	perror("błąd send answer w server: wykonaj logout");
		   	return -1;
			}
		}
		else if(strcmp(command,"exit")==0) {
			//printf("s wychodzi z wykonuj.\n");
			return 0;
			
		}
		else{
		
			FILE* p = popen(tresc,"r");
			while(fgets(buf,1024,p)!=NULL){
				//printf("send: %s",buf);
				if(send(sock, buf, sizeof(char)*1024, 0) != sizeof(char)*1024) {
					// wysłano za mało danych, coś jest nie w porządku
					perror("błąd send answer w server: wykonaj ls/pwd");
					return -1;
				}
			}
		}
		
	buf[0] ='#'; //## koniec polecenia
	buf[1] ='#';
	buf[2] ='\0';
	
	if(send(sock, buf, sizeof(char)*1024, 0) != sizeof(char)*1024) {
		   // wysłano za mało danych, coś jest nie w porządku
		   perror("błąd send answer w server: wykonaj final");
		   return -1;
	}
   return 1;
}


int wykonuj_polecenia(int socketfd){
	 
	 int zalogowany = 1;
	 char polecenie[SIZE];

	 while(zalogowany){
	 	if(recv(socketfd, polecenie, sizeof(char)*SIZE, 0) != sizeof(char)*SIZE) {
		   // odebrano za mało danych, coś jest nie w porządku
		   perror("błąd recv question w server: wykonuj_polecenia");
		   return -1;
		}
		zalogowany = wykonaj(polecenie, socketfd);
	 }
	 //printf("s wychodzi z wykonuj_polecenia.\n");
return 0;
}


int wysylaj_polecenia(int sock){

	int zalogowany = 1;
	char polecenie[SIZE];
	char wynik[1024];

	while(zalogowany){
		printf("podaj polecenie: \n");
		wynik[0]='\0';

		label:
		fgets(polecenie, SIZE, stdin);
		if(strlen(polecenie)==1) goto label;

		//printf("wysylam do serwera: \"%s\"", polecenie);
		 if(send(sock, polecenie, sizeof(char)*SIZE, 0) != sizeof(char)*SIZE) {
				// wysłano za mało danych, coś jest nie w porządku
				perror("błąd send question w klient: wysylaj_polecenia");
				return -1;
			}
			
			printf("wynik od serwera: \n");
			
			while(strcmp(wynik,"##")!=0){
			
				if(recv(sock, wynik, sizeof(char)*1024, 0) != sizeof(char)*1024) {
					// odebrano za mało danych, coś jest nie w porządku
					perror("błąd recv answer w klient: wysylaj_polecenia");
					return -1;
				}
				if(strcmp(wynik,"exit")==0){
				strcpy(polecenie,"exit");
					if(send(sock, polecenie, sizeof(char)*SIZE, 0) != sizeof(char)*SIZE) {
						perror("błąd send exit confirmation w klient: wysylaj_polecenia");
						return -1;
					}
					printf("Zostales wylogowany.\n");
					break;
				}
					
				if(strcmp(wynik,"##")!=0)
					printf("%s",wynik);
			}
			if(strcmp(wynik,"exit")==0)
				return 0;
			printf("\n");
	}
	return 0;
}

// server loguje klienta i przechodzi do obslugi zapytan
int server_function(int socketfd) {

  char login[SIZE];
  char pass[SIZE];
  int result = 0;
   
   while(result==0){
   
		if(recv(socketfd, login, sizeof(char)*SIZE, 0) != sizeof(char)*SIZE) {
		   // odebrano za mało danych, coś jest nie w porządku
		   perror("błąd recv login w server_function");
		   return -1;
		}
		if(recv(socketfd, pass, sizeof(char)*SIZE, 0) != sizeof(char)*SIZE) {
		   // odebrano za mało danych, coś jest nie w porządku
		   perror("błąd recv pass w server_function");
		   return -1;
		}
		
		result = zaloguj(login,pass);
		printf("Zalogowano klienta: %s.\n", login);
		
		if(send(socketfd, &result, sizeof(result), 0) != sizeof(result)) {
		   // wysłano za mało danych, coś jest nie w porządku
		   perror("błąd send answer authorization w server_function");
		   return -1;
		}
   
   }//udalo sie logowanie
   
   wykonuj_polecenia(socketfd);
   printf("Wylogowano klienta: %s.\n",login);
   
   return 0;
}

// pętla serwera, parametrem jest deskryptor gniazda, na którym nasłuchujemy
void server_loop(int socketfd) {
   // pętla nieskończona
   for(;;) {
      // struktura na parametry klienta
      struct sockaddr_in client_addr;
      int client_addr_len=sizeof(client_addr);
      
      // odbierz połączenie
      int con_sockfd = accept(socketfd, (struct sockaddr*)&client_addr, &client_addr_len);
      if(con_sockfd != -1) {
	 // połączenie poprawne, obsłuż
	 printf("serwer: odebrano połączenie od: %s:%d\n", inet_ntoa(client_addr.sin_addr), ntohs(client_addr.sin_port));
	 server_function(con_sockfd);
	 // zamknij połączenie
	 close(con_sockfd);
      } else {
	 // błąd accept, wypisz błąd
	 perror("błąd accept w server_loop");
      }
   }
}

// funkcja uruchamiajaca i logujaca klienta
int client_function(int socketfd) {
  
   int result = 0;
   char login[SIZE];
   char pass[SIZE];
   
   while(result==0){
   
		printf("podaj login: \n");
		scanf("%s", login);
		printf("podaj haslo: \n");
		scanf("%s", pass);
		
		if(send(socketfd, login, sizeof(char)*SIZE, 0) != sizeof(char)*SIZE) {
		   // wysłano za mało, zgłoś błąd
		   perror("błąd send login w client_function");
		   return -1;
		}
		if(send(socketfd, pass, sizeof(char)*SIZE, 0) != sizeof(char)*SIZE) {
		   // wysłano za mało, zgłoś błąd
		   perror("błąd send pass w client_function");
		   return -1;
		}
		
		if(recv(socketfd, &result, sizeof(result), 0) != sizeof(result)) {
		   // jeśli nie wyszło - błąd
		   perror("błąd authorization answer w client_function");
		   return -1;
		}
		switch(result){
			case 0: printf("Podano niepoprawne dane. Sprobuj ponownie.\n");break;
			case 1: printf("Zostales zalogowany.\n");break;
		}
   }
   wysylaj_polecenia(socketfd);
   
   return 0;
}

// funkcja główna
int main(int argc, char** argv) {
   int socketfd;
   // struktura zawierająca adres i port serwera (adres ustawiony później)
   struct sockaddr_in addr;
   addr.sin_family=AF_INET;
   addr.sin_port=htons(SERV_SOCK_PORT);

   // sprawdzenie popraności
   if(argc < 2) {
      printf("sposób uruchomienia: %s SERVER | CLIENT <adres>\n", argv[0]);
      exit(1);
   }
   
   // w zależności od parametru
   if(!strncmp(argv[1], "SERVER", 6)) {

      // znalezienie i rozwiązanie nazwy hosta
      struct hostent *host;
      char hostname[100];
      // pobierz nazwę hosta
      gethostname(hostname,100);
      // znajdź jego adres IP
      host=gethostbyname(hostname);
      if (host == NULL) {
	 perror("błąd gethostbyname");
	 exit(1);
      }
      printf("startuję serwer pod adresem %s (IP=%s) na gnieździe %d\n", host->h_name, inet_ntoa(*((struct in_addr *)host->h_addr)), SERV_SOCK_PORT);

      // jako serwer - otwórz gniazdo
      socketfd=socket(PF_INET, SOCK_STREAM, 0);
      if(socketfd == -1) {
	 perror("błąd socket w main (serwer)");
	 return -1;
      }
      // bind
      addr.sin_addr.s_addr=INADDR_ANY;
      if(bind(socketfd, (struct sockaddr*)&addr, sizeof(addr)) == -1) {
	 // błąd
	 perror("błąd bind w main");
	 return -1;
      }
      // listen
      if(listen(socketfd, 5) == -1) {
	 // błąd
	 perror("błąd listen w main");
	 return -1;
      }
      // uruchom pętlę główną
      server_loop(socketfd);
      
   } else if(!strncmp(argv[1], "CLIENT", 6)) {
      printf("startuję klienta\n");

      // jako serwer - otwórz gniazdo
      socketfd=socket(PF_INET, SOCK_STREAM, 0);
      if(socketfd == -1) {
	 perror("błąd socket w main (klient)");
	 return -1;
      }
      // connect
      addr.sin_addr.s_addr=inet_addr(argv[2]);
      if(connect(socketfd, (struct sockaddr*)&addr, sizeof(addr)) != 0) {
	 // jeśli błąd
	 perror("błąd connect w main");
	 return -1;
      } 
      // jest OK, uruchom funkcję główną
    
      client_function(socketfd);
      close(socketfd);
      return 0;
   } else {
      printf("sposób uruchomienia: %s SERVER | CLIENT <adres>\n", argv[0]);
   }
   return 0;
}
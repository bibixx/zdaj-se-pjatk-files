.ORG 0 ; 
	LD A,0BCH; A - wejscie ,B wyjscie,tryb 1
	OUT (0BH),A; zapis do rejestru CR
	LD A,09H ;
	OUT (0BH),A;
	LD A,05H;
	OUT (0BH),A;	
START:
	LD A,6;
	LD C,A;  
WYSLIJ:
	IN A,(0AH); Odczyt z portu PC
	BIT 0,A; test bitu
	JP Z,WYSLIJ; Skok jezeli nie ma znaku 
	DEC C;
	LD A,C;
	OUT (09H),A;
	JP Z,START;
	JP WYSLIJ;
.END

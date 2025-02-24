	.ORG 0
START:	IN A,(0010H);	odczyt z wejsc bezp
	LD B,A;
	LD A, 0000H;
	XOR B;
	JP Z, ZW;
	JP NZ, ZM ;



ZM:	DEC B ; zmniejsza o 1
	LD A,B;
	OUT (0010H),A ;
	JP START ;

ZW:	INC B ; zwieksza o 1
	LD A,B;
	OUT (0010H),A ;
	JP START ;

	.END
